package daoimpl;

import java.sql.*;
import java.util.*;

import connector.Connector;
import daointerfaces.*;
import dto.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements IRaavareBatchDAO{

	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch WHERE rb_id = " + rbId);
		try {
			if (!rs.first()) throw new DALException("Raavarebatchen " + rbId + " findes ikke");
			return new RaavareBatchDTO (rs.getInt(1), rs.getInt(2), rs.getDouble(3));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch WHERE raavare_id = " + raavareId);
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		Connector.doUpdate(
				"INSERT INTO raavarebatch(rb_id, raavare_id, maengde) VALUES " +
						"(" + raavarebatch.getRbId() + ", '" + raavarebatch.getRaavareId() + "', '" + raavarebatch.getMaengde() + "')"
				);
	}

	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		Connector.doUpdate(
				"UPDATE raavarebatch SET raavare_id = " + raavarebatch.getRaavareId() + ", maengde =  " + raavarebatch.getMaengde() + 
				" WHERE rb_id = " + raavarebatch.getRbId()
				);
	}
}