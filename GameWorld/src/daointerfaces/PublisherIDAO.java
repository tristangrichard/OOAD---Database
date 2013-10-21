package daointerfaces;

import java.util.List;

import dto.PublisherDTO;

abstract public class PublisherIDAO
{
	abstract public void create(PublisherDTO row) throws DALException;
	abstract public void delete(int pId) throws DALException;
	abstract public void update(PublisherDTO row) throws DALException;
	abstract public PublisherDTO getById(int pId) throws DALException;
	abstract public PublisherDTO getByPub(String pub) throws DALException;
	abstract public List<PublisherDTO> getList() throws DALException;
}
