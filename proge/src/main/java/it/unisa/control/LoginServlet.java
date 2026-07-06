package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import it.unisa.model.*;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDao usDao = new UserDao();

		try {
			String username = request.getParameter("un");
			String password = request.getParameter("pw");

			String hashedPassword = PasswordUtil.hashPassword(password);

			UserBean user = usDao.doRetrieve(username, hashedPassword);

			String checkout = request.getParameter("checkout");

			if (user.isValid()) {

				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);

				if (checkout != null)
					response.sendRedirect(request.getContextPath() + "/account?page=Checkout.jsp");
				else
					response.sendRedirect(request.getContextPath() + "/Home.jsp");

			} else {
				response.sendRedirect(request.getContextPath() + "/Login.jsp?action=error");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
