package net.josegarvin.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet que controla les peticions que vinguin de "/planetaDeSatelit/*".
 */
@WebServlet("/planetaDeSatelit/*")
public class PlanetaDeSatelit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanetaDeSatelit() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "Planets-persistence-unit")
	EntityManager em;

	@Resource
	UserTransaction tx;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		System.out.println(request.getRequestURI());
		
		String ruta = request.getRequestURI();
		
		if(ruta.length() == 25 || ruta.length() == 26){
			
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
			out.println("<h1>Planeta d'un satel.lit</h1>");
			out.println("<h2>Especifica el nom del satel.lit:</h2>");

			//out.println("<form id='search' name='search' method='post' action='/Planets/satelitsPerPlaneta' enctype='application/x-www-form-urlencoded'>");
			out.println("<span class='search'><table>");
			out.println("<tbody><tr>");

			out.println("<td class='label'><label for='nomPlaneta'>Nom:</label></td>");
			out.println("<td class='component'><input id='nomSatelit' type='text' name='nomSatelit'></td>");
			out.println("</td>");
			out.println("<td class='required'></td></tr>");
			out.println("</tbody>");
			out.println("</table>");

			out.println("<span class='buttons'>");
			//out.println("<a href='/Planets/satelitsPerPlaneta' class='btn btn-primary'>Obtenir satel.lits</a>");
			out.println("<button onclick='obtenirRuta()' class='btn btn-primary'>Obtenir planeta</button>");
			out.println("</table>");
			out.println("</span>");

			out.println("</span>");

			//out.println("</form>");

			out.println("</div>");
			out.println("</div>");
			out.println("<script type=\"text/javascript\">");
		      out.println("function obtenirRuta()\n"
		          + "            {\n var nomSatelit = document.getElementById('nomSatelit').value; \n"
		    	  + "var novaRuta = '/Planets/planetaDeSatelit/' + nomSatelit; "
		          + "                window.location = novaRuta;\n"
		          + "            }");
		      out.println("</script>");

			out.println("</body>");
			out.println("</html>");
			
		}else{
			
			
			String satelitNom = ruta.substring(26, ruta.length());
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Planeta de " + satelitNom +"</title>");
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
			out.println("<h1>Planeta del satel.lit " + satelitNom + "</h1>");
			

			
			
			try{
				
				//Obtenim l'informació del satel.lit
				TypedQuery<Satelit> query1 =
					      em.createQuery("SELECT s FROM Satelit s where nom = '" + satelitNom + "'", Satelit.class);
				Satelit satelit = query1.getSingleResult();
					  
//					  out.println("<h3>Informació del satel.lit:</h3>");
//				    	out.println("Nom: " + satelit.getNom());
//				    	out.println("Distancia: " + satelit.getDiametre());
				    	out.println("<h2 class='text-centered'> El planeta de " + satelitNom + " es " + satelit.getPlaneta().getNom().toUpperCase() + ".</h2>");
				    	out.println();
				    	out.println();
				    	
			} catch(Exception e){
				out.println("<h3>No s'ha trobat cap planeta asociat al satel.lit " + satelitNom + " </h3>");
				
			}
				  
			
				  

			out.println("</div>");
			out.println("</div>");
			out.println("<script type=\"text/javascript\">");
		      out.println("function obtenirRuta()\n"
		          + "            {\n var nomSatelit = document.getElementById('nomSatelit').value; \n"
		    	  + "var novaRuta = '/Planets/planetaDeSatelit/' + nomSatelit; "
		          + "                window.location = novaRuta;\n"
		          + "            }");
		      out.println("</script>");

			out.println("</body>");
			out.println("</html>");
			
			
			
			
		}
		
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
