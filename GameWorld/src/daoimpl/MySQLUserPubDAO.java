package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.UserPubDTO;
import daointerfaces.UserPubIDAO;
import daointerfaces.DALException;


public class MySQLUserPubDAO extends UserPubIDAO
{

	public void create(UserPubDTO row) throws DALException
	{
		String create = "INSERT INTO UserPub(email, Pid) VALUES ('" + row.getEmail() + "', " + row.getPid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(String email, int Pid) throws DALException
	{
		String delete = "DELETE FROM UserPub WHERE email = '" + email + "' AND Pid = " + Pid + ";";
		Connector.doUpdate(delete);
	}
	public void update(UserPubDTO row) throws DALException
	{
		String update = "UPDATE UserPub SET email = '" + row.getEmail() + "', Pid = " + row.getPid() + " WHERE email = '" + row.getEmail() + "';";
		Connector.doUpdate(update);
	}

	public UserPubDTO get(String email) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM UserPub WHERE email = '" + email + "';");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UserPubDTO(rs.getString("email"), rs.getInt("Pid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<UserPubDTO> getList() throws DALException
	{
		try
		{
			List<UserPubDTO> results = new ArrayList<UserPubDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM UserPub;");
			if(!rs.next()) throw new DALException("Missing table: UserPub");
			do results.add(new UserPubDTO(rs.getString("email"), rs.getInt("Pid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
