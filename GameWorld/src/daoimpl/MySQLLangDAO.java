package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connector.Connector;
import dto.LangDTO;
import daointerfaces.LangIDAO;
import daointerfaces.DALException;


public class MySQLLangDAO extends LangIDAO
{

	public void create(LangDTO row) throws DALException
	{
		String create = "INSERT INTO Lang(Langid, Lang) VALUES (" + row.getLangid() + ", '" + row.getLang() + "');";
		Connector.doUpdate(create);
	}
	public void delete(int Langid) throws DALException
	{
		String delete = "DELETE FROM Lang WHERE Langid = " + Langid + ";";
		Connector.doUpdate(delete);
	}
	public void update(LangDTO row) throws DALException
	{
		String update = "UPDATE Lang SET Langid = " + row.getLangid() + ", Lang = '" + row.getLang() + "' WHERE Langid = " + row.getLangid() + ";";
		Connector.doUpdate(update);
	}

	public LangDTO get(int Langid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Lang WHERE Langid = " + Langid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new LangDTO(rs.getInt("Langid"), rs.getString("Lang"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<LangDTO> getList() throws DALException
	{
		try
		{
			List<LangDTO> results = new ArrayList<LangDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM Lang ORDER by Lang;");
			if(!rs.next()) throw new DALException("Missing table: Lang");
			do results.add(new LangDTO(rs.getInt("Langid"), rs.getString("Lang")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
