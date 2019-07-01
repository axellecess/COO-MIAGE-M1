package contact;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

import services.*;

public class Menu {
	
	public Menu() {
		answers = new ArrayList<String>();
		answers.add("1");
		answers.add("2");
		answers.add("3");
		answers.add("4");
		answers.add("5");
		answers.add("6");
		answers.add("7");
		answers.add("8");
	}
	
	ArrayList<String> answers;
	
	public void start() {
		boolean status = true;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bienvenue dans l'annuaire de contacts");
		System.out.println("Que voulez vous faire ?\n");
		System.out.println("(1) Afficher les contacts");
		System.out.println("(2) Afficher les amis d'un contact");
		System.out.println("(3) Ajouter");
		System.out.println("(4) Ajouter un ami");
		System.out.println("(5) Chercher un contact");
		System.out.println("(6) Modifier");
		System.out.println("(7) Supprimer");
		System.out.println("(8) Quitter\n");
		
		String answer = sc.nextLine();
		
		while(status) {
			/*System.out.println("\nQue voulez vous faire ?\n");
			System.out.println("(1) Afficher");
			System.out.println("(2) Ajouter");
			System.out.println("(3) Chercher un contact");
			System.out.println("(4) Modifier");
			System.out.println("(5) Supprimer");
			System.out.println("(6) Quitter\n");
			answer = sc.nextLine();*/
			
			if(this.answers.contains(answer)) {
				if (answer.equals("8")) {
					System.out.println("Au plaisir de vous revoir");
					status = false;
				}
				else {
					if(answer.equals("1")) {
						ArrayList<String> contacts = SearchService.displayAll();
						if(!contacts.isEmpty()) {
							for(String c : contacts) {
								System.out.println(c);
							}
							System.out.println("\nQue voulez vous faire ensuite ?");
							answer = sc.nextLine();
						}
						else {
							System.out.println("Aucun contact à afficher");
							System.out.println("\nQue voulez vous faire ensuite ?");
							answer = sc.nextLine();
						}
					}
					
					else if(answer.equals("2") ) {
						System.out.println("\nEcrivez son identifiant :");
						String identifiant = sc.nextLine();
						ArrayList<String> contacts = SearchService.searchFriends(identifiant);
						
						if(!contacts.isEmpty()) {
							for(String c : contacts) {
								System.out.println(c);
							}
							System.out.println("\nQue voulez vous faire ensuite ?");
							answer = sc.nextLine();
						}
						else {
							System.out.println("Le contact n'existe pas ou Le contact n'a pas d'amis");
							System.out.println("\nQue voulez vous faire ensuite ?");
							answer = sc.nextLine();
						}
					}
					
					else if(answer.equals("3") ) {
						System.out.println("Nom");
						String lastname = sc.nextLine();
						System.out.println("Prénom");
						String firstname = sc.nextLine();
						System.out.println("Numéro de téléphone");
						String phonenumber = sc.nextLine();
						
						AddService.add(lastname, firstname, phonenumber); 
						System.out.println("Contact ajouté");
						System.out.println("\nQue voulez vous faire ensuite ?");
						answer = sc.nextLine();
					}
					
					else if(answer.equals("4") ) {
						System.out.println("Nom");
						String lastname = sc.nextLine();
						System.out.println("Prénom");
						String firstname = sc.nextLine();
						System.out.println("Numéro de téléphone");
						String phonenumber = sc.nextLine();
						System.out.println("Identifiant du contact qui est ami");
						String identifiant_friend = sc.nextLine();
						
						AddService.addFriend(lastname, firstname, phonenumber, identifiant_friend);
						System.out.println("Ami ajouté");
						System.out.println("\nQue voulez vous faire ensuite ?");
						answer = sc.nextLine();
					}
										
					else if(answer.equals("5") ) {
						System.out.println("\nEcrivez son nom :");
						String name = sc.nextLine();
						ArrayList<String> contacts = SearchService.search(name);
						
						if(!contacts.isEmpty()) {
							for(String c : contacts) {
								System.out.println(c);
							}
							System.out.println("\nQue voulez vous faire ensuite ?");
							answer = sc.nextLine();
						}
						else {
							System.out.println("Le contact n'existe pas");
							System.out.println("\nQue voulez vous faire ensuite ?");
							answer = sc.nextLine();
						}
					}
					
					else if(answer.equals("6") ) {
						System.out.println("Identifiant du contact");
						String identifiant = sc.nextLine();
						String contact = UpdateService.findById(identifiant);
						if(contact == null) {
							System.out.println("Le contact n'existe pas");
						}
						else {
							System.out.println("\nQue voulez vous modifiez ?");
							System.out.println("(1) Nom");
							System.out.println("(2) Prénom");
							System.out.println("(3)Numéro de téléphone");
							answer = sc.nextLine();
							boolean result = false;
							
							if(answer.equals("1")) {
								System.out.println("\nEcrivez le nom :");
								String lastname = sc.nextLine();
								result = UpdateService.edit(identifiant, "lastname", lastname );
							}
							else if(answer.equals("2")) {
								System.out.println("\nEcrivez le prénom :");
								String firstname = sc.nextLine();
								result = UpdateService.edit(identifiant, "firstname", firstname );
							}
							else if(answer.equals("3")) {
								System.out.println("\nEcrivez le numéro de téléphone:");
								String numberphone = sc.nextLine();
								result = UpdateService.edit(identifiant, "numberphone", numberphone );
							}
							
							if(result) {
								System.out.println("Contact modifié");
							}
							else {
								System.out.println("Le contact n'existe pas");
							}
						}
						System.out.println("\nQue voulez vous faire ensuite ?");
						answer = sc.nextLine();
					}
					
					else if(answer.equals("7") ) {
						System.out.println("Identifiant du contact");
						String identifiant = sc.nextLine();
						boolean result = DeleteService.delete(identifiant);
						
						if(result) {
							System.out.println("Contact supprimé");
						}
						else {
							System.out.println("Le contact n'existe pas");
						}
						
						System.out.println("\nQue voulez vous faire ensuite ?");
						answer = sc.nextLine();
					}
				}
			}
			else {
				System.out.println("Réessayez encore\n");
				answer = sc.nextLine();
			}
		}
		sc.close();
		return;
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.start();
	}
	

}
