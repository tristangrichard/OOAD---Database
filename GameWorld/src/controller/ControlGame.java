package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import daoimpl.MySQLDeveloperDAO;
import daoimpl.MySQLGenreDAO;
import daoimpl.MySQLLangDAO;
import daoimpl.MySQLOSDAO;
import daoimpl.MySQLPublisherDAO;
import daointerfaces.DALException;
import daointerfaces.DeveloperIDAO;
import daointerfaces.GenreIDAO;
import daointerfaces.LangIDAO;
import daointerfaces.OSIDAO;
import daointerfaces.PublisherIDAO;
import dto.DeveloperDTO;
import dto.GameDTO;
import dto.GenreDTO;
import dto.LangDTO;
import dto.OSDTO;
import dto.PublisherDTO;
import dto.UsersDTO;
import funktionalitet.GameLogic;
import funktionalitet.IGameLogic;
import funktionalitet.IUserLogic;
import funktionalitet.UserLogic;
/**
 * 
 * @author Tristan Richard
 *
 */
public class ControlGame extends HttpServlet {

	private GenreIDAO gen = null;
	private LangIDAO lan = null;
	private PublisherIDAO pub = null;
	private DeveloperIDAO dev = null;
	private OSIDAO os = null;
	private IGameLogic  gameLogic = null;
	private UsersDTO user = null;
	private IUserLogic u = null;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = request.getSession().getServletContext();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		gameLogic = (GameLogic) application.getAttribute("gameLogic");
		if (gameLogic == null) {
			try {
				gameLogic = new GameLogic();
				application.setAttribute("gameLogic", gameLogic);
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
		gen = (MySQLGenreDAO) application.getAttribute("genreDAO"); // Used to get type of Genre on website
		if (gen == null) {
				gen = new MySQLGenreDAO();
				application.setAttribute("genreDAO", gen);
		}
		lan = (MySQLLangDAO) application.getAttribute("LangDAO");
		if (lan == null) {
				lan = new MySQLLangDAO();
				application.setAttribute("LangDAO", lan);
		}
		os = (MySQLOSDAO) application.getAttribute("os"); // Used to get os on website
		if (os == null) {
				os = new MySQLOSDAO();
				application.setAttribute("lan", lan);
		}
		dev = (MySQLDeveloperDAO) application.getAttribute("developer"); // Used to get os on website
		if (dev == null) {
				dev = new MySQLDeveloperDAO();
				application.setAttribute("developer", dev);
		}
		pub = (MySQLPublisherDAO) application.getAttribute("publisher"); // Used to get os on website
		if (pub == null) {
				pub = new MySQLPublisherDAO();
				application.setAttribute("publisher", pub);
		}
		// Getting the action parameter.
		String action = null;
		action = request.getParameter("action");

		// Gather data to be able to create a game
		// Redirects to createGame.jsp
		if ("CreateGame".equals(action)) { 
			try {
				List<LangDTO> langList = new ArrayList<LangDTO>(lan.getList());
				List<GenreDTO> genreList = new ArrayList<GenreDTO>(gen.getList());
				List<OSDTO> osList = new ArrayList<OSDTO>(os.getList());
				List<DeveloperDTO> devList = new ArrayList<DeveloperDTO>(dev.getList());
				List<PublisherDTO> pubList = new ArrayList<PublisherDTO>(pub.getList());
				request.setAttribute("langList", langList);
				request.setAttribute("genreList", genreList);
				request.setAttribute("devList", devList);
				request.setAttribute("pubList", pubList);
				request.setAttribute("osList", osList);
				request.getRequestDispatcher("../WEB-INF/game/createGame.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", "");
				request.getRequestDispatcher("../WEB-INF/game/index.jsp?").forward(request, response);
			}
		}
		else if("gameFilled".equals(action)){ // Create new Game.
			try {
				String[] lang =  request.getParameterValues("newLang");
				String[] operating = request.getParameterValues("newOS");
				String[] genre = request.getParameterValues("newGenre");
				String title = request.getParameter("newTitle");
				String release = request.getParameter("newRelease");
				String url = request.getParameter("newUrl");
				String[] pub = request.getParameterValues("newPub");
				String[] dev = request.getParameterValues("newDev");
				gameLogic.createGame(title, release, url, genre, lang, operating, dev, pub);
				request.setAttribute("message", title + " was succesfully added to our databse!");
				request.getRequestDispatcher("../WEB-INF/game/index.jsp?").forward(request, response);
			}catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("createGame.jsp?action=CreateGame").forward(request, response);
			}
		}
		else if("List".equals(action)){ // Get our Games
			try {
				List<GameDTO> gameList = new ArrayList<GameDTO>(gameLogic.listGames(user.getEmail()));
				List<String> ga = new ArrayList<String>();
				List<String> url = new ArrayList<String>();
				List<Integer> Pid = new ArrayList<Integer>();
				for(GameDTO game: gameList)
					ga.add(game.getGname());
				Collections.sort(ga);
				for (String name : ga){
					for(GameDTO game: gameList){
						if (name.equals(game.getGname())){
						url.add(game.getUrl());
						Pid.add(game.getGid());
						break;
						}
					}
				}
				request.setAttribute("idList", Pid);
				request.setAttribute("gameList", ga);
				request.setAttribute("gameUrl", url);
				request.getRequestDispatcher("../WEB-INF/game/ourGames.jsp").forward(request, response); // Sends the request to the actual user list jsp file.
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/game/index.jsp?").forward(request, response);

			}
			
		}
		else if("Stat".equals(action)){ // Get Stats
			request.getRequestDispatcher("../WEB-INF/game/index.jsp?").forward(request, response);
		}
		
		else { 
			request.getRequestDispatcher("../WEB-INF/game/index.jsp?").forward(request, response);
		}

	}
}
