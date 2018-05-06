package view;

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import model.Auction;
import model.User;

public class ViewAuction {

	public int showAuctions(Scanner theScanner, User theUser) {
		System.out.println("Auctions available to bid on:\n");
		
		Collection<Auction> auctionCollection = theUser.getMyAuctions();
		Iterator<Auction> iterator = auctionCollection.iterator();
		for (int count = 0; count < auctionCollection.size(); count++) {
			Auction auction = iterator.next();
			System.out.println("\t" + (count + 1) + ". With " + auction.getName() + ", " 
			+ auction.getDate() + " (" + auction.getAllItems().size() + ")");
		}
		
		System.out.println("Select an auction number for which you would like to view items\n"
				+ "(Enter 0 to return to the main menu)");
	
		return theScanner.nextInt();
	}

}
