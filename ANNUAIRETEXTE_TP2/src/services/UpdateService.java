package services;

import contact.Contact;
import contact.ContactMapper;

public class UpdateService {
	
	public static boolean edit(String identifiant, String toEdit, String value) {
		if(!ContactMapper.getContacts_BD().isEmpty()) {
			for(Contact c : ContactMapper.getContacts_BD()) {
				if(c.getId() == Integer.parseInt(identifiant)) {
					ContactMapper.update(c, toEdit, value);
					return true;
				}
			}
		}
		return false;
	}
	
	public static String findById(String identifiant) {
		Contact c = ContactMapper.findById(Integer.parseInt(identifiant));
		if(c == null) {
			return null;
		}
		return c.toString();
	}
}
