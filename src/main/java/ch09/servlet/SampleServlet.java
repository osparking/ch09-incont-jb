package ch09.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SampleServlet extends HttpServlet{
	public boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return false;
		}
		String authenticationAttribute = 
				(String) session.getAttribute("authenticated");
		return Boolean.valueOf(authenticationAttribute).booleanValue();
	}
}
