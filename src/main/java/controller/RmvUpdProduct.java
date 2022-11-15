package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;

/**
 * Servlet implementation class RmvUpdProduct
 */
@WebServlet("/RmvUpdProduct")
public class RmvUpdProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * update thong tin hoac xoa san pham
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action2 = (String) request.getParameter("action2");
		String shoesid = (String) request.getParameter("pid");
		ProductDAO dao = new ProductDAO();
		if (action2.equals("remove")) {
			dao.rmvShoes(shoesid);
			response.sendRedirect("admin-page.jsp");
		}
		if (action2.equals("Update")) {
			dao.updShoes(request.getParameter("prid"), request.getParameter("prn"), request.getParameter("prp"));
			response.sendRedirect("admin-page.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
