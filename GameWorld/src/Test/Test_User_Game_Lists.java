package Test;

import java.util.ArrayList;
import java.util.List;

import daoimpl.MySQLGameDAO;
import daoimpl.MySQLUsersDAO;
import daoimpl.MySQLUsersGamesDAO;
import daointerfaces.DALException;
import daointerfaces.GameIDAO;
import daointerfaces.UsersGamesIDAO;
import daointerfaces.UsersIDAO;
import dto.GameDTO;
import dto.RaavareDTO;
import dto.UsersDTO;
import dto.UsersGamesDTO;

public class Test_User_Game_Lists {

	private boolean error = false;
	List<UsersDTO> ulist = new ArrayList<UsersDTO>();
	List<UsersGamesDTO> uglist = new ArrayList<UsersGamesDTO>();
	List<GameDTO> glist = new ArrayList<GameDTO>();

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
		UsersDTO bdto = new UsersDTO("Test", "Testesen", "11-3-2001", "passtest", "test@test.mail",true);
		GameDTO gdto = new GameDTO(101, "MODERNWARFARETEST", "11-3-2001");
		UsersGamesDTO ugdto = new UsersGamesDTO("test@test.mail", 101);

		createUGL(uidao,bdto, gidao, gdto, ugidao, ugdto);
		updateUL(uidao);
		updateUGL(uidao, gidao, ugidao);
	}

	//sets the 3 lists, and creates in databases.
	public void createUGL(UsersIDAO uidao, UsersDTO bdto, GameIDAO gidao, GameDTO gdto, UsersGamesIDAO ugidao, UsersGamesDTO ugdto){

		for (int i = 0 ; i < 10 ; i++){
			bdto.setFname("Test"+i);				//user
			bdto.setLname("Testesen"+i);
			bdto.setDob("11-3-"+ 2001+i);
			bdto.setPass("pass"+i+"test");
			bdto.setEmail("test"+i+"@test.mail");
			ulist.add(bdto);

			gdto.setGid(101+i);						//game
			gdto.setGname("MODERNWARFARETEST"+i);
			gdto.setReleased("11-3-"+ 2001+i);
			glist.add(gdto);

			ugdto.setUid(bdto.getEmail());			//usergame
			ugdto.setGid(gdto.getGid());;
			uglist.add(ugdto);

			try{																	
				error = false;						//database creation
				uidao.create(bdto);
				gidao.create(gdto);
				ugidao.create(ugdto);

			}																		
			catch(Exception e){
				System.out.print("create usergamelists in DB failed");
				error = true;															
			}																		
			finally {TestLauncher.printProgress(error);}	
		}
	}

	public void updateUL(UsersIDAO uidao){

		for (UsersDTO dto : ulist){
			dto.setFname("testnewFname");
			
			try{																	
				error = false;						//database updating
				uidao.update(dto);
				if (dto.getFname() != uidao.get(dto.getEmail()).getFname()){
					error = true;
					}
			}																		
			catch(Exception e){
				System.out.print("create usergamelists in DB failed");
				error = true;															
			}	
			finally{TestLauncher.printProgress(error);}
		}
		
	}
	
	public void updateUGL(UsersIDAO uidao, GameIDAO gidao, UsersGamesIDAO ugidao){
		int i=0;
		for (UsersDTO dto : ulist){
			i++;
			dto.setEmail("testnewEmail"+i);
			
			try{																	
				error = false;						//database updating
				uidao.update(dto);
				String email = dto.getEmail();
				int gid  = 101+i;
				if (email != ugidao.get(email, gid).getEmail()){
					error = true;
				}
			}																		
			catch(Exception e){
				System.out.print("change email of users failed");
				error = true;															
			}	
		}				
		TestLauncher.printProgress(error);
		}
		
	
}


