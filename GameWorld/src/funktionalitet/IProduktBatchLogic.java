package funktionalitet;

import java.util.Date;
import java.util.List;

import daointerfaces.DALException;
import dto.*;

public interface IProduktBatchLogic {
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	public void createProduktBatch(int pbId, int status, int receptId, Date start) throws DALException;
	public void updateProduktBatch(int pbId, int status, int receptId, Date start, Date slut) throws DALException;
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException;
	public void createProduktBatchKomp(int pbId, int rbId, double tara, double netto, int oprId) throws DALException; // NOT USED
	public void updateProduktBatchKomp(int pbId, int rbId, double tara, double netto, int oprId) throws DALException;	
}