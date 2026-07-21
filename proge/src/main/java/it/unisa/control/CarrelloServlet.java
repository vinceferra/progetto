package it.unisa.control;

import it.unisa.model.ProdottoBean;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import it.unisa.model.Carrello;
import it.unisa.model.ItemCarrello;
import it.unisa.model.ProdottoDao;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ProdottoDao prodDao = new ProdottoDao();
		Carrello cart = (Carrello)request.getSession().getAttribute("cart");
		
		if(cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		String quantita = request.getParameter("qnt");
		String redirectedPage = request.getParameter("page");

		if(redirectedPage == null || redirectedPage.isEmpty()) {
		    redirectedPage = "Home.jsp";
		}
		
		try {

		    // aggiorna i prodotti del carrello con i dati presenti nel database
		    if(cart != null) {
		        for(ItemCarrello item : cart.getProdotti()) {

		            ProdottoBean prodottoAggiornato = prodDao.doRetrieveByKey(item.getId());

		            if(prodottoAggiornato != null) {
		                item.setProdotto(prodottoAggiornato);
		            }
		        }
		    }


		    if (action != null && action.equalsIgnoreCase("addC")) {
		        int id = Integer.parseInt(request.getParameter("id"));
		        ProdottoBean prodotto = prodDao.doRetrieveByKey(id);

		        if (prodotto != null && prodotto.isInVendita() && prodotto.getQuantita() > 0) {

		            cart.addProdotto(prodotto);

		        } else {

		            request.getSession().setAttribute("errore",
		                "Prodotto non disponibile");
		        }
		       }

		    else if(action != null && action.equalsIgnoreCase("deleteC")) {
		        int id = Integer.parseInt(request.getParameter("id"));
		        ProdottoBean prodotto = prodDao.doRetrieveByKey(id);

		        if(prodotto != null) {
		            cart.deleteProdotto(prodotto);
		        }
		    }


		     if(quantita != null) {
		        int id = Integer.parseInt(request.getParameter("Id"));
		        ItemCarrello item = cart.getItem(id);

		        if(item != null) {
		            item.setQuantitaCarrello(Integer.parseInt(quantita));
		        }
		     }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/" + redirectedPage);
			dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response);
	}
	

}