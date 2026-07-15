package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoDao;

/**
 * Servlet implementation class RicercaProdottoServlet
 */
@WebServlet("/RicercaProdotto")
public class RicercaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");

        ArrayList<ProdottoBean> risultato = new ArrayList<>();
        ProdottoDao dao = new ProdottoDao();

        if (query == null || query.trim().isEmpty()) {
            response.getWriter().write("[]");
            return;
        }

        query = query.toLowerCase();

        try {
            ArrayList<ProdottoBean> prodotti = dao.doRetrieveAllDisponibili(null);

            for (ProdottoBean p : prodotti) {
                if (p.getNome() != null &&
                    p.getNome().toLowerCase().contains(query)) {

                    risultato.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(risultato);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}