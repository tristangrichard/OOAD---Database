package test01917;

import daoimpl.MySQLRaavareBatchDAO;
import dto.RaavareBatchDTO;

import java.sql.SQLException;

import connector.Connector;
import daointerfaces.DALException;


public class TestRaavareBatch {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Raavarebatch nummer 3:");
		MySQLRaavareBatchDAO rb = new MySQLRaavareBatchDAO();
		try { System.out.println(rb.getRaavareBatch(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny raavarebatch med rb_id =  8");
		RaavareBatchDTO rbDTO = new RaavareBatchDTO(8, 3, 400.0);
		try { rb.createRaavareBatch(rbDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Raavarebatch nummer 8:");
		try { System.out.println(rb.getRaavareBatch(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af maengde for raavarebatch nummer 8");
		rbDTO.setMaengde(500.0);
		try { rb.updateRaavareBatch(rbDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavarebatch nummer 8:");
		try { System.out.println(rb.getRaavareBatch(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle raavarebatches:");
		try { System.out.println(rb.getRaavareBatchList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle raavarebatches med raavare nummer 3:");
		try { System.out.println(rb.getRaavareBatchList(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Raavarebatch nummer 9:");
		try { System.out.println(rb.getRaavareBatch(9)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
	}
}
