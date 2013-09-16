package controller;

import java.util.*;
import daointerfaces.DALException;
import dto.ProduktBatchDTO;
import dto.ProduktBatchKompDTO;
import dto.ReceptKompDTO;
import funktionalitet.*;

public class AktivProduktBatchControl {
	private ProduktBatchDTO pb_dto;
	private String terminal;

	private IProduktBatchLogic pbl;
	private IRaavareBatchLogic rbl;
	private IReceptLogic rcptl;

	private ProduktBatchKompDTO activeProduktBatchKomponent;

	public AktivProduktBatchControl(int pbId){
		try {
			pbl = new ProduktBatchLogic();
			rbl = new RaavareBatchLogic();
			rcptl = new ReceptLogic();
			pb_dto = pbl.getProduktBatch(pbId);
			if (pb_dto.getStart().getTime() == 0) {
				pb_dto.setStart(new Date());
				System.out.println("Starttid sat til: " + pb_dto.getStart());
			}
			pbl.updateProduktBatch(pb_dto.getPbId(), 1, pb_dto.getReceptId(), pb_dto.getStart(), pb_dto.getSlut());
		} catch (DALException e) {
			e.printStackTrace();
		}

	}

	public static boolean produktBatchIsDone(int pbId) throws DALException{
		if (new ProduktBatchLogic().getProduktBatch(pbId).getStatus() == 2){
			return true;
		}
		return false;
	}

	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public boolean raavareBatchNummerIsValid(int raavareBatchNr) {
		boolean goodRaavareBatchNr = false;
		int raavareId;
		
		System.out.println(raavareBatchNr);
		// Check if the raavare is on the recept komponent list.
		try {
			raavareId = rbl.getRaavareBatch(raavareBatchNr).getRaavareId();
			for (ReceptKompDTO rk_dto : rcptl.getReceptKompList(pb_dto.getReceptId())) {
				System.out.println(rk_dto.getRaavareId());
				if (rk_dto.getRaavareId() == raavareId){
					goodRaavareBatchNr = true;
				}
			}
		} catch (DALException e1) {
			System.out.println("Raavarebatchen findes ikke");
		}

		// if it is on the list, check if the raavare has already been weighed.
		if (goodRaavareBatchNr){
			try {
				ProduktBatchKompDTO tmpPbk_dto = pbl.getProduktBatchKomp(pb_dto.getPbId(), raavareBatchNr);
				if (tmpPbk_dto.getNetto() > 0) goodRaavareBatchNr = false;
			} catch (DALException e) {
				System.out.println("raavare batch nr ok. produkt batch komponent not created yet.");
			}
		}
		return goodRaavareBatchNr;
	}

	public double getNetto() throws DALException {
		int raavareBatchId = activeProduktBatchKomponent.getRbId();
		int raavareId = rbl.getRaavareBatch(raavareBatchId).getRaavareId();
		int receptId = pb_dto.getReceptId();
		double netto = rcptl.getReceptKomp(receptId, raavareId).getNomNetto();
		return netto;
	}

	public double getTolerence() throws DALException {
		int raavareBatchId = activeProduktBatchKomponent.getRbId();
		int raavareId = rbl.getRaavareBatch(raavareBatchId).getRaavareId();
		int receptId = pb_dto.getReceptId();
		double toelerence = rcptl.getReceptKomp(receptId, raavareId).getTolerance();
		return toelerence;
	}

	private int getRId(ProduktBatchKompDTO pbkDTO) throws DALException{
		int raavareId = rbl.getRaavareBatch(pbkDTO.getRbId()).getRaavareId();
		return raavareId;
	}

	public String getReceptName() throws DALException {
		return rcptl.getRecept(pb_dto.getReceptId()).getReceptNavn();
	}

	public void updateProduktBatchOnDatabase(double tara, double netto, int oprId) throws DALException {
		pbl.updateProduktBatchKomp(activeProduktBatchKomponent.getPbId(), activeProduktBatchKomponent.getRbId(), tara, netto, oprId);
		double maengde = rbl.getRaavareBatch(activeProduktBatchKomponent.getRbId()).getMaengde();
		double nyMaengde = maengde - netto;
		rbl.updateRaavareBatch(activeProduktBatchKomponent.getRbId(), getRId(activeProduktBatchKomponent), nyMaengde);
		activeProduktBatchKomponent.setNetto(netto);
		activeProduktBatchKomponent.setTara(tara);
	}

	public boolean isDone() throws DALException {
		List<ProduktBatchKompDTO> pbk_list = pbl.getProduktBatchKompList(pb_dto.getPbId());
		List<ReceptKompDTO> rk_list = rcptl.getReceptKompList(pb_dto.getReceptId());

		// easier way? maybe it works...
		if(pbk_list.size() == rk_list.size()){
			for(ProduktBatchKompDTO pbk_dto: pbk_list){
				if(!(pbk_dto.getNetto() > 0)){
					return false;
				}
			}
		} else return false;
		
		pb_dto.setStatus(2);
		pb_dto.setSlut(new Date());
		System.out.println("Status på produkt batch sat til: " + pb_dto.getStatus() + " slut tid sat til: " + pb_dto.getSlut());
		try {
			pbl.updateProduktBatch(pb_dto.getPbId(), pb_dto.getStatus(), pb_dto.getReceptId(),pb_dto.getStart(), pb_dto.getSlut());
		} catch (DALException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void startSessionWithRaavareBatch(int raavareBatchNr, int oprId) throws DALException {
		pbl.createProduktBatchKomp(pb_dto.getPbId(), raavareBatchNr, 0, 0, oprId);
		activeProduktBatchKomponent = new ProduktBatchKompDTO(pb_dto.getPbId(), raavareBatchNr, 0, 0, oprId);
	}


}