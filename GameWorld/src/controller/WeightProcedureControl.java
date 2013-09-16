package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import daointerfaces.DALException;
import dto.BrugerDTO;

import funktionalitet.IOperatorLogic;
import funktionalitet.OperatorLogic;

import scale.RemoteConnect;

public class WeightProcedureControl {
	private RemoteConnect connection;
	private Scanner scan = new Scanner(System.in);
	private double tara;
	private double netto;
	private IOperatorLogic oprLogic;
	private BrugerDTO operatoer;
	private AktivProduktBatchControl aktivProduktBatch;

	// Julian
	//	private String ip = "10.16.173.140";

	// Jonas
	//	private String ip = "10.16.172.44";

	// Peter
	//	private String ip = "10.16.172.130";

	// Emil
	//	private String ip = "10.16.161.201";

	//	private int port = 4567;

	// Weight ip and port
	private String ip = "169.254.2.3";
	private int port = 8000;

	public WeightProcedureControl() throws IOException {
		ip = getIpFromUser();
		port = getPortFromUser();
		System.out.println("IP: " + ip + " - Port: " + port + "\n");
		connection = new RemoteConnect(ip, port);
		try {
			oprLogic = new OperatorLogic();
			weighing();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	// the weighing procedure
	private void weighing() throws DALException {
		try {
			while (true) {
				// Set weight in communication mode
				connection.sendCommand("K 3");
				connection.emptyReader();

				// 3.-4.
				while(!identifyOperator());

				// 5.-6.
				startSessionFromProduktBatchNumber();

				do {
					// 7.
					checkIfWeightIsEmpty();

					// 8.
					taraScale();

					// 9.
					getInput("Tryk ok og placer tara b");

					// 10. 11. 12.
					getWeightForContainerAndTaraWeight();
					
					// 13
					opretRaavareBatchFraNr();

					// 14 - delta bar.
					maengdeAfvej();

					// 15 & 16
				} while (!aktivProduktBatch.isDone());
				
				// Besked om at pb'en er færdig
				getInput("Produktbatch færdig");

				// 17
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	private void checkIfWeightIsEmpty() throws IOException {
		String s = "";
		s = getInput("Tryk ok og kont ubelast");
		System.out.println(s);
		// Vis vægt
		connection.sendCommand("DW");
		// vent på godkendelse
		s = connection.listenForInput();
		System.out.println(s);
		
	}

	private void getWeightForContainerAndTaraWeight() throws IOException {
		String s = "";
		connection.sendCommand("DW");
		s = connection.listenForInput();
		System.out.println(s);
		String taraWeight = "";
		do{
			taraWeight = this.taraScale();
		} while(taraWeight.charAt(0) !='T');
		System.out.println(taraWeight);
		tara = Double.parseDouble(taraWeight.split(" ")[taraWeight.split(" ").length-2]);
		System.out.println("Vægten af tara-beholderen er " + tara + " kg");
		
	}

	private void maengdeAfvej() throws IOException, DALException {
		double tolVaegt = 0.0, nettoTarget = 0.0;
		String s = "";
		do {
			nettoTarget = aktivProduktBatch.getNetto();
			tolVaegt = Double.parseDouble(new DecimalFormat("#.####").format(aktivProduktBatch.getTolerence() *nettoTarget).replaceAll(",", "."));
			System.out.println("Komp-vægt: " + nettoTarget + " - tolVaegt: " + tolVaegt);
			getInput("Tryk ok og afvej " + nettoTarget);
			s = connection.sendCommand("P121 " + nettoTarget + " kg " + tolVaegt + " kg " +	tolVaegt + " kg");
			System.out.println("Modtog: " + s);
			// wait for transfer to be pushed
			s = connection.listenForInput();
			System.out.println(s);

			// get the netto weight
			String nettoWeight = connection.sendCommand("S");
			System.out.println(nettoWeight);

			// Reset deltabar
			s = connection.sendCommand("P120");
			System.out.println("Modtog: " + s);

			// Format weight
			System.out.println(nettoWeight.split(" ")[nettoWeight.split(" ").length-2]);
			netto = Double.parseDouble(nettoWeight.split(" ")[nettoWeight.split(" ").length-2]);

			// check if the netto weight is within the tolerable limits.
		} while (netto > nettoTarget + tolVaegt || netto < nettoTarget - tolVaegt);

		// Update database
		aktivProduktBatch.updateProduktBatchOnDatabase(tara, netto, operatoer.getOprId());
		
		// Besked om at afvejningen er registreret
		getInput("Afvejning registreret");

	}

	private void opretRaavareBatchFraNr() throws DALException {
		int raavareBatchNr = 0;
		do {
			try {

				String input = getInput("Indtast raavarebatch-nr.");
				while(input.equals("")){ input = connection.listenForInput(); }
				raavareBatchNr = Integer.parseInt(input);

			} catch (NumberFormatException e) {
				System.out.println("Dårligt nummer");
			} catch (IOException e) {
				System.out.println("Dårligt svar");
			}
		} while (!aktivProduktBatch.raavareBatchNummerIsValid(raavareBatchNr));

		// Start a session with the valid raavareBatchNr.
		aktivProduktBatch.startSessionWithRaavareBatch(raavareBatchNr, operatoer.getOprId());
		System.out.println(raavareBatchNr);
	}

	private void startSessionFromProduktBatchNumber() throws DALException {
		String receptName = "";
		int pbId = 0; boolean ugyldig = false;
		while(pbId == 0) { // inputvalidering
			try {
				if(!ugyldig) {
					pbId = Integer.parseInt(getInput("Indtast produktBatch nr"));
				} else {
					pbId = Integer.parseInt(getInput("Ugyldigt nr, tast pb-nr"));
				}
				while(AktivProduktBatchControl.produktBatchIsDone(pbId)) {
					pbId = Integer.parseInt(getInput("Færdig pb, tast pb-nr"));
				}
			} catch (Exception e) {
				pbId = 0;
				ugyldig = true;
			}
		}
		aktivProduktBatch = new AktivProduktBatchControl(pbId);
		receptName = aktivProduktBatch.getReceptName();
		try {
			getInput(receptName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean identifyOperator() throws IOException {
		boolean goodOperator = false;
		do{
			try {
				// get input oprid
				String opIdSvar = getInput("Opr. ID");
				int operatorId = 0;
				if(opIdSvar.equals("RM20 B")) return false;
				else operatorId = Integer.parseInt(opIdSvar);
				// fetch operator from id
				operatoer = oprLogic.getOperatoer(operatorId);
				if (!operatoer.getRolle().equalsIgnoreCase("inaktiv")) {
					goodOperator = true;
				} else {
					System.out.println("Inaktiv operatoer");
				}
			} catch (Exception e) {
				System.out.println("Dårlig operatoer");
			}
		} while (!goodOperator);
		// get confirmation on name
		String response = getInput("Er du " + operatoer.getOprNavn() + "?");
		if(response.equals("RM20 B")) return false;
		return true;
	}

	private String getInput(String msg) throws IOException {
		String str = "";
		str = connection.sendRM20(msg);
		System.out.println("RM20 resp: " + str);
		String[] tmp = str.split("\"");
		if (tmp.length >= 2) {
			System.out.println(tmp[1]);
			return tmp[1];
		} else {
			return "";
		}
	}

	private String taraScale() throws IOException {
		return connection.sendCommand("T");
	}

	private void closeConnection() {
		connection.closeConnection();

	}

	private String getUserStringUsingPrompt(String prompt){
		String str = "";
		while (str.isEmpty()) {
			System.out.print(prompt);
			str = scan.next();
		}
		return str;
	}

	// returns validated port number
	private int getPortFromUser() {
		int port = 111111;
		while (port > 65535 || port < 0){
			String stringPort = getUserStringUsingPrompt("Please enter port number of weight");
			try {
				port = Integer.parseInt(stringPort);
			} catch (NumberFormatException e){
				e.printStackTrace();
			}
		}
		return port;
	}

	// returns validated ip adress
	private String getIpFromUser(){
		String ip = "";
		Pattern pattern;
		Matcher matcher;
		final String IPADDRESS_PATTERN = 
				"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		do {
			ip = getUserStringUsingPrompt("Please enter IP of weight");
			matcher = pattern.matcher(ip);
		} while (!matcher.matches());
		return ip;
	}
}
