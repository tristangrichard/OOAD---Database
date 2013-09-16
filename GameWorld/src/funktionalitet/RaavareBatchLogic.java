package funktionalitet;

import java.sql.SQLException;

import daointerfaces.*;

import java.util.List;

import connector.Connector;
import daoimpl.MySQLRaavareBatchDAO;
import dto.RaavareBatchDTO;

public class RaavareBatchLogic implements IRaavareBatchLogic {
	private IRaavareBatchDAO o;

	public RaavareBatchLogic() throws DALException {
		try {
			new Connector(); 
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new DALException("Connection lost!");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o = new MySQLRaavareBatchDAO();
	}

	@Override
	public void createRaavareBatch(int rbId, int raavareId, double maengde) throws DALException {
		RaavareBatchDTO r = new RaavareBatchDTO(rbId, raavareId, maengde);
		o.createRaavareBatch(r);
	}

	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		return o.getRaavareBatch(rbId);
	}

	@Override
	public void updateRaavareBatch(int rbId, int raavareId, double maengde) throws DALException {
		RaavareBatchDTO r = new RaavareBatchDTO(rbId, raavareId, maengde);
		o.updateRaavareBatch(r);

	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		return o.getRaavareBatchList();
	}
}