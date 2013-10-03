package Test;

public class TestLauncher {

	private static int errors = 0;
	private static int tests = 0;
	
	Test_User_Game tdb = new Test_User_Game();
	Test_User_Game_Lists tdbl = new Test_User_Game_Lists();
	Test_Publisher_Genre tpg = new Test_Publisher_Genre();
	
	TestBL tbl = new TestBL();
	TestStatistics ts = new TestStatistics();
	
	///////////////////////////////////////////////////////////////////////////////////////////
	//This simply runs the test protocol, and increments error count, every time a test fails//
	///////////////////////////////////////////////////////////////////////////////////////////
		
	public static void printProgress(Boolean error){
		if (error) {
			errors=errors+1;
			}
		tests = tests+1;
		System.out.println("Number of errors is: "+errors+" out of "+tests+" tests!");
	}
	
	
	public void TestLauncherStart(){
		
		tdb.TestDBStart();
		tdbl.TestDBStart();
		tpg.TestDBStart();
		
//		tbl.TestBLStart();
//		ts.TestStatisticsStart();
		
		System.out.println("Test finished");
		//possibly add function here to return to start of program
	}
}