package daointerfaces;

import java.util.List;

import dto.GameDTO;

abstract public class GameIDAO
{
	abstract public void create(GameDTO row) throws DALException;
	abstract public void delete(int Gid) throws DALException;
	abstract public void update(GameDTO row) throws DALException;
	abstract public GameDTO get(int Gid) throws DALException;
	abstract public List<GameDTO> getList() throws DALException;
}
