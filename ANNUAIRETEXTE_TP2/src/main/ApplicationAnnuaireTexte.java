package main;

import java.sql.*;
import java.util.Scanner;

import contact.ContactMapper;
import contact.Menu;

public class ApplicationAnnuaireTexte {

	public static void main(String[] args) {
		Statement stmt;
		Private p = new Private();
		String sql;
		
		
		try {
			
			String link = "jdbc:oracle:thin:@oracle.fil.univ-lille1.fr:1521:filora";
			System.out.println("Connexion à la bas de données...");
			Connection connection =  DriverManager.getConnection(link, p.getLogin(), p.getPassword());
			System.out.println("Connexion réussie...");
			
			ContactMapper.setConnection(connection);

			stmt = connection.createStatement();
			
			/*sql = "DROP TABLE FRIENDS";
			stmt.executeUpdate(sql);
			sql = "DROP TABLE DIRECTORY";
			stmt.executeUpdate(sql);*/
			
						
			sql = "CREATE TABLE DIRECTORY " +
							"(id INTEGER not NULL, " +
							" lastname VARCHAR(255), " + 
							" firstname VARCHAR(255), " + 
							" phonenumber VARCHAR(255), " + 
							" PRIMARY KEY ( id ))"; 

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE FRIENDS " +
					"(id_friend_1 INTEGER not NULL, " +
					" id_friend_2 INTEGER not NULL, " +
					" CONSTRAINT fk_foreign_contact FOREIGN KEY (id_friend_1) REFERENCES DIRECTORY(id),"+
					" CONSTRAINT fk_foreign_contact_friend FOREIGN KEY (id_friend_2) REFERENCES DIRECTORY(id),"+
					" PRIMARY KEY ( id_friend_1, id_friend_2 ))";
			
			stmt.executeUpdate(sql);
			
			Menu menu = new Menu();
			menu.start();
			
			
						
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
