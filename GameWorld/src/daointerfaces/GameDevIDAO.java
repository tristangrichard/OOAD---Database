package daointerfaces;

import java.util.List;

import dto.GameDevDTO;

public interface GameDevIDAO
{
	public void create(GameDevDTO row) throws DALException;
	public void delete(int Did, int Gid) throws DALException;
	public void update(GameDevDTO row) throws DALException;
	public GameDevDTO get(int Did, int Gid) throws DALException;
	public List<GameDevDTO> getList() throws DALException;
}
