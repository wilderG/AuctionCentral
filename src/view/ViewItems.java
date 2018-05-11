package view;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

import model.Auction;
import model.AuctionItem;
import model.Bid;
import model.Bidder;

/**
 * Displaying view items.
 * 
 * @author Jared Malone
 * @author Jim Rosales
 * @author Steven Kenneth Golob
 * @author Wilder Emanuel Garcia Y Garcia
 * @author Yohei Sato
 * @version 5/8/2018
 */
public class ViewItems {
	/**
	 * showing items.
	 * 
	 * @param theScanner
	 * @param theAuction
	 * @return the number users pick.
	 */
	public int showItems(Scanner theScanner, Auction theAuction) {
		
		System.out.println("Auction with " +
		theAuction.getName() + ", " + theAuction.getDate() + ":");
		Collection<AuctionItem> items =
				theAuction.getAllItems();
		AuctionItem[] indexedItems = items.toArray(
				new AuctionItem[items.size()]);
		for (int count = 0; count < items.size(); count++) {
			AuctionItem item = indexedItems[count];
			BigDecimal minValue = item.getMinimumAcceptableBidValue();
			System.out.println("\t" + (count + 1) + 
					". " + item.getDescription() + "\n\t     Minimum Bid: " +
					MainDriver.formatCurrency(minValue));
		}
		
		System.out.println("\nWould you like to place a bid?\n"
				+ "(If so, enter the number corresponding to an item. \n"
				+ " Otherwise, enter 0)\n");

		return MainDriver.getNextInt(items.size());
		
	}
	/**
	 * showing items for non profit.
	 * 
	 * @param theScanner
	 * @param theAuction
	 */
	public void showItemsForNonProfAuction
	(Scanner theScanner, Auction theAuction) {
		String theDate = MainDriver.formatDate(theAuction.getDate());
		System.out.println("Items for your auction on " + theDate + ":");
		AuctionItem[] indexedItems = 
				theAuction.getAllItems().toArray(
						new AuctionItem[theAuction.getAllItems().size()]);
		for (int count = 0; count < indexedItems.length; count++) {
			System.out.print("   " + (count + 1) + ". ");
			AuctionItem item = indexedItems[count];
			System.out.println(item.getDescription() + " ("
			+ item.getBidCount() + " bids)");
			System.out.println("\tMinimum bid set at: " +
			MainDriver.formatCurrency(item.getMinimumAcceptableBidValue()));
		}
		System.out.println("\nPress enter to return to the main menu: ");
		theScanner.nextLine();

	}
	/**
	 * showing Bidder items for every auctions.
	 * 
	 * @param theScanner
	 * @param theUser
	 */
	public void showBiddersItemsForAllAuctions
	   (Scanner theScanner, Bidder theUser) {
		Collection<Auction> auctionCollection = theUser.getMyAuctions();

		for (Iterator<Auction> i=
				auctionCollection .iterator(); i.hasNext();) {
			Auction auction =  i.next();
			Collection<AuctionItem> auctionItems =
					auction.getAllItemsWithBidder(theUser);
			TreeSet<Bid> myBids = auction.getMyBids(theUser);

			System.out.println("   " + auction.getName() + ", "
			+ MainDriver.formatDate(auction.getDate()));

			int count2 = 1;

			for (Iterator<AuctionItem> j = 
					auctionItems .iterator(); j.hasNext(); ) {
				AuctionItem auctionItem =  j.next();
				BigDecimal bidValue = BigDecimal.ZERO;
				BigDecimal minValue = auctionItem.getMinimumAcceptableBidValue();
				for(Iterator <Bid> p = myBids.iterator(); p.hasNext();) {	
					Bid bids = p.next();
					if(bids.getAuctionItem().equals(auctionItem)) {
						bidValue = bids.getValue();
					}		
				}
				System.out.println("      Item " + count2++ +
						": " + auctionItem.getDescription());
				System.out.println("          Minimum bid: "+
						MainDriver.formatCurrency(minValue)
				+"\t\tMy bid: " + MainDriver.formatCurrency(bidValue));
			}
			System.out.println();
		}

		System.out.print("Press enter to go back.");	
		theScanner.nextLine();
	}
	/**
	 * showing bidder items.
	 * 
	 * @param theScanner
	 * @param theUser
	 * @param theAuctionIndex
	 */
	public void showBiddersItems(Scanner theScanner,
			Bidder theUser, int theAuctionIndex) {
		Collection<Auction> auctionCollection =
				theUser.getMyAuctions();
		Auction[] indexedAuctions =
				auctionCollection.toArray(
						new Auction[auctionCollection.size()]);
		Auction auction = indexedAuctions[theAuctionIndex - 1];
		
		Collection<AuctionItem> auctionItemCollection =
				auction.getAllItemsWithBidder(theUser);
		AuctionItem[] indexedItems =
				auctionItemCollection.toArray(
						new AuctionItem[auctionItemCollection.size()]);
		System.out.println(auction.getName() + " (" +
						MainDriver.formatDate(auction.getDate()) + "):\n");
		for (int count = 0; count < indexedItems.length; count++) {
			AuctionItem item = indexedItems[count];
			BigDecimal bidValue = auction.getBidForItem(theUser, item).getValue();
			BigDecimal minValue = item.getMinimumAcceptableBidValue(); 
			System.out.println("    Item " + (count + 1)
					+ ": " + item.getDescription());
			System.out.println("        Minimum bid: " + 
					MainDriver.formatCurrency(minValue) + 
					"\t My bid: " +
					MainDriver.formatCurrency(bidValue));
		}

		System.out.println("\nEnter any key to go back.");	
		theScanner.nextLine();
	}
}
