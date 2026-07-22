package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.dao.ComposizioneDao;
import it.unisa.dao.OrdineDao;
import it.unisa.dao.ProdottoDao;
import it.unisa.model.UserBean;

@WebServlet("/Ordine")
public class OrdineServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        OrdineDao ordDao = new OrdineDao();
        ComposizioneDao compDao = new ComposizioneDao();

        UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        String action = request.getParameter("action");

        try {
            if (action == null) {
                response.sendRedirect(request.getContextPath() + "/home?page=Home.jsp");
                return;
            }

            if (action.equalsIgnoreCase("mieiOrdini")) {
                request.getSession().removeAttribute("ordini");
                request.getSession().setAttribute("ordini", ordDao.doRetrieveByEmail(user.getEmail()));

                RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/view/MieiOrdini.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (action.equalsIgnoreCase("dettagliOrdine")) {
                int id = Integer.parseInt(request.getParameter("id"));

                request.getSession().setAttribute("composizione", compDao.doRetrieveByOrdine(id));
                request.getSession().setAttribute("ordineSelezionato", ordDao.doRetrieveByKey(id));

                ProdottoDao prodDao = new ProdottoDao();
                request.getSession().setAttribute("products", prodDao.doRetrieveAllProducts());

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ComposizioneOrdine.jsp");
                dispatcher.forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identificativo ordine non valido");

        } catch (SQLException e) {
            throw new ServletException("Errore durante il caricamento degli ordini", e);
        }
    }
}
