package daointerfaces;

import java.util.List;

import dto.GameGenreDTO;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public interface GameGenreIDAO
{
	public void create(GameGenreDTO row) throws DALException;
	public void delete(int Gid, int Genreid) throws DALException;
	public void update(GameGenreDTO row) throws DALException;
	public GameGenreDTO get(int Gid, int Genreid) throws DALException;
	public List<GameGenreDTO> getList() throws DALException;
}
