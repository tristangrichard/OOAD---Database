package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import daointerfaces.DALException;
import dto.BrugerDTO;
import funktionalitet.IOperatorLogic;
import funktionalitet.OperatorLogic;

public class ControlCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOperatorLogic operatorLogic = null;
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
		HttpSession session = request.getSession();

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
		// Action: Log outs the current user.
		// Redirects to index.jsp.
		if ("updateOprFilled".equals(action)) {
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
			//	request.getRequestDispatcher("../index.jsp?action=oprList").forward(request, response);
			} catch (DALException e) {
			//	e.printStackTrace();
			//	request.setAttribute("error", e.getMessage());
			//	request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
			} catch (NumberFormatException e) {
			//	e.printStackTrace();
			//	request.setAttribute("error", "Attribute oprIDToUpdate could not be parsed as an integer.");
			//	request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
			}
		}
	}
}