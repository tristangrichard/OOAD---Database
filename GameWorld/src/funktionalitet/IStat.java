package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.RankDTO;
/**
 * 
 * @author Tristan Richard, Mikkel Barfred, Rasmus Hansen
 *
 */
public interface IStat {

	public int countPLayers(String game, String sex, String language, String minDOB, String maxDOB) throws DALException;
	public List<RankDTO> getMostOwnedGame(int max) throws DALException;
	public List<RankDTO> getMostOwnedGame(String max, String sex, String country, String minDOB, String maxDOB) throws DALException;
	public List<RankDTO> getMostOwnedGameMW(int max) throws DALException;
	public List<RankDTO> getMostOwnedGameGenre(int max) throws DALException;
	public List<RankDTO> getMostOwnedGameGenreSex(int max) throws DALException;
}
