package daointerfaces;

import java.util.List;

import dto.RankDTO;

public interface StatIDAO
{
	public List<RankDTO> rankGames(int maxResults) throws DALException;
	public List<RankDTO> rankGames(int maxResults, int sex, int language, String minDOB, String maxDOB) throws DALException;
	public int countPlayers(int game, int sex, int language, String minDOB, String maxDOB) throws DALException;
	public List<RankDTO> rankGamesMW(int maxResults) throws DALException;
	public List<RankDTO> rankGamesGenreSex(int maxResults) throws DALException;
	public List<RankDTO> rankGamesGenre(int maxResults) throws DALException;
}
