package contact;

import java.util.ArrayList;

public class Contact {
	
	public Contact(String n, String f, String pn) {
		this.lastname = n;
		this.firstName = f;
		this.phoneNumber = pn;
		this.friends = new ArrayList<Contact>();
	}
	
	int id;
	String lastname;
	String firstName;
	String phoneNumber;
	ArrayList<Contact> friends;
	FriendsIterator fit;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public FriendsIterator getFriends() {
		// TODO Auto-generated method stub
		return fit;
	}
	
	public void addFriend(Contact c) {
		this.friends.add(c);
		fit = new FriendsIterator(this.friends);
	}
	
	public String toString() {
		return this.lastname + " " + this.firstName + " " + this.phoneNumber; 
	}

	

}
