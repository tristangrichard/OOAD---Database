package Test;

public class TestLauncher {

	private int errors = 0;
	
	TestDB tdb = new TestDB();
	TestBL tbl = new TestBL();
	TestStatistics ts = new TestStatistics();
	
	///////////////////////////////////////////////////////////////////////////////////////////
	//This simply runs the test protocol, and increments error count, every time a test fails//
	///////////////////////////////////////////////////////////////////////////////////////////
		
	public void TestLauncherStart(){
		
		errors = errors + tdb.TestDBStart();
		errors = errors + tbl.TestBLStart();
		errors = errors + ts.TestStatisticsStart();
		
		System.out.println("Number of errors is: "+errors);
	}
}