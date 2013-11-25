package daointerfaces;

import java.util.List;

import dto.PublisherDTO;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public interface PublisherIDAO
{
	public void create(PublisherDTO row) throws DALException;
	public void delete(int pId) throws DALException;
	public void update(PublisherDTO row) throws DALException;
	public PublisherDTO getById(int pId) throws DALException;
	public PublisherDTO getByPub(String pub) throws DALException;
	public List<PublisherDTO> getList() throws DALException;
}
