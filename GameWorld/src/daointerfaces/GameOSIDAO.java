package daointerfaces;

import java.util.List;

import dto.GameOSDTO;

abstract public class GameOSIDAO
{
	abstract public void create(GameOSDTO row) throws DALException;
	abstract public void delete(int OSid, int Gid) throws DALException;
	abstract public void update(GameOSDTO row) throws DALException;
	abstract public GameOSDTO get(int OSid, int Gid) throws DALException;
	abstract public List<GameOSDTO> getList() throws DALException;
}
