package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import daoimpl.MySQLLangDAO;
import daointerfaces.DALException;
import daointerfaces.LangIDAO;
import dto.LangDTO;
import dto.UsersDTO;
import funktionalitet.*;

@WebServlet("/control")
public class ControlUserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserLogic userLogic = null;
	private UsersDTO user = null;
	private LangIDAO lang = null;

	public ControlUserAdmin() {
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

		// Create logic if not already created.
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
		
		lang = (LangIDAO) application.getAttribute("language");
		if (lang == null) {
			lang = new MySQLLangDAO();
			application.setAttribute("language", lang);
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

		// All actions are done below.
		// Action: View operator list.
		// Redirects to oprList.jsp.
//		if ("oprList".equals(action)) { 
//			try {
//				List<BrugerDTO> oprList = userLogic.getOperatoerList(); // Get the operator list.
//				request.setAttribute("oprList", oprList); // Assign the operator list to request attribute oprList.
//				request.getRequestDispatcher("../WEB-INF/opr/oprList.jsp?").forward(request, response); // Sends the request to the actual operator list jsp file.
//			} catch (DALException e) {
//				e.printStackTrace();
//				request.setAttribute("error", e.getMessage());
//				request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
//			}
//		} 
		// Action: Create Operator
		// Redirects to createOpr.jsp.
		if ("AddPub".equals(action)) { // Creation of a new operator.
			List<LangDTO> langList;
			try {
				langList = lang.getList();
				request.setAttribute("langList", langList);
				request.getRequestDispatcher("../WEB-INF/admin/createOpr.jsp?").forward(request, response); 
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Sends the request back to the create operator jsp file if an error is detected.
		} 
		// Action: Showing the update operator form.
		// Redirects to updateOpr.jsp.
//		else if ("updateOpr".equals(action)) { 
//			int oprIDToUpdate =  0;
//			// Setting the oprToUpdate to the current operator if no operator has been selected.
//			if (request.getParameter("oprIDToUpdate") == null) {
//				oprIDToUpdate = user.getOprId();
//			} else {
//				try {
//					oprIDToUpdate = Integer.parseInt(request.getParameter("oprIDToUpdate"));
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
//					request.setAttribute("error", "Attribute oprToUpdate could not be parsed as an integer.");
//					request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
//				}
//			}
//			if (oprIDToUpdate != 0) {
//				try {
//					// Creating a User bean that is to hold the information about the user to be updated.
//					BrugerDTO userToUpdate = userLogic.getOperatoer(oprIDToUpdate);
//					session.setAttribute("userToUpdate", userToUpdate);
//					request.getRequestDispatcher("../WEB-INF/opr/updateOpr.jsp?").forward(request, response); // Send the request to the update operator file.
//				} catch (DALException e) {
//					e.printStackTrace();
//					request.setAttribute("error", e.getMessage());
//					request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
//				}
//			} else {
//				request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
//			}
//		}
//		// Action: Delete operator. Deletion is made from the operator list.
//		// NOTE: This does not actually delete the operator, it deactivates it.
//		// Redirects to index.jsp.
//		else if ("deleteOpr".equals(action)) {
//			try {
//				int oprToDelete = Integer.parseInt(request.getParameter("oprIDToDelete"));
//				userLogic.deleteOpr(user.getOprId(), oprToDelete);
//				request.setAttribute("message", "Operator with ID: " + oprToDelete + " successfully deactivated.");
//			} catch (DALException e) {
//				e.printStackTrace();
//				request.setAttribute("error", e.getMessage());
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//				request.setAttribute("error", "Attribute oprIDToDelete could not be parsed as an integer.");
//			} finally {
//				request.getRequestDispatcher("index.jsp?action=oprList").forward(request, response);
//			}
//		} 
//		// Action: Carry out the changes made in the filled update operator form.
		// Redirects to index.jsp.
//		else if ("updateOprFilled".equals(action)) {
//			// Getting all the details from the filled form.
//			try {
//				int updOprID = Integer.parseInt(request.getParameter("oprIDToUpdate"));
//				String updOprName = request.getParameter("updOprName");
//				String updOprCpr = request.getParameter("updOprCpr");
//				String updOprPass1 = request.getParameter("updOprPass1");
//				String updOprPass2 = request.getParameter("updOprPass2");
//				String updOprPassOld = request.getParameter("updOprPassOld");
//				String updOprRole = request.getParameter("updOprRole");
//
//				if (!userLogic.isAdmin(user.getOprId())) { // Use the normal method if user is not an admin.
//					userLogic.updateOpr(updOprID, updOprName, updOprCpr, updOprPassOld, updOprPass1, updOprPass2, updOprRole);
//				} else { // Use the admin method that does not require the old password if the user is an admin.
//					userLogic.updateOprAdmin(updOprID, updOprName, updOprCpr, updOprPass1, updOprPass2, updOprRole);
//				}
//				request.setAttribute("message", "Operator with ID: " + updOprID + " successfully updated.");
//				request.getRequestDispatcher("index.jsp?action=oprList").forward(request, response);
//			} catch (DALException e) {
//				e.printStackTrace();
//				request.setAttribute("error", e.getMessage());
//				request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//				request.setAttribute("error", "Attribute oprIDToUpdate could not be parsed as an integer.");
//				request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
//			}
//		} 
		// Action: default action if no action is set.
		else { 
			request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
		}
	}
}