package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import daointerfaces.DALException;
import daointerfaces.LangIDAO;
import daoimpl.MySQLLangDAO;
import dto.BrugerDTO;
import dto.LangDTO;
import funktionalitet.IUserLogic;
import funktionalitet.UserLogic;

public class ControlCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserLogic userLogic = null;
	private BrugerDTO user = null;
	private LangIDAO l = null;

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
		l = (MySQLLangDAO) application.getAttribute("LangDAO");
		if (l == null) {
				l = new MySQLLangDAO();
				application.setAttribute("LangDAO", l);
		}

		// Getting the action parameter.
		String action = null;
		action = request.getParameter("action");
		// Action: Log outs the current user.
		// Redirects to index.jsp.
		if ("List".equals(action)) { 
			try {
				List<LangDTO> langList = new ArrayList<LangDTO>(l.getList());
				request.setAttribute("langList", langList);
				request.getRequestDispatcher("../WEB-INF/create/createOpr.jsp?").forward(request, response); // Sends the request to the actual operator list jsp file.
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				
			}
		}else if ("userFilled".equals(action)) {
			// Getting all the details from the filled form.
			String fName = null;
			String lName = null;
			String userBirth = null;
			String userRole = "User";
			String userEmail = null;
			String userSex = null;
			int sex = 0;
			String userLang = null;
			int langu = 0;
			try {
				fName = request.getParameter("newFName");
				lName = request.getParameter("newLName");
				userBirth = request.getParameter("newUserBirth");
				userEmail = request.getParameter("newUserEmail");
				userSex = request.getParameter("newUserSex");
				sex = Integer.parseInt(userSex);
				userLang = request.getParameter("newUserLang");
				langu = Integer.parseInt(userLang);
				String pass = userLogic.createUser(fName, lName, userBirth, userRole, userEmail, sex, langu);
				//} else { // Use the admin method that does not require the old password if the user is an admin.
				//		userLogic.updateOprAdmin(updOprID, updOprName, updOprCpr, updOprPass1, updOprPass2, updOprRole);
				//}
				request.setAttribute("message", "Operator with ID: " + userEmail + " successfully updated. Your password is:"+pass);
				request.getRequestDispatcher("../WEB-INF/create/createOpr.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.setAttribute("action", "List");
				request.getRequestDispatcher("index.jsp?action=List").forward(request, response);
			}
		}
		else {request.getRequestDispatcher("../WEB-INF/create/createOpr.jsp?").forward(request, response);}
	}
}