package it.unisa.control;

import java.io.IOException; 
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.dao.ProdottoDao;
import it.unisa.model.ProdottoBean;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProdottoDao prodDao = new ProdottoDao();
		ProdottoBean bean = new ProdottoBean();
		String sort = request.getParameter("sort");
		String action = request.getParameter("action");
		String redirectedPage = request.getParameter("page");
		if (redirectedPage == null || redirectedPage.isEmpty()) {
		    redirectedPage = "admin/GestioneCatalogo.jsp";
		}
	
		try {
			if(action!=null) {
				if (action.equalsIgnoreCase("add")) {
				    String quantitaParam =request.getParameter("quantita");
				    String tagliaParam =request.getParameter("Taglia");

				    int quantita;

				    try {
				        quantita = Integer.parseInt(quantitaParam);

				    } catch (NumberFormatException e) {
				        request.setAttribute("erroreCatalogo", "La quantità deve essere un numero intero.");
				        request.getRequestDispatcher("/WEB-INF/view/admin/AddProdotto.jsp").forward(request, response);

				        return;
				    }

				    if (quantita < 0) {
				        request.setAttribute("erroreCatalogo", "La quantità non può essere negativa.");
				        request.getRequestDispatcher("/WEB-INF/view/admin/AddProdotto.jsp").forward(request, response);

				        return;
				    }

				    if (tagliaParam != null && !tagliaParam.isBlank() && !tagliaParam.matches("^[A-Za-z,\\s]+$")) {
				        request.setAttribute("erroreCatalogo", "La taglia può contenere solo lettere, spazi e virgole.");
				        request.getRequestDispatcher("/WEB-INF/view/admin/AddProdotto.jsp").forward(request, response);

				        return;
				    }

				    bean.setNome(request.getParameter("nome"));
				    bean.setDescrizione(request.getParameter("descrizione"));
				    bean.setIva(request.getParameter("iva"));
				    bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
				    bean.setQuantita(quantita);
				    bean.setCategoria(request.getParameter("Categoria"));
				    bean.setvendite(0);

				    if ("Abbigliamento".equalsIgnoreCase(request.getParameter("Categoria"))) {
				        bean.setTaglie(tagliaParam);

				    } else {
				        bean.setTaglie(null);
				    }
				    bean.setImmagine(request.getParameter("img"));
				    bean.setInVendita(true);
				    
				    prodDao.doSave(bean);
				}
				
				else if (action.equalsIgnoreCase("modifica")) {
					
				    String quantitaParam = request.getParameter("quantita");
				    String tagliaParam = request.getParameter("Taglia");
				    int quantita;

				    try {
				        quantita = Integer.parseInt(quantitaParam);
				        
				    } catch (NumberFormatException e) {
				        request.setAttribute("erroreCatalogo", "La quantità deve essere un numero intero.");
				        request.getRequestDispatcher("/WEB-INF/view/admin/ModificaProdotto.jsp?prodotto=" + request.getParameter("id")).forward(request, response);
				       
				        return;
				    }

				    if (quantita < 0) {
				        request.setAttribute("erroreCatalogo", "La quantità non può essere negativa.");
				        request.getRequestDispatcher("/WEB-INF/view/admin/ModificaProdotto.jsp?prodotto=" + request.getParameter("id")).forward(request, response);

				        return;
				    }

				    if (tagliaParam != null && !tagliaParam.isBlank() && !tagliaParam.matches("^[A-Za-z,\\s]+$")) {
				        request.setAttribute("erroreCatalogo", "La taglia può contenere solo lettere, spazi e virgole.");
				        request.getRequestDispatcher("/WEB-INF/view/admin/ModificaProdotto.jsp?prodotto=" + request.getParameter("id")).forward(request, response);

				        return;
				    }

				    bean.setIdProdotto(Integer.parseInt(request.getParameter("id")));
				    bean.setNome(request.getParameter("nome"));
				    bean.setDescrizione(request.getParameter("descrizione"));
				    bean.setIva(request.getParameter("iva"));
				    bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
				    bean.setQuantita(quantita);
				    bean.setCategoria(request.getParameter("Categoria"));
				    bean.setvendite(Integer.parseInt(request.getParameter("Vendite")));

				    if ("Abbigliamento".equalsIgnoreCase(request.getParameter("Categoria"))) {
				        bean.setTaglie(tagliaParam);
				    } else {
				        bean.setTaglie(null);
				    }

				    bean.setImmagine(request.getParameter("img"));
				    bean.setInVendita(true);

				    prodDao.doUpdate(bean);
				}
				
				else if(action.equalsIgnoreCase("Elimina"))
				{
				    int id = Integer.parseInt(request.getParameter("id"));
				    prodDao.doUpdateVendita(id, false);
				}
				request.getSession().removeAttribute("categorie");

			}
			
		} catch (SQLException | NumberFormatException e) {
		    throw new ServletException("Errore durante la gestione del catalogo", e);
		    }


		try {
		    request.getSession().removeAttribute("products");
		    request.getSession().setAttribute("products", prodDao.doRetrieveAll(sort)
		    );

		} catch (SQLException e) {
		    throw new ServletException("Errore nel caricamento dei prodotti", e);
		}

		request.getRequestDispatcher(
		    "/WEB-INF/view/" + redirectedPage
		).forward(request, response);
	 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
