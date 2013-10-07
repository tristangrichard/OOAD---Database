package daointerfaces;

import java.util.List;

import dto.UserPubDTO;

abstract public class UserPubIDAO
{
	abstract public void create(UserPubDTO row) throws DALException;
	abstract public void delete(String email, int Pid) throws DALException;
	abstract public void update(UserPubDTO row) throws DALException;
	abstract public UserPubDTO get(String email) throws DALException;
	abstract public List<UserPubDTO> getList() throws DALException;
}
