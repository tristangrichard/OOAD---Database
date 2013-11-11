package daointerfaces;

import java.util.List;

import dto.GameOSDTO;

public interface GameOSIDAO
{
	public void create(GameOSDTO row) throws DALException;
	public void delete(int OSid, int Gid) throws DALException;
	public void update(GameOSDTO row) throws DALException;
	public GameOSDTO get(int OSid, int Gid) throws DALException;
	public List<GameOSDTO> getList() throws DALException;
}
