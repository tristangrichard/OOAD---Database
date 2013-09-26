package daointerfaces;

import java.util.List;

import dto.GenreDTO;

abstract public class GenreIDAO
{
	abstract public void create(GenreDTO row) throws DALException;
	abstract public void delete(int Genreid) throws DALException;
	abstract public void update(GenreDTO row) throws DALException;
	abstract public GenreDTO get(int Genreid) throws DALException;
	abstract public List<GenreDTO> getList() throws DALException;
}
