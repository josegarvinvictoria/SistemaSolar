package net.josegarvin.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import net.josegarvin.model.Planetes;
import net.josegarvin.services.PlanetaService;


public class PlanetaServiceImpl implements PlanetaService{

	@PersistenceContext(unitName="Planets-persistence-unit") 
    EntityManager em;
	
	@Resource
    UserTransaction tx;

	  public PlanetaServiceImpl() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Planets-persistence-unit");
	    em = emf.createEntityManager();
	    generaPlanetesDeProva();
	  }
	  
	  
	  private void generaPlanetesDeProva() {
		  
		  //UserTransaction userTxn = sessionContext.getUserTransaction();
		  
		    // Crea dos objectes i es persisteixen
		    Planetes mart = new Planetes();
		    
		    mart.setNom("Mart");
		    mart.setDiametre(1000000.0);
		    mart.setVelocitat(58.8);
		    mart.setDistancia(58888.6);
		    
		    Planetes pluto = new Planetes();
		    pluto.setNom("Plutó");
		    pluto.setDiametre(100430000.0);
		    pluto.setVelocitat(5833.8);
		    pluto.setDistancia(58484348.6);

		    try {
				tx.begin();
				em.persist(mart);
			    em.persist(pluto);
			    em.flush();
			    tx.commit();
			} catch (NotSupportedException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicMixedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		    
		    

		    
		  }

	@Override
	public List<Planetes> findAll() {
		TypedQuery<Planetes> colorsq = em.createQuery("SELECT c FROM Planetes c", Planetes.class);
	    return colorsq.getResultList();
	}
}
