package test01917;

import daoimpl.MySQLProduktBatchDAO;
import dto.ProduktBatchDTO;

import java.sql.SQLException;

import connector.Connector;
import daointerfaces.DALException;

public class TestProduktBatch {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Produktbatch nummer 3:");
		MySQLProduktBatchDAO pb = new MySQLProduktBatchDAO();
		try { System.out.println(pb.getProduktBatch(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny produktbatch med pb_id = 6");
		ProduktBatchDTO pbDTO = new ProduktBatchDTO(6, 1, 3, null, null);
		try { pb.createProduktBatch(pbDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Produktbatch nummer 6:");
		try { System.out.println(pb.getProduktBatch(6)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af status for produktbatch nummer 6");
		pbDTO.setStatus(0);
		try { pb.updateProduktBatch(pbDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatch nummer 6:");
		try { System.out.println(pb.getProduktBatch(6)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatches:");
		try { System.out.println(pb.getProduktBatchList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatch nummer 7:");
		try { System.out.println(pb.getProduktBatch(7)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
	}
}
