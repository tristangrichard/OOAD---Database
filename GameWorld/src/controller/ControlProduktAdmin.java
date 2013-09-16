package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import daointerfaces.DALException;
import dto.*;
import funktionalitet.*;


public class ControlProduktAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProduktBatchLogic produktBatchLogic = null;
	private IReceptLogic receptLogic = null;
	private IRaavareLogic raavareLogic = null;
	private IRaavareBatchLogic raavareBatchLogic = null;
	private IOperatorLogic oprLogic= null;

	public ControlProduktAdmin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getSession().getServletContext();


		// Create logic if not already created.
		produktBatchLogic = (ProduktBatchLogic) application.getAttribute("produktBatchLogic");
		if (produktBatchLogic == null) {
			try {
				produktBatchLogic = new ProduktBatchLogic();
				application.setAttribute("produktBatchLogic", produktBatchLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}
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
		raavareBatchLogic = (RaavareBatchLogic) application.getAttribute("raavareBatchLogic");
		if (raavareBatchLogic == null) {
			try {
				raavareBatchLogic = new RaavareBatchLogic();
				application.setAttribute("raavareBatchLogic", raavareBatchLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}
		oprLogic = (OperatorLogic) application.getAttribute("oprLogic");
		if (oprLogic == null) {
			try {
				oprLogic = new OperatorLogic();
				application.setAttribute("operatorLogic", oprLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}

		// Getting the action parameter.
		String action = null;
		action = request.getParameter("action");

		// All actions are done below.
		// Action: View produktbatch list.
		// Redirects to produktBatchList.jsp.
		if ("produktBatchList".equals(action)) { 
			try {
				List<ProduktBatchDTO> produktBatchList = new ArrayList<ProduktBatchDTO>(produktBatchLogic.getProduktBatchList());
				request.setAttribute("produktBatchList", produktBatchList);
				request.getRequestDispatcher("../WEB-INF/produkt/produktBatchList.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/produkt/index.jsp?").forward(request, response);
			} 
		} 
		// Action: View specific produktbatch.
		// Redirects to viewProduktBatch.jsp.
		else if ("viewProduktBatch".equals(action)) {
			try {
				List<ProduktBatchLinjeDTO> pblList = new ArrayList<ProduktBatchLinjeDTO>();
				int pbIDToView = Integer.parseInt(request.getParameter("PBIDToView"));
				ProduktBatchDTO pbToView = produktBatchLogic.getProduktBatch(pbIDToView);
				int stat = pbToView.getStatus();
				String status = null;
				if (stat == 0) {status="Ikke påbegyndt";}
				else if (stat == 1) {status="Under produktion";}
				else status="Afsluttet";
				List<ReceptKompDTO> rkList = receptLogic.getReceptKompList(pbToView.getReceptId());
				for (ReceptKompDTO rk : rkList) {
					ProduktBatchLinjeDTO pbl = new ProduktBatchLinjeDTO(raavareLogic.getRaavare(rk.getRaavareId()).getRaavareNavn(), rk.getTolerance(), rk.getRaavareId(), rk.getNomNetto());
					pblList.add(pbl);
				}
				List<ProduktBatchKompDTO> PBKList = produktBatchLogic.getProduktBatchKompList(pbIDToView);
				for (ProduktBatchKompDTO pbk : PBKList) {
					int RId =raavareBatchLogic.getRaavareBatch(pbk.getRbId()).getRaavareId();
					for(ProduktBatchLinjeDTO pbl : pblList) {
						if (pbl.getRaavareid() == RId){
							pbl.setTara(pbk.getTara());
							pbl.setNetto(pbk.getNetto());
							pbl.setRaavarebatch(pbk.getRbId());
							pbl.setOpr(oprLogic.getOperatoer(pbk.getOprId()).getIni());
						}		
					}
				}
				request.setAttribute("status", status);
				request.setAttribute("pblList", pblList);
				request.setAttribute("pbToView", pbToView);
				request.getRequestDispatcher("../WEB-INF/produkt/viewProdukt.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=produktBatchList").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute PBToView could not be parsed as an integer");
				request.getRequestDispatcher("index.jsp?action=produktBatchList").forward(request, response);
			}
		}
		// Action: Create ProduktBatch
		// Redirects to createProduktBatch.jsp.
		else if ("createProduktBatch".equals(action)) { // Creation of a new produktbatch.
			if (request.getParameter("newPBID") != null) { // This is true if the form has been filled.
				try {
					int newPBID = Integer.parseInt(request.getParameter("newPBID"));
					int newReceptID = Integer.parseInt(request.getParameter("newReceptID"));
					int newStatus = Integer.parseInt(request.getParameter("newStatus"));
					Date start = null;
					if (newStatus == 1) {
						start = new Date();
					}
					produktBatchLogic.createProduktBatch(newPBID, newStatus, newReceptID, start);
					request.setAttribute("message", "ProduktBatch with ID: " + newPBID + " successfully created.");
					request.getRequestDispatcher("index.jsp?action=produktBatchList").forward(request, response);
				} catch (DALException e) {
					e.printStackTrace();
					String errorMessage;
					if (e.getMessage().contains("PRIMARY")) {
						errorMessage = "A ProduktBatch with ID: " + request.getParameter("newPBID") + " already exists.";
					} else {
						errorMessage = e.getMessage();
					}
					request.setAttribute("error", errorMessage);
					request.getRequestDispatcher("../WEB-INF/produkt/createProduktBatch.jsp?").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("error", "Error parsing attributes as integer.");
					request.getRequestDispatcher("../WEB-INF/produkt/createProduktBatch.jsp?").forward(request, response);
				} 
			} else { // This will show the form.
				request.getRequestDispatcher("../WEB-INF/produkt/createProduktBatch.jsp?").forward(request, response);
			}
		} 
		// Action: Showing the update produktbatch form.
		// Redirects to updateProduktBatch.jsp.
		else if ("updateProduktBatch".equals(action)) {
			try {
				int PBIDToUpdate = Integer.parseInt(request.getParameter("PBIDToUpdate"));
				ProduktBatchDTO PBToUpdate = produktBatchLogic.getProduktBatch(PBIDToUpdate);
				List<ProduktBatchKompDTO> PBKList = produktBatchLogic.getProduktBatchKompList(PBIDToUpdate);
				request.setAttribute("PBToUpdate", PBToUpdate);
				request.setAttribute("PBKList", PBKList);
				request.getRequestDispatcher("../WEB-INF/produkt/updateProduktBatch.jsp?").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=produktBatchList").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Attribute PBToUpdate could not be parsed as an integer");
				request.getRequestDispatcher("index.jsp?action=produktBatchList").forward(request, response);
			}
			
		}
		// Action: Carry out the changes made in the filled update produktBatch form.
		// Redirects to index.jsp.
		else if ("updateProduktBatchFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				int updPBID = Integer.parseInt(request.getParameter("PBIDToUpdate"));
				int updReceptID = Integer.parseInt(request.getParameter("updReceptID"));
				int updStatus = Integer.parseInt(request.getParameter("updStatus"));
				ProduktBatchDTO pb = produktBatchLogic.getProduktBatch(updPBID);
				Date start = pb.getStart();
				if (updStatus == 1 && (start == null || start.getTime() == 0)) {
					start = new Date();
				}
				produktBatchLogic.updateProduktBatch(updPBID, updReceptID, updStatus, start, pb.getSlut());
				request.setAttribute("message", "ProduktBatch with ID: " + updPBID + " successfully updated.");
				request.getRequestDispatcher("index.jsp?action=produktBatchList").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=updateProduktBatch").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error parsing attributes as integer.");
				request.getRequestDispatcher("index.jsp?action=updateProduktBatch").forward(request, response);
			}
		} 
		// Action: default action if no action is set.
		else { 
			request.getRequestDispatcher("../WEB-INF/produkt/index.jsp?").forward(request, response);
		}
	}
}