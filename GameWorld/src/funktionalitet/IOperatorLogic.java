package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.OperatoerDTO;

public interface IOperatorLogic {
	public boolean isAdmin(int id) throws DALException;
	public String createOpr(int oprID, String oprName, String cpr, String rolle) throws DALException;
	public void updateOpr(int oprID, String oprName, String cpr, String oldPassword, String newPassword, String newPassword2, String rolle) throws DALException;
	public void updateOprAdmin(int oprID, String oprName, String cpr,  String newPassword, String newPassword2, String rolle) throws DALException;
	public void deleteOpr(int currentUser, int oprID) throws DALException;
	public OperatoerDTO getOperatoer(int oprID) throws DALException;
	public List<OperatoerDTO> getOperatoerList() throws DALException;
}