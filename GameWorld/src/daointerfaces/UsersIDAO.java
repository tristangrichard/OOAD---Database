package daointerfaces;

import java.util.List;

import dto.UsersDTO;

public interface UsersIDAO
{
	public void create(UsersDTO row) throws DALException;
	public void delete(String string) throws DALException;
	public void update(String oldEmail, UsersDTO row) throws DALException;
	public UsersDTO get(String email) throws DALException;
	public List<UsersDTO> getList() throws DALException;
}
