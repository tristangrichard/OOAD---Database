package daointerfaces;

import java.util.List;

import dto.OSDTO;

abstract public class OSIDAO
{
	abstract public void create(OSDTO row) throws DALException;
	abstract public void delete(int OSid) throws DALException;
	abstract public void update(OSDTO row) throws DALException;
	abstract public OSDTO get(int OSid) throws DALException;
	abstract public List<OSDTO> getList() throws DALException;
}
