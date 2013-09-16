package controller;

import java.io.IOException;

import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import daointerfaces.DALException;
import dto.BrugerDTO;
import funktionalitet.*;

@WebServlet("/control")
public class ControlUserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOperatorLogic operatorLogic = null;
	private BrugerDTO user = null;

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
		operatorLogic = (OperatorLogic) application.getAttribute("operatorLogic");
		if (operatorLogic == null) {
			try {
				operatorLogic = new OperatorLogic();
				application.setAttribute("operatorLogic", operatorLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}

		// Create user bean if not already existing.
		user = (BrugerDTO) session.getAttribute("user");
		if (user == null) {
			try {
				int oprID = Integer.parseInt(request.getUserPrincipal().getName());
				user = operatorLogic.getOperatoer(oprID);
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
		if ("oprList".equals(action)) { 
			try {
				List<BrugerDTO> oprList = operatorLogic.getOperatoerList(); // Get the operator list.
				request.setAttribute("oprList", oprList); // Assign the operator list to request attribute oprList.
				request.getRequestDispatcher("../WEB-INF/opr/oprList.jsp?").forward(request, response); // Sends the request to the actual operator list jsp file.
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
			}
		} 
		// Action: Create Operator
		// Redirects to createOpr.jsp.
		else if ("createOpr".equals(action)) { // Creation of a new operator.
			if (request.getParameter("newOprName") != null) { 
				// This part is run when the form has not been filled.
				try {
					int newOprID = Integer.parseInt(request.getParameter("newOprID")); // Parse the operator id as an integer.
					String newPass = operatorLogic.createOpr(newOprID, request.getParameter("newOprName"), request.getParameter("newOprCpr"), request.getParameter("newOprRole")); // This creates the operator and returns the password.
					request.setAttribute("message", "Operator with ID: " + newOprID + " successfully created, password: <span id=\"password\">" + newPass + "</span>");
					request.getRequestDispatcher("index.jsp?action=oprList").forward(request, response); // Sends the request back to the index page of the user administration.
				} catch (DALException e) {
					e.printStackTrace();
					String errorMessage = e.getMessage();
					if (errorMessage.contains("PRIMARY")) { errorMessage = "Operator ID already in use."; } // Shows a special error message if the opr_id is already in use.
					request.setAttribute("error", errorMessage);
					request.getRequestDispatcher("../WEB-INF/opr/createOpr.jsp?").forward(request, response); // Sends the request back to the create operator jsp file if an error is detected.
				} catch (NumberFormatException e) {
					// If the operator ID cannot be parsed.
					e.printStackTrace();
					request.setAttribute("error", "Operator-ID needs to be a number");
					request.getRequestDispatcher("../WEB-INF/opr/createOpr.jsp?").forward(request, response);
				}
			} else {
				// This will show the form to create the operator, if it has not already been filled.
				request.getRequestDispatcher("../WEB-INF/opr/createOpr.jsp?").forward(request, response); // Sends the request to the create operator jsp page.
			}
		} 
		// Action: Showing the update operator form.
		// Redirects to updateOpr.jsp.
		else if ("updateOpr".equals(action)) { 
			int oprIDToUpdate =  0;
			// Setting the oprToUpdate to the current operator if no operator has been selected.
			if (request.getParameter("oprIDToUpdate") == null) {
				oprIDToUpdate = user.getOprId();
			} else {
				try {
					oprIDToUpdate = Integer.parseInt(request.getParameter("oprIDToUpdate"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("error", "Attribute oprToUpdate could not be parsed as an integer.");
					request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
				}
			}
			if (oprIDToUpdate != 0) {
				try {
					// Creating a User bean that is to hold the information about the user to be updated.
					BrugerDTO userToUpdate = operatorLogic.getOperatoer(oprIDToUpdate);
					session.setAttribute("userToUpdate", userToUpdate);
					request.getRequestDispatcher("../WEB-INF/opr/updateOpr.jsp?").forward(request, response); // Send the request to the update operator file.
				} catch (DALException e) {
					e.printStackTrace();
					request.setAttribute("error", e.getMessage());
					request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
			}
		}
		// Action: Delete operator. Deletion is made from the operator list.
		// NOTE: This does not actually delete the operator, it deactivates it.
		// Redirects to index.jsp.
		else if ("deleteOpr".equals(action)) {
			try {
				int oprToDelete = Integer.parseInt(request.getParameter("oprIDToDelete"));
				operatorLogic.deleteOpr(user.getOprId(), oprToDelete);
				request.setAttribute("message", "Operator with ID: " + oprToDelete + " successfully deactivated.");
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute oprIDToDelete could not be parsed as an integer.");
			} finally {
				request.getRequestDispatcher("index.jsp?action=oprList").forward(request, response);
			}
		} 
		// Action: Carry out the changes made in the filled update operator form.
		// Redirects to index.jsp.
		else if ("updateOprFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				int updOprID = Integer.parseInt(request.getParameter("oprIDToUpdate"));
				String updOprName = request.getParameter("updOprName");
				String updOprCpr = request.getParameter("updOprCpr");
				String updOprPass1 = request.getParameter("updOprPass1");
				String updOprPass2 = request.getParameter("updOprPass2");
				String updOprPassOld = request.getParameter("updOprPassOld");
				String updOprRole = request.getParameter("updOprRole");

				if (!operatorLogic.isAdmin(user.getOprId())) { // Use the normal method if user is not an admin.
					operatorLogic.updateOpr(updOprID, updOprName, updOprCpr, updOprPassOld, updOprPass1, updOprPass2, updOprRole);
				} else { // Use the admin method that does not require the old password if the user is an admin.
					operatorLogic.updateOprAdmin(updOprID, updOprName, updOprCpr, updOprPass1, updOprPass2, updOprRole);
				}
				request.setAttribute("message", "Operator with ID: " + updOprID + " successfully updated.");
				request.getRequestDispatcher("index.jsp?action=oprList").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute oprIDToUpdate could not be parsed as an integer.");
				request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
			}
		} 
		// Action: default action if no action is set.
		else { 
			request.getRequestDispatcher("../WEB-INF/opr/index.jsp?").forward(request, response);
		}
	}
}