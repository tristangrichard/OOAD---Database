package daointerfaces;

import java.util.List;

import dto.OSDTO;

public interface OSIDAO
{
	public void create(OSDTO row) throws DALException;
	public void delete(int OSid) throws DALException;
	public void update(OSDTO row) throws DALException;
	public OSDTO get(int OSid) throws DALException;
	public List<OSDTO> getList() throws DALException;
}
