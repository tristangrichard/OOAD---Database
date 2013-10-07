package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ControlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlLogin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Action: Log outs the current user.
		// Redirects to index.jsp.
		if ("/logout.jsp".equals(request.getPathInfo())) {
			session.invalidate();
			request.setAttribute("action", null);
			request.logout();
			response.sendRedirect("../index.jsp");
		}
		// Action: default action if no action is set.
		// Sends the user back to the index page after login.
		else { 
			response.sendRedirect("../index.jsp");
		}
	}
}