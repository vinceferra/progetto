package it.unisa.control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.dao.UserDao;
import it.unisa.model.UserBean;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");

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
        
        nome = nome.trim();
        cognome = cognome.trim();
        email = email.trim();
        username = username.trim();

        if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            request.setAttribute("erroreRegistrazione", "Il nome può contenere solo lettere."
            );

            request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp").forward(request, response);

            return;
        }

        if (!cognome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            request.setAttribute("erroreRegistrazione", "Il cognome può contenere solo lettere.");

            request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp").forward(request, response);

            return;
        }

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            request.setAttribute("erroreRegistrazione", "Email non valida.");

            request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp").forward(request, response);

            return;
        }

        if (!username.matches("^[A-Za-z0-9]+$")) {
            request.setAttribute("erroreRegistrazione", "Lo username può contenere solo lettere e numeri.");
            request.getRequestDispatcher("/WEB-INF/view/Registrazione.jsp").forward(request, response);

            return;
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" + "(?=.*[^A-Za-z0-9]).{8,}$")) {

            request.setAttribute("erroreRegistrazione", "La password deve contenere almeno 8 caratteri, " + "una maiuscola, una minuscola, un numero " + "e un carattere speciale.");
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

            user.setPassword(password);
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