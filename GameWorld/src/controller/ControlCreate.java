package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import daointerfaces.DALException;
import dto.BrugerDTO;
import funktionalitet.IUserLogic;
import funktionalitet.UserLogic;

public class ControlCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserLogic userLogic = null;
	private BrugerDTO user = null;

	public ControlCreate() {
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
		//HttpSession session = request.getSession();

		userLogic = (UserLogic) application.getAttribute("operatorLogic");
		if (userLogic == null) {
			try {
				userLogic = new UserLogic();
				application.setAttribute("operatorLogic", userLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}

		// Create user bean if not already existing.
//		user = (BrugerDTO) session.getAttribute("user");
//		if (user == null) {
//			try {
//				int oprID = Integer.parseInt(request.getUserPrincipal().getName());
//				user = userLogic.getOperatoer(oprID);
//				session.setAttribute("user", user);
//			} catch (DALException e) {
//				e.printStackTrace();
//				request.setAttribute("error", e.getMessage());
//			}
//		}

		// Getting the action parameter.
		String action = null;
		action = request.getParameter("action");
		// Action: Log outs the current user.
		// Redirects to index.jsp.
		if ("userFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				String userName = request.getParameter("newUserName");
				String userBirth = request.getParameter("newUserBirth");
				String userRole = request.getParameter("newUserRole");
				String userEmail = request.getParameter("newUserEmail");
				String userSex = request.getParameter("newUserSex");
				int sex = Integer.parseInt(userSex);
				userLogic.createUser(userName, userBirth, userRole, userEmail, sex);
				System.out.println("Done");
				//} else { // Use the admin method that does not require the old password if the user is an admin.
			//		userLogic.updateOprAdmin(updOprID, updOprName, updOprCpr, updOprPass1, updOprPass2, updOprRole);
				//}
				//request.setAttribute("message", "Operator with ID: " + updOprID + " successfully updated.");
			request.getRequestDispatcher("../index.jsp").forward(request, response);
			} catch (DALException e) {
				System.out.println("Failed1");
			//	e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("../WEB-INF/create/createOpr.jsp?").forward(request, response);
			} catch (NumberFormatException e) {
				System.out.println("Failed2");
			//	e.printStackTrace();
			//	request.setAttribute("error", "Attribute oprIDToUpdate could not be parsed as an integer.");
			//	request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
			}
		}else {request.getRequestDispatcher("../WEB-INF/create/createOpr.jsp?").forward(request, response);}
	}
}