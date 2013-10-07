package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import daointerfaces.DALException;
import dto.GenreDTO;
import dto.UsersDTO;
import funktionalitet.GenreLogic;
import funktionalitet.IGenreLogic;
import funktionalitet.IUserLogic;
import funktionalitet.UserLogic;

public class ControlGenre extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IGenreLogic genreLogic = null;
	private UsersDTO user = null;
	private IUserLogic u = null;
	
	public ControlGenre() {
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

		genreLogic = (GenreLogic) application.getAttribute("genreLogic");
		if (genreLogic == null) {
			try {
				genreLogic = new GenreLogic();
				application.setAttribute("genreLogic", genreLogic);
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

		String action = null;
		action = request.getParameter("action");


		if ("createGenre".equals(action)) { 
			request.getRequestDispatcher("../WEB-INF/genre/createGenre.jsp").forward(request, response);
		}
		else if ("genreFilled".equals(action)) { 
			try {
				String genre = request.getParameter("newGenre");
				genreLogic.createGenre(genre);
				request.setAttribute("message", genre +" succesfully added");
				request.getRequestDispatcher("../WEB-INF/genre/index.jsp").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/genre/createGenre.jsp").forward(request, response);
			}
		}
		else if ("listGenre".equals(action)) { 
			try {
				List<GenreDTO> genreList = new ArrayList<GenreDTO>();
				genreList = genreLogic.getList();
				request.setAttribute("genreList", genreList);
				request.getRequestDispatcher("../WEB-INF/genre/list.jsp").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/genre/index.jsp").forward(request, response);
			}

		}else request.getRequestDispatcher("../WEB-INF/genre/index.jsp?").forward(request, response);



	}
}
