package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import it.unisa.dao.OrdineDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrdiniAdminServlet
 */
@WebServlet("/ordiniAdmin")
public class OrdiniAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrdineDao ord = new OrdineDao();
		String action = request.getParameter("action");
		
		try {
		    if (action != null) {

		        if (action.equalsIgnoreCase("data")) {
		            String data1 = request.getParameter("dal");
		            String data2 = request.getParameter("al");

		            request.getSession().setAttribute("adminOrdini", ord.doRetrieveByDate(data1, data2));

		        } else if (action.equalsIgnoreCase("nominativo")) {
		            String nome = request.getParameter("nome");
		            String cognome = request.getParameter("cognome");

		            request.getSession().setAttribute("adminOrdini", ord.doRetrieveByNominativo(nome, cognome));
		        }
		    }

		} catch (SQLException e) {
		    throw new ServletException("Errore durante il caricamento degli ordini", e);
		}

		request.getRequestDispatcher("/WEB-INF/view/admin/ViewOrdini.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() +"/admin/ViewOrdini.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
