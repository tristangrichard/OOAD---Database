package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.UsersGamesDTO;
import daointerfaces.UsersGamesIDAO;
import daointerfaces.DALException;


public class MySQLUsersGamesDAO extends UsersGamesIDAO
{

	public void create(UsersGamesDTO row) throws DALException
	{
		String create = "INSERT INTO UsersGames(Uid, Gid) VALUES (" + row.getUid() + ", " + row.getGid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int Uid, int Gid) throws DALException
	{
		String delete = "DELETE FROM UsersGames WHERE Uid = " + Uid + " AND Gid = " + Gid + ";";
		Connector.doUpdate(delete);
	}
	public void update(UsersGamesDTO row) throws DALException
	{
		String update = "UPDATE UsersGames SET Uid = " + row.getUid() + ", Gid = " + row.getGid() + " WHERE Uid = " + row.getUid() + " AND Gid = " + row.getGid() + ";";
		Connector.doUpdate(update);
	}

	public UsersGamesDTO get(int Uid, int Gid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM UsersGames WHERE Uid = " + Uid + " AND Gid = " + Gid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UsersGamesDTO(rs.getInt("Uid"), rs.getInt("Gid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<UsersGamesDTO> getList() throws DALException
	{
		try
		{
			List<UsersGamesDTO> results = new ArrayList<UsersGamesDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM UsersGames;");
			if(!rs.next()) throw new DALException("Missing table: UsersGames");
			do results.add(new UsersGamesDTO(rs.getInt("Uid"), rs.getInt("Gid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
