package view;

import backend.StorageIO;
import model.AuctionManager;
import model.Bidder;
import model.User;
/**
 * Test user data class for checking if the project works.
 * 
 * @author 
 *
 */
public class ForceNewTestUser {
	/**
	 * The main method for running Test user Data.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		StorageIO storage = new StorageIO("data/storage.dat");
		
		// add a Bidder(username, displayname) 
		// or NonProfitContact(username, displayname)
		User newUser = new Bidder("tester", "Test User");
		

		// stores and checks
		storage.storeUser(newUser);
		AuctionManager manager = new AuctionManager();
		User user = manager.getUser(newUser.getUsername());
		System.out.println(user.getDisplayName() + 
				" (" + user.getUsername() + ") saved.");
		System.out.println("User type: " + user.getClass());
	}

}
