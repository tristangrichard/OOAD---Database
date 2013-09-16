package test;

import java.io.IOException;
import controller.WeightProcedureControl;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			WeightProcedureControl controller = new WeightProcedureControl();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
