package funktionalitet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import connector.Connector;
import daoimpl.MySQLRoleDAO;
import daoimpl.MySQLStatDAO;
import daoimpl.MySQLUserPubDAO;
import daoimpl.MySQLUsersDAO;
import daoimpl.MySQLUsersLangDAO;
import daointerfaces.DALException;
import daointerfaces.StatIDAO;
import dto.RankDTO;

public class StatLogic implements IStat {
	
	private RankDTO mostOwnedGame;
	private StatIDAO stat;

	public StatLogic() throws DALException {
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stat = new MySQLStatDAO();
	}
	@Override
	public int countPLayers(String game, String sex, String language, String minDOB, String maxDOB) throws DALException {
		int game1 = -1;
		int sex1 = -1;
		int lang1 = -1;
		String min1 = null;
		String max1 = null;
		if (!game.equalsIgnoreCase("null"))	game1 = Integer.parseInt(game);
		if (!sex.equalsIgnoreCase("null")) sex1 = Integer.parseInt(sex);
		if (!language.equalsIgnoreCase("null")) lang1 = Integer.parseInt(language);
		if (!minDOB.equalsIgnoreCase("null")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -Integer.parseInt(minDOB));
			min1 = "" + c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + "";
		}
		if (!maxDOB.equalsIgnoreCase("null")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -Integer.parseInt(maxDOB));
			max1 = "" + c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + "";
		}
		int i = stat.countPlayers(game1, sex1, lang1, min1, max1);
		return i;
	}

	@Override
	public List<RankDTO> getMostOwnedGame(int max) throws DALException {
		List<RankDTO> list = new ArrayList<RankDTO>();
		list = stat.rankGames(max);
		return list;
	}
	@Override
	public List<RankDTO> getMostOwnedGame(String max, String sex, String country, String minDOB, String maxDOB) throws DALException {
		int gameMax = -1;
		int sex1 = -1;
		int lang1 = -1;
		String min1 = null;
		String max1 = null;
		if (!max.equalsIgnoreCase("null"))	gameMax = Integer.parseInt(max);
		if (!sex.equalsIgnoreCase("null")) sex1 = Integer.parseInt(sex);
		if (!country.equalsIgnoreCase("null")) lang1 = Integer.parseInt(country);
		if (!minDOB.equalsIgnoreCase("null")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -Integer.parseInt(minDOB));
			min1 = "" + c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + "";
		}
		if (!maxDOB.equalsIgnoreCase("null")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -Integer.parseInt(maxDOB));
			max1 = "" + c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + "";
		}
		List<RankDTO> list = new ArrayList<RankDTO>();
		list = stat.rankGames(gameMax, sex1, lang1, min1, max1);
		return list;
	}
	@Override
	public List<RankDTO> getMostOwnedGameG(int max) throws DALException {
		List<RankDTO> list = new ArrayList<RankDTO>();
		list = stat.rankGamesG(max);
		return list;
	}
	@Override
	public List<RankDTO> getMostOwnedGameM(int max) throws DALException {
		List<RankDTO> list = new ArrayList<RankDTO>();
		list = stat.rankGamesM(max);
		return list;
	}

}
