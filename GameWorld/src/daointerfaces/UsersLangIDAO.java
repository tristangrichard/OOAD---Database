package daointerfaces;

import java.util.List;

import dto.UsersLangDTO;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public interface UsersLangIDAO
{
	public void create(UsersLangDTO row) throws DALException;
	public void delete(int Uid, int Langid) throws DALException;
	public void update(UsersLangDTO row) throws DALException;
	public UsersLangDTO get(String email) throws DALException;
	public List<UsersLangDTO> getList() throws DALException;
}
