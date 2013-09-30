package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoimpl.MySQLUsersGamesDAO;
import daointerfaces.DALException;
import daointerfaces.UsersGamesIDAO;
import dto.BrugerDTO;
import dto.GameDTO;
import dto.UsersDTO;
import dto.UsersGamesDTO;
import funktionalitet.IMyGamesLogic;
import funktionalitet.IUserLogic;
import funktionalitet.MyGamesLogic;
import funktionalitet.UserLogic;

public class ControlMyGames extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IMyGamesLogic myGames = null;
	private UsersDTO user = null;
	private IUserLogic u = null;
	
	
	public ControlMyGames() {
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

		myGames = (MyGamesLogic) application.getAttribute("myGames");
		if (myGames == null) {
			try {
				myGames = new MyGamesLogic();
				application.setAttribute("myGames", myGames);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}
		u = (UserLogic) application.getAttribute("userLogic");
		if (u == null) {
			try {
				u = new UserLogic();
				application.setAttribute("userLogic", u);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}
		user = (UsersDTO) session.getAttribute("user");
		if (user == null) {
			try {
				String email = request.getUserPrincipal().getName();
				user = u.getUser(email);
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
		if ("List".equals(action)) { 
			try {
				List<GameDTO> gameList = new ArrayList<GameDTO>(myGames.getMyGames(user.getEmail()));
				request.setAttribute("gameList", gameList);
				request.getRequestDispatcher("../WEB-INF/myGames/myGames.jsp").forward(request, response); // Sends the request to the actual operator list jsp file.
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());

			}
		
//		}else if ("userFilled".equals(action)) {
//			// Getting all the details from the filled form.
//			String fName = null;
//			String lName = null;
//			String userBirth = null;
//			String userRole = "User";
//			String userEmail = null;
//			String userSex = null;
//			int sex = 0;
//			String userLang = null;
//			int langu = 0;
//			try {
//				fName = request.getParameter("newFName");
//				lName = request.getParameter("newLName");
//				userBirth = request.getParameter("newUserBirth");
//				userEmail = request.getParameter("newUserEmail");
//				userSex = request.getParameter("newUserSex");
//				sex = Integer.parseInt(userSex);
//				userLang = request.getParameter("newUserLang");
//				langu = Integer.parseInt(userLang);
//				String pass = userLogic.createUser(fName, lName, userBirth, userRole, userEmail, sex, langu);
//				//} else { // Use the admin method that does not require the old password if the user is an admin.
//				//		userLogic.updateOprAdmin(updOprID, updOprName, updOprCpr, updOprPass1, updOprPass2, updOprRole);
//				//}
//				request.setAttribute("message", "Operator with ID: " + userEmail + " successfully updated. Your password is:"+pass);
//				request.getRequestDispatcher("../WEB-INF/create/createOpr.jsp?").forward(request, response);
//			} catch (DALException e) {
//				request.setAttribute("error", e.getMessage());
//				request.setAttribute("action", "List");
//				request.getRequestDispatcher("index.jsp?action=List").forward(request, response);
//			}
		}
		else {request.getRequestDispatcher("../WEB-INF/myGames/myGames.jsp?").forward(request, response);}
	}
}
