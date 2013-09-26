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
		String create = "INSERT INTO UsersLang(Uid, Langid) VALUES (" + row.getUid() + ", " + row.getLangid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int Uid, int Langid) throws DALException
	{
		String delete = "DELETE FROM UsersLang WHERE Uid = " + Uid + " AND Langid = " + Langid + ";";
		Connector.doUpdate(delete);
	}
	public void update(UsersLangDTO row) throws DALException
	{
		String update = "UPDATE UsersLang SET Uid = " + row.getUid() + ", Langid = " + row.getLangid() + " WHERE Uid = " + row.getUid() + " AND Langid = " + row.getLangid() + ";";
		Connector.doUpdate(update);
	}

	public UsersLangDTO get(int Uid, int Langid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM UsersLang WHERE Uid = " + Uid + " AND Langid = " + Langid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new UsersLangDTO(rs.getInt("Uid"), rs.getInt("Langid"));
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
			do results.add(new UsersLangDTO(rs.getInt("Uid"), rs.getInt("Langid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
