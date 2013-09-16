package daoimpl;

import daointerfaces.*;

import java.sql.*;
import java.util.*;

import connector.Connector;
import dto.RaavareDTO;

public class MySQLRaavareDAO implements IRaavareDAO {

	public RaavareDTO getRaavare(int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM raavare WHERE raavare_id = " + raavareId);
		try {
			if (!rs.first()) throw new DALException("Raavaren " + raavareId + " findes ikke");
			return new RaavareDTO (rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM raavare");
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareDTO(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}


	public void createRaavare(RaavareDTO raavare) throws DALException {
		Connector.doUpdate(
				"INSERT INTO raavare(raavare_id, raavare_navn, leverandoer) VALUES " +
						"(" + raavare.getRaavareId() + ", '" + raavare.getRaavareNavn() + "', '" + raavare.getLeverandoer() + "')"
				);
	}

	public void updateRaavare(RaavareDTO raavare) throws DALException {
		Connector.doUpdate(
				"UPDATE raavare SET raavare_navn = '" + raavare.getRaavareNavn() + "', leverandoer =  '" + raavare.getLeverandoer() + 
				"' WHERE raavare_id = " + raavare.getRaavareId()
				);
	}
}