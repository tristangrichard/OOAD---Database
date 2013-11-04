package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import daointerfaces.DALException;
import dto.UsersDTO;
import funktionalitet.*;



public class ControlStat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserLogic userLogic = null;
	private UsersDTO user = null;
	private boolean graphBars = false;
	private boolean graphSevBars = false;
	private boolean graphSevLines = false;


	public ControlStat() {
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
		ServletContext application = request.getSession().getServletContext();
		HttpSession session = request.getSession();

		// Create path to user logic
		userLogic = (UserLogic) application.getAttribute("userLogic");
		if (userLogic == null) {
			try {
				userLogic = new UserLogic();
				application.setAttribute("userLogic", userLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}
		
		// Create user bean if not already existing.
		user = (UsersDTO) session.getAttribute("user");
		if (user == null) {
			try {
				String email = request.getUserPrincipal().getName();
				user = userLogic.getUser(email);
				session.setAttribute("user", user);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}	
			// Getting the action parameter.
			String action = null;
			action = request.getParameter("action");
			
			if ("**".equals(action)) {
			
			}
			else {
				request.setAttribute("graphBars", false);
				request.setAttribute("graphSevBars", false);
				request.setAttribute("graphSevLines", false);
				request.getRequestDispatcher("../WEB-INF/stat/index.jsp?").forward(request, response);}
		
	}
}