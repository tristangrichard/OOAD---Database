package daointerfaces;

import java.util.List;

import dto.DeveloperDTO;

abstract public class DeveloperIDAO
{
	abstract public void create(DeveloperDTO row) throws DALException;
	abstract public void delete(int dId) throws DALException;
	abstract public void update(DeveloperDTO row) throws DALException;
	abstract public DeveloperDTO getById(int dId) throws DALException;
	abstract public DeveloperDTO getByDev(String dev) throws DALException;
	abstract public List<DeveloperDTO> getList() throws DALException;
}
