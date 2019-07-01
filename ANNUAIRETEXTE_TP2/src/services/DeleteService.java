package services;

import contact.Contact;
import contact.ContactMapper;

public class DeleteService {
	
	public static boolean delete(String identifiant) {
		if(!ContactMapper.getContacts_BD().isEmpty()) {
			for(Contact c : ContactMapper.getContacts_BD()) {
				if(c.getId() == Integer.parseInt(identifiant)) {
					ContactMapper.delete(c);
					return true;
				}
			}
		}
		
		return false;
	}
}
