package test01917;

import daoimpl.MySQLProduktBatchKompDAO;
import dto.ProduktBatchKompDTO;

import java.sql.SQLException;

import connector.Connector;
import daointerfaces.DALException;


public class TestProduktBatchKomp {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Produktbatchkompenent med produktbatch nummer 3 og raavarebatch nummer 3:");
		MySQLProduktBatchKompDAO pbk = new MySQLProduktBatchKompDAO();
		try { System.out.println(pbk.getProduktBatchKomp(3, 3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny produktbatchkomponent med produktbatch nummer 5 og raavarebatch nummer 4");
		ProduktBatchKompDTO pbkDTO = new ProduktBatchKompDTO(5, 4, 400.0, 2.0, 1);
		try { pbk.createProduktBatchKomp(pbkDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Produktbatchkompenent med produktbatch nummer 5 og raavarebatch nummer 4:");
		try { System.out.println(pbk.getProduktBatchKomp(5, 4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af nettov�gt for produktbatchkomponent med produktbatch nummer 5 og raavarebatch nummer 4");
		pbkDTO.setNetto(5.0);
		try { pbk.updateProduktBatchKomp(pbkDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatchkompenent med produktbatch nummer 5 og raavarebatch nummer 4:");
		try { System.out.println(pbk.getProduktBatchKomp(5, 4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatchkompenent:");
		try { System.out.println(pbk.getProduktBatchKompList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatchkomponenter med produktbatch nummer 3:");
		try { System.out.println(pbk.getProduktBatchKompList(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
		System.out.println("Produktbatchkomponent nummer 6:");
		try { System.out.println(pbk.getProduktBatchKomp(6,1)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
	}
}
