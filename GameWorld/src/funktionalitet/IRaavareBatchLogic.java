package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.RaavareBatchDTO;

public interface IRaavareBatchLogic {
	public void createRaavareBatch(int rbId, int raavareId, double maengde) throws DALException;
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException;
	public void updateRaavareBatch(int rbId, int raavareId,double maengde) throws DALException;
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
}