package services;

import contact.*;

public class AddService {
	
	public static void add(String lastname, String firstname, String phonenumber) {
		Contact c = new Contact(lastname, firstname, phonenumber);
		ContactMapper.insertContact(c);
	}
	
	public static void addFriend(String lastname, String firstname, String phonenumber, String identifiant_friend) {
		Contact c = new Contact(lastname, firstname, phonenumber);
		ContactMapper.insertFriend(c, Integer.parseInt(identifiant_friend));
	}

}
