package funktionalitet;

import java.sql.SQLException;
import java.util.List;

import connector.Connector;
import daoimpl.*;
import daointerfaces.*;
import dto.*;

public class ReceptLogic implements IReceptLogic{
	private IReceptDAO o;
	private IReceptKompDAO ork;

	public ReceptLogic() throws DALException {
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
		o = new MySQLReceptDAO();
		ork = new MySQLReceptKompDAO();
	}

	@Override
	public void createRecept(int receptId, String receptNavn) throws DALException {
		ReceptDTO newRecept = new ReceptDTO(receptId, receptNavn);
		validateRecept(newRecept);
		o.createRecept(newRecept);
	}

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		return o.getRecept(receptId);
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		return o.getReceptList();
	}

	@Override
	public void updateRecept(int receptId, String receptNavn) throws DALException {
		ReceptDTO upRecept = new ReceptDTO(receptId, receptNavn);
		validateRecept(upRecept);
		o.updateRecept(upRecept);
	}

	@Override
	public void createReceptKomp(int receptId, int raavareId, double nomNetto, double tolerance) throws DALException {
		ReceptKompDTO newReceptKomp = new ReceptKompDTO(receptId, raavareId, nomNetto, tolerance);
		validateReceptKomp(newReceptKomp);
		ork.createReceptKomp(newReceptKomp);
	}

	@Override
	public void updateReceptKomp(int receptId, int raavareId, double nomNetto, double tolerance) throws DALException {
		ReceptKompDTO upReceptKomp = new ReceptKompDTO(receptId, raavareId, nomNetto, tolerance);
		validateReceptKomp(upReceptKomp);
		ork.updateReceptKomp(upReceptKomp);
	}

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		return ork.getReceptKomp(receptId, raavareId);
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		return ork.getReceptKompList(receptId);
	}

	private void validateRecept(ReceptDTO recept) throws DALException {
		String receptNavn = recept.getReceptNavn();
		if (receptNavn.length() < 2 || receptNavn.length() > 20) { throw new DALException("ReceptNavn must be between 2 - 20 characters in length"); }
	}
	private void validateReceptKomp(ReceptKompDTO rk) throws DALException {
		double nomNetto = rk.getNomNetto();
		double tolerance = rk.getTolerance();
		if (nomNetto < 0.05 || nomNetto > 20) throw new DALException("nomNetto must be between 0.05 - 20.0 kg");
		if (tolerance < 0.1 || tolerance > 10) throw new DALException("tolerance must be between 0.1 - 10.0 %");
	}
}