package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import it.unisa.model.*;

/**
 * Servlet implementation class SpedizioneServlet
 */
@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProdottoDao daoProd = new ProdottoDao();
		OrdineDao daoOrd = new OrdineDao();
		ComposizioneDao daoComp = new ComposizioneDao();
		IndirizzoSpedizioneDao daoSped = new IndirizzoSpedizioneDao();
		MetodoPagamentoDao daoPag = new MetodoPagamentoDao();
		
		UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
		OrdineBean ordine = new OrdineBean();
		ComposizioneBean comp = new ComposizioneBean();
		IndirizzoSpedizioneBean sped = new IndirizzoSpedizioneBean();
		MetodoPagamentoBean pag = new MetodoPagamentoBean();
	
		Carrello cart = (Carrello) request.getSession().getAttribute("cart");

		if(cart == null || cart.isEmpty()) {
		    response.sendRedirect("Carrello.jsp");
		    return;
		}

		try {

			for(ItemCarrello item : cart.getProdotti()) {
			    ProdottoBean prodotto = daoProd.doRetrieveByKey(item.getId());

			    if(prodotto == null || !prodotto.isInVendita() || prodotto.getQuantita() < item.getQuantitaCarrello()) {
			        request.getSession().setAttribute("errore","Uno o più prodotti non sono più disponibili.");
			        response.sendRedirect(request.getContextPath() + "/Carrello.jsp");
			        return;
			    }
			}
			
		    Double prezzoTot = cart.calcolaCosto();
		
		Date now = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateString = formatter.format(now);
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("tel");
		String citta = request.getParameter("citta");
		String ind = request.getParameter("ind");
		String cap = request.getParameter("cap");
		String prov = request.getParameter("prov");	
		
		String tit = request.getParameter("tit");
		String numC = request.getParameter("numC");
		String scad = request.getParameter("scad");
			
			 if(daoSped.doRetrieveByKey(ind,cap)==null){
				 sped.setNome(nome);
				 sped.setCognome(cognome);
				 sped.setIndirizzo(ind);
				 sped.setTelefono(telefono);
				 sped.setCap(cap);
				 sped.setProvincia(prov);
				 sped.setCitta(citta);
				 daoSped.doSave(sped);
			 }
			  
			 if(daoPag.doRetrieveByKey(numC)==null){
				 pag.setTitolare(tit);
				 pag.setNumero(numC);
				 pag.setScadenza(scad);
				 daoPag.doSave(pag);
			 }
			 
			
			ordine.setEmail(user.getEmail());
			ordine.setIndirizzo(ind);
			ordine.setCap(cap);
			ordine.setCartaCredito(numC);
			ordine.setData(mysqlDateString);
			ordine.setStato("confermato");
			ordine.setImportoTotale(prezzoTot);
			daoOrd.doSave(ordine);

			ArrayList<OrdineBean> ordini =daoOrd.doRetrieveByEmail(user.getEmail());

			if (ordini.isEmpty()) {
			    throw new SQLException("Ordine appena creato non trovato");
			}
			int newId = ordini.get(0).getIdOrdine();
			
			for(int i = 0; i < cart.size() ; i++) {
				int qnt = cart.get(i).getQuantitaCarrello();
		
				daoProd.doUpdateQnt(cart.get(i).getId(), qnt);
				
				comp.setIdOrdine(newId);
				comp.setIdProdotto(cart.get(i).getId());
				comp.setPrezzoTotale(cart.get(i).getTotalPrice());
				comp.setIva(cart.get(i).getProdotto().getIva());
				comp.setQuantita(cart.get(i).getQuantitaCarrello());
				daoComp.doSave(comp);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return;

		}
		
		request.getSession().removeAttribute("cart");

		request.getSession().setAttribute("messaggio", "Ordine avvenuto con successo!");

		response.sendRedirect(request.getContextPath() + "/Ordine?action=mieiOrdini");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

}
