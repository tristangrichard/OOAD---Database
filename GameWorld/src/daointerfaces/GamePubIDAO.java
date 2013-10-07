package daointerfaces;

import java.util.List;

import dto.GamePubDTO;

abstract public class GamePubIDAO
{
	abstract public void create(GamePubDTO row) throws DALException;
	abstract public void delete(int Pid, int Gid) throws DALException;
	abstract public void update(GamePubDTO row) throws DALException;
	abstract public GamePubDTO get(int Pid, int Gid) throws DALException;
	abstract public List<GamePubDTO> getList(int Pid) throws DALException;
}
