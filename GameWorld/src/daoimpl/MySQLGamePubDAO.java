package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.GamePubDTO;
import daointerfaces.GamePubIDAO;
import daointerfaces.DALException;


public class MySQLGamePubDAO implements GamePubIDAO
{

	public void create(GamePubDTO row) throws DALException
	{
		String create = "INSERT INTO GamePub(Pid, Gid) VALUES (" + row.getPid() + ", " + row.getGid() + ");";
		Connector.doUpdate(create);
	}
	public void delete(int Pid, int Gid) throws DALException
	{
		String delete = "DELETE FROM GamePub WHERE Pid = " + Pid + " AND Gid = " + Gid + ";";
		Connector.doUpdate(delete);
	}
	public void update(GamePubDTO row) throws DALException
	{
		String update = "UPDATE GamePub SET Pid = " + row.getPid() + ", Gid = " + row.getGid() + " WHERE Pid = " + row.getPid() + " AND Gid = " + row.getGid() + ";";
		Connector.doUpdate(update);
	}

	public GamePubDTO get(int Pid, int Gid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM GamePub WHERE Pid = " + Pid + " AND Gid = " + Gid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new GamePubDTO(rs.getInt("Pid"), rs.getInt("Gid"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<GamePubDTO> getList(int Pid) throws DALException
	{
		try
		{
			List<GamePubDTO> results = new ArrayList<GamePubDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM GamePub Where Pid = "+ Pid + ";");
			if(!rs.next()) throw new DALException("Missing table: GamePub");
			do results.add(new GamePubDTO(rs.getInt("Pid"), rs.getInt("Gid")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
