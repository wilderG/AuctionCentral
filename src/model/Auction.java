package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents an Auction event. The object contains a collection of Items and
 * a collection of Bids. 
 * @author Jared Malone
 * @version 5/5/2018
 *
 */
public final class Auction implements Serializable {

	/** Generated Serial ID **/
	private static final long serialVersionUID = -5386584822819727993L;
	
	/** The maximum number of items allowed for this auction. **/
	private int myMaximumItems;
	
	/** The maximum number of bids allowed from a single bidder. **/
	private int myMaximumBidsFromUniqueBidder;
	
	/** The date of this auction. **/
	private final LocalDate myDate;
	
	/** The collection of items for this auction. **/
	private HashSet<AuctionItem> myItems;
	
	/** The collection of bids for this auction. **/
	private HashMap<Bidder, HashSet<Bid>> myBids;
	
	/**
	 * 
	 */
	private String myNonProfitName;
	
	/**
	 * Creates a new auction.
	 * @param theDate date of the auction.
	 * @param theMaxItemCount the maximum number of items allowed.
	 * @param theMaxBidCount the maximum number of bids allowed from a unique bidder.

	 */
	public Auction(final LocalDate theDate, final int theMaxItemCount, final int theMaxBidCount, String theNonProfit) {
		myDate = theDate;
		myMaximumItems = theMaxItemCount;
		myNonProfitName = theNonProfit;
		myMaximumBidsFromUniqueBidder = theMaxBidCount;
		myItems = new HashSet<>();
		myBids = new HashMap<>();
	}
	
	
	public Auction(final LocalDate theDate, final int theMaxItemCount, final int theMaxBidCount, String theNonProfit,
			final HashSet<AuctionItem> theItems, final HashMap<Bidder, HashSet<Bid>> theBids) {
		this(theDate, theMaxItemCount, theMaxBidCount, theNonProfit);
		myItems = theItems;
		myBids = theBids;
	}
	
	
	
	/**
	 * Adds an item to the auction if auction is allowing new items.
	 * @param theItem
	 */
	public void addItem(final AuctionItem theItem) {
		if (isAllowingNewItem()) {
			myItems.add(theItem);
		}
	}
	
	/**
	 * Getter for the name of the non profit that the auction is associated with.
	 * @return The name of the non profit associated with the non-profit.
	 */
	public String getName() {
		return myNonProfitName;
	}
	
	/**
	 * Adds a bid to the auction if the bidder has not already bid on the maximum allowed
	 * number of items.
	 * @param theBidder making the bid
	 * @param theBid being placed in the auction
	 */
	public void addBid(final Bidder theBidder, final Bid theBid) 
			throws IllegalArgumentException {
		
		if (!isAllowingNewBid(theBidder)) {
			throw new IllegalArgumentException("Bidder is not allowed to bid on this auction.");
		}
		
		if (theBid.getAmount().compareTo(theBid.getAuctionItem().
				getMinimumAcceptableBidValue()) < 0) {
			throw new IllegalArgumentException(
					"This bid is below the minimum accepted bid for the item.");
		}
		
		// new bidder instantiate empty set of bids
		if (!myBids.containsKey(theBidder)) {
			myBids.put(theBidder, new HashSet<>());
		}
		
		// check for existing bid and remove
		Collection<Bid> bids = myBids.get(theBidder);
		
		for (Bid e : bids) {
			if (e.getAuctionItem().equals(theBid.getAuctionItem())) {
				bids.remove(e);
			}
		}
		
		bids.add(theBid);
	}
	
	/**
	 * Checks if the auction is open for bids, and if the bidder has
	 * already placed the maximum allowed number of bids.
	 * @param theBidder to check against
	 * @return true if the bidder may add another
	 */
	public boolean isAllowingNewBid(final Bidder theBidder) {
		boolean result = true;
		
		if (myDate.equals(LocalDate.now())) {
			result = false;
		} else if (myBids.containsKey(theBidder)) {
			int bidCount = myBids.get(theBidder).size(); 
			result = bidCount < myMaximumBidsFromUniqueBidder;
		} 

		return result;
	}
	
	/**
	 * Checks if the auction is allowing new items.
	 * @return true if a new item may be added.
	 */
	public boolean isAllowingNewItem() {
		return (myItems.size() < myMaximumItems);
	}
	
	/**
	 * Returns a collection of items for this auction.
	 * @return the items
	 */
	public Collection<AuctionItem> getAllItems() {
		return myItems;
	}
	
	/**
	 * Returns a collection of items that the bidder has bid on.
	 * @param theBidder to reference
	 * @return a collection of items the bidder has bid on.
	 */
	public Collection<AuctionItem> getAllItemsWithBidder(final Bidder theBidder) {
		Collection<AuctionItem> bidderItems = new HashSet<>();
		
		if (myBids.containsKey(theBidder)) {
		for (Bid e : myBids.get(theBidder)) {
			bidderItems.add(e.getAuctionItem());

		}
		}
		return bidderItems;
	}
	
	/**
	 * Returns the date of this auction.
	 * @return the date of this auction.
	 */
	public LocalDate getDate() {
		return myDate;
	}
	public HashSet<Bid> getMyBids(final Bidder theBidder) {
		
		return myBids.get(theBidder);
		
		
	}

}
