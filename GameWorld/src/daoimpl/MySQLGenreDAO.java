package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.GenreDTO;
import daointerfaces.GenreIDAO;
import daointerfaces.DALException;


public class MySQLGenreDAO extends GenreIDAO
{

	public void create(GenreDTO row) throws DALException
	{
		String create = "INSERT INTO Genre(Genreid, Genre) VALUES (" + row.getGenreid() + ", '" + row.getGenre() + "');";
		Connector.doUpdate(create);
	}
	public void delete(int Genreid) throws DALException
	{
		String delete = "DELETE FROM Genre WHERE Genreid = " + Genreid + ";";
		Connector.doUpdate(delete);
	}
	public void update(GenreDTO row) throws DALException
	{
		String update = "UPDATE Genre SET Genreid = " + row.getGenreid() + ", Genre = '" + row.getGenre() + "' WHERE Genreid = " + row.getGenreid() + ";";
		Connector.doUpdate(update);
	}

	public GenreDTO getById(int Genreid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Genre WHERE Genreid = " + Genreid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new GenreDTO(rs.getInt("Genreid"), rs.getString("Genre"));
		}
		catch(SQLException e){throw new DALException(e);}
	}
	public GenreDTO getByGenre(String Genre) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Genre WHERE Genre = '" + Genre + "';");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new GenreDTO(rs.getInt("Genreid"), rs.getString("Genre"));
		}
		catch(SQLException e){throw new DALException(e);}
	}
	

	public List<GenreDTO> getList() throws DALException
	{
		try
		{
			List<GenreDTO> results = new ArrayList<GenreDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM Genre ORDER BY Genre;");
			if(!rs.next()) throw new DALException("Missing table: Genre");
			do results.add(new GenreDTO(rs.getInt("Genreid"), rs.getString("Genre")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
