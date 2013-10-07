package daointerfaces;

import java.util.List;

import dto.GameDevDTO;

abstract public class GameDevIDAO
{
	abstract public void create(GameDevDTO row) throws DALException;
	abstract public void delete(int Did, int Gid) throws DALException;
	abstract public void update(GameDevDTO row) throws DALException;
	abstract public GameDevDTO get(int Did, int Gid) throws DALException;
	abstract public List<GameDevDTO> getList() throws DALException;
}
