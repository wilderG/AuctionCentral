package controller;

import model.Auction;
import model.Calendar;
import model.User;

/**
 * Test driver for persistent storage. You can create an auction and user the auction belongs to.
 * Then call addUser to store both objects. If you rerun the program and call testload, both
 * objects and their relationship will be present in memory.
 * @author Jared
 */
public class TestDriver {

	public static Storage storage;
			
	public static void main(String[] args) {
		storage = new Storage();
		
		
		/* to create a user and auction
		   uncomment and add auction name and username */
		
        Auction auction = new Auction("Test Auction 1");
		User user = new User("tester", auction);
		Calendar calendar = new Calendar("Calendar", auction);
		//		User user2 = new User("admin", auction);
				
//		storage.storeUser(user);
//		storage.storeCalendar(calendar);
//		storage.writeData();
		
//		System.out.println("Added auction: " + auction); 
//		System.out.println("Added user: " + user);
//		addUser(user2, auction);
//		
		
		/* to load a user and print auction
		   uncomment and change parameter to username */
		
		testLoad("tester");
//		testLoad("admin");
		
		storage.getUser("tester").myAuction.myName = "Changed NameAgain";
//				
		testLoad("tester");
//		testLoad("admin");
//		
		storage.writeData();
		
		
	
	}
	
	//add user and auction to storage
	public static void addUser(final User user, final Auction auction) {
//		storage.storeAuction(auction);
		storage.storeUser(user);
		storage.writeData();
		
//		System.out.println("Added auction: " + auction); 
		System.out.println("Added user: " + user);
	}

	public static void testLoad(String theUserName) {
		//this entry is saved in the file
		User user = storage.getUser(theUserName);
		
		//get auction from storage using key
		System.out.println(user);
		System.out.println(storage.getCalendar());
	}
}
