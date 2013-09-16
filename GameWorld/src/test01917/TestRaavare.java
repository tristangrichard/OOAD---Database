package test01917;

import daoimpl.MySQLRaavareDAO;
import dto.RaavareDTO;

import java.sql.SQLException;

import connector.Connector;
import daointerfaces.DALException;


public class TestRaavare {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Raavare nummer 3:");
		MySQLRaavareDAO raavare = new MySQLRaavareDAO();
		try { System.out.println(raavare.getRaavare(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny raavare med raavare_id =  8");
		RaavareDTO raavareDTO = new RaavareDTO(8,"Sukker","Gode Sager A/S");
		try { raavare.createRaavare(raavareDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Raavare nummer 8:");
		try { System.out.println(raavare.getRaavare(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af navn for raavare nummer 8");
		raavareDTO.setRaavareNavn("Tofu");
		try { raavare.updateRaavare(raavareDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavare nummer 8:");
		try { System.out.println(raavare.getRaavare(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle raavarer:");
		try { System.out.println(raavare.getRaavareList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavare nummer 9:");
		try { System.out.println(raavare.getRaavare(9)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
	}
}
