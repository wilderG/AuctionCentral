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
import model.User;

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
	public void showBidderAuctions(Scanner theScanner, Bidder theUser) {
		   int count = 0;
			Collection<Auction> auctionCollection = theUser.getMyAuctions();

			for (Iterator<Auction> i= auctionCollection .iterator();
				 i.hasNext(); ) {
				
				Auction auction =  i.next();
				Collection<AuctionItem> auctionItems = auction.getAllItemsWithBidder(theUser);
				HashSet<Bid> myBids = auction.getMyBids(theUser);
				
				System.out.println("\t" + (count + 1) + ". With " + auction.getName() + ", " 
						+ auction.getDate() + " (" + auction.getAllItems().size() + ")");
				
				int count2 = 0;
				
				for (Iterator<AuctionItem> j= auctionItems .iterator();
						 j.hasNext(); ) {
					BigDecimal myBidValue = BigDecimal.ZERO;
					AuctionItem auctionItem =  j.next();
					for(Iterator <Bid> p = myBids.iterator(); p.hasNext();) {	
						 Bid bids = p.next();
						if(bids.getAuctionItem().equals(auctionItem)) {
							myBidValue = bids.getValue();
						}
										
				System.out.println("\t\t Item" + (count2 + 1) + ": " + auctionItem.getDescription());
				System.out.println("\t\tMinimum bid:"+ auctionItem.getMinimumAcceptableBidValue() +"\t\tMy bid:" + myBidValue );
				
				
				
					
			
		   }
	  }
				
		}

			System.out.println("Enter 0 to go back");	
}
}