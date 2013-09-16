package scale;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if (args.length > 0) {
			Scale s = new Scale(Integer.parseInt(args[0]));
		} else {
			Scale s = new Scale();
		}
	}
}
