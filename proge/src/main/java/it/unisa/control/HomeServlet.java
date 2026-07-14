package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoDao;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProdottoDao dao = new ProdottoDao();
		
		ArrayList<ArrayList<ProdottoBean>> categorie = new ArrayList<>();
		String redirectedPage = request.getParameter("page");

		if (redirectedPage == null || redirectedPage.isEmpty()) {
		    redirectedPage = "index.jsp";
		}
		
		try {
			ArrayList<ProdottoBean> Buy = dao.doRetrieveLastBuy();
			ArrayList<ProdottoBean> Pref = dao.doRetrieveRandomProducts(5);
			ArrayList<ProdottoBean> Best = dao.doRetrieveBestSellers();
			ArrayList<ProdottoBean> Giochi = dao.doRetrieveByCategoria("Giochi/Giocattoli");
			ArrayList<ProdottoBean> Elec = dao.doRetrieveByCategoria("Elettronica");
			ArrayList<ProdottoBean> Acc = dao.doRetrieveByCategoria("Accessori");
			ArrayList<ProdottoBean> Abb = dao.doRetrieveByCategoria("Abbigliamento");
			
			categorie.add(Buy);
			categorie.add(Pref);
			categorie.add(Best);
			categorie.add(Giochi);
			categorie.add(Elec);
			categorie.add(Acc);
			categorie.add(Abb);
		
			request.getSession().removeAttribute("categorie");
			request.getSession().setAttribute("categorie", categorie);
			
			
		}catch(SQLException e) {
		    throw new ServletException("Errore caricamento prodotti", e);
		}

		RequestDispatcher dispatcher = getServletContext()
		        .getRequestDispatcher("/" + redirectedPage);

		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
