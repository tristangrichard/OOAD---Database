package Test;

import java.util.List;

import daoimpl.MySQLPublisherDAO;
import daointerfaces.PublisherIDAO;
import dto.PublisherDTO;

public class TestBL {

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
	PublisherIDAO pidao = new MySQLPublisherDAO();
	

	
	public void TestBLStart(){
	
	publisherGamesGraphAble();
		
		
	}
	
	public void publisherGamesGraphAble(){
		try{
			List<PublisherDTO> pdtoL = pidao.getList();
			}
			catch(Exception e){
				
			}
		
		
	}
	//define methods for testing BusinessLogic
	
}
	
	