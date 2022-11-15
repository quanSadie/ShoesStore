package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.jsp" })

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String n = null;
		String adm = null;

		if (session != null) {
			n = (String) session.getAttribute("name");
			adm = (String) session.getAttribute("isadd");
		}

		boolean isLoggedIn = (n != null);

		// check neu nguoi dung vao trang login
		if (req.getRequestURI().equals(req.getContextPath() + "/login.jsp")) {
			if (isLoggedIn) {
				// neu da~ login -> chuyen den trang home
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(req.getContextPath() + "/home.jsp");
			} else {
				// neu chua login thi tiep tuc vao trang login
				chain.doFilter(request, response);
			}

			// check nguoi dung vao trang danh cho admin
		} else if (req.getRequestURI().equals(req.getContextPath() + "/admin-page.jsp")) {
			if (isLoggedIn && adm.equals("1")) {
				// neu da login va la admin thi tiep tuc vao trang admin
				chain.doFilter(request, response);
			} else {
				// cac truong hop con lai thi chuyen den trang home
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(req.getContextPath() + "/home.jsp");
			}
		} else {
			// cac truong hop con lai cho phep tiep tuc truy cap
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
