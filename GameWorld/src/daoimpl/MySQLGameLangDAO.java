package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.GameLangDTO;
import daointerfaces.GameLangIDAO;
import daointerfaces.DALException;


public class MySQLGameLangDAO extends GameLangIDAO
{

	public void create(GameLangDTO row) throws DALException
	{
		String create = "INSERT INTO GameLang(Gid, Langid) VALUES (" + row.getGid() + ", " + row.getLangid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int Gid, int Langid) throws DALException
	{
		String delete = "DELETE FROM GameLang WHERE Gid = " + Gid + " AND Langid = " + Langid + ";";
		Connector.doUpdate(delete);
	}
	public void update(GameLangDTO row) throws DALException
	{
		String update = "UPDATE GameLang SET Gid = " + row.getGid() + ", Langid = " + row.getLangid() + " WHERE Gid = " + row.getGid() + " AND Langid = " + row.getLangid() + ";";
		Connector.doUpdate(update);
	}

	public GameLangDTO get(int Gid, int Langid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM GameLang WHERE Gid = " + Gid + " AND Langid = " + Langid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new GameLangDTO(rs.getInt("Gid"), rs.getInt("Langid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<GameLangDTO> getList() throws DALException
	{
		try
		{
			List<GameLangDTO> results = new ArrayList<GameLangDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM GameLang;");
			if(!rs.next()) throw new DALException("Missing table: GameLang");
			do results.add(new GameLangDTO(rs.getInt("Gid"), rs.getInt("Langid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
