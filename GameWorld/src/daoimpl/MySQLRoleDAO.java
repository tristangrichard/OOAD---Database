package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;

import dto.RoleDTO;
import daointerfaces.RoleIDAO;
import daointerfaces.DALException;


public class MySQLRoleDAO extends RoleIDAO
{

	public void create(RoleDTO row) throws DALException
	{
		String create = "INSERT INTO Role(Uid, Role) VALUES (" + row.getUid() + ", '" + row.getRole() + "');";
		Connector.doUpdate(create);
	}
	public void delete(int Uid) throws DALException
	{
		String delete = "DELETE FROM Role WHERE Uid = " + Uid + ";";
		Connector.doUpdate(delete);
	}
	public void update(RoleDTO row) throws DALException
	{
		String update = "UPDATE Role SET Uid = " + row.getUid() + ", Role = '" + row.getRole() + "' WHERE Uid = " + row.getUid() + ";";
		Connector.doUpdate(update);
	}

	public RoleDTO get(int Uid) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Role WHERE Uid = " + Uid + ";");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new RoleDTO(rs.getInt("Uid"), rs.getString("Role"));
		}
		catch(SQLException e){throw new DALException(e);}
	}

	public List<RoleDTO> getList() throws DALException
	{
		try
		{
			List<RoleDTO> results = new ArrayList<RoleDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM Role;");
			if(!rs.next()) throw new DALException("Missing table: Role");
			do results.add(new RoleDTO(rs.getInt("Uid"), rs.getString("Role")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
}
