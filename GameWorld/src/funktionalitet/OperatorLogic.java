package funktionalitet;

import daoimpl.*;
import daointerfaces.*;
import dto.*;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.*;

import connector.Connector;

public class OperatorLogic implements IOperatorLogic{	
	private IOperatoerDAO o;
	private BrugerDTO user;

	public OperatorLogic() throws DALException {
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
		o = new MySQLOperatoerDAO();

	}
	
	public OperatorLogic(IOperatoerDAO dao) { //This construtctor is used for testing
		this.o = dao;
	}

	@Override
	public boolean isAdmin(int id) throws DALException {
		return "administrator".equals(o.getOperatoer(id).getRolle());

	}

	@Override
	public String createOpr(int oprID, String oprName, String cpr, String rolle) throws DALException {
		String tempCPR = processCPR(cpr);
		String newPass;
		String ini = initGen(oprName);
		do {
			newPass = createPass();
		} while (!controlPassword(newPass));
		BrugerDTO newUser = new BrugerDTO(oprID, oprName, ini, tempCPR, newPass, rolle);
		o.createOperatoer(newUser);
		return newPass;
	}

	@Override
	public void updateOpr(int oprID, String oprName, String cpr, String oldPassword, String newPassword, String newPassword2, String newRolle) throws DALException {
		try {
			user = o.getOperatoer(oprID);
		} catch (DALException e) {
			throw new DALException("The specified operator does not exist.");
		}
		if (!(user.getPassword().equals(oldPassword))) { // eventuelt exception handling
			throw new DALException("Old password is not correct.");
		}
		updateOprAdmin(oprID, oprName, cpr, newPassword, newPassword2, newRolle);
	}

	@Override
	public void updateOprAdmin(int oprID, String oprName, String cpr, String newPassword, String newPassword2, String newRolle) throws DALException {
		try {
			user = o.getOperatoer(oprID);
		}
		catch (DALException e) {
			throw new DALException("The specified operator does not exist.");
		}
		if (newPassword.equals("")) {
			newPassword = user.getPassword();
			newPassword2 = user.getPassword();
		}
		if (newPassword.equals(newPassword2)) {
			if (controlPassword(newPassword)) {
				cpr = processCPR(cpr);
				String ini = initGen(oprName);
				BrugerDTO opr = new BrugerDTO(oprID, oprName, ini, cpr, newPassword, newRolle);
				o.updateOperatoer(opr);
			} else {
				throw new DALException("The new password is invalid.");
			}
		} else {
			throw new DALException("The two passwords do not match.");
		}
	}

	@Override
	public void deleteOpr(int currentUser, int oprID) throws DALException {
		if (isAdmin(currentUser) && !isAdmin(oprID)) {
			BrugerDTO opr = o.getOperatoer(oprID);
			opr.setRolle("inaktiv");
			o.updateOperatoer(opr);
		} else {
			throw new DALException("Operator could not be deleted.");
		}
	}

	@Override
	public List<BrugerDTO> getOperatoerList() throws DALException {
		return o.getOperatoerList();
	}

	@Override
	public BrugerDTO getOperatoer(int oprID) throws DALException {
		return o.getOperatoer(oprID);
	}

	private String createPass() {
		String Pass = PassGenerator.getRandomString(8);
		return Pass;
	}

	private String processCPR(String orgCPR) throws DALException {
		String tempCPR = null;
		Pattern pCPR = Pattern.compile("^(\\d{6})-?(\\d{4})$");
		Matcher m = pCPR.matcher(orgCPR);
		if (m.matches()) {
			tempCPR = m.group(1) + m.group(2);
		} else {
			throw new DALException("CPR number is invalid.");
		}
		return tempCPR;
	}

	private boolean controlPassword(String password) {
		Pattern pPassword = Pattern.compile("(?:(?:(?=.*[A-Z])(?=.*[\\d])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[\\d])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[A-Z])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])))^.{7,8}$");
		Matcher m = pPassword.matcher(password);
		return m.matches();
	}

	private String initGen(String oprName) throws DALException {
		Pattern pInitials = Pattern.compile("(?=^.{2,20}$)^(\\w)\\w*(?: \\w*)* (\\w)\\w*$");
		Pattern pChars = Pattern.compile("[^\\w ]");
		String ini;
		Matcher m;
		m = pChars.matcher(oprName);
		if (m.find()) { throw new DALException("Name can only contain a-z, A-Z and spaces"); }
		if (oprName.length() > 20 || oprName.length() < 2) { throw new DALException("Name must be between 2 - 20 characters in length."); }
		m = pInitials.matcher(oprName);
		if (m.matches()) {
			ini = m.group(1) + m.group(2);
		} else {
			throw new DALException("Name needs to be two seperate words.");
		}
		return ini;
	}
}