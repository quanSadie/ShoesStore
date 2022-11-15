package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class RmvUpdUser
 */
@WebServlet("/RmvUpdUser")
public class RmvUpdUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * update thong tin hoac xoa nguoi dung
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");
		String userid = (String) request.getParameter("uid");
		UserDAO dao = new UserDAO();
		if (action.equals("remove")) {
			dao.rmvUser(userid);
			response.sendRedirect("admin-page.jsp");
		}
		if (action.equals("Update")) {
			dao.updUser(request.getParameter("updID"), request.getParameter("updName"),
					request.getParameter("updEmail"), request.getParameter("updPass"), request.getParameter("updAd"));
			response.sendRedirect("admin-page.jsp");
		}

	}

}
