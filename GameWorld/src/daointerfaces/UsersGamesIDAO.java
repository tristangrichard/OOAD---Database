package daointerfaces;

import java.util.List;

import dto.UsersGamesDTO;

abstract public class UsersGamesIDAO
{
	abstract public void create(UsersGamesDTO row) throws DALException;
	abstract public void delete(String email, int Gid) throws DALException;
	abstract public void update(UsersGamesDTO row) throws DALException;
	abstract public UsersGamesDTO get(String email, int Gid) throws DALException;
	abstract public List<UsersGamesDTO> getList(String email) throws DALException;
}
