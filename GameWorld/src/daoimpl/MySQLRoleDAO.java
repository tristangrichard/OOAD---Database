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
		String create = "INSERT INTO Role(email, Role) VALUES ('" + row.getEmail() + "', '" + row.getRole() + "');";
		Connector.doUpdate(create);
	}
	public void delete(String email) throws DALException
	{
		String delete = "DELETE FROM Role WHERE email = '" + email + "';";
		Connector.doUpdate(delete);
	}
	public void update(RoleDTO row) throws DALException
	{
		String update = "UPDATE Role SET email = '" + row.getEmail() + "', Role = '" + row.getRole() + "' WHERE email = '" + row.getEmail() + "';";
		Connector.doUpdate(update);
	}

	public RoleDTO get(String email) throws DALException
	{
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM Role WHERE email = '" + email + "';");
			if(!rs.next()) throw new DALException("Missing entry.");
			return new RoleDTO(rs.getString("email"), rs.getString("Role"));
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
			do results.add(new RoleDTO(rs.getString("email"), rs.getString("Role")));
			while(rs.next());
			return results;
		}
		catch(SQLException e){throw new DALException(e);}
	}
	@Override
	public int countAdmin() throws DALException {
		ResultSet rs = Connector.doQuery("SELECT count(*) AS admin FROM Role WHERE role = 'administrator';");
		int i = 0;
		try {
			while (rs.next()) {
				i++;
			}
			return i;
		} catch (SQLException e){throw new DALException(e);}
	}
}
