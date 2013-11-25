package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.OSDTO;
import daointerfaces.OSIDAO;
import daointerfaces.DALException;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class MySQLOSDAO implements OSIDAO
{

	public void create(OSDTO row) throws DALException
	{
		String create = "INSERT INTO OS(OSid, OS) VALUES (" + row.getOsid() + ", '" + row.getOs() + "');";
		Connector.doUpdate(create);
	}
	public void delete(int OSid) throws DALException
	{
		String delete = "DELETE FROM OS WHERE OSid = " + OSid + ";";
		Connector.doUpdate(delete);
	}
	public void update(OSDTO row) throws DALException
	{
		String update = "UPDATE OS SET OSid = " + row.getOsid() + ", OS = '" + row.getOs() + "' WHERE OSid = " + row.getOsid() + ";";
		Connector.doUpdate(update);
	}

	public OSDTO get(int OSid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM OS WHERE OSid = " + OSid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new OSDTO(rs.getInt("OSid"), rs.getString("OS"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<OSDTO> getList() throws DALException
	{
		try
		{
			List<OSDTO> results = new ArrayList<OSDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM OS ORDER BY OS;");
			if(!rs.next()) throw new DALException("Missing table: OS");
			do results.add(new OSDTO(rs.getInt("OSid"), rs.getString("OS")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
