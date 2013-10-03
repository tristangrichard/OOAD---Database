package Test;

import daoimpl.MySQLGenreDAO;
import daoimpl.MySQLPublisherDAO;
import daointerfaces.GenreIDAO;
import daointerfaces.PublisherIDAO;
import dto.GenreDTO;
import dto.PublisherDTO;

public class Test_Publisher_Genre {

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
		//testing creation updating deletion of a publisher and genre, clean tests	
		GenreIDAO gidao = new MySQLGenreDAO();
		PublisherIDAO pidao = new MySQLPublisherDAO();

		// sets up fictional company and genre for testing
		GenreDTO gdto = new GenreDTO(303, "Adventure");
		PublisherDTO pdto = new PublisherDTO(404, "Criterion", "04-04-2004");

		createGenre(gdto, gidao);
		updateGenre(gdto, gidao);
		deleteGenre(gdto, gidao);
		createPublisher(pdto, pidao);
		updatePublisher(pdto, pidao);
		deletePublisher(pdto, pidao);
	}

	public void createGenre(GenreDTO gdto, GenreIDAO gidao){ //creates 3 genres in DB
		error = false;
		for(int i = 0 ; i<3; i++){
			gdto.setGenreid(303+i);
			gdto.setGenre("Adventure"+i);
			try{																																		
				gidao.create(gdto);
			}																		
			catch(Exception e){													
				System.out.print("create genre failed in DB");
				error = true;															
			}																		
			finally {TestLauncher.printProgress(error);}	
		}
	}

	public void updateGenre(GenreDTO gdto, GenreIDAO gidao){	//gets the 3 created genres and updates them with new name
		error = false;	
		for(int i = 0 ; i<3; i++){
			try{	
				gdto = gidao.get(303+i);
				gdto.setGenre("Adventure"+2*i);													
				gidao.update(gdto);
			}																		
			catch(Exception e){													
				System.out.print("update genre failed in DB");
				error = true;															
			}																		
			finally {TestLauncher.printProgress(error);}	
		}
	}

	public void deleteGenre(GenreDTO gdto, GenreIDAO gidao){	//deletes the 3 genres created
		error = false;	
		for(int i = 0 ; i<3; i++){
			try{													
				gidao.delete(303+i);
			}																		
			catch(Exception e){													
				System.out.print("delete genre failed in DB");
				error = true;															
			}																		
			finally {TestLauncher.printProgress(error);}	
		}
	}
	
	public void createPublisher(PublisherDTO pdto, PublisherIDAO pidao){	//creates 3 Publishers in DB
		error = false;
		for(int i = 0 ; i<3; i++){
			pdto.setPid(404+i);
			pdto.setFounded("04-04-"+2004+i);
			pdto.setPublisher("Criterion"+i);
			try{																																		
				pidao.create(pdto);
			}																		
			catch(Exception e){													
				System.out.print("create publisher failed in DB");
				error = true;															
			}																		
			finally {TestLauncher.printProgress(error);}	
		}
	}
	
	public void updatePublisher(PublisherDTO pdto, PublisherIDAO pidao){	//updates the 3 Publisher in DB
		error = false;	
		for(int i = 0 ; i<3; i++){
			try{	
				pdto = pidao.get(404+i);
				pdto.setPublisher("CriterionTest"+2*i);													
				pidao.update(pdto);
			}																		
			catch(Exception e){													
				System.out.print("update Publisher failed in DB");
				error = true;															
			}																		
			finally {TestLauncher.printProgress(error);}	
		}
	}
	
	public void deletePublisher(PublisherDTO pdto, PublisherIDAO pidao){	//deletes the 3 Publishers in DB
		error = false;	
		for(int i = 0 ; i<3; i++){
			try{													
				pidao.delete(404+i);
			}																		
			catch(Exception e){													
				System.out.print("delete Publisher failed in DB");
				error = true;															
			}																		
			finally {TestLauncher.printProgress(error);}	
		}
	}
}
