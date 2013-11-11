package daointerfaces;

import java.util.List;

import dto.DeveloperDTO;

public interface DeveloperIDAO
{
	public void create(DeveloperDTO row) throws DALException;
	public void delete(int dId) throws DALException;
	public void update(DeveloperDTO row) throws DALException;
	public DeveloperDTO getById(int dId) throws DALException;
	public DeveloperDTO getByDev(String dev) throws DALException;
	public List<DeveloperDTO> getList() throws DALException;
}
