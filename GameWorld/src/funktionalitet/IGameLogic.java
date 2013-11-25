package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.GameDTO;
/**
 * 
 * @author Tristan Richard
 *
 */
public interface IGameLogic {

	public void createGame(String title, String release, String url, String[] genre, String[] language, String[] os, String[] dev, String[] pub) throws DALException;
	public void editGame(String title, String release, String url, String[] genre, String[] language, String[] os) throws DALException;
	public List<GameDTO> listGames(String email) throws DALException;

}
