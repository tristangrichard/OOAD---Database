package test01917;

import daoimpl.MySQLReceptKompDAO;
import dto.ReceptKompDTO;

import java.sql.SQLException;

import connector.Connector;
import daointerfaces.DALException;


public class TestReceptKomp {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Receptkompenent med recept nummer 3 og raavare nummer 1:");
		MySQLReceptKompDAO receptKomp = new MySQLReceptKompDAO();
		try { System.out.println(receptKomp.getReceptKomp(3, 1)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny receptkomponent med recept nummer 4 og raavare nummer 4");
		ReceptKompDTO receptKompDTO = new ReceptKompDTO(4, 4, 400.0, 2.0);
		try { receptKomp.createReceptKomp(receptKompDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Receptkompenent med recept nummer 4 og raavare nummer 4:");
		try { System.out.println(receptKomp.getReceptKomp(4, 4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af tolerance for receptkomponent med recept nummer 4 og raavare nummer 4");
		receptKompDTO.setTolerance(5.0);
		try { receptKomp.updateReceptKomp(receptKompDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Receptkompenent med recept nummer 4 og raavare nummer 4:");
		try { System.out.println(receptKomp.getReceptKomp(4, 4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle receptkomponenter:");
		try { System.out.println(receptKomp.getReceptKompList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle receptkomponenter med recept nummer 3:");
		try { System.out.println(receptKomp.getReceptKompList(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
		System.out.println("Receptkomp nummer 5:");
		try { System.out.println(receptKomp.getReceptKomp(5, 4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
	}
}
