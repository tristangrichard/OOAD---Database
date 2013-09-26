package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.GameGenreDTO;
import daointerfaces.GameGenreIDAO;
import daointerfaces.DALException;


public class MySQLGameGenreDAO extends GameGenreIDAO
{

	public void create(GameGenreDTO row) throws DALException
	{
		String create = "INSERT INTO GameGenre(Gid, Genreid) VALUES (" + row.getGid() + ", " + row.getGenreid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int Gid, int Genreid) throws DALException
	{
		String delete = "DELETE FROM GameGenre WHERE Gid = " + Gid + " AND Genreid = " + Genreid + ";";
		Connector.doUpdate(delete);
	}
	public void update(GameGenreDTO row) throws DALException
	{
		String update = "UPDATE GameGenre SET Gid = " + row.getGid() + ", Genreid = " + row.getGenreid() + " WHERE Gid = " + row.getGid() + " AND Genreid = " + row.getGenreid() + ";";
		Connector.doUpdate(update);
	}

	public GameGenreDTO get(int Gid, int Genreid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM GameGenre WHERE Gid = " + Gid + " AND Genreid = " + Genreid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new GameGenreDTO(rs.getInt("Gid"), rs.getInt("Genreid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<GameGenreDTO> getList() throws DALException
	{
		try
		{
			List<GameGenreDTO> results = new ArrayList<GameGenreDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM GameGenre;");
			if(!rs.next()) throw new DALException("Missing table: GameGenre");
			do results.add(new GameGenreDTO(rs.getInt("Gid"), rs.getInt("Genreid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
