package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;
import model.Auction;
import model.AuctionCalendar;
import model.User;

/**
 * Creates an object that loads stored data into memory. Call getUser to get the user
 * and its references to other models will be intact.
 * @author Jared
 *
 */
public class Storage {

//	private HashMap<UUID, Auction> myAuctions;
	private HashMap<String, User> myUsers;	
	private AuctionCalendar myCalendar;
	
	public Storage() {
		//instantiate HashMaps
//		myAuctions = new HashMap<>();
		myUsers = new HashMap<>();
		
		
		//load files
		loadData();
	}
	
//	//stores a new auction
//	public UUID storeAuction(final Auction theAuction) {
//		UUID key;
//		
//		//check for duplicate key before storing in map.
//		do {
//			key = UUID.randomUUID();
//		} while (myAuctions.containsKey(key));
//		
//		myAuctions.put(key, theAuction);
//		return key;
//	}
	
	//stores a new user
	public String storeUser(final User theUser) {
		String key = theUser.myName;
			
		myUsers.put(key, theUser);
		return key;
	}
		
	//returns a stored user
	public User getUser(final String username) {
		return myUsers.get(username);
	}
		
	//stores a calendar
	public void storeCalendar(final AuctionCalendar theCalendar) {
				
		myCalendar = theCalendar;
	}
			
	//returns a stored user
	public AuctionCalendar getCalendar() {
		return myCalendar;
	}
	
	//write current data to file
	public void writeData() {
		Capsule data = new Capsule(myUsers, myCalendar);
		
		try {
			FileOutputStream fileOut = new FileOutputStream("data/auction.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(data);
		
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		
//		try {
//			FileOutputStream fileOut = new FileOutputStream("data/user.dat");
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			
//			out.writeObject(myUsers);
//		
//			out.close();
//			fileOut.close();
//		} catch (IOException i) {
//			i.printStackTrace();
//		}
	}
		
	//load data files into memory
	@SuppressWarnings("unchecked")
	private void loadData() {
		Capsule data;
		
		try {
			FileInputStream fileIn = new FileInputStream("data/auction.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			data = (Capsule) in.readObject();
			in.close();
			fileIn.close();
			
			myUsers = data.users;
			myCalendar = data.calendar;
			
		} catch (IOException i) {
			//i.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
//		try {
//			FileInputStream fileIn = new FileInputStream("data/user.dat");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			
//			myUsers = (HashMap<String, User>) in.readObject();
//			in.close();
//			fileIn.close();
//			
//		} catch (IOException i) {
//			//i.printStackTrace();
//		} 
//			catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}

}
