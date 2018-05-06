package view;

import backend.StorageIO;
import model.AuctionManager;
import model.Bidder;
import model.NonProfitContact;
import model.User;

public class ForceNewTestUser {

	public static void main(String[] args) {

		StorageIO storage = new StorageIO("data/storage.dat");
		
		// add a Bidder(username, displayname) 
		// or NonProfitContact(username, displayname)
		User newUser = new Bidder("tester", "Test User");
		

		// stores and checks
		storage.storeUser(newUser);
		AuctionManager manager = new AuctionManager();
		User user = manager.getUser(newUser.getUsername());
		System.out.println(user.getDisplayName() + " (" + user.getUsername() + ") saved.");
		System.out.println("User type: " + user.getClass());
	}

}
