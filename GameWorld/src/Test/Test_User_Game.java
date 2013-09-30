package Test;

import daoimpl.MySQLGameDAO;
import daoimpl.MySQLUsersDAO;
import daointerfaces.DALException;
import daointerfaces.GameIDAO;
import daointerfaces.UsersIDAO;
import dto.GameDTO;
import dto.UsersDTO;

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
	GameDTO gdto = new GameDTO(10001, "MODERNWARFARETEST", "11-3-2001");
	
	create(uidao,bdto);
	update(uidao,bdto);
	delete(uidao,bdto);
	createG(gidao,gdto);
	updateG(gidao,gdto);
	deleteG(gidao,gdto);
	}
	
	//////////////////////////////////////////////////////////////////////////	
	//simple user creation test
	public void create(UsersIDAO uidao, UsersDTO bdto){
	try {
		uidao.create(bdto);
	} catch (DALException e) {
		error = true;
		System.out.println("create user failed");
	}
	finally{
		{TestLauncher.printProgress(error);	
	}
	error = false;	}
	}
	
	//////////////////////////////////////////////////////////////////////////
	//simple user update test
	public void update(UsersIDAO uidao, UsersDTO bdto){
	UsersDTO old = bdto;
	bdto.setFname("Test1");
	bdto.setLname("Testesten1");
	bdto.setPass("pass");
	try {
		uidao.update(bdto);
		if (old != uidao.get(bdto.getEmail())){//compares old to updated UsersDTO
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
	error = false;		}
	}
	//////////////////////////////////////////////////////////////////////////
	// cleans up user creation and at the same time tests if deletion is successfull
	public void delete(UsersIDAO uidao, UsersDTO bdto){
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
	error = false;		
	}
	}
	//////////////////////////////////////////////////////////////////////////	
	//simple game creation test
	public void createG(GameIDAO gidao, GameDTO gdto){
	try {
		gidao.create(gdto);
	} catch (DALException e) {
		error = true;
		System.out.println("create game failed");
	}
	finally{
		{TestLauncher.printProgress(error);	
	}
	error = false;	}
	}
	
	//////////////////////////////////////////////////////////////////////////
	//simple game update test
	public void updateG(GameIDAO gidao, GameDTO gdto){
	GameDTO old = gdto;
	gdto.setGname("GameTest");
	gdto.setReleased("01-01-2001");
	try {
		gidao.update(gdto);
		if (old != gidao.get(gdto.getGid())){//compares old to updated GameDTO
			error = true;
			System.out.println("update game failed");}
		else{}
	}
	catch (DALException e) {
		error = true;
		System.out.println("update game failed");
	}
	finally{
		{TestLauncher.printProgress(error);	
	}
	error = false;		}
	}
	//////////////////////////////////////////////////////////////////////////
	// cleans up game creation and at the same time tests if deletion is successfull
	public void deleteG(GameIDAO gidao, GameDTO gdto){
	try {
		gidao.delete(gdto.getGid());
	} catch (DALException e) {
		error = true;
		System.out.println("delete game failed");
	}
	try {
		gidao.get(gdto.getGid());
	} catch (DALException e) {
	}
	finally{
		{TestLauncher.printProgress(error);	
	}
	error = false;		
	}
	}
}