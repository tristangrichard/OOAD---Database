package Test;

import java.util.ArrayList;

import daoimpl.MySQLGameDAO;
import daoimpl.MySQLUsersDAO;
import daoimpl.MySQLUsersGamesDAO;
import daointerfaces.GameIDAO;
import daointerfaces.UsersGamesIDAO;
import daointerfaces.UsersIDAO;
import dto.GameDTO;
import dto.UsersDTO;
import dto.UsersGamesDTO;

public class Test_User_Game_Lists {

	private boolean error = false;
	ArrayList<UsersDTO> ulist = new ArrayList<UsersDTO>();
	ArrayList<UsersGamesDTO> uglist = new ArrayList<UsersGamesDTO>();
	ArrayList<GameDTO> glist = new ArrayList<GameDTO>();

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
		UsersGamesIDAO ugidao = new MySQLUsersGamesDAO();

		// sets up fictional user, games, usergame for testing DB with
		//UsersDTO bdto = new UsersDTO("Test", "Testesen", "11-3-2001", "passtest", "test@test1.mail",true);
		//GameDTO gdto = new GameDTO(101, "MODERNWARFARETEST", "11-3-2001", "not used");
		//UsersGamesDTO ugdto = new UsersGamesDTO("test@test.mail", 101);

		createUGL(uidao, gidao, ugidao);
		updateUL(uidao);
		updateGL(gidao);
		cleanUGList(ugidao);
		cleanUserList(uidao);
		cleanGameList(gidao);
	}

	//sets the 3 lists, and creates in databases.
	public void createUGL(UsersIDAO uidao, GameIDAO gidao, UsersGamesIDAO ugidao){
		error = false;	
		for (int i = 0 ; i < 10 ; i++){
			UsersDTO bdto = new UsersDTO("Test", "Testesen", "11-3-2001", "passtest", "test@test1.mail",true);
			bdto.setFname("Test"+i);				//user
			bdto.setLname("Testesen"+i);
			bdto.setDob("11-3-"+ 2001+i);
			bdto.setPass("pass"+i+"test");
			bdto.setEmail("test"+i+"@test.mail");
			ulist.add(bdto);

			GameDTO gdto = new GameDTO(101, "MODERNWARFARETEST", "11-3-2001", "not used");
			gdto.setGid(101+i);						//game
			gdto.setGname("MODERNWARFARETEST"+i);
			gdto.setReleased("11-3-"+ 2001+i);
			glist.add(gdto);

			UsersGamesDTO ugdto = new UsersGamesDTO("test@test.mail", 101);
			ugdto.setEmail("test"+i+"@test.mail");			//usergame
			ugdto.setGid(101+i);;
			uglist.add(ugdto);

			try{					//database creation												
				uidao.create(bdto);
				gidao.create(gdto);
				ugidao.create(ugdto);
			}																		
			catch(Exception e){
				error = true;															
			}	
		}
		if (error = true){System.out.println("create usergamelists in DB failed");}
		TestLauncher.printProgress(error);
	}


	public void updateUL(UsersIDAO uidao){		//update user list in DB
		error = false;	
		for (UsersDTO dto : ulist){
			dto.setFname("testnewFname");
			try{								//database updating
				uidao.update(dto.getEmail(), dto);
				if (!dto.getFname().equals(uidao.get(dto.getEmail()).getFname())){
					error = true;
				}
			}																		
			catch(Exception e){
				System.out.print("update users in DB failed");
				error = true;															
			}	
		}
		TestLauncher.printProgress(error);
	}

	public void updateGL(GameIDAO gidao){		//update game list in DB
		error = false;
		for (GameDTO dto : glist){
			dto.setGname("testnewGname");
			try{								//database updating
				gidao.update(dto);
				if (!dto.getGname().equals(gidao.getById(dto.getGid()).getGname())){
					error = true;
				}
			}																		
			catch(Exception e){
				System.out.print("update games in DB failed");
				error = true;															
			}			
		}
		TestLauncher.printProgress(error);
	}

	public void cleanUserList(UsersIDAO uidao){
		error = false;
		for (UsersDTO dto : ulist){
			try{																	
										//database deletion
				uidao.delete(dto.getEmail());
			}																		
			catch(Exception e){		
				System.out.print("delete user in DB failed");
				error = true;															
			}
		}
		TestLauncher.printProgress(error);	
	}

	public void cleanGameList(GameIDAO gidao){
		error = false;
		for (GameDTO dto : glist){
			try{								//database deletion
				gidao.delete(dto.getGid());
			}																		
			catch(Exception e){		
				System.out.print("delete game in DB failed");
				error = true;															
			}	
		}
		TestLauncher.printProgress(error);		
	}

	public void cleanUGList(UsersGamesIDAO ugidao){
		error = false;
		for (UsersGamesDTO dto : uglist){
			
			try{											//database deletion
				ugidao.delete(dto.getEmail(), dto.getGid());
				
			}																		
			catch(Exception e){		
				System.out.print("delete usersgames in DB failed");
				error = true;															
			}	
		}
		TestLauncher.printProgress(error);
	}
}



