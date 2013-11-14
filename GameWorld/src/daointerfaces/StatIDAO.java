package daointerfaces;

import java.util.List;

import dto.RankDTO;

public interface StatIDAO
{
	public List<RankDTO> rankGames(int maxResults) throws DALException;
	public int countPlayers(int game, int sex, int language, String minDOB, String maxDOB) throws DALException;
	public List<RankDTO> rankGamesM(int maxResults) throws DALException;
	public List<RankDTO> rankGamesG(int maxResults) throws DALException;
}
