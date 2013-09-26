package daointerfaces;

import java.util.List;

import dto.GameLangDTO;

abstract public class GameLangIDAO
{
	abstract public void create(GameLangDTO row) throws DALException;
	abstract public void delete(int Gid, int Langid) throws DALException;
	abstract public void update(GameLangDTO row) throws DALException;
	abstract public GameLangDTO get(int Gid, int Langid) throws DALException;
	abstract public List<GameLangDTO> getList() throws DALException;
}
