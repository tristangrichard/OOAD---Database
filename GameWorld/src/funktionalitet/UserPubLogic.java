package funktionalitet;

import java.sql.SQLException;
import java.util.List;
import connector.Connector;
import daoimpl.MySQLPublisherDAO;
import daoimpl.MySQLUserPubDAO;
import daointerfaces.DALException;
import daointerfaces.PublisherIDAO;
import daointerfaces.UserPubIDAO;
import dto.PublisherDTO;
import dto.UserPubDTO;

public class UserPubLogic implements IUserPubLogic {

	private UserPubIDAO userPub;
	private PublisherIDAO pub;

	public UserPubLogic() throws DALException {
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userPub = new MySQLUserPubDAO();
		pub = new MySQLPublisherDAO();
	}

	public void createUserPub(String email, int Pid) throws DALException {
		UserPubDTO row = new UserPubDTO(email, Pid);
		userPub.create(row);
	}

	public List<UserPubDTO> getList() throws DALException {
		List<UserPubDTO> list = userPub.getList();
		return list;
	}

	public PublisherDTO getPub(int Pid) throws DALException {
		return pub.get(Pid);
	}

}
