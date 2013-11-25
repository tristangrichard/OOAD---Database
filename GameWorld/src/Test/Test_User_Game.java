package Test;

import daoimpl.MySQLGameDAO;
import daoimpl.MySQLUsersDAO;
import daointerfaces.DALException;
import daointerfaces.GameIDAO;
import daointerfaces.UsersIDAO;
import dto.GameDTO;
import dto.UsersDTO;
/**
 * 
 * @author Mikkel Barfred
 *
 */
public class Test_User_Game {

	private boolean error = false;

	//////////////////////////////////////////////////////////////////////////
	//Framework for tests													//
	//try{																	//
	//error = false;														//
	//System.out.print(9);													//
	//}																		//
	//catch(Exception e){													//
	//error = true;															//
	//}																		//
	//finally {TestLauncher.printProgress(error);}							//
	//////////////////////////////////////////////////////////////////////////

	public void TestDBStart(){	
		//testing creation updating deletion of a user, and game in DB, clean test		
		UsersIDAO uidao = new MySQLUsersDAO();
		GameIDAO gidao = new MySQLGameDAO();
		// sets up fictional user for testing DB with
		UsersDTO bdto = new UsersDTO("Test", "Testesen", "11-3-2001", "passtest", "test@test.mail",true);
		GameDTO gdto = new GameDTO(10001, "MODERNWARFARETEST", "11-3-2001", "not used");

		create(uidao,bdto);
		createDuplicate(uidao,bdto);
		update(uidao,bdto);
		delete(uidao,bdto);
		createG(gidao,gdto);
		createGduplicate(gidao,gdto);
		updateG(gidao,gdto);
		deleteG(gidao,gdto);
	}

	//////////////////////////////////////////////////////////////////////////	
	//simple user creation test
	public void create(UsersIDAO uidao, UsersDTO bdto){
		error = false;
		try {
			uidao.create(bdto);
		} catch (DALException e) {
			error = true;
			System.out.println("create user failed");
		}
			TestLauncher.printProgress(error);	
		error = false;
	}
	//////////////////////////////////////////////////////////////////////////	
	//simple user creation test, same email
	public void createDuplicate(UsersIDAO uidao, UsersDTO bdto){
		error = false;
		boolean temp = true;
		try {
			uidao.create(bdto);
		} catch (DALException e) {
			error = false;
			temp = false;
		}
		if (temp){
			error = true;
			System.out.println("create duplicate, succeeded, which is wrong");
		}
		TestLauncher.printProgress(error);	
		error = false;

	}

	//////////////////////////////////////////////////////////////////////////
	//simple user update test
	public void update(UsersIDAO uidao, UsersDTO bdto){
		error = false;
		UsersDTO old = bdto;
		bdto.setFname("Test1");
		bdto.setLname("Testesten1");
		bdto.setPass("pass");
		try {
			uidao.update(old.getEmail(), bdto);
			if (old == uidao.get(bdto.getEmail())){//compares old to updated UsersDTO
				error = true;
				System.out.println("update user failed");}
			else{}
		}
		catch (DALException e) {
			error = true;
			System.out.println("update user failed");
		}
		finally{
			{TestLauncher.printProgress(error);	
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////
	// cleans up user creation and at the same time tests if deletion is successfull
	public void delete(UsersIDAO uidao, UsersDTO bdto){
		error = false;	
		try {
			uidao.delete(bdto.getEmail());
		} catch (DALException e) {
			error = true;
			System.out.println("delete user failed");
		}
		try {
			uidao.get(bdto.getEmail());
		} catch (DALException e) {
		}
		finally{
			{TestLauncher.printProgress(error);	
			}	
		}
	}
	//////////////////////////////////////////////////////////////////////////	
	//simple game creation test
	public void createG(GameIDAO gidao, GameDTO gdto){
		error = false;
		try {
			gidao.create(gdto);
		} catch (DALException e) {
			error = true;
			System.out.println("create game failed");
		}
		finally{
			{TestLauncher.printProgress(error);	
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////	
	//simple game creation test, of duplicate
	public void createGduplicate(GameIDAO gidao, GameDTO gdto){
		boolean temp = true;
		error = false;
		try {
			gidao.create(gdto);
		} catch (DALException e) {
			error = false;
			temp = false;
		}
		if (temp){
			error = true;
			System.out.println("duplicate game creation succeeded, which is wrong");
		}
		TestLauncher.printProgress(error);	
		error = false;
	}

	//////////////////////////////////////////////////////////////////////////
	//simple game update test
	public void updateG(GameIDAO gidao, GameDTO gdto){
		error = false;
		GameDTO old = new GameDTO(10001, "MODERNWARFARETEST", "11-3-2001", "not used");
		gdto.setGname("GameTest");
		gdto.setReleased("01-01-2001");
		try {
			gidao.update(gdto);
			String oldname = old.getGname();
			String newname = gidao.getById(gdto.getGid()).getGname();
			if (oldname.equals(newname)){		//compares old to updated GameDTO
				error = true;
				System.out.println("update game failed0");}
			else{}
		}
		catch (DALException e) {
			error = true;
			System.out.println("update game failed1");
		}
		finally{
			{TestLauncher.printProgress(error);	
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////
	// cleans up game creation and at the same time tests if deletion is successfull
	public void deleteG(GameIDAO gidao, GameDTO gdto){
		error = false;
		try {
			gidao.delete(gdto.getGid());
		} catch (DALException e) {
			error = true;
			System.out.println("delete game failed");
		}
		try {
			gidao.getById(gdto.getGid());
		} catch (DALException e) {
		}
		finally{
			{TestLauncher.printProgress(error);	
			}

		}
	}
}