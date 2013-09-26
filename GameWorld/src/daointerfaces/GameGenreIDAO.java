package daointerfaces;

import java.util.List;

import dto.GameGenreDTO;

abstract public class GameGenreIDAO
{
	abstract public void create(GameGenreDTO row) throws DALException;
	abstract public void delete(int Gid, int Genreid) throws DALException;
	abstract public void update(GameGenreDTO row) throws DALException;
	abstract public GameGenreDTO get(int Gid, int Genreid) throws DALException;
	abstract public List<GameGenreDTO> getList() throws DALException;
}
