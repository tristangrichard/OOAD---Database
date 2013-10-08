package daointerfaces;

import java.util.List;

import dto.UsersLangDTO;

abstract public class UsersLangIDAO
{
	abstract public void create(UsersLangDTO row) throws DALException;
	abstract public void delete(int Uid, int Langid) throws DALException;
	abstract public void update(UsersLangDTO row) throws DALException;
	abstract public UsersLangDTO get(String email) throws DALException;
	abstract public List<UsersLangDTO> getList() throws DALException;
}
