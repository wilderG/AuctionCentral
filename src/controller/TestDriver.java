package controller;

import model.Auction;
import model.Bidder;

/**
 * Test driver for persistent storage. You can create an auction and user the auction belongs to.
 * Then call addUser to store both objects. If you rerun the program and call testload, both
 * objects and their relationship will be present in memory.
 * @author Jared
 */
public class TestDriver {

	/**
	 * The storage object that will be used to save user and auction data.
	 */
	public static Storage storage;
			
	public static void main(String[] args) {
		storage = new Storage();
		
		
		/* to create a user and auction
		   uncomment and add auction name and username */
		
        //Auction auction = new Auction("Test Auction 1");
		//User user = new User("tester", auction);
		//addUser(user, auction);
		
		
		/* to load a user and print auction
		   uncomment and change parameter to username */
		
		testLoad("tester");
	
	}
	
	/**
	 * Adds the given user and auction to storage.
	 * @param theUser data that will be saved to storage
	 * @param theAuction data that will be saved to storage
	 */
	public static void addUser(final Bidder theUser, final Auction theAuction) {
		storage.storeAuction(theAuction);
		storage.storeUser(theUser);
		storage.writeData();
		
		System.out.println("Added auction: " + theAuction); 
		System.out.println("Added user: " + theUser);
	}

	
	/**
	 * Tests whether the user associated with the given username was saved successfully.
	 * @param theUserName associated with the user that will be retrieved from storage
	 */
	public static void testLoad(String theUserName) {
		//this entry is saved in the file
		Bidder user = storage.getUser(theUserName);
		
		//get auction from storage using key
		System.out.println(user);
	}
}
