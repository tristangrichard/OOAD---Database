package funktionalitet;

import java.sql.SQLException;
import java.util.List;

import connector.Connector;
import daointerfaces.*;
import daoimpl.MySQLRaavareDAO;
import dto.RaavareDTO;

public class RaavareLogic implements IRaavareLogic {
	private IRaavareDAO o;

	public RaavareLogic() throws DALException {
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o = new MySQLRaavareDAO();
	}

	@Override
	public void createRaavare(int raavareId, String raavareNavn, String leverandoer) throws DALException {
		RaavareDTO newRaavare = new RaavareDTO(raavareId, raavareNavn, leverandoer);
		validateRaavare(newRaavare);
		o.createRaavare(newRaavare);
	}

	@Override
	public void updateRaavare(int raavareId, String raavareNavn, String leverandoer) throws DALException {
		RaavareDTO upRaavare = new RaavareDTO(raavareId, raavareNavn, leverandoer);
		validateRaavare(upRaavare);
		o.updateRaavare(upRaavare);
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		return o.getRaavareList();
	}

	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		return o.getRaavare(raavareId);
	}

	private void validateRaavare(RaavareDTO raavare) throws DALException {
		if (raavare.getRaavareNavn().length() < 2 || raavare.getRaavareNavn().length() > 20) { throw new DALException("RaavareNavn must be between 2 - 20 characters in length"); }
		if (raavare.getLeverandoer().length() < 2 || raavare.getLeverandoer().length() > 20) { throw new DALException("Leverandoer must be between 2 - 20 characters in length"); }
	}
}