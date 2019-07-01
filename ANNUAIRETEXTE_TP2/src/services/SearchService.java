package services;

import java.util.ArrayList;
import java.util.Iterator;

import contact.Contact;
import contact.ContactMapper;
import contact.FriendsIterator;

public class SearchService {
	
	public static ArrayList<String> search(String name) {
		ArrayList<Contact> cs = ContactMapper.find(name);
		ArrayList<String> ctoString = new ArrayList<String>();
		
		if(!cs.isEmpty()){
			for(Contact c : cs) {
				ctoString.add(c.toString());
			} 
		}		
		return ctoString;
	}
	
	public static ArrayList<String> displayAll() {
		ArrayList<Contact> cs = ContactMapper.display();
		ArrayList<String> ctoString = new ArrayList<String>();
		
		if(!cs.isEmpty()){
			for(Contact c : cs) {
				ctoString.add(c.toString());
			} 
		}		
		return ctoString;
	}
	
	public static ArrayList<String> searchFriends(String identifiant) {
		Contact c = ContactMapper.findById(Integer.parseInt(identifiant));
		ArrayList<String> ftoString = new ArrayList<String>();
		while (c.getFriends().hasNext()) {
			ftoString.add(c.getFriends().next().toString());
		}
		/*
		ArrayList<Contact> fs = ContactMapper.findFriends(Integer.parseInt(identifiant));
		Iterator<Contact> it = new FriendsIterator(fs);
		ArrayList<String> ftoString = new ArrayList<String>();
		
		while(it.hasNext()) {
			ftoString.add(it.next().toString());
		}
		*/
		return ftoString;
	}

}
