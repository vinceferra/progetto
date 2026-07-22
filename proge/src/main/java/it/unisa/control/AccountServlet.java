package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.dao.IndirizzoSpedizioneDao;
import it.unisa.dao.MetodoPagamentoDao;
import it.unisa.dao.UserDao;
import it.unisa.model.IndirizzoSpedizioneBean;
import it.unisa.model.MetodoPagamentoBean;
import it.unisa.model.UserBean;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		String redirectedPage = request.getParameter("page");
		
		UserDao daoUser = new UserDao();
		UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
		
		if (user == null) {
		    response.sendRedirect(
		        request.getContextPath() + "/Login"
		    );
		    return;
		}
		
		IndirizzoSpedizioneBean sped = new IndirizzoSpedizioneBean();
		IndirizzoSpedizioneDao daoSped = new IndirizzoSpedizioneDao();
		MetodoPagamentoBean pag = new MetodoPagamentoBean();
		MetodoPagamentoDao daoPag = new MetodoPagamentoDao();
		String action = request.getParameter("action");	
		
		String tit = request.getParameter("tit");
		String numC = request.getParameter("numC");
		String scad = request.getParameter("scad");
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("tel");
		String citta = request.getParameter("citta");
		String ind = request.getParameter("ind");
		String cap = request.getParameter("cap");
		String prov = request.getParameter("prov");	
		
		String currentPassword = request.getParameter("currentPassword");

			String newPassword = request.getParameter("newPassword");

			String confirmPassword = request.getParameter("confirmPassword");
	
		try {
		    if (action != null) 
		    {
		        if (action.equalsIgnoreCase("addS")) 
		        {
						
						 sped.setNome(nome);
						 sped.setCognome(cognome);
						 sped.setIndirizzo(ind);
						 sped.setTelefono(telefono);
						 sped.setCap(cap);
						 sped.setProvincia(prov);
						 sped.setCitta(citta);
						 daoSped.doSave(sped);
						 daoUser.doUpdateSpedizione(user.getEmail(), ind, cap);
						 user.setIndirizzo(ind);
						 user.setCap(cap);
				}

		         else if (action.equalsIgnoreCase("addP")) 
		         {
		            pag.setTitolare(tit);
		            pag.setNumero(numC);
		            pag.setScadenza(scad);
		            daoPag.doSave(pag);
		            daoUser.doUpdatePagamento(user.getEmail(), numC);
		            user.setCartaDiCredito(numC);

		        } 
		         else if (action.equalsIgnoreCase("Cambia Password")) {

		        	    if (currentPassword == null || currentPassword.isBlank() || newPassword == null
		        	            || newPassword.isBlank() || confirmPassword == null || confirmPassword.isBlank()) {

		        	        request.setAttribute("errorMessage", "Compila tutti i campi.");

		        	        request.getRequestDispatcher("/WEB-INF/view/password.jsp").forward(request, response);

		        	        return;
		        	    }

		        	    boolean passwordAttualeCorretta =
		        	        daoUser.checkPassword(user.getEmail(), currentPassword);

		        	    if (!passwordAttualeCorretta) {
		        	        request.setAttribute("errorMessage", "La password attuale non è corretta.");

		        	        request.getRequestDispatcher("/WEB-INF/view/password.jsp").forward(request, response);
		        	        return;
		        	    }

		        	    if (!newPassword.equals(confirmPassword)) {
		        	        request.setAttribute("errorMessage", "La nuova password e la conferma non coincidono.");
		        	        request.getRequestDispatcher("/WEB-INF/view/password.jsp").forward(request, response);
		        	        return;
		        	    }

		        	    if (currentPassword.equals(newPassword)) {
		        	        request.setAttribute("errorMessage", "La nuova password deve essere diversa da quella attuale.");
		        	        request.getRequestDispatcher("/WEB-INF/view/password.jsp").forward(request, response);
		        	        return;
		        	    }

		        	    if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$")) {

		        	        request.setAttribute("errorMessage", "La password deve contenere almeno 8 caratteri, " + "una maiuscola, una minuscola, un numero " + "e un carattere speciale.");
		        	        request.getRequestDispatcher("/WEB-INF/view/password.jsp").forward(request, response);

		        	        return;
		        	    }
		        	    
		        	    if (!newPassword.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
		        	        request.setAttribute("errorMessage", "La password deve contenere almeno 8 caratteri, una maiuscola, una minuscola e un numero.");

		        	        request.getRequestDispatcher("/WEB-INF/view/password.jsp").forward(request, response);
		        	        return;
		        	    }

		        	    daoUser.doUpdatePassword(user.getEmail(), newPassword);
		        	    request.getSession().setAttribute("messaggio", "Password modificata correttamente.");
		        	    response.sendRedirect(request.getContextPath() + "/account?page=impostazioni.jsp");
		        	    return;
		        	}
		  }
		} catch (SQLException e) {
		    throw new ServletException("Errore durante la gestione dell'account", e);
		}
			
		if(user.getIndirizzo()!=null && user.getCap()!=null) {
			try {
				request.getSession().removeAttribute("spedizione");
				request.getSession().setAttribute("spedizione", daoSped.doRetrieveByKey(user.getIndirizzo(),user.getCap()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(user.getCartaDiCredito()!=null) {
			try {
				request.getSession().setAttribute("pagamento", daoPag.doRetrieveByKey(user.getCartaDiCredito()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/view/" + redirectedPage);
			dispatcher.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}
}
