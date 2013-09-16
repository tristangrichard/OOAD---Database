package daoimpl;

import java.sql.*;
import java.util.*;

import connector.Connector;
import daointerfaces.*;
import dto.ReceptDTO;

public class MySQLReceptDAO implements IReceptDAO {

	public ReceptDTO getRecept(int receptId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptId);
		try {
			if (!rs.first()) throw new DALException("Recepten " + receptId + " findes ikke");
			return new ReceptDTO (rs.getInt(1), rs.getString(2));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM recept");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public void createRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate(
				"INSERT INTO recept(recept_id, recept_navn) VALUES " +
						"(" + recept.getReceptId() + ", '" + recept.getReceptNavn() + "')"
				);
	}

	public void updateRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate(
				"UPDATE recept SET recept_navn = '" + recept.getReceptNavn() + 
				"' WHERE recept_id = " + recept.getReceptId()
				);
	}
}