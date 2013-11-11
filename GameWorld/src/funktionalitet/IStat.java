package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.RankDTO;

public interface IStat {

	public int countPLayers(String game, String sex, String language, String minDOB, String maxDOB) throws DALException;
	public List<RankDTO> getMostOwnedGame(int max) throws DALException;
}
