package daointerfaces;

import java.util.List;

import dto.RoleDTO;

abstract public class RoleIDAO
{
	abstract public void create(RoleDTO row) throws DALException;
	abstract public void delete(String email) throws DALException;
	abstract public void update(RoleDTO row) throws DALException;
	abstract public RoleDTO get(String email) throws DALException;
	abstract public List<RoleDTO> getList() throws DALException;
}
