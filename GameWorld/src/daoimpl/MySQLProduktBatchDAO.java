package daoimpl;

import java.sql.*;
import java.util.*;
import java.util.Date;

import connector.Connector;
import daointerfaces.*;
import dto.ProduktBatchDTO;

public class MySQLProduktBatchDAO implements IProduktBatchDAO {

	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = " + pbId);
		try {
			if (!rs.first()) throw new DALException("Produktbatchen " + pbId + " findes ikke");
			Date start = new Date(rs.getLong(4));
			Date slut = new Date(rs.getLong(5));			
			return new ProduktBatchDTO (rs.getInt(1), rs.getInt(2), rs.getInt(3), start, slut);
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch");
		try
		{
			while (rs.next()) 
			{
				Date start = new Date(rs.getLong(4));
				Date slut = new Date(rs.getLong(5));
				list.add(new ProduktBatchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), start, slut));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public void createProduktBatch(ProduktBatchDTO produktbatch)
			throws DALException {
		Long start = null;
		Long slut = null;
		if (produktbatch.getStart() != null) {
			start = produktbatch.getStart().getTime();
		}
		if (produktbatch.getSlut() != null) { 
			slut = produktbatch.getSlut().getTime(); 
		}
		Connector.doUpdate(
				"INSERT INTO produktbatch(pb_id, status, recept_id, start, slut) VALUES " +
						"(" + produktbatch.getPbId() + ", '" + produktbatch.getStatus() + "', '" + produktbatch.getReceptId() + "', " + start + ", " + slut +  ")"
				);
	}

	public void updateProduktBatch(ProduktBatchDTO produktbatch)
			throws DALException {
		Long start = null;
		Long slut = null;
		if (produktbatch.getStart() != null) {
			start = produktbatch.getStart().getTime();
		}
		if (produktbatch.getSlut() != null) { 
			slut = produktbatch.getSlut().getTime(); 
		}
		Connector.doUpdate(
				"UPDATE produktbatch SET status = '" + produktbatch.getStatus() + "', recept_id =  " + produktbatch.getReceptId() + ", start = " + start 
				+ ", slut = " + slut + " WHERE pb_id = " + produktbatch.getPbId());
	}
}