package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.UsersLangDTO;
import daointerfaces.UsersLangIDAO;
import daointerfaces.DALException;


public class MySQLUsersLangDAO extends UsersLangIDAO
{

	public void create(UsersLangDTO row) throws DALException
	{
		String create = "INSERT INTO UsersLang(email, Langid) VALUES ('" + row.getEmail() + "', " + row.getLangid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int Uid, int Langid) throws DALException
	{
		String delete = "DELETE FROM UsersLang WHERE Uid = " + Uid + " AND Langid = " + Langid + ";";
		Connector.doUpdate(delete);
	}
	public void update(UsersLangDTO row) throws DALException
	{
		String update = "UPDATE UsersLang SET email ='" + row.getEmail() + "', Langid = " + row.getLangid() + " WHERE email = '" + row.getEmail() + "';";
		Connector.doUpdate(update);
	}

	public UsersLangDTO get(String email) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM UsersLang WHERE email = '" + email + " ';");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UsersLangDTO(rs.getString("email"), rs.getInt("Langid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<UsersLangDTO> getList() throws DALException
	{
		try
		{
			List<UsersLangDTO> results = new ArrayList<UsersLangDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM UsersLang;");
			if(!rs.next()) throw new DALException("Missing table: UsersLang");
			do results.add(new UsersLangDTO(rs.getString("Email"), rs.getInt("Langid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
