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
		String create = "INSERT INTO UsersGames(email, Gid) VALUES ('" + row.getEmail() + "', " + row.getGid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(String email, int Gid) throws DALException
	{
		String delete = "DELETE FROM UsersGames WHERE email = '" + email + "' AND Gid = " + Gid + ";";
		Connector.doUpdate(delete);
	}
	public void update(UsersGamesDTO row) throws DALException
	{
		String update = "UPDATE UsersGames SET email = '" + row.getEmail() + "', Gid = " + row.getGid() + " WHERE email = " + row.getEmail() + " AND Gid = " + row.getGid() + ";";
		Connector.doUpdate(update);
	}

	public UsersGamesDTO get(String email, int Gid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM UsersGames WHERE email = '" + email + "' AND Gid = " + Gid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UsersGamesDTO(rs.getString("email"), rs.getInt("Gid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<UsersGamesDTO> getList(String email) throws DALException
	{
		try
		{
			List<UsersGamesDTO> results = new ArrayList<UsersGamesDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM UsersGames where email ='"+ email +"';");
			if(!rs.next()) throw new DALException("Missing table: UsersGames");
			do results.add(new UsersGamesDTO(rs.getString("email"), rs.getInt("Gid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
