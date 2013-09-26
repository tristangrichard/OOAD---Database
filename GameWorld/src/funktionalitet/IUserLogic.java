package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.BrugerDTO;

public interface IUserLogic {
	public boolean isAdmin(int id) throws DALException;
	public String createUser(String userName, String birth, String rolle, String email, int sex) throws DALException;
	public void updateOpr(int oprID, String oprName, String cpr, String oldPassword, String newPassword, String newPassword2, String rolle) throws DALException;
	public void updateOprAdmin(int oprID, String oprName, String cpr,  String newPassword, String newPassword2, String rolle) throws DALException;
	public void deleteOpr(int currentUser, int oprID) throws DALException;
	public BrugerDTO getOperatoer(int oprID) throws DALException;
	public List<BrugerDTO> getOperatoerList() throws DALException;
}