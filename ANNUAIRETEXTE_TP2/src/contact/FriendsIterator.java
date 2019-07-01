package contact;

import java.util.ArrayList;
import java.util.Iterator;

public class FriendsIterator implements Iterator<Contact> {
	
	private ArrayList<Contact> friends;
	private int cursor;
	
	public FriendsIterator(ArrayList<Contact> friends) {
		cursor = 0;
		this.friends = friends;
	}
	
	@Override
	public boolean hasNext() {
		if(cursor < friends.size()) {
			return true;
		}
		return false;
	}

	@Override
	public Contact next() {
		if(this.hasNext()) {
			return friends.get(cursor++);
		}
		return null;
	}
}
