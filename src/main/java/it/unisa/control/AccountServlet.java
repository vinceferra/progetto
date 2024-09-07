package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.IndirizzoSpedizioneBean;
import it.unisa.model.IndirizzoSpedizioneDao;
import it.unisa.model.MetodoPagamentoBean;
import it.unisa.model.MetodoPagamentoDao;
import it.unisa.model.UserBean;
import it.unisa.model.UserDao;

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
		String citta = request.getParameter("città");
		String ind = request.getParameter("ind");
		String cap = request.getParameter("cap");
		String prov = request.getParameter("prov");	
		
		String pwd = request.getParameter("newPassword");
	
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
				}

		         else if (action.equalsIgnoreCase("addP")) 
		         {
		            pag.setTitolare(tit);
		            pag.setNumero(numC);
		            pag.setScadenza(scad);
		            daoPag.doSave(pag);
		            daoUser.doUpdatePagamento(user.getEmail(), numC);

		        } 
		        else if (action.equalsIgnoreCase("Cambia Password")) 
		        {
		        	        user.setPassword(pwd);
		        	        daoUser.doUpdatePassword(user.getEmail(), pwd);
		        	    
		        }
		  }
		} catch (Exception e) {
		    e.printStackTrace();  // Log the exception for debugging purposes
		    // Handle the exception as needed
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
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + redirectedPage);
		dispatcher.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}
}
