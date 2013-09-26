package daointerfaces;

import java.util.List;

import dto.PublisherDTO;

abstract public class PublisherIDAO
{
	abstract public void create(PublisherDTO row) throws DALException;
	abstract public void delete(int Pid) throws DALException;
	abstract public void update(PublisherDTO row) throws DALException;
	abstract public PublisherDTO get(int Pid) throws DALException;
	abstract public List<PublisherDTO> getList() throws DALException;
}
