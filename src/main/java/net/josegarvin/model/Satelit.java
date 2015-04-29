package net.josegarvin.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import net.josegarvin.model.Planeta;

@Entity
@Table(name = "Satelits")
public class Satelit implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(name = "nom")
   private String nom;

   @Column(name = "diametre")
   private Double diametre;

   //   @ManyToOne
   //   @JoinColumn(name="planeta")
   //@Column(name = "planetaID", nullable = false)
   @ManyToOne
   @JoinColumn(name = "planeta")
   Planeta planeta;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Satelit))
      {
         return false;
      }
      Satelit other = (Satelit) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getNom()
   {
      return nom;
   }

   public void setNom(String nom)
   {
      this.nom = nom;
   }

   public Double getDiametre()
   {
      return diametre;
   }

   public void setDiametre(Double diametre)
   {
      this.diametre = diametre;
   }

   public Planeta getPlaneta()
   {
      return planeta;
   }

   public void setPlaneta(Planeta planeta)
   {
      this.planeta = planeta;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nom != null && !nom.trim().isEmpty())
         result += "nom: " + nom;
      if (diametre != null)
         result += ", diametre: " + diametre;
      return result;
   }
}