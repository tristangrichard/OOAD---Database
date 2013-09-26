package daointerfaces;

import java.util.List;

import dto.UsersGamesDTO;

abstract public class UsersGamesIDAO
{
	abstract public void create(UsersGamesDTO row) throws DALException;
	abstract public void delete(int Uid, int Gid) throws DALException;
	abstract public void update(UsersGamesDTO row) throws DALException;
	abstract public UsersGamesDTO get(int Uid, int Gid) throws DALException;
	abstract public List<UsersGamesDTO> getList() throws DALException;
}
