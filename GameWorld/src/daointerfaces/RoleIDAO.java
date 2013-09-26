package daointerfaces;

import java.util.List;

import dto.RoleDTO;

abstract public class RoleIDAO
{
	abstract public void create(RoleDTO row) throws DALException;
	abstract public void delete(int Uid) throws DALException;
	abstract public void update(RoleDTO row) throws DALException;
	abstract public RoleDTO get(int Uid) throws DALException;
	abstract public List<RoleDTO> getList() throws DALException;
}
