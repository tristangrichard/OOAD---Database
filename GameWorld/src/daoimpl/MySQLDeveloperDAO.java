package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.DeveloperDTO;
import daointerfaces.DeveloperIDAO;
import daointerfaces.DALException;


public class MySQLDeveloperDAO extends DeveloperIDAO
{

	public void create(DeveloperDTO row) throws DALException
	{
		String create = "INSERT INTO Developer(Did, Developer, Founded) VALUES (" + row.getDid() + ", '" + row.getDeveloper() + "', '" + row.getFounded() + "');";
		Connector.doUpdate(create);
	}
	public void delete(int Did) throws DALException
	{
		String delete = "DELETE FROM Developer WHERE Did = " + Did + ";";
		Connector.doUpdate(delete);
	}
	public void update(DeveloperDTO row) throws DALException
	{
		String update = "UPDATE Developer SET Did = " + row.getDid() + ", Developer = '" + row.getDeveloper() + "', Founded = '" + row.getFounded() + "' WHERE Did = " + row.getDid() + ";";
		Connector.doUpdate(update);
	}

	public DeveloperDTO get(int Did) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Developer WHERE Did = " + Did + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new DeveloperDTO(rs.getInt("Did"), rs.getString("Developer"), rs.getString("Founded"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<DeveloperDTO> getList() throws DALException
	{
		try
		{
			List<DeveloperDTO> results = new ArrayList<DeveloperDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM Developer;");
			if(!rs.next()) throw new DALException("Missing table: Developer");
			do results.add(new DeveloperDTO(rs.getInt("Did"), rs.getString("Developer"), rs.getString("Founded")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
