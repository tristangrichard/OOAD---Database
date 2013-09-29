package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.UsersDTO;
import daointerfaces.UsersIDAO;
import daointerfaces.DALException;


public class MySQLUsersDAO extends UsersIDAO
{

	public int create(UsersDTO row) throws DALException
	{
		String create = "INSERT INTO Users(Uid, Fname, Lname, DOB, pass, email, sex) VALUES (" + row.getUid() + ", '" + row.getFname() + "', '" + row.getLname() + "', '" + row.getDob() + "', '" + row.getPass() + "', '" + row.getEmail() + "', " + row.getSex() +");";
		Connector.doUpdate(create);
		int Uid = -1;
		try {
			ResultSet getUid = Connector.doQuery("SELECT Uid FROM Users WHERE email = '" + row.getEmail() + "';");
			if(!getUid.next()) throw new DALException("Missing entry.");
			Uid = getUid.getInt("Uid");
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return Uid;
	}
	public void delete(int Uid) throws DALException
	{
		String delete = "DELETE FROM Users WHERE Uid = " + Uid + ";";
		Connector.doUpdate(delete);
	}
	public void update(UsersDTO row) throws DALException
	{
		String update = "UPDATE Users SET Uid = " + row.getUid() + ", Fname = '" + row.getFname() + "', Lname = '" + row.getLname() + "', DOB = '" + row.getDob() + "', pass = '" + row.getPass() + "', email = '" + row.getEmail() + "', sex = " + row.getSex() + " WHERE Uid = " + row.getUid() + ";";
		Connector.doUpdate(update);
	}

	public UsersDTO get(int Uid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Users WHERE Uid = " + Uid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UsersDTO(rs.getInt("Uid"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("DOB"), rs.getString("pass"), rs.getString("email"), rs.getBoolean("sex"));
		}
		catch(SQLException e){throw new DALException(e);}
	}
	public UsersDTO getByEmail(String email) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Users WHERE email = '" + email + "';");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UsersDTO(rs.getInt("Uid"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("DOB"), rs.getString("pass"), rs.getString("email"), rs.getBoolean("sex"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<UsersDTO> getList() throws DALException
	{
		try
		{
			List<UsersDTO> results = new ArrayList<UsersDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM Users;");
			if(!rs.next()) throw new DALException("Missing table: Users");
			do results.add(new UsersDTO(rs.getInt("Uid"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("DOB"), rs.getString("pass"), rs.getString("email"), rs.getBoolean("sex")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
