package funktionalitet;

import daoimpl.*;
import daointerfaces.*;
import dto.*;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connector.Connector;
/**
 * 
 * @author Tristan Richard
 *
 */
public class UserLogic implements IUserLogic{	
	private UsersIDAO o;
	private UsersDTO user;
	private UsersLangIDAO l;
	private RoleIDAO r;
	private RoleDTO newRole;
	private UserPubDTO userPub;
	private UserPubIDAO p;
	private UsersLangDTO newLang;

	public UserLogic() throws DALException {
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
		o = new MySQLUsersDAO();
		l = new MySQLUsersLangDAO();
		r = new MySQLRoleDAO();
		p = new MySQLUserPubDAO();

	}
	
	public UserLogic(UsersIDAO dao) { //This construtctor is used for testing
		this.o = dao;
	}

	public String createUser(String fName, String lName, String birth, String role, String temail, int sex, int lang) throws DALException {
		try {
			o.get(temail);
			}catch(DALException e)
			{
				String DOB = processBirth(birth);
				String email = processEmail(temail);
				Boolean bSex = getSex(sex);
				String newPass;
				do {
					newPass = createPass();
				} while (!controlPassword(newPass));
				if (fName == "" || lName == "")
					throw new DALException("Please enter your full name!");
				UsersDTO newUser = new UsersDTO(fName, lName, DOB, newPass, email, bSex);
				o.create(newUser);
				UsersLangDTO langdto = new UsersLangDTO(email,lang);
				l.create(langdto);
				RoleDTO ro = new RoleDTO(email, role);
				r.create(ro);
				return newPass;
			}
		throw new DALException("User already exists!");
		
	}

	public void updateOpr(String fName, String lName, String birth, String oldEmail, String email, int sex, int lang, String oldPassword, String newPassword, String newPassword2) throws DALException {
		try {
			user = o.get(oldEmail);
		} catch (DALException e) {
			throw new DALException("The specified user does not exist.");
		}
		if (!(user.getPass().equals(oldPassword))) { // eventuelt exception handling
			throw new DALException("Old password is not correct.");
		}
		RoleDTO role = r.get(oldEmail);
		updateOprAdmin(fName, lName, birth,oldEmail, email, sex, lang, role.getRole(), newPassword, newPassword2);
	}
	@Override
	public void updatePub(String fName, String lName, String birth, String oldEmail, String email, int sex, int lang, String oldPassword, String newPassword, String newPassword2, int Pid) throws DALException {
		try {
			user = o.get(oldEmail);
		} catch (DALException e) {
			throw new DALException("The specified user does not exist.");
		}
		if (!(user.getPass().equals(oldPassword))) { // eventuelt exception handling
			throw new DALException("Old password is not correct.");
		}
		RoleDTO role = r.get(oldEmail);
		updatePubAdmin(fName, lName, birth, oldEmail, email, sex, lang, role.getRole(), newPassword, newPassword2, Pid);
		
	}

	public void updateOprAdmin(String fName, String lName, String birth,String oldEmail, String email, int sex, int lang, String role, String newPassword, String newPassword2) throws DALException {
		try {
			user = o.get(oldEmail);
		}
		catch (DALException e) {
			throw new DALException("The specified user does not exist.");
		}
		String DOB = processBirth(birth);
		if (newPassword.equals("")) {
			newPassword = user.getPass();
			newPassword2 = user.getPass();
		}
		if (newPassword.equals(newPassword2)) {
			if (controlPassword(newPassword)) {
				email = processEmail(email);
				birth = processBirth(birth);
				Boolean bSex = getSex(sex);
				UsersDTO user = new UsersDTO(fName, lName, DOB, newPassword, email, bSex);
				o.update(oldEmail, user);
				newRole = new RoleDTO(email,role);
				r.update(newRole);
				newLang = new UsersLangDTO(email, lang);
				l.update(newLang);
			} else {
				throw new DALException("The new password is invalid.");
			}
		} else {
			throw new DALException("The two passwords do not match.");
		}
	}
	
	public void updatePubAdmin(String fName, String lName, String birth, String oldEmail, String email, int sex, int lang, String role, String newPassword, String newPassword2, int Pid) throws DALException {
		try {
			user = o.get(oldEmail);
		}
		catch (DALException e) {
			throw new DALException("The specified user does not exist.");
		}
		String DOB = processBirth(birth);
		if (newPassword.equals("")) {
			newPassword = user.getPass();
			newPassword2 = user.getPass();
		}
		if (newPassword.equals(newPassword2)) {
			if (controlPassword(newPassword)) {
				email = processEmail(email);
				birth = processBirth(birth);
				Boolean bSex = getSex(sex);
				UsersDTO user = new UsersDTO(fName, lName, DOB, newPassword, email, bSex);
				o.update(oldEmail,user);
				newRole = new RoleDTO(email,role);
				r.update(newRole);
				userPub = new UserPubDTO(oldEmail, Pid);
				p.update(userPub);
				newLang = new UsersLangDTO(oldEmail, lang);
				l.update(newLang);
			} else {
				throw new DALException("The new password is invalid.");
			}
		} else {
			throw new DALException("The two passwords do not match.");
		}
	}

	public void deactivateUser(String email) throws DALException {
		RoleDTO currentRole = r.get(email);
		newRole = new RoleDTO(email,"inactive");
		if (currentRole.getRole().equalsIgnoreCase("user")) {
			r.update(newRole);
		} else if( r.countAdmin() > 1 && currentRole.getRole().equalsIgnoreCase("administrator")){
			r.update(newRole);
		} else if(currentRole.getRole().equalsIgnoreCase("game")){
			newRole = new RoleDTO(email,"inactivePub");
			r.update(newRole);
		}else throw new DALException("Unable to deactivate the last admin");
	}

	public List<UsersDTO> getUserList() throws DALException {
		return o.getList();
	}

	public UsersDTO getUser(String email) throws DALException {
		return o.get(email);
	}

	private String createPass() {
		String Pass = PassGenerator.getRandomString(8);
		return Pass;
	}

	private String processBirth(String birth) throws DALException {
		String tempbirth = null;
		Pattern pBirth = Pattern.compile("\\d{2}(.|-|/)\\d{2}(.|-|/)\\d{4}");
		Matcher m = pBirth.matcher(birth);
		if (m.matches()) {
			tempbirth = birth.substring(6,10)+"-"+birth.substring(3,5)+"-"+ birth.substring(0,2);
		} else {
			throw new DALException("Invalid date of birth.");
		}
		return tempbirth;
	}
	
	private String processEmail(String email) throws DALException {
		String tempEmail = null;
		Pattern pEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = pEmail.matcher(email);
		if (m.find()) {
			tempEmail = email;
		} else {
			throw new DALException("Invalid email Address.");
		}
		return tempEmail;
	}

	private boolean controlPassword(String password) {
		Pattern pPassword = Pattern.compile("(?:(?:(?=.*[A-Z])(?=.*[\\d])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[\\d])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[A-Z])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])))^.{7,8}$");
		Matcher m = pPassword.matcher(password);
		return m.matches();
	}

	private boolean getSex(int sex){
		Boolean bSex = false;
		if (sex == 1)
			bSex = true;
		return bSex;
	}
}