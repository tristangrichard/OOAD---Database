package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import daointerfaces.DALException;
import dto.RaavareBatchDTO;
import funktionalitet.*;

public class ControlRaavareBatchAdmin extends HttpServlet {


	//	private IReceptLogic receptLogic = null;
	private IRaavareBatchLogic raavareBatchLogic = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ControlRaavareBatchAdmin() {
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
		raavareBatchLogic = (RaavareBatchLogic) application.getAttribute("raavareBatchLogic");
		if (raavareBatchLogic == null) {
			try {
				raavareBatchLogic = new RaavareBatchLogic();
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
			application.setAttribute("rLogic", raavareBatchLogic);
		}

		// Getting the action parameter.
		String action = null;
		action = request.getParameter("action");

		// All actions are done below.
		// Action: View operator list.
		// Redirects to oprList.jsp.
		if ("raavareBatchList".equals(action)) { 
			try {
				List<RaavareBatchDTO> raavareBatchList = new ArrayList<RaavareBatchDTO>(raavareBatchLogic.getRaavareBatchList());
				request.setAttribute("raavareBatchList", raavareBatchList);
				request.getRequestDispatcher("../WEB-INF/raavarebatch/raavareBatchList.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/raavarebatch/index.jsp?").forward(request, response);
			}
		}
		// Action: Create Operator
		// Redirects to createOpr.jsp.
		else if ("createRaavareBatch".equals(action)) { 
			if (request.getParameter("newRbId") != null) { // This is true if the form has been filled.
				try {
					int rbId = Integer.parseInt(request.getParameter("newRbId"));
					int raavareId = Integer.parseInt(request.getParameter("newRaavareId"));
					double maengde = Double.parseDouble(request.getParameter("newMaengde"));
					raavareBatchLogic.createRaavareBatch(rbId, raavareId, maengde);
					request.setAttribute("message", "Råvare Batch with ID: " + rbId + " successfully created.");
					request.getRequestDispatcher("index.jsp?action=raavareBatchList").forward(request, response);
				} catch (DALException e) {
					e.printStackTrace();
					String errorMessage = e.getMessage();
					if (errorMessage.contains("PRIMARY")) { errorMessage = "A RåvareBatch with ID:"+ request.getParameter("newRbId") +" already exists."; }
					request.setAttribute("error", errorMessage);
					request.getRequestDispatcher("../WEB-INF/raavarebatch/createRaavareBatch.jsp?").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("error", "Error parsing some attributes.");
					request.getRequestDispatcher("../WEB-INF/raavarebatch/createRaavareBatch.jsp?").forward(request, response);
				}
			} else { // This will show the form.
				request.getRequestDispatcher("../WEB-INF/raavarebatch/createRaavareBatch.jsp?").forward(request, response);
			}
		} 
		// Action: Showing the update operator form.
		// Redirects to updateOpr.jsp.
		else if ("updateRaavareBatch".equals(action)) {
			try {
				int rBIDToUpdate = Integer.parseInt(request.getParameter("raavareBatchIDToUpdate"));
				RaavareBatchDTO raavareBatchToUpdate = raavareBatchLogic.getRaavareBatch(rBIDToUpdate);
				request.setAttribute("raavareBatchToUpdate", raavareBatchToUpdate);
				request.getRequestDispatcher("../WEB-INF/raavarebatch/updateRaavareBatch.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=raavareBatchList").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute raavareBatchIDToUpdate could not be parsed as an integer");
				request.getRequestDispatcher("index.jsp?action=raavareBatchList").forward(request, response);
			}
		}
		// Action: Carry out the changes made in the filled update operator form.
		// Redirects to index.jsp.
		else if ("updateRaavareBatchFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				int updRBID = Integer.parseInt(request.getParameter("raavareBatchIDToUpdate"));
				int updRID = Integer.parseInt(request.getParameter("updRaavareID"));
				double updM = Double.parseDouble(request.getParameter("updMaengde"));
				raavareBatchLogic.updateRaavareBatch(updRBID, updRID, updM);
				request.setAttribute("message", "Raavare Batch with ID: " + updRBID + " successfully updated.");
				request.getRequestDispatcher("index.jsp?action=raavareBatchList").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				String errorMessage = e.getMessage();
				if (errorMessage.contains("FOREIGN")) { errorMessage = "No råvare with ID:"+ request.getParameter("updRaavareID") +" exists."; }
				request.setAttribute("error", errorMessage);
				request.getRequestDispatcher("index.jsp?action=updateRaavareBatch").forward(request, response);
			} catch (NumberFormatException e) { 
				e.printStackTrace();
				request.setAttribute("error", "Error parsing some attributes.");
				request.getRequestDispatcher("index.jsp?action=updateRaavareBatch").forward(request, response);
			}
		} 
		// Action: default action if no action is set.
		else { 
			request.getRequestDispatcher("../WEB-INF/raavarebatch/index.jsp?").forward(request, response);
		}
	}
}