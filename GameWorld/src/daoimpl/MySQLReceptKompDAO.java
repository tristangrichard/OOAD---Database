package daoimpl;

import java.sql.*;
import java.util.*;

import connector.Connector;
import daointerfaces.*;
import dto.ReceptKompDTO;

public class MySQLReceptKompDAO implements IReceptKompDAO {

	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId + " AND raavare_id = " + raavareId);
		try {
			if (!rs.first()) throw new DALException("Receptkomponenten " + receptId + ", " + raavareId + " findes ikke");
			return new ReceptKompDTO (rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES " +
						"(" + receptkomponent.getReceptId() + ", " + receptkomponent.getRaavareId() + ", "
						+ receptkomponent.getNomNetto() + ", " + receptkomponent.getTolerance() + ")"
				);
	}

	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		Connector.doUpdate(
				"UPDATE receptkomponent SET nom_netto = " + receptkomponent.getNomNetto() + ", tolerance = "
						+ receptkomponent.getTolerance() + " WHERE recept_id = " + receptkomponent.getReceptId()
						+ " AND raavare_id = " + receptkomponent.getRaavareId()
				);	
	}
}