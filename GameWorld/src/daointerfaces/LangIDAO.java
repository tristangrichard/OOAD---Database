package daointerfaces;

import java.util.List;

import dto.LangDTO;

public interface LangIDAO
{
	public void create(LangDTO row) throws DALException;
	public void delete(int langid) throws DALException;
	public void update(LangDTO row) throws DALException;
	public LangDTO getById(int langid) throws DALException;
	public LangDTO getByLang(String lang) throws DALException;
	public List<LangDTO> getList() throws DALException;
}
