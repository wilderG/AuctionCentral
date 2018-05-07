package view;

import java.util.Scanner;

import model.AuctionItem;
import model.AuctionManager;
import model.Bidder;

public class viewBidMenu {

	public void placeBid(Bidder theUser, AuctionItem theItem, Scanner theScanner, AuctionManager theManager) {
		System.out.println("Bid Menu for Item: " + theItem.getDescription());
		System.out.println("     Minimum bid: $" + theItem.getMinimumAcceptableBidValue());
		System.out.println();
		System.out.println("What is the bid amount you would like to place for the Item?");
		System.out.print("Choice: ");
		
		System.out.println("Bid Successfully placed!");
	}

}
