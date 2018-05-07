package view;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import model.Auction;
import model.AuctionItem;
import model.Bid;
import model.Bidder;
import sun.net.www.protocol.http.AuthenticationInfo;
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
			System.out.println("\t" + (count + 1) + 
					". " + item.getDescription());
		}
		
		System.out.println("Would you like to place a bid? \n"
				+ "(If so, enter the number corresponding to an item. \n"
				+ " Otherwise, enter 0)\n");

		System.out.print("Choice: ");
		return mainDriver.getNextInt(items.size());
		
	}
	/**
	 * showing items for non profit.
	 * 
	 * @param theScanner
	 * @param theAuction
	 */
	public void showItemsForNonProfAuction
	(Scanner theScanner, Auction theAuction) {
		String theDate = mainDriver.formatDate(theAuction.getDate());
		System.out.println("Items for your auction on " + theDate + ":");
		AuctionItem[] indexedItems = 
				theAuction.getAllItems().toArray(
						new AuctionItem[theAuction.getAllItems().size()]);
		for (int count = 0; count < indexedItems.length; count++) {
			System.out.print("   " + (count + 1) + ". ");
			AuctionItem item = indexedItems[count];
			System.out.println(item.getDescription() + " ("
			+ item.getBidCount() + " bids)");
			System.out.println("\tMinimum bid set at: $" +
			item.getMinimumAcceptableBidValue());
		}
		System.out.println("\nEnter any key to return to the main menu: ");
		theScanner.next();

	}
	/**
	 * showing Bidder items for every auctions.
	 * 
	 * @param theScanner
	 * @param theUser
	 */
	public void showBiddersItemsForAllAuctions
	   (Scanner theScanner, Bidder theUser) {
		int count = 0;
		Collection<Auction> auctionCollection = theUser.getMyAuctions();

		for (Iterator<Auction> i=
				auctionCollection .iterator(); i.hasNext();) {
			Auction auction =  i.next();
			Collection<AuctionItem> auctionItems =
					auction.getAllItemsWithBidder(theUser);
			HashSet<Bid> myBids = auction.getMyBids(theUser);

			System.out.println("   " + auction.getName() + ", "
			+ mainDriver.formatDate(auction.getDate()));

			int count2 = 1;

			for (Iterator<AuctionItem> j = 
					auctionItems .iterator(); j.hasNext(); ) {
				BigDecimal myBidValue = BigDecimal.ZERO;
				AuctionItem auctionItem =  j.next();
				for(Iterator <Bid> p = myBids.iterator(); p.hasNext();) {	
					Bid bids = p.next();
					if(bids.getAuctionItem().equals(auctionItem)) {
						myBidValue = bids.getValue();
					}		
				}
				System.out.println("      Item " + count2++ +
						": " + auctionItem.getDescription());
				System.out.println("          Minimum bid: $"+
						auctionItem.getMinimumAcceptableBidValue()
				+"\t\tMy bid: $" + myBidValue );
			}
			System.out.println();
		}

		System.out.print("Enter any key to go back: ");	
		theScanner.next();
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
		Auction auction = indexedAuctions[theAuctionIndex];
		
		Collection<AuctionItem> auctionItemCollection =
				auction.getAllItemsWithBidder(theUser);
		AuctionItem[] indexedItems =
				auctionItemCollection.toArray(
						new AuctionItem[auctionItemCollection.size()]);
		System.out.println(auction.getName() + " (" +
						mainDriver.formatDate(auction.getDate()) + "):\n");
		for (int count = 0; count < indexedItems.length; count++) {
			AuctionItem item = indexedItems[count];
			System.out.println("    Item " + (count + 1)
					+ ": " + item.getDescription());
			System.out.println("        Minimum bid: $" + 
					item.getMinimumAcceptableBidValue() + 
					"\t My bid: $" +
					auction.getBidForItem(theUser, item).getValue());
		}

		System.out.println("Enter any key to go back: ");	
		theScanner.next();
	}
}
