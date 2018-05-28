package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import model.AuctionCalendar;
import model.User;

/**
 * Stores an AuctionCalendar and a collection of Users. 
 * If the provided filename
 * does not exist then an empty file will be created. 
 * 
 * @author Jared Malone (5/3/2018)
 */
public class StorageIO implements Handler {

	/** Generated Serial Version UID */
	private static final long serialVersionUID = -6890061097946533747L;
	
	/** The local filename to load/save persistent storage. */
	private final String myFileName;
	
	/** Collection of users. */
	private HashMap<String, User> myUsers;	
	
	/** The program calendar. */
	private AuctionCalendar myCalendar;
	
	
	/**
	 * Creates a persistent storage handler for saving and loading the calendar
	 * and system users. If the specified file does not exist then it will be
	 * created. 
	 * @param theFileName is a file to load/save the data
	 */
	public StorageIO(final String theFileName) {
		myFileName = theFileName;
		loadData();
	}
	
	/**
	 * Creates a persistent storage handler for saving and loading the calendar
	 * and system users. If the specified file does not exist then it will be
	 * created. If boolean flag is true then existing data will be overwritten.  
	 * @param theFileName is a file to load/save the data
	 * @param theNewFileFlag existing data will be written over.
	 */
	public StorageIO(final String theFileName, final boolean theNewFileFlag) {
		myFileName = theFileName;
		
		if (theNewFileFlag) {
			initializeNewData();
		} else {
			loadData();	
		}
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
	 * This method overrides any existing AuctionCalendar and stores
	 * the new one.
	 * @param theCalendar
	 */
	@Override
	public void setCalendar(final AuctionCalendar theCalendar) {
		myCalendar = theCalendar;
		writeData();
	}
	
	
	/**
	 * Returns a stored user.
	 * @param theUsername username key for the user.
	 * @return the User object
	 */
	@Override
	public User getUser(final String theUsername) throws IllegalArgumentException {
		if (!myUsers.containsKey(theUsername)) {
			throw new IllegalArgumentException("User not found.");
		}
		
		return myUsers.get(theUsername);
	}
	
	
	/**
	 * Stores a user in persistent storage.
	 * @param theUsername the key to associate with the user
	 * @param theUser user object
	 */
	@Override
	public void storeUser(User theUser) {
		myUsers.put(theUser.getUsername(), theUser);
		writeData();
	}
	
	
	/**
	 * Writes the current state of all data to disk.
	 */
	public void writeData() {
		StorageCapsule data = new StorageCapsule(myUsers, myCalendar);
		
		try {
			FileOutputStream fileOut = new FileOutputStream(myFileName);
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
			FileInputStream fileIn = new FileInputStream(myFileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			data = (StorageCapsule) in.readObject();
			in.close();
			fileIn.close();
			
			myUsers = data.myUsers;
			myCalendar = data.myCalendar;
			
		} catch (IOException i) {
			initializeNewData();
		} catch (ClassNotFoundException e) {
			initializeNewData();
		}
		
	}


	/**
	 * This method is called if an existing file can not be read.
	 * Instantiates empty collections and then saves data to the new file.
	 */
	private void initializeNewData() {
		myUsers = new HashMap<>();
		myCalendar = new AuctionCalendar();
		writeData();
	}

}
