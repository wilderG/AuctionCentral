package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;
import model.Auction;
import model.Bidder;

/**
 * Creates an object that loads stored data into memory. Call getUser to get the user
 * and its references to other models will be intact.
 * @author Jared
 *
 */
public class Storage {
	
	/**
	 * Constant for the file path where the user data will be saved.
	 */
	private static String USER_DATA_FILE_PATH = "data/user.dat";
	
	
	/**
	 * Constant for the file path were the auction data will be saved.
	 */
	private static String AUCTION_DATA_FILE_PATH = "data/user.dat";

	/**
	 * Hashmap for each auction that will be saved and its respective UUID code.
	 */
	private HashMap<UUID, Auction> myAuctions;
	
	/**
	 * Hashmap of each user and its respective username. 
	 */
	private HashMap<String, Bidder> myUsers;	
	
	public Storage() {
		//instantiate HashMaps
		myAuctions = new HashMap<>();
		myUsers = new HashMap<>();
		
		//load files
		loadData();
	}
	
	/**
	 * Stores the given auctions data into the myAuctions map.
	 * @param theAuction for which a UUID will be generated and stored in myAuctions
	 * @return The generated UUID for theAuction
	 */
	public UUID storeAuction(final Auction theAuction) {
		UUID key;
		
		//check for duplicate key before storing in map.
		do {
			key = UUID.randomUUID();
		} while (myAuctions.containsKey(key));
		
		myAuctions.put(key, theAuction);
		return key;
	}
	
	/**
	 * Stores a new user into myUsers.
	 * @param theUser who will be added to myUsers
	 * @return the username for theUser
	 */
	public String storeUser(final Bidder theUser) {
		String key = theUser.getUsername();
			
		myUsers.put(key, theUser);
		return key;
	}
		
	/**
	 * Returns the user object for the given username.
	 * @param theUsername that will be used to retrieve a user
	 * @return The user associated with the given username
	 */
	public Bidder getUser(final String theUsername) {
		return myUsers.get(theUsername);
	}
		
	/**
	 * Writes the current data to the output file.
	 */
	public void writeData() {
		try {
			FileOutputStream fileOut = new FileOutputStream(AUCTION_DATA_FILE_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(myAuctions);
		
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream(USER_DATA_FILE_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(myUsers);
		
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
		
	/**
	 * Loads the data files into memory.
	 */
	@SuppressWarnings("unchecked")
	private void loadData() {
		try {
			FileInputStream fileIn = new FileInputStream(AUCTION_DATA_FILE_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			myAuctions = (HashMap<UUID, Auction>) in.readObject();
			in.close();
			fileIn.close();
			
		} catch (IOException i) {
			//i.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fileIn = new FileInputStream(USER_DATA_FILE_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			myUsers = (HashMap<String, Bidder>) in.readObject();
			in.close();
			fileIn.close();
			
		} catch (IOException i) {
			//i.printStackTrace();
		} 
			catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
