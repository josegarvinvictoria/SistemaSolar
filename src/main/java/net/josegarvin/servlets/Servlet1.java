package net.josegarvin.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import net.josegarvin.model.Planetes;


/**
 * Servlet implementation class Servlet1
 */

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }


    @PersistenceContext(unitName="Planets-persistence-unit") 
    EntityManager em; 

    @Resource
    UserTransaction tx;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
//		mart.setDiametre(2000.0);
//		mart.setDistancia(2000.0);
//		mart.setVelocitat(2000.0);
		
		try {
			tx.begin();
			Planetes planeta = new Planetes();
		    planeta.setNom("Pluto");
		    planeta.setDiametre(100);    
		    
			
			em.persist(planeta);
			
			em.flush();
			tx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException
				| SystemException | javax.transaction.RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.println("Mart afegit!");
	}
	
	  private Planetes crearPlaneta(String nom) {
		    Planetes planeta = new Planetes();
		    planeta.setNom(nom);
		    planeta.setDiametre(100);    
		    return planeta;
		  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
