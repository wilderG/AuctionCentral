package view;

import java.math.BigDecimal;
import java.util.Scanner;

import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bid;
import model.Bidder;
import model.NewBidRequest;

public class ViewBidMenu {

	public void placeBid(Bidder theUser, AuctionItem theItem, Scanner theScanner, AuctionManager theManager, Auction theAuction) {
		System.out.println("Bid Menu for Item: " + theItem.getDescription());
		System.out.println("     Minimum bid: $" + theItem.getMinimumAcceptableBidValue());
		System.out.println();
		System.out.println("What is the bid amount you would like to place for the Item?");
		
		boolean invalidResponse = false;
	
		BigDecimal bidAmount = BigDecimal.ZERO;
		do {
			try {
				System.out.print("Amount: ");
				double value = theScanner.nextDouble();
				bidAmount = BigDecimal.valueOf(value);
				invalidResponse = false;
			} catch (Exception e) {
				invalidResponse = true;
				System.out.println("The given value is not valid!");
				System.out.println("Please try again");
			}
			if (bidAmount.doubleValue() < theItem.getMinimumAcceptableBidValue().doubleValue()) {
				System.out.println("The given bid must be greater than or equal to the minimum bid for the item.");
				System.out.println("Please try again");
			}
		} while (invalidResponse || bidAmount.doubleValue() < theItem.getMinimumAcceptableBidValue().doubleValue());
		NewBidRequest theRequest = new NewBidRequest(theUser, theAuction, theItem, bidAmount);
		Bid userBid = theManager.processNewBid(theRequest);
		System.out.println("Bid Successfully placed!");
		System.out.println("Press any key to continue");
		theScanner.next();
	}

}
