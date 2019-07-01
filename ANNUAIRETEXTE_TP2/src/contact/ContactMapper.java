package contact;

import java.sql.*;
import java.util.ArrayList;
public class ContactMapper {
	
	
	static Connection con;
	static int id ;
	static ArrayList<Contact> contacts_BD;
	
	
	public static ArrayList<Contact> getContacts_BD() {
		return contacts_BD;
	}

	public static void setConnection(Connection connection) {
		con = connection;
		id = 0;
		contacts_BD = new ArrayList<Contact>();
	}
	
	public static Contact findById(int identifiant) {
		Contact c = null;
		try {
			String sql = "SELECT * FROM DIRECTORY WHERE id =(?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, identifiant);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
		        String lastname = rs.getString("lastname");
		        String firstname = rs.getString("firstname");
		        String phonenumber = rs.getString("phonenumber");
		         
		        c = new Contact(lastname, firstname, phonenumber);
		        c.setId(identifiant);
		    }
		    rs.close();
		    
		    for(Contact f : findFriends(identifiant)) {
		    	c.addFriend(f);
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public static ArrayList<Contact> findFriends(int id) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		try {
			String sql = "SELECT * FROM DIRECTORY JOIN FRIENDS ON DIRECTORY.ID = FRIENDS.ID_FRIEND_1 WHERE FRIENDS.ID_FRIEND_1 =(?) OR ID_FRIEND_2 = (?) ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, id);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				int identifiant = rs.getInt("id");
		        String lastname = rs.getString("lastname");
		        String firstname = rs.getString("firstname");
		        String phonenumber = rs.getString("phonenumber");
		         
		        Contact c = new Contact(lastname, firstname, phonenumber);
		        c.setId(identifiant);
		        contacts.add(c);
		    }
		    rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}
	
	public static ArrayList<Contact> find(String name) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		try {
			String sql = "SELECT * FROM DIRECTORY WHERE lastname =(?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				int identifiant = rs.getInt("id");
		        String lastname = rs.getString("lastname");
		        String firstname = rs.getString("firstname");
		        String phonenumber = rs.getString("phonenumber");
		         
		        Contact c = new Contact(lastname, firstname, phonenumber);
		        c.setId(identifiant);
		        contacts.add(c);
		    }
		    rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}
	
	public static void insertContact(Contact c) {
		try {
			id += 1;
			String sql = "INSERT INTO DIRECTORY  VALUES (?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, c.getLastname());
			pst.setString(3, c.getFirstName());
			pst.setString(4, c.getPhoneNumber());
			pst.executeUpdate();
			c.setId(id);
			contacts_BD.add(c);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertFriend(Contact c, int id_friend) {
		try {
			id += 1;
			String sql = "INSERT INTO DIRECTORY  VALUES (?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, c.getLastname());
			pst.setString(3, c.getFirstName());
			pst.setString(4, c.getPhoneNumber());
			pst.executeUpdate();
			c.setId(id);
			contacts_BD.add(c);
			
			Contact c_friend = findById(id_friend);
			c.addFriend(c_friend);
			c_friend.addFriend(c);
			
			sql = "INSERT INTO FRIENDS  VALUES (?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, id_friend);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(Contact c, String toEdit, String value) {
		try {
			String sql = null;
			if(toEdit.equals("lastname")) {
				sql = "UPDATE DIRECTORY SET lastname = (?) where id = (?)";
			}
			else if(toEdit.equals("firstname")) {
				sql = "UPDATE DIRECTORY SET firstname = (?) where id = (?)";
			}
			else if(toEdit.equals("numberphone")) {
				sql = "UPDATE DIRECTORY SET numberphone = (?) where id = (?)";
			}
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setString(1, value);
			pst.setInt(2, c.getId());
			pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void delete(Contact c) {
		try {
			String sql = "DELETE FROM DIRECTORY WHERE id =(?)";
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setInt(1, c.getId());
			pst.executeQuery();
			
			contacts_BD.remove(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Contact> display() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		try {
			String sql = "SELECT * FROM DIRECTORY";
			Statement stmt;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
		        //Retrieve by column name
				int identifiant = rs.getInt("id");
		        String lastname = rs.getString("lastname");
		        String firstname = rs.getString("firstname");
		        String phonenumber = rs.getString("phonenumber");
		         
		         Contact c = new Contact(lastname, firstname, phonenumber);
		         c.setId(identifiant);
		         contacts.add(c);
		    }
		    rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contacts;
		
	}

}
