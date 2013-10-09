package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import daoimpl.MySQLLangDAO;
import daoimpl.MySQLPublisherDAO;
import daoimpl.MySQLRoleDAO;
import daoimpl.MySQLUserPubDAO;
import daoimpl.MySQLUsersLangDAO;
import daointerfaces.DALException;
import daointerfaces.LangIDAO;
import daointerfaces.PublisherIDAO;
import daointerfaces.RoleIDAO;
import daointerfaces.UserPubIDAO;
import daointerfaces.UsersLangIDAO;
import dto.LangDTO;
import dto.PublisherDTO;
import dto.RoleDTO;
import dto.UserPubDTO;
import dto.UsersDTO;
import dto.UsersLangDTO;
import funktionalitet.*;

@WebServlet("/control")
public class ControlUserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserLogic userLogic = null;
	private IUserPubLogic userPubLogic = null;
	private UsersDTO user = null;
	private LangIDAO lang = null;
	private PublisherIDAO pub = null;
	private UserPubIDAO userPub = null;
	private UsersLangIDAO userLang = null;
	private RoleIDAO userRole = null;

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
		userPubLogic = (UserPubLogic) application.getAttribute("userPubLogic");
		if (userPubLogic == null) {
			try {
				userPubLogic = new UserPubLogic();
				application.setAttribute("userPubLogic", userPubLogic);
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
		userLang = (UsersLangIDAO) application.getAttribute("userlanguage");
		if (userLang == null) {
			userLang = new MySQLUsersLangDAO();
			application.setAttribute("userlanguage", userLang);
		}
		pub = (PublisherIDAO) application.getAttribute("publisher");
		if (pub == null) {
			pub = new MySQLPublisherDAO();
			application.setAttribute("publisher", pub);
		}
		userPub = (UserPubIDAO) application.getAttribute("userPublisher");
		if (userPub == null) {
			userPub = new MySQLUserPubDAO();
			application.setAttribute("userPublisher", userPub);
		}
		userRole = (RoleIDAO) application.getAttribute("role");
		if (userRole == null) {
			userRole = new MySQLRoleDAO();
			application.setAttribute("role", userRole);
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

		// Redirects to createPublisher JSP
		if ("createPublisher".equals(action)) { // Creation of a new operator.
			List<PublisherDTO> pubList;
			List<LangDTO> langList;
			try {
				pubList = pub.getList();
				langList = lang.getList();
				request.setAttribute("pubList", pubList);
				request.setAttribute("langList", langList);
				request.getRequestDispatcher("../WEB-INF/admin/createPublisher.jsp?").forward(request, response); 
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
			// Sends the request back to the create operator jsp file if an error is detected.
		} 
		// Action: Showing the update operator form.
		// Redirects to updateOpr.jsp.
		else if ("publisherFilled".equals(action)) { 
			try {
				String fName = request.getParameter("newFName");
				String lName = request.getParameter("newLName");
				String email = request.getParameter("newUserEmail");
				String birth = request.getParameter("newUserBirth");
				String sex = request.getParameter("newUserSex");
				String lang = request.getParameter("newUserLang");
				String company = request.getParameter("newPub");
				String role = "game";
				int tsex = Integer.parseInt(sex);
				int tlang = Integer.parseInt(lang);
				int tcompany = Integer.parseInt(company);
				userLogic.createUser(fName, lName, birth, role, email, tsex, tlang);
				userPubLogic.createUserPub(email, tcompany);
				request.setAttribute("message", fName + " was succesfully added to the database");
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/createPublisher.jsp?").forward(request, response);
			}

		} else if ("listPublisher".equals(action)) {
			try {
				List<UserPubDTO> userPubList = userPubLogic.getList();
				List<UsersDTO> users = new ArrayList<UsersDTO>();
				List<PublisherDTO> comp = new ArrayList<PublisherDTO>();
				for (UserPubDTO i: userPubList) {
					users.add(userLogic.getUser(i.getEmail()));
					comp.add(userPubLogic.getPub(i.getPid()));
				}
				request.setAttribute("userList", users);
				request.setAttribute("compList", comp);
				request.getRequestDispatcher("../WEB-INF/admin/publisherList.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}

		} else if ("listUsers".equals(action)) {
			try {
				List<UsersDTO> userList = userLogic.getUserList();
				List<String> roles = new ArrayList<String>();
				for (UsersDTO i: userList) {
					roles.add(userRole.get(i.getEmail()).getRole());
				}
				request.setAttribute("roles", roles);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("../WEB-INF/admin/userList.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
		} else if ("updateUser".equals(action)) {
			try {
				String userEmail = request.getParameter("userToUpdate");
				RoleDTO role = userRole.get(userEmail);
				UsersDTO user1 = userLogic.getUser(userEmail);
				UsersLangDTO userLangRow = userLang.get(userEmail);
				List<LangDTO> langu = lang.getList();
				request.setAttribute("role", role);
				request.setAttribute("userLang", userLangRow);
				request.setAttribute("langList", langu);
				request.setAttribute("user1", user1);
				if (role.getRole().equalsIgnoreCase("user") || role.getRole().equalsIgnoreCase("administrator")) {
					request.getRequestDispatcher("../WEB-INF/admin/updateUser.jsp?").forward(request, response);
				} else {
					UserPubDTO comp = userPub.get(userEmail);
					List<PublisherDTO> pubList = pub.getList();
					request.setAttribute("userPub", comp);
					request.setAttribute("pubList", pubList);
					request.getRequestDispatcher("../WEB-INF/admin/updatePublisher.jsp?").forward(request, response);
				}
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
		} else if ("updateOprFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				String fName = request.getParameter("newFName");
				String lName = request.getParameter("newLName");
				String email = request.getParameter("newUserEmail");
				String birth = request.getParameter("newUserBirth");
				String sex = request.getParameter("newUserSex");
				String lang = request.getParameter("newUserLang");
				String role = request.getParameter("newUserRole");
				String pass1 = request.getParameter("updOprPass1");
				String pass2 = request.getParameter("updOprPass2");
				int iSex = Integer.parseInt(sex);
				int iLang = Integer.parseInt(lang);	
				if (role.equalsIgnoreCase("user") || role.equalsIgnoreCase("administrator")) {
				userLogic.updateOprAdmin(fName, lName, birth, email, iSex, iLang, role, pass1, pass2);
				request.setAttribute("message", "User with email: " + email + " successfully updated.");
				} else {
					String publisher = request.getParameter("newPub");
					int iPublisher = Integer.parseInt(publisher);
					userLogic.updatePubAdmin(fName, lName, birth, email, iSex, iLang, role, pass1, pass2, iPublisher);
					request.setAttribute("message", "User with email: " + email + " successfully updated.");
				}
				request.getRequestDispatcher("index.jsp?action=updateUser").forward(request, response);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=updateOpr").forward(request, response);
			}
		} else if ("deactivateUser".equals(action)) {
			String userEmail =request.getParameter("userToDeactivate");
			try {
				userLogic.deactivateUser(userEmail);
				request.setAttribute("message", userEmail + " was succesfully deactivated");
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", userEmail + " did not get deactivated");
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
		}

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