package daointerfaces;

import java.util.List;

import dto.UsersDTO;

abstract public class UsersIDAO
{
	abstract public void create(UsersDTO row) throws DALException;
	abstract public void delete(String string) throws DALException;
	abstract public void update(String oldEmail, UsersDTO row) throws DALException;
	abstract public UsersDTO get(String email) throws DALException;
	abstract public List<UsersDTO> getList() throws DALException;
}
