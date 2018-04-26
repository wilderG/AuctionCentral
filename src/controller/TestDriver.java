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
		User user;
		Calendar calendar;
		
		
		/* to create a user and auction
		   uncomment and add auction name and username */
		
//        Auction auction = new Auction("Test Auction 1");
//		user = new User("tester", auction);
//		calendar = new Calendar("Calendar", auction);
//				
//		storage.storeUser(user);
//		storage.storeCalendar(calendar);
//		storage.writeData();
		
		
		
		
		/* to load a user and print details */
		user = storage.getUser("tester");
		calendar = storage.getCalendar();
		System.out.println(user);
		System.out.println(calendar);
		
//		/* change auction name */
//		user.myAuction.myName = "Changed Name";
//		storage.writeData();

//		
//		//get auction from storage using key
//		user = storage.getUser("tester");
//		calendar = storage.getCalendar();
//		
//		System.out.println(user);
//		System.out.println(calendar);
	
	}
	
}
