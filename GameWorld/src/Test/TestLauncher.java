package Test;

public class TestLauncher {

	private static int errors = 0;
	private static int tests = 0;
	
	TestDB tdb = new TestDB();
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
		tbl.TestBLStart();
		ts.TestStatisticsStart();
		System.out.println("Test finished");
		//possibly add function here to return to start of program
	}
}