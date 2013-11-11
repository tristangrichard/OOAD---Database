package daointerfaces;

import java.util.List;

import dto.RankDTO;

public interface StatIDAO
{
	public List<RankDTO> rankGames(int maxResults) throws DALException;
	public int countPlayers(int game, int sex, int language, String minDOB, String maxDOB) throws DALException;
}
