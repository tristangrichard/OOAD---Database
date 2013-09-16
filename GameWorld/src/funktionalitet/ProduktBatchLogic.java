package funktionalitet;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import connector.Connector;
import daoimpl.*;
import daointerfaces.*;
import dto.*;

public class ProduktBatchLogic implements IProduktBatchLogic {
	private IProduktBatchDAO o;
	private IProduktBatchKompDAO pb;

	public ProduktBatchLogic() throws DALException {
		try{
			new Connector();
		}catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o = new MySQLProduktBatchDAO();
		pb = new MySQLProduktBatchKompDAO();
	}

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		return o.getProduktBatch(pbId);
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		return o.getProduktBatchList();
	}

	@Override
	public void createProduktBatch(int pbId, int status, int receptId, Date start) throws DALException {
		ProduktBatchDTO newPB = new ProduktBatchDTO(pbId, status, receptId, start, null);
		validateProduktBatch(newPB);
		o.createProduktBatch(newPB);
	}

	@Override
	public void updateProduktBatch(int pbId, int status, int receptId, Date start, Date slut) throws DALException {
		ProduktBatchDTO updPB = new ProduktBatchDTO(pbId, status, receptId, start, slut);
		validateProduktBatch(updPB);
		o.updateProduktBatch(updPB);
	}

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		return pb.getProduktBatchKomp(pbId, rbId);
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		return pb.getProduktBatchKompList(pbId);
	}

	@Override
	public void createProduktBatchKomp(int pbId, int rbId, double tara, double netto, int oprId) throws DALException {
		ProduktBatchKompDTO newPBK = new ProduktBatchKompDTO(pbId, rbId, tara, netto, oprId);
		pb.createProduktBatchKomp(newPBK);
	}

	@Override
	public void updateProduktBatchKomp(int pbId, int rbId, double tara, double netto, int oprId) throws DALException {
		ProduktBatchKompDTO upPBK = new ProduktBatchKompDTO(pbId, rbId, tara, netto, oprId);
		pb.updateProduktBatchKomp(upPBK);
	}

	private void validateProduktBatch(ProduktBatchDTO pb) throws DALException { 
		if (pb.getStatus() < 0 || pb.getStatus() > 2) { throw new DALException("Status needs to be between 0 - 2"); }
	}
}