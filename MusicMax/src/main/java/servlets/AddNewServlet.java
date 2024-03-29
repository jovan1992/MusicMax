package servlets;

//import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.JPAUtil;
import javax.persistence.EntityManager;

import model.Tim6kompozicija;

/**
 * Servlet implementation class AddNewServlet
 */
public class AddNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String fileLocation = "D:'\'git'\'MusicMax'\'MusicMax'\'src'\'main'\'resources'\'data";
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Tim6kompozicija kompozicija = new Tim6kompozicija();
			kompozicija.setNaziv(request.getParameter("title"));
			//TODO implementirati mogucnost dodavanja vise autora
			kompozicija.setIzvodjac(request.getParameter("performer"));
			kompozicija.setAlbum(request.getParameter("album"));
			kompozicija.setGodina(Integer.parseInt(request.getParameter("year")));
			kompozicija.setPravac(request.getParameter("genre"));
			em.persist(kompozicija);
			//TODO implementirati upload fajla do kraja
			//File file = new File(request.getParameter("file"));
			
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
