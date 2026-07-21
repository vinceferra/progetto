package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import it.unisa.model.UserBean;
import it.unisa.model.UserDao;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/Login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("un");
        String password = request.getParameter("pw");
        String checkout = request.getParameter("checkout");

        // Controllo campi vuoti
        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {

            request.setAttribute(
                "erroreLogin",
                "Inserisci username e password."
            );

            request.getRequestDispatcher("/WEB-INF/view/Login.jsp")
                   .forward(request, response);
            return;
        }

        UserDao usDao = new UserDao();

        try {
            String hashedPassword =
                PasswordUtil.hashPassword(password);

            UserBean user =
                usDao.doRetrieve(username, hashedPassword);

            // Controllo anche user != null
            if (user != null && user.isValid()) {

                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                session.removeAttribute("categorie");

                if (checkout != null) {
                    response.sendRedirect(
                        request.getContextPath()
                        + "/account?page=Checkout.jsp"
                    );
                } else {
                    response.sendRedirect(
                        request.getContextPath()
                        + "/home?page=Home.jsp"
                    );
                }

                return;
            }

            request.setAttribute(
                "erroreLogin",
                "Username o password errati."
            );

            request.getRequestDispatcher("/WEB-INF/view/Login.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                "Errore durante il login",
                e
            );
        }
    }
}