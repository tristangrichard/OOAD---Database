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

	public void create(UsersDTO row) throws DALException
	{
		String create = "INSERT INTO Users(Fname, Lname, DOB, pass, email, sex) VALUES ('" + row.getFname() + "', '" + row.getLname() + "', '" + row.getDob() + "', '" + row.getPass() + "', '" + row.getEmail() + "', " + row.getSex() +");";
		Connector.doUpdate(create);
	}
	public void delete(String email) throws DALException
	{
		String delete = "DELETE FROM Users WHERE email = '" + email + "';";
		Connector.doUpdate(delete);
	}
	public void update(String oldEmail, UsersDTO row) throws DALException
	{
		String update = "UPDATE Users SET Fname = '" + row.getFname() + "', Lname = '" + row.getLname() + "', DOB = '" + row.getDob() + "', pass = '" + row.getPass() + "', email = '" + row.getEmail() + "', sex = " + row.getSex() + " WHERE email = '" + oldEmail + "';";
		Connector.doUpdate(update);
	}
	public UsersDTO get(String email) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT *,DATE_FORMAT(DOB,'%d/%m/%Y') as DOB1 FROM Users WHERE email = '" + email + "';");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UsersDTO(rs.getString("Fname"), rs.getString("Lname"), rs.getString("DOB1"), rs.getString("pass"), rs.getString("email"), rs.getBoolean("sex"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<UsersDTO> getList() throws DALException
	{
		try
		{
			List<UsersDTO> results = new ArrayList<UsersDTO>();
			ResultSet rs = Connector.doQuery("SELECT *,DATE_FORMAT(DOB,'%d/%m/%Y') as DOB1 FROM Users;");
			if(!rs.next()) throw new DALException("Missing table: Users");
			do results.add(new UsersDTO(rs.getString("Fname"), rs.getString("Lname"), rs.getString("DOB1"), rs.getString("pass"), rs.getString("email"), rs.getBoolean("sex")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
