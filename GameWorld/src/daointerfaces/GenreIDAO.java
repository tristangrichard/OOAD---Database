package daointerfaces;

import java.util.List;

import dto.GenreDTO;

abstract public class GenreIDAO
{
	abstract public void create(GenreDTO row) throws DALException;
	abstract public void delete(int Genreid) throws DALException;
	abstract public void update(GenreDTO row) throws DALException;
	abstract public GenreDTO getById(int Genreid) throws DALException;
	abstract public GenreDTO getByGenre(String Genre) throws DALException;
	abstract public List<GenreDTO> getList() throws DALException;
}
