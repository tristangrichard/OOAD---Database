package daointerfaces;

import java.util.List;

import dto.UsersGamesDTO;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public interface UsersGamesIDAO
{
	public void create(UsersGamesDTO row) throws DALException;
	public void delete(String email, int Gid) throws DALException;
	public void update(UsersGamesDTO row) throws DALException;
	public UsersGamesDTO get(String email, int Gid) throws DALException;
	public List<UsersGamesDTO> getListbyEmail(String email) throws DALException;
	public List<UsersGamesDTO> getListbyID(int id) throws DALException;
}
