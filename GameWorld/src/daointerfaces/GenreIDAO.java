package daointerfaces;

import java.util.List;

import dto.GenreDTO;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public interface GenreIDAO
{
	public void create(GenreDTO row) throws DALException;
	public void delete(int Genreid) throws DALException;
	public void update(GenreDTO row) throws DALException;
	public GenreDTO getById(int Genreid) throws DALException;
	public GenreDTO getByGenre(String Genre) throws DALException;
	public List<GenreDTO> getList() throws DALException;
}
