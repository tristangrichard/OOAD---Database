package daointerfaces;

import java.util.List;

import dto.RoleDTO;

public interface RoleIDAO
{
	public void create(RoleDTO row) throws DALException;
	public void delete(String email) throws DALException;
	public void update(RoleDTO row) throws DALException;
	public RoleDTO get(String email) throws DALException;
	public int countAdmin() throws DALException;
	public List<RoleDTO> getList() throws DALException;
}
