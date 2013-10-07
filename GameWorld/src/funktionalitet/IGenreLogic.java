package funktionalitet;

import java.util.List;
import daointerfaces.DALException;
import dto.GenreDTO;

public interface IGenreLogic {

	public void createGenre(String genre) throws DALException;
	public List<GenreDTO> getList() throws DALException;
	
}
