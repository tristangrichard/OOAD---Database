package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import dto.RaavareDTO;
import daointerfaces.DALException;
import funktionalitet.*;

public class ControlRaavareAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRaavareLogic raavareLogic = null;

	public ControlRaavareAdmin() {
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

		// Create logic if not already created.
		raavareLogic = (RaavareLogic) application.getAttribute("raavareLogic");
		if (raavareLogic == null) {
			try {
				raavareLogic = new RaavareLogic();
				application.setAttribute("raavareLogic", raavareLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}

		// Getting the action parameter.
		String action = null;
		action = request.getParameter("action");

		// All actions are done below.
		// Action: View raavare list.
		// Redirects to raavareList.jsp.
		if ("raavareList".equals(action)) { 
			try {
				List<RaavareDTO> raavareList = new ArrayList<RaavareDTO>(raavareLogic.getRaavareList());
				request.setAttribute("raavareList", raavareList);
				request.getRequestDispatcher("../WEB-INF/raavare/raavareList.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/raavare/index.jsp?").forward(request, response);
			}
		} 
		// Action: Create Raavare
		// Redirects to createRaavare.jsp.
		else if ("createRaavare".equals(action)) { // Creation of a new raavare.
			if (request.getParameter("newRaavareName") != null) { // This is true if the form has been filled.
				try {
					int newRaavareID = Integer.parseInt(request.getParameter("newRaavareID"));
					raavareLogic.createRaavare(newRaavareID, request.getParameter("newRaavareName"), request.getParameter("newRaavareLeverandoer"));
					request.setAttribute("message", "Raavare with ID: " + newRaavareID + " successfully created.");
					request.getRequestDispatcher("index.jsp?action=raavareList").forward(request, response);
				} catch (DALException e) {
					e.printStackTrace();
					String errorMessage = e.getMessage();
					if (errorMessage.contains("PRIMARY")) { errorMessage = "A Råvare with ID:"+ request.getParameter("newRaavareID") +" already exists."; }
					request.setAttribute("error", errorMessage);
					request.getRequestDispatcher("../WEB-INF/raavare/createRaavare.jsp?").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("error", "Attribute newRaavareID could not be parsed as an integer.");
					request.getRequestDispatcher("../WEB-INF/raavare/createRaavare.jsp?").forward(request, response);
				}
			} else { // This will show the form.
				request.getRequestDispatcher("../WEB-INF/raavare/createRaavare.jsp?").forward(request, response);
			}
		} 
		// Action: Showing the update raavare form.
		// Redirects to updateRaavare.jsp.
		else if ("updateRaavare".equals(action)) {
			try {
				int raavareIDToUpdate = Integer.parseInt(request.getParameter("raavareIDToUpdate"));
				RaavareDTO raavareToUpdate = raavareLogic.getRaavare(raavareIDToUpdate);
				request.setAttribute("raavareToUpdate", raavareToUpdate);
				request.getRequestDispatcher("../WEB-INF/raavare/updateRaavare.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=raavareList").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute raavareToUpdate could not be parsed as an integer");
				request.getRequestDispatcher("index.jsp?action=raavareList").forward(request, response);
			}
		}
		// Action: Carry out the changes made in the filled update raavare form.
		// Redirects to index.jsp.
		else if ("updateRaavareFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				int updRaavareID = Integer.parseInt(request.getParameter("raavareIDToUpdate"));
				String updRaavareName = request.getParameter("updRaavareName");
				String updRaavareLeverandoer = request.getParameter("updRaavareLeverandoer");
				raavareLogic.updateRaavare(updRaavareID, updRaavareName, updRaavareLeverandoer);
				request.setAttribute("message", "Råvare with ID: " + updRaavareID + " successfully updated.");
				request.getRequestDispatcher("index.jsp?action=raavareList").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=updateRaavare").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute raavareIDToUpdate could not be parsed as an integer.");
				request.getRequestDispatcher("index.jsp?action=updateRaavare").forward(request, response);
			}
		} 
		// Action: default action if no action is set.
		else { 
			request.getRequestDispatcher("../WEB-INF/raavare/index.jsp?").forward(request, response);
		}
	}
}