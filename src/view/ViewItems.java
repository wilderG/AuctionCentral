package view;

import java.util.Collection;
import java.util.Scanner;

import model.Auction;
import model.AuctionItem;

public class ViewItems {

	public int showItems(Scanner theScanner, Auction theAuction) {
		
		System.out.println("Auction with " + theAuction.getName() + ", " + theAuction.getDate() + ":");
		Collection<AuctionItem> items = theAuction.getAllItems();
		AuctionItem[] indexedItems = items.toArray(new AuctionItem[items.size()]);
		for (int count = 0; count < items.size(); count++) {
			AuctionItem item = indexedItems[count];
			System.out.println("\t" + (count + 1) + ". " + item.getDescription());
		}
		
		System.out.println("Would you like to place a bid? \n"
				+ "(If so, enter the number corresponding to an item. \n"
				+ " Otherwise, enter 0)\n");
		
		return theScanner.nextInt();
		
	}
	
}
