package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.UsersDTO;

public interface IUserLogic {
	public String createUser(String fName, String lName, String birth, String rolle, String email, int sex, int lang) throws DALException;
	public void updateOpr(String fName, String lName, String birth, String oldEmail, String email, int sex, int lang, String oldPassword, String newPassword, String newPassword2) throws DALException;
	public void updateOprAdmin(String fName, String lName, String birth, String oldEmail, String email, int sex, int lang, String role, String newPassword, String newPassword2) throws DALException;
	public void updatePubAdmin(String fName, String lName, String birth, String oldEmail, String email, int sex, int lang, String role, String newPassword, String newPassword2, int Pid) throws DALException;
	public void updatePub(String fName, String lName, String birth, String oldEmail, String email, int sex, int lang, String oldPassword, String newPassword, String newPassword2, int Pid) throws DALException;
	public void deactivateUser(String email) throws DALException;
	public UsersDTO getUser(String email) throws DALException;
	public List<UsersDTO> getUserList() throws DALException;
}