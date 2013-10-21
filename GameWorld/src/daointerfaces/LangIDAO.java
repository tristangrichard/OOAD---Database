package daointerfaces;

import java.util.List;

import dto.LangDTO;

abstract public class LangIDAO
{
	abstract public void create(LangDTO row) throws DALException;
	abstract public void delete(int langid) throws DALException;
	abstract public void update(LangDTO row) throws DALException;
	abstract public LangDTO getById(int langid) throws DALException;
	abstract public LangDTO getByLang(String lang) throws DALException;
	abstract public List<LangDTO> getList() throws DALException;
}
