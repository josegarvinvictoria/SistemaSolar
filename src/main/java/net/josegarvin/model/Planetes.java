package net.josegarvin.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;

@Entity
public class Planetes implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String nom;

   @Column
   private double distancia;

   @Column
   private double velocitat;

   @Column
   private double diametre;



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
      if (!(obj instanceof Planetes))
      {
         return false;
      }
      Planetes other = (Planetes) obj;
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

   public double getDistancia()
   {
      return distancia;
   }

   public void setDistancia(double distancia)
   {
      this.distancia = distancia;
   }

   public double getVelocitat()
   {
      return velocitat;
   }

   public void setVelocitat(double velocitat)
   {
      this.velocitat = velocitat;
   }

   public double getDiametre()
   {
      return diametre;
   }

   public void setDiametre(double d)
   {
      this.diametre = d;
   }



   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nom != null && !nom.trim().isEmpty())
         result += "nom: " + nom;
      result += ", distancia: " + distancia;
      result += ", velocitat: " + velocitat;
      result += ", diametre: " + diametre;
      return result;
   }
}