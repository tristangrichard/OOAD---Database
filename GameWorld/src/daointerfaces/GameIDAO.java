package daointerfaces;

import java.util.List;

import dto.GameDTO;

public interface GameIDAO
{
	public void create(GameDTO row) throws DALException;
	public void delete(int Gid) throws DALException;
	public void update(GameDTO row) throws DALException;
	public GameDTO getById(int Gid) throws DALException;
	public GameDTO getByTitle(String Title) throws DALException;
	public List<GameDTO> getList() throws DALException;
}
