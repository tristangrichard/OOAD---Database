package scale;

import java.io.*;
import java.net.*;

public class RemoteConnect {
	private Socket socket;
	private BufferedReader r;
	private PrintWriter w;

	public RemoteConnect(String ip, int port) throws IOException {
		InetAddress address = InetAddress.getByName(ip);
		this.socket = new Socket(address, port);
		socket.setKeepAlive(true);
		r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		w = new PrintWriter(socket.getOutputStream(),true);
	}

	public String sendCommand(String cmd) throws IOException{
		String c = cmd + "\r\n";

		w.print(c);
		w.flush();
		System.out.println(cmd + " sendt");
		System.out.println("listening");
		c = r.readLine();
		return c;
	}

	public String sendRM20(String cmd) throws IOException {
		String c = "RM20 8 \"" + cmd + "\" \"\" \"&3\"\r\n";
		System.out.println("Sent: " + cmd);
		w.print(c);
		w.flush();
		
		String b = "";
		try{
		b = r.readLine();
		} catch (Exception e) {
			System.out.println("exception i readLine() af svar fra sendRM20(" + cmd + ")");
		}
		System.out.println(b);
		while (!(b.isEmpty())) {
			if (b.equals("RM20 B")) {
				String b2 = r.readLine();

				while (!(b2.isEmpty())) {
					if (b2.equals("RM20 C")) {
						return "";
					} else {
						return b2;
					}
				}
			} else if (b.startsWith("RM20 A")) {
				return b;
			} else {
				return "";
			}
		} 
		return "";
	}

//	public void taraScale() throws IOException {
//		String c;
//		sendCommand("D \"Tara\"");
//		while (!(c = r.readLine()).isEmpty()) {
//			if (c.equals("K C 3")) {
//				sendCommand("T");
//				return;
//			}
//		}
//	}

	public void closeConnection() {
		try {
			sendCommand("Q");
			r.close();
			w.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void emptyReader() {
		try {
			String s = r.readLine();
			System.out.println("tømte reader og fandt: " + s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String listenForInput() {
		String s = "";
		try{
			s = r.readLine();
			System.out.println("listening");
			} catch (Exception e) {
				System.out.println("listen failed");
			}
		return s;
	}
}
