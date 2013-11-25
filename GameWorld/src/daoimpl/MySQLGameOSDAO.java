package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.GameOSDTO;
import daointerfaces.GameOSIDAO;
import daointerfaces.DALException;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class MySQLGameOSDAO implements GameOSIDAO
{

	public void create(GameOSDTO row) throws DALException
	{
		String create = "INSERT INTO GameOS(OSid, Gid) VALUES (" + row.getOsid() + ", " + row.getGid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int OSid, int Gid) throws DALException
	{
		String delete = "DELETE FROM GameOS WHERE OSid = " + OSid + " AND Gid = " + Gid + ";";
		Connector.doUpdate(delete);
	}
	public void update(GameOSDTO row) throws DALException
	{
		String update = "UPDATE GameOS SET OSid = " + row.getOsid() + ", Gid = " + row.getGid() + " WHERE OSid = " + row.getOsid() + " AND Gid = " + row.getGid() + ";";
		Connector.doUpdate(update);
	}

	public GameOSDTO get(int OSid, int Gid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM GameOS WHERE OSid = " + OSid + " AND Gid = " + Gid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new GameOSDTO(rs.getInt("OSid"), rs.getInt("Gid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<GameOSDTO> getList() throws DALException
	{
		try
		{
			List<GameOSDTO> results = new ArrayList<GameOSDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM GameOS;");
			if(!rs.next()) throw new DALException("Missing table: GameOS");
			do results.add(new GameOSDTO(rs.getInt("OSid"), rs.getInt("Gid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
