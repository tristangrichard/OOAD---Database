package daointerfaces;

import java.util.List;

import dto.GamePubDTO;

public interface GamePubIDAO
{
	public void create(GamePubDTO row) throws DALException;
	public void delete(int Pid, int Gid) throws DALException;
	public void update(GamePubDTO row) throws DALException;
	public GamePubDTO get(int Pid, int Gid) throws DALException;
	public List<GamePubDTO> getList(int Pid) throws DALException;
}
