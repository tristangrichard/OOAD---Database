package funktionalitet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daoimpl.MySQLGenreDAO;
import daointerfaces.DALException;
import daointerfaces.GenreIDAO;
import dto.GenreDTO;

public class GenreLogic implements IGenreLogic {

	private GenreIDAO gen;
	
	public GenreLogic() throws DALException {
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
		gen = new MySQLGenreDAO();
	}
	public void createGenre(String genre) throws DALException {
		GenreDTO row = new GenreDTO(0,genre);
		gen.create(row);
	}

	public List<GenreDTO> getList() throws DALException {
		List<GenreDTO> genList = new ArrayList<GenreDTO>();
		genList = gen.getList();
		return genList;
	}

}
