package scale;

import java.awt.EventQueue;
import java.util.Locale;

public class Scale {

	private static double brutto = 0;
	private static double tara = 0;
	private static GUI frame;
	public static boolean k3 = false;
	private static RemoteListen rl;
	private boolean valueSet = false;
	private static String oprInput;

	public Scale(int port) {
		rl = new RemoteListen(port, this);
		new Thread(rl).start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Scale() {
		rl = new RemoteListen(4567, this);
		new Thread(rl).start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void outstream(String out) {
		rl.outstream(out);
	}

	public static void setDisplay(String s) {
		frame.setUpperField(s);
		// Her skal der ske ting og sager!!!
	}

	public static void setBrutto(double brutto) {
		Scale.brutto = brutto;
		updateWeight();
	}

	public static void updateWeight() {
		String temp = String.format(new Locale("en", "us"), "%.3f", brutto
				- tara)
				+ " kg";
		frame.setUpperField(temp);
	}
	
	public static void updateWeightDisplay() {
		String temp = String.format(new Locale("en", "us"), "%.3f", brutto)
				+ " kg";
		frame.setUpperField(temp);
	}

	public static double getBrutto() {
		return brutto;
	}

	public static void setTara(double tara) {
		Scale.tara = tara;
		updateWeight();
	}

	public static double getTara() {
		return tara;
	}

	public static void k3(boolean k3) {
		Scale.k3 = k3;
	}

	synchronized String get(String desc) {
		frame.setUpperField(desc);
		frame.getrmTwentyResponse(this);
		if (!valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("interruptedException caught");
			}
		valueSet = false;
		notifyAll();
		return oprInput;
	}

	synchronized void put(String displayText) {
		System.out.println("displayText set to: " + displayText);
		if (valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("interruptedException caught");
			}

		oprInput = displayText;
		valueSet = true;
		notifyAll();
	}
}
