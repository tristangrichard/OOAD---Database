package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.*;

public interface IReceptLogic {
	public void createRecept(int receptId, String receptNavn) throws DALException;
	public void updateRecept(int receptId, String receptNavn) throws DALException;
	public ReceptDTO getRecept(int receptId) throws DALException;
	public List<ReceptDTO> getReceptList() throws DALException;
	public void createReceptKomp(int receptId, int raavareId, double nomNetto, double tolerance) throws DALException;
	public void updateReceptKomp(int receptId, int raavareId, double nomNetto, double tolerance) throws DALException;
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException;
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException;
}