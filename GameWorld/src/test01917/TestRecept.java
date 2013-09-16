package test01917;

import daoimpl.MySQLReceptDAO;
import dto.ReceptDTO;

import java.sql.SQLException;

import connector.Connector;
import daointerfaces.DALException;

public class TestRecept {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }

		System.out.println("Recept nummer 3:");
		MySQLReceptDAO recept = new MySQLReceptDAO();
		try { System.out.println(recept.getRecept(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }

		System.out.println("Indsaettelse af ny recept med recept_id =  4");
		ReceptDTO receptDTO = new ReceptDTO(4,"Kage");
		try { recept.createRecept(receptDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	

		System.out.println("Recept nummer 4:");
		try { System.out.println(recept.getRecept(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }

		System.out.println("Opdatering af navn for recept nummer 4");
		receptDTO.setReceptNavn("Stegt tofu");
		try { recept.updateRecept(receptDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }

		System.out.println("Recept nummer 4:");
		try { System.out.println(recept.getRecept(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }

		System.out.println("Alle recepter:");
		try { System.out.println(recept.getReceptList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Recept nummer 5:");
		try { System.out.println(recept.getRecept(5)); }
		catch (DALException e) { System.out.println(e.getMessage()); }	

	}
}
