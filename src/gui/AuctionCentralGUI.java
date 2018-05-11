package gui;

import model.AuctionManager;
import model.Bidder;

public class AuctionCentralGUI {

	public static void main(String[] args) {
		// make a manager
		AuctionManager manager = new AuctionManager();
		
		// get a user
		Bidder bidder = (Bidder) manager.getUser("bidder1");

		// see if you can build a screen and display information
		// from the bidder
		
		
	}

}
