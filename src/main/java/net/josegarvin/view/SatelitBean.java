package net.josegarvin.view;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.josegarvin.model.Satelit;
import net.josegarvin.model.Planeta;

/**
 * Backing bean for Satelit entities.
 * <p/>
 * This class provides CRUD functionality for all Satelit entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class SatelitBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Satelit entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private Satelit satelit;

   public Satelit getSatelit()
   {
      return this.satelit;
   }

   public void setSatelit(Satelit satelit)
   {
      this.satelit = satelit;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(unitName = "Planets-persistence-unit", type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      this.conversation.setTimeout(1800000L);
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
         this.conversation.setTimeout(1800000L);
      }

      if (this.id == null)
      {
         this.satelit = this.example;
      }
      else
      {
         this.satelit = findById(getId());
      }
   }

   public Satelit findById(Long id)
   {

      return this.entityManager.find(Satelit.class, id);
   }

   /*
    * Support updating and deleting Satelit entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.satelit);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.satelit);
            return "view?faces-redirect=true&id=" + this.satelit.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         Satelit deletableEntity = findById(getId());
         Planeta planeta = deletableEntity.getPlaneta();
         planeta.getSatelits().remove(deletableEntity);
         deletableEntity.setPlaneta(null);
         this.entityManager.merge(planeta);
         this.entityManager.remove(deletableEntity);
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching Satelit entities with pagination
    */

   private int page;
   private long count;
   private List<Satelit> pageItems;

   private Satelit example = new Satelit();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public Satelit getExample()
   {
      return this.example;
   }

   public void setExample(Satelit example)
   {
      this.example = example;
   }

   public String search()
   {
      this.page = 0;
      return null;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<Satelit> root = countCriteria.from(Satelit.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Satelit> criteria = builder.createQuery(Satelit.class);
      root = criteria.from(Satelit.class);
      TypedQuery<Satelit> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Satelit> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String nom = this.example.getNom();
      if (nom != null && !"".equals(nom))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("nom")), '%' + nom.toLowerCase() + '%'));
      }
      Planeta planeta = this.example.getPlaneta();
      if (planeta != null)
      {
         predicatesList.add(builder.equal(root.get("planeta"), planeta));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Satelit> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Satelit entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Satelit> getAll()
   {

      CriteriaQuery<Satelit> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Satelit.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Satelit.class))).getResultList();
   }
   
   public List<Satelit> getAllSatelitsByPlaneta()
   {
	   List<Satelit> satelits = new ArrayList<Satelit>();

      CriteriaQuery<Satelit> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Satelit.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Satelit.class))).getResultList();
   }

   public Long findIdByName(String nomPlaneta){
	   Query query = entityManager.createQuery("select id from Planeta where nom = '" + nomPlaneta +"'");
	   Long idPlaneta = (Long) query.getSingleResult();
	   System.out.println(idPlaneta);
	   

	   
	   return idPlaneta;
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final SatelitBean ejbProxy = this.sessionContext.getBusinessObject(SatelitBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Satelit) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Satelit add = new Satelit();

   public Satelit getAdd()
   {
      return this.add;
   }

   public Satelit getAdded()
   {
      Satelit added = this.add;
      this.add = new Satelit();
      return added;
   }
}
