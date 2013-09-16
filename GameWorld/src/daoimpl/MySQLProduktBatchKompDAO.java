package daoimpl;

import java.sql.*;
import java.util.*;

import daointerfaces.*;
import connector.Connector;
import dto.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements IProduktBatchKompDAO {

	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId);
		try {
			if (!rs.first()) throw new DALException("Produktbatchkomponenten " + pbId + ", " + rbId + " findes ikke");
			return new ProduktBatchKompDTO (rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}


	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES " +
						"(" + produktbatchkomponent.getPbId() + ", " + produktbatchkomponent.getRbId() + ", "
						+ produktbatchkomponent.getTara() + ", " + produktbatchkomponent.getNetto() + ", " + produktbatchkomponent.getOprId() + ")"
				);
	}

	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatchkomponent SET tara = " + produktbatchkomponent.getTara() + ", netto = "
						+ produktbatchkomponent.getNetto() + ", opr_id = " + produktbatchkomponent.getOprId() + " WHERE pb_id = " + produktbatchkomponent.getPbId()
						+ " AND rb_id = " + produktbatchkomponent.getRbId()
				);
	}

}
