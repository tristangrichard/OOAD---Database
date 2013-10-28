package Test;

import java.sql.SQLException;

import connector.Connector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connector conn = new Connector();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestLauncher tl = new TestLauncher();
		tl.TestLauncherStart();
	}


}
