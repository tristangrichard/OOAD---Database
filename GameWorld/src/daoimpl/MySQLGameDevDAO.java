package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.GameDevDTO;
import daointerfaces.GameDevIDAO;
import daointerfaces.DALException;


public class MySQLGameDevDAO implements GameDevIDAO
{

	public void create(GameDevDTO row) throws DALException
	{
		String create = "INSERT INTO GameDev(Did, Gid) VALUES (" + row.getDid() + ", " + row.getGid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int Did, int Gid) throws DALException
	{
		String delete = "DELETE FROM GameDev WHERE Did = " + Did + " AND Gid = " + Gid + ";";
		Connector.doUpdate(delete);
	}
	public void update(GameDevDTO row) throws DALException
	{
		String update = "UPDATE GameDev SET Did = " + row.getDid() + ", Gid = " + row.getGid() + " WHERE Did = " + row.getDid() + " AND Gid = " + row.getGid() + ";";
		Connector.doUpdate(update);
	}

	public GameDevDTO get(int Did, int Gid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM GameDev WHERE Did = " + Did + " AND Gid = " + Gid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new GameDevDTO(rs.getInt("Did"), rs.getInt("Gid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<GameDevDTO> getList() throws DALException
	{
		try
		{
			List<GameDevDTO> results = new ArrayList<GameDevDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM GameDev;");
			if(!rs.next()) throw new DALException("Missing table: GameDev");
			do results.add(new GameDevDTO(rs.getInt("Did"), rs.getInt("Gid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
