package scale;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteListen implements Runnable {
	private static ServerSocket listener;
	private static String inline;
	private static int portdst = 4567;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;
	private Scale scale;

	public RemoteListen(int portdst, Scale scale) {
		RemoteListen.portdst = portdst;
		this.scale = scale;
	}

	public void run() {
		try {
			listener = new ServerSocket(portdst);
			sock = listener.accept();
			instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
			System.out.println("Connected to: " + sock);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		input();
	}

	private void input() {
		try{
			while (!(inline = instream.readLine().toUpperCase()).isEmpty()){
				if (inline.startsWith("DW")){
					Scale.updateWeight();
					outstream.writeBytes("DW A\r\n");
				} else if (inline.startsWith("D")) {
					if (inline.equals("D")) {
						Scale.setDisplay("");
					} else {
						String[] temp = inline.split("\"");
						Scale.setDisplay(temp[1]);
					}
					outstream.writeBytes("DB"+"\r\n");
				} else if (inline.startsWith("K 1")) {
					System.out.println("K 1 modtaget");
					Scale.k3(false);
					Scale.updateWeightDisplay();
					outstream.writeBytes("K A"+"\r\n");
					System.out.println("K A sendt");
				}
				else if (inline.startsWith("K 3")) {
					System.out.println("K 3 modtaget");
					Scale.k3(true);
					outstream.writeBytes("K A"+"\r\n");
					System.out.println("K A sendt");
				} else if (inline.startsWith("T")) {
					System.out.println("T modtaget og " + Scale.getBrutto() + " sendt.");
					outstream.writeBytes("T " + (Scale.getBrutto()) + " kg "+"\r\n");
					Scale.setTara(Scale.getBrutto());
				} else if (inline.startsWith("S")) {
					System.out.println("S modtaget");
					outstream.writeBytes("S S       " + (Scale.getBrutto()-Scale.getTara())+ " kg " +"\r\n");
				} else if (inline.startsWith("P121")) {
					// deltabar-kommando
					System.out.println("P121 modtaget");
					Scale.updateWeight();
					outstream.writeBytes("P121 A\r\n");
				} else if (inline.startsWith("P120")) {
					// deltabar-reset
					System.out.println("P120 modtaget");
					Scale.updateWeight();
					outstream.writeBytes("P120 A\r\n");
				} else if (inline.startsWith("B")) { // denne ordre findes
					//ikke på en fysisk vægt
					System.out.println("B modtaget (ikke på en fysisk vægt)");
					String temp= inline.substring(2,inline.length());
					Scale.setBrutto(Double.parseDouble(temp));
					outstream.writeBytes("DB"+"\r\n");
				} else if (inline.startsWith("RM20 8 ")) {
					String[] temp = inline.split("\"");
					// temp bliver udskrevet på vaegten.
					String arg1 = temp[1];
					//	String arg2 = temp[3]; //For now we do not look at anything but the content of the first "" block
					//	String arg3 = temp[5]; //arg2 and arg3 can be used for that purpose later.
					String oprInput = scale.get(arg1);
					outstream.writeBytes("RM20 A \"" + oprInput + "\"" + "\r\n");
				} else if ((inline.startsWith("Q"))){
					System.out.println("");
					System.out.println("Q modtaget, program stoppet.");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
					System.exit(0);
				} else {
					outstream.writeBytes("ES"+"\r\n");
					input();
				}
			}
		} catch (Exception e){
			System.out.println("Exception: "+e.getMessage() + e.getCause() + e.getStackTrace());
			try {
				outstream.writeBytes("ES"+"\r\n");
				input();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				System.in.close();
				System.out.close();
				instream.close();
				outstream.close();
			} catch (IOException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	public void outstream(String out) {
		try {
			outstream.writeBytes(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
