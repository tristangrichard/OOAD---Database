package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.PublisherDTO;
import daointerfaces.PublisherIDAO;
import daointerfaces.DALException;


public class MySQLPublisherDAO extends PublisherIDAO
{

	public void create(PublisherDTO row) throws DALException
	{
		String create = "INSERT INTO Publisher(Pid, Publisher, Founded) VALUES (" + row.getPid() + ", '" + row.getPublisher() + "', '" + row.getFounded() + "');";
		Connector.doUpdate(create);
	}
	public void delete(int Pid) throws DALException
	{
		String delete = "DELETE FROM Publisher WHERE Pid = " + Pid + ";";
		Connector.doUpdate(delete);
	}
	public void update(PublisherDTO row) throws DALException
	{
		String update = "UPDATE Publisher SET Pid = " + row.getPid() + ", Publisher = '" + row.getPublisher() + "', Founded = '" + row.getFounded() + "' WHERE Pid = " + row.getPid() + ";";
		Connector.doUpdate(update);
	}

	public PublisherDTO get(int Pid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Publisher WHERE Pid = " + Pid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new PublisherDTO(rs.getInt("Pid"), rs.getString("Publisher"), rs.getString("Founded"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<PublisherDTO> getList() throws DALException
	{
		try
		{
			List<PublisherDTO> results = new ArrayList<PublisherDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM Publisher;");
			if(!rs.next()) throw new DALException("Missing table: Publisher");
			do results.add(new PublisherDTO(rs.getInt("Pid"), rs.getString("Publisher"), rs.getString("Founded")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
