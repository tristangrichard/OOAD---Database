package daointerfaces;

import java.util.List;

import dto.GameLangDTO;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public interface GameLangIDAO
{
	public void create(GameLangDTO row) throws DALException;
	public void delete(int Gid, int Langid) throws DALException;
	public void update(GameLangDTO row) throws DALException;
	public GameLangDTO get(int Gid, int Langid) throws DALException;
	public List<GameLangDTO> getList() throws DALException;
}
