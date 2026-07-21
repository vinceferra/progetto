package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.dao.UserDao;
import it.unisa.model.UserBean;

/**
 * Servlet implementation class CheckUsernameServlet
 */
@WebServlet("/CheckUsername")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		UserDao dao = new UserDao();
		String us = request.getParameter("us");
		
		ArrayList<UserBean> users;
		try {
			users = dao.doRetrieveAll(null);
			for (UserBean user : users) {
			    if (us.equalsIgnoreCase(user.getUsername())) {
			        response.getWriter().write("0");
			        return;
			    }
		}
			response.getWriter().write("1");

			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
