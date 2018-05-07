package view;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

import model.Auction;
import model.AuctionManager;
import model.Bidder;
import model.NonProfitContact;
import model.User;

public class ViewAuction {

	public int showAllAuctions(Scanner theScanner, User theUser, AuctionManager theManager) {
		if (theUser instanceof NonProfitContact) {
			nonProfitIntroMessage(theUser);
		} else {
			bidderIntroMessage();
		}
		
		Collection<Auction> auctionCollection = null;
		
		if (theUser instanceof NonProfitContact) {
			auctionCollection = theUser.getMyAuctions();
		} else {
			auctionCollection = theManager.getAvailableAuctions((Bidder) theUser);
			if (auctionCollection.isEmpty()) {
				System.out.println("Sorry you're at the max amount of bids allowable");
				System.out.println("Please choose a different option");
				return 0;
			}
		}
		
		Iterator<Auction> iterator = auctionCollection.iterator();
		for (int count = 0; count < auctionCollection.size(); count++) {
			Auction auction = iterator.next();
			System.out.print("  " + (count + 1) + ". ");
			if (theUser instanceof NonProfitContact) {
				nonProfitOutputMessage(auction);
			} else {
				bidderOutputMessage(auction);
			}
		}
		
		System.out.println("Select an auction number for which you would like to view items\n"
				+ "(Enter 0 to return to the main menu)");
	
		return theScanner.nextInt();
	}
	
	private void bidderIntroMessage() {
		System.out.println("Auctions available to bid on:\n");
	}
	
	private void nonProfitIntroMessage(User theUser) {
		System.out.println("All auctions for " + theUser.getDisplayName() + "\n");
	}
	
	private void bidderOutputMessage(Auction theAuction) {
		System.out.println("With " + theAuction.getName() + ", " 
				+ theAuction.getDate() + " (" + theAuction.getAllItems().size() + " items available)");
	}
	
	private void nonProfitOutputMessage(Auction theAuction) {
		Locale local = Locale.US ; // Or Locale.US
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate( FormatStyle.LONG )
		                                       .withLocale(local) ;
		String dateOutput = theAuction.getDate().format( formatter );
		System.out.println(dateOutput + " (" + theAuction.getAllItems().size() + " Items)");
	}
	
	public int showBiddersAuctions(Scanner theScanner, Bidder theUser) {
		System.out.println("Please select an auction");
		
		Collection<Auction> auctionCollection = theUser.getMyAuctions();
		Iterator<Auction> iterator = auctionCollection.iterator();
		for (int count = 0; count < auctionCollection.size(); count++) {
			Auction auction = iterator.next();
			System.out.print("  " + (count + 1) + ". ");
//			Auction 1 with Non-Prof A: (4 bid on items) April 21, 2018
			System.out.println("Auction " + (count + 1) + " with "+ auction.getName() + ": " + 
			"(" + auction.getAllItemsWithBidder(theUser).size() + " bid on items) " 
					+ mainDriver.formatDate(auction.getDate()));
		}
		System.out.println("To return to the main menu enter 0");
		System.out.print("Choice: ");
//		System.out.println("Select an auction number for which you would like to view items\n"
//				+ "(Enter 0 to return to the main menu)");
	
		return theScanner.nextInt();
	}

}
