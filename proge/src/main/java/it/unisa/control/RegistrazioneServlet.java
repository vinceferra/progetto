package it.unisa.control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import it.unisa.model.UserBean;
import it.unisa.model.UserDao;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String dataNascita = request.getParameter("nascita");
        String username = request.getParameter("us");
        String password = request.getParameter("pw");

        if (nome == null || nome.isBlank() || cognome == null || cognome.isBlank() || email == null || email.isBlank() || dataNascita == null 
        		|| dataNascita.isBlank() || username == null || username.isBlank() || password == null || password.isBlank()) {

            request.setAttribute("erroreRegistrazione", "Compila tutti i campi.");
            request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp").forward(request, response);
            return;
        }

        UserDao dao = new UserDao();

        try {
            UserBean user = new UserBean();

            user.setNome(nome);
            user.setCognome(cognome);
            user.setEmail(email);

            // input type="date" restituisce già yyyy-MM-dd
            user.setDataDiNascita(Date.valueOf(dataNascita));
            user.setUsername(username);

            String hashedPassword = PasswordUtil.hashPassword(password);

            user.setPassword(hashedPassword);
            user.setAmministratore(false);
            user.setCap(null);
            user.setIndirizzo(null);
            user.setCartaDiCredito(null);

            dao.doSave(user);

            response.sendRedirect(request.getContextPath() + "/home?page=Home.jsp");

        } catch (IllegalArgumentException e) {
            request.setAttribute("erroreRegistrazione", "Data di nascita non valida.");
            request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                "Errore durante la registrazione",
                e
            );
        }
    }
}