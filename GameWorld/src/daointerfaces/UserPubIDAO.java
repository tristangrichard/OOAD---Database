package daointerfaces;

import java.util.List;

import dto.UserPubDTO;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public interface UserPubIDAO
{
	public void create(UserPubDTO row) throws DALException;
	public void delete(String email, int Pid) throws DALException;
	public void update(UserPubDTO row) throws DALException;
	public UserPubDTO get(String email) throws DALException;
	public List<UserPubDTO> getList() throws DALException;
}
