package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import model.AuctionCalendar;
import model.User;

/**
 * Creates an object that loads stored data into memory. Call getUser to get the user
 * and its references to other models will be intact.
 * @author Jared
 *
 */
public class StorageIO implements Handler {

	/** Generated Serial Version UID */
	private static final long serialVersionUID = -6890061097946533747L;
	
	/** The local filename to load/save persistent storage. */
	private static final String fileName = "data/storage.dat";
	
	/** Collection of users. */
	private HashMap<String, User> myUsers;	
	
	/** The program calendar. */
	private AuctionCalendar myCalendar;
	
	
	/**
	 * Creates a persistent storage handler for saving and loading the calendar
	 * and system users.
	 */
	public StorageIO() {
		loadData();
	}
	
	
	/**
	 * Returns the AuctionCalendar from storage.
	 * @return the AuctionCalendar
	 */
	@Override
	public AuctionCalendar getCalendar() {
		return myCalendar;
	}

	
	/**
	 * Returns a stored user.
	 * @param theUsername username key for the user.
	 * @return the User object
	 */
	public User getUser(final String theUsername) {
		return myUsers.get(theUsername);
	}
	
	
	/**
	 * Stores a user in persistent storage.
	 * @param theUsername the key to associate with the user
	 * @param theUser user object
	 */
	@Override
	public void storeUser(String theUsername, User theUser) {
		myUsers.put(theUsername, theUser);
	}
	
	
	/**
	 * Writes the current state of all data to disk.
	 */
	public void writeData() {
		StorageCapsule data = new StorageCapsule(myUsers, myCalendar);
		
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(data);
		
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
		
	
	/**
	 * Loads stored data file. If file cannot be loaded then
	 * initializeNewData() is called. 
	 */
	private void loadData() {
		StorageCapsule data;
		
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			data = (StorageCapsule) in.readObject();
			in.close();
			fileIn.close();
			
			myUsers = data.users;
			myCalendar = data.calendar;
			
		} catch (IOException i) {
			initializeNewData();
		} catch (ClassNotFoundException e) {
			initializeNewData();
		}
		
	}


	/**
	 * Instantiates empty data objects.
	 */
	private void initializeNewData() {
		myUsers = new HashMap<>();
		myCalendar = new AuctionCalendar();
	}

}
