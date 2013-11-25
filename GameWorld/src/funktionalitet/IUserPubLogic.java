package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.PublisherDTO;
import dto.UserPubDTO;
/**
 * 
 * @author Tristan Richard
 *
 */
public interface IUserPubLogic {
	
	public void createUserPub(String email, int Pid) throws DALException;
	public PublisherDTO getPub(int Pid) throws DALException;
	public List<UserPubDTO> getList() throws DALException;

}
