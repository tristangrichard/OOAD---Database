package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.DeveloperDTO;
import dto.GenreDTO;
import dto.LangDTO;
import dto.PublisherDTO;
/**
 * 
 * @author Tristan Richard, Mathias Jeppesen
 *
 */
public interface IDataLogic {

	public void createGenre(String genre) throws DALException;
	public List<GenreDTO> getListGenre() throws DALException;
	public void createLang(String lang) throws DALException;
	public List<LangDTO> getListLang() throws DALException;
	public void createDev(String Dev, String con) throws DALException;
	public List<DeveloperDTO> getListDev() throws DALException;
	public void createPub(String Pub, String con) throws DALException;
	public List<PublisherDTO> getListPub() throws DALException;
	
}
