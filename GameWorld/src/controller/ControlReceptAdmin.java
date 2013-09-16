package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import daointerfaces.DALException;
import dto.*;
import funktionalitet.*;

public class ControlReceptAdmin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IReceptLogic receptLogic = null;
	private IRaavareLogic raavareLogic = null;

	public ControlReceptAdmin() {
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
		receptLogic = (ReceptLogic) application.getAttribute("receptLogic");
		if (receptLogic == null) {
			try {
				receptLogic = new ReceptLogic();
				application.setAttribute("receptLogic", receptLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}

		// create raavareLogic to be used for creating receptKomps.
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
		// Action: View recept list.
		// Redirects to receptList.jsp.
		if ("receptList".equals(action)) { 
			try {
				List<ReceptDTO> receptList = new ArrayList<ReceptDTO>(receptLogic.getReceptList());
				request.setAttribute("receptList", receptList);
				request.getRequestDispatcher("../WEB-INF/recept/receptList.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/recept/index.jsp?").forward(request, response);
			}
		}
		// Action: View Recept
		// Redirects to viewRecept.jsp
		else if ("viewRecept".equals(action)) {
			try {
				int receptIDToView = Integer.parseInt(request.getParameter("receptIDToView"));
				ReceptDTO receptToView = receptLogic.getRecept(receptIDToView);
				List<ReceptKompDTO> rkList = receptLogic.getReceptKompList(receptIDToView);
				List<RaavareDTO> raavareList = new ArrayList<RaavareDTO>();
				for (ReceptKompDTO rk : rkList) {
					raavareList.add(raavareLogic.getRaavare(rk.getRaavareId()));
				}
				request.setAttribute("receptToView", receptToView);
				request.setAttribute("receptKompList", rkList);
				request.setAttribute("raavareList", raavareList);
				request.getRequestDispatcher("../WEB-INF/recept/viewRecept.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=receptList").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute receptIDToView could not be parsed as an integer");
				request.getRequestDispatcher("index.jsp?action=receptList").forward(request, response);
			}
		}
		// Action: Create receptKomponentFilled
		else if ("createReceptKompFilled".equals(action)) {
			try {
				int newReceptID = Integer.parseInt(request.getParameter("receptIDToUpdate"));
				int newRaavareID = Integer.parseInt(request.getParameter("newRaavareID"));
				double newNomNetto = Double.parseDouble(request.getParameter("newNomNetto"));
				double newTolerance = Double.parseDouble(request.getParameter("newTolerance"));
				receptLogic.createReceptKomp(newReceptID, newRaavareID, newNomNetto, newTolerance);
				request.setAttribute("message", "ReceptKomp with ReceptID: " + newReceptID + " and RaavareID: " + newRaavareID + " successfully created.");
				request.getRequestDispatcher("index.jsp?action=updateRecept").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				String errorMessage = e.getMessage();
				if (errorMessage.contains("PRIMARY")) { errorMessage = "A receptKomponent with receptID: " + request.getParameter("receptIDToUpdate") + " and raavareId: " + request.getParameter("newRaavareID") + " already exists."; }
				request.setAttribute("error", errorMessage);
				request.getRequestDispatcher("index.jsp?action=createReceptKomp").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error parsing some attributes");
				request.getRequestDispatcher("index.jsp?action=receptList").forward(request, response);
			}
		}
		// Action: Create receptKomponent
		// Redirects to createReceptKomp.jsp.
		else if ("createReceptKomp".equals(action)) { // Creation of a new recept komponent.
			try {
				List<RaavareDTO> raavareList = raavareLogic.getRaavareList();
				ReceptDTO receptToUpdate = receptLogic.getRecept(Integer.parseInt(request.getParameter("receptIDToUpdate")));
				request.setAttribute("receptToUpdate", receptToUpdate);
				request.setAttribute("raavareList", raavareList);
				request.getRequestDispatcher("../WEB-INF/recept/createReceptKomp.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=updateRecept").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute receptIDToUpdate could not be parsed as an integer");
				request.getRequestDispatcher("index.jsp?action=receptList").forward(request, response);
			}
		} 
		// Action: Create Recept
		// Redirects to createRecept.jsp.
		else if ("createRecept".equals(action)) { // Creation of a new recept.
			if (request.getParameter("newReceptName") != null) { // This is true if the form has been filled.
				try {
					int newReceptID = Integer.parseInt(request.getParameter("newReceptID"));
					receptLogic.createRecept(newReceptID, request.getParameter("newReceptName"));
					request.setAttribute("message", "Recept with ID: " + newReceptID + " successfully created.");
					request.getRequestDispatcher("index.jsp?action=updateRecept&receptIDToUpdate="+newReceptID).forward(request, response);
				} catch (DALException e) {
					e.printStackTrace();
					String errorMessage = e.getMessage();
					if (errorMessage.contains("PRIMARY")) { errorMessage = "A Recept with ID:"+ request.getParameter("newReceptID") +" already exists."; }
					request.setAttribute("error", errorMessage);
					request.getRequestDispatcher("../WEB-INF/recept/createRecept.jsp?").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("error", "Attribute newReceptID could not be parsed as an integer");
					request.getRequestDispatcher("../WEB-INF/recept/createRecept.jsp?").forward(request, response);
				}
			} else { // This will show the form.
				request.getRequestDispatcher("../WEB-INF/recept/createRecept.jsp?").forward(request, response);
			}
		} 
		// Action: Showing the update recept form.
		// Redirects to updateRecept.jsp.
		else if ("updateRecept".equals(action)) {
			try {
				int receptIDToUpdate = Integer.parseInt(request.getParameter("receptIDToUpdate"));
				ReceptDTO receptToUpdate = receptLogic.getRecept(receptIDToUpdate);
				List<ReceptKompDTO> rkList = receptLogic.getReceptKompList(receptIDToUpdate);
				List<RaavareDTO> raavareList = new ArrayList<RaavareDTO>();
				for (ReceptKompDTO rk : rkList) {
					raavareList.add(raavareLogic.getRaavare(rk.getRaavareId()));
				}
				request.setAttribute("receptToUpdate", receptToUpdate);
				request.setAttribute("receptKompList", rkList);
				request.setAttribute("raavareList", raavareList);
				request.getRequestDispatcher("../WEB-INF/recept/updateRecept.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=receptList").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute receptIDToUpdate could not be parsed as an integer");
				request.getRequestDispatcher("index.jsp?action=receptList").forward(request, response);
			}
		}
		// Action: Carry out the changes made in the filled update recept form.
		// Redirects to index.jsp.
		else if ("updateReceptFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				int updReceptID = Integer.parseInt(request.getParameter("receptIDToUpdate"));
				String updReceptName = request.getParameter("updReceptName");
				receptLogic.updateRecept(updReceptID, updReceptName);
				request.setAttribute("message", "Operator with ID: " + updReceptID + " successfully updated.");
				request.getRequestDispatcher("index.jsp?action=receptList").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=updateRecept").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute receptIDToUpdate could not be parsed as an integer.");
				request.getRequestDispatcher("index.jsp?action=updateRecept").forward(request, response);
			}
		} 
		// Action: default action if no action is set.
		else { 
			request.getRequestDispatcher("../WEB-INF/recept/index.jsp?").forward(request, response);
		}
	}
}