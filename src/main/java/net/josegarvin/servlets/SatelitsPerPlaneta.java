package net.josegarvin.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import net.josegarvin.model.Planeta;
import net.josegarvin.model.Satelit;


/**
 * Servlet que controla les peticions que vinguin de "/satelitsPerPlaneta/*".
 */
@WebServlet("/satelitsPerPlaneta/*")
public class SatelitsPerPlaneta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SatelitsPerPlaneta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtenim l'entity manager a partir del contexte de persistencia.
	 */
	@PersistenceContext(unitName = "Planets-persistence-unit")
	EntityManager em;

	/**
	 * Obtenim l'usertransaction per poder fer transaccions sobre la BBDD.
	 */
	@Resource
	UserTransaction tx;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		System.out.println(request.getRequestURI());
		
		String ruta = request.getRequestURI();
		
		if(ruta.length() == 27 || ruta.length() == 28){
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Cercar satel.lits</title>");
			out.println("<link rel='icon' href='/Planets/faces/javax.faces.resource/favicon.ico'>");
			out.println("<link type='text/css' rel='stylesheet' href='/Planets/faces/javax.faces.resource/bootstrap.css'>");
			out.println("<link type='text/css' rel='stylesheet' href='/Planets/faces/javax.faces.resource/forge-style.css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='navbar navbar-fixed-top'>");
			out.println("<div class='navbar-inner'>");
			out.println("<div class='container'><a id='brandLink' name='brandLink' href='/Planets/faces/index.xhtml' class='brand'>Planets</a>");
			out.println("<div class='nav-collapse collapse'>");
			out.println("<ul class='nav'>");
			out.println("<li><a href='/Planets/satelitsPerPlaneta'>Satel.lits d'un planeta</a></li>");
			out.println("<li><a href='/Planets/planetaDeSatelit'>Planeta d'un satel.lit</a></li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");

			out.println("<div class='container forgecontainer'>");
			out.println("<div id='navigation'><a id='homeLink' name='homeLink' href='/Planets/faces/index.xhtml'>");
			out.println("<img src='/Planets/faces/javax.faces.resource/forge-logo.png' alt='Forge... get hammered' border='0'></a><ul>");
			out.println("<li><a href='/Planets/faces/planeta/search.xhtml'>Llista de planetes</a>");
			out.println("</li>");
			out.println("<li><a href='/Planets/faces/satelit/search.xhtml'>Llistat de satel.lits</a></li></ul>");
			
			out.println("</div>");
			out.println("<div id='content'>");
			out.println("<h1>Satel.lits d'un planeta</h1>");
			out.println("<h2>Especifica el nom del planeta:</h2>");

			//out.println("<form id='search' name='search' method='post' action='/Planets/satelitsPerPlaneta' enctype='application/x-www-form-urlencoded'>");
			out.println("<span class='search'><table>");
			out.println("<tbody><tr>");

			out.println("<td class='label'><label for='nomPlaneta'>Nom:</label></td>");
			out.println("<td class='component'><input id='nomPlaneta' type='text' name='nomPlaneta'></td>");
			out.println("</td>");
			out.println("<td class='required'></td></tr>");
			out.println("</tbody>");
			out.println("</table>");

			out.println("<span class='buttons'>");
			out.println("<button onclick='obtenirRuta()' class='btn btn-primary'>Obtenir satel.lits</button>");
			out.println("</table>");
			out.println("</span>");

			out.println("</span>");


			out.println("</div>");
			out.println("</div>");
			out.println("<script type=\"text/javascript\">");
		      out.println("function obtenirRuta()\n"
		          + "            {\n var nomPlaneta = document.getElementById('nomPlaneta').value; \n"
		    	  + "var novaRuta = '/Planets/satelitsPerPlaneta/' + nomPlaneta; "
		          + "                window.location = novaRuta;\n"
		          + "            }");
		      out.println("</script>");

			out.println("</body>");
			out.println("</html>");
			
		}else{
			
			
			String planetaNom = ruta.substring(28, ruta.length());
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Satel.lits de " + planetaNom +"</title>");
			out.println("<link rel='icon' href='/Planets/faces/javax.faces.resource/favicon.ico'>");
			out.println("<link type='text/css' rel='stylesheet' href='/Planets/faces/javax.faces.resource/bootstrap.css'>");
			out.println("<link type='text/css' rel='stylesheet' href='/Planets/faces/javax.faces.resource/forge-style.css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='navbar navbar-fixed-top'>");
			out.println("<div class='navbar-inner'>");
			out.println("<div class='container'><a id='brandLink' name='brandLink' href='/Planets/faces/index.xhtml' class='brand'>Planets</a>");
			out.println("<div class='nav-collapse collapse'>");
			out.println("<ul class='nav'>");
			out.println("<li><a href='/Planets/satelitsPerPlaneta'>Satel.lits d'un planeta</a></li>");
			out.println("<li><a href='/Planets/planetaDeSatelit'>Planeta d'un satel.lit</a></li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");

			out.println("<div class='container forgecontainer'>");
			out.println("<div id='navigation'><a id='homeLink' name='homeLink' href='/Planets/faces/index.xhtml'>");
			out.println("<img src='/Planets/faces/javax.faces.resource/forge-logo.png' alt='Forge... get hammered' border='0'></a><ul>");
			out.println("<li><a href='/Planets/faces/planeta/search.xhtml'>Llista de planetes</a>");
			out.println("</li>");
			out.println("<li><a href='/Planets/faces/satelit/search.xhtml'>Llistat de satel.lits</a></li></ul>");
			out.println("</div>");
			out.println("<div id='content'>");
			out.println("<h1>Satel.lits del planeta " + planetaNom + "</h1>");
			out.println("<h2>Resultats obtinguts:</h2>");

			
			
			try{
				
				//Obtenim l'informació del planeta
				TypedQuery<Planeta> query1 =
					      em.createQuery("SELECT p FROM Planeta p where nom = '" + planetaNom + "'", Planeta.class);
				Planeta planet = query1.getSingleResult();
		    	
				    	
				//A partit de l'ID del planeta cerquem els satel.lits
				TypedQuery<Satelit> query2 =
					em.createQuery("SELECT s FROM Satelit s where planeta = '" + planet.getId() + "'", Satelit.class);
				List<Satelit> satelits = query2.getResultList(); 
				
				out.println("<h3>Satel.lits trobats:</h3>");
		    	out.println("<table class='data-table'>");
		    	out.println("<thead>");
		    	out.println("<th>Nom</th>");    
		    	out.println("<th>Diametre</th>");  
		    	out.println("</thead>");
		    	out.println("<tbody>");
		    	
		    	for(int i = 0; i<satelits.size();i++){
		    		out.println("<tr>");
		    		out.println("<td>" + satelits.get(i).getNom() + "</td>");
		    		out.println("<td>" + satelits.get(i).getDiametre() + "</td>");

		    		out.println("</tr>");
		    		
		    		
		    	}
		    	
		    	out.println("</tbody>");
			} catch(Exception e){
				out.println("<h3>No s'han trobats satel.lits associats al planeta " + planetaNom + " </h3>");
				
			}
				  
			
				  

			out.println("</div>");
			out.println("</div>");
			out.println("<script type=\"text/javascript\">");
		      out.println("function obtenirRuta()\n"
		          + "            {\n var nomPlaneta = document.getElementById('nomPlaneta').value; \n"
		    	  + "var novaRuta = '/Planets/satelitsPerPlaneta/' + nomPlaneta; "
		          + "                window.location = novaRuta;\n"
		          + "            }");
		      out.println("</script>");

			out.println("</body>");
			out.println("</html>");
			
			
			
			
		}
		
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
