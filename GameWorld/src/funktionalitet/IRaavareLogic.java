package funktionalitet;
import java.util.List;

import daointerfaces.DALException;
import dto.RaavareDTO;

public interface IRaavareLogic {
	
	public void createRaavare(int raavareId, String raavareNavn, String leverandoer) throws DALException;
	public void updateRaavare(int raavareId, String raavareNavn, String leverandoer) throws DALException;
	public RaavareDTO getRaavare(int raavareId) throws DALException;
	public List<RaavareDTO> getRaavareList() throws DALException;
}