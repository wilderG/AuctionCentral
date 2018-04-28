package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class represents an auction 
 * @author Jared Malone
 *
 */
public final class Auction implements Serializable {

	/** Generated Serial ID **/
	private static final long serialVersionUID = -5386584822819727993L;
	
	/** The maximum number of items allowed for this auction. **/
	private int myMaximumItems;
	
	/** The maximum number of bids allowed from a single bidder. **/
	private int myMaximumBidsFromUniqueBidder;
	
	/** The number of days before the auction date to stop taking bids. **/
	private int myBidCutoffDays;
	
	/** The date of this auction. **/
	private final Date myDate;
	
	/** The collection of items for this auction. **/
	private HashSet<Item> myItems;
	
	/** The collection of bids for this auction. **/
	private HashMap<Bidder, HashSet<Bid>> myBids;
	
	/**
	 * Creates a new auction.
	 * @param theDate date of the auction.
	 * @param theMaxItemCount the maximum number of items allowed.
	 * @param theMaxBidCount the maximum number of bids allowed from a unique bidder.
	 * @param theBidCutOff the number of days before the auction to stop accepting bids.
	 */
	public Auction(final Date theDate, final int theMaxItemCount, final int theMaxBidCount,
			final int theBidCutOff) {
		myDate = theDate;
		myMaximumItems = theMaxItemCount;
		myMaximumBidsFromUniqueBidder = theMaxBidCount;
		myBidCutoffDays = theBidCutOff;
		
		myItems = new HashSet<>();
		myBids = new HashMap<>();
	}
	
	/**
	 * Adds an item to the auction if auction is allowing new items.
	 * @param theItem
	 */
	public void addItem(final Item theItem) {
		if (isAllowingNewItem()) {
			myItems.add(theItem);
		}
	}
	
	/**
	 * Adds a bid to the auction if the bidder has not already bid on the maximum allowed
	 * number of items.
	 * @param theBidder making the bid
	 * @param theBid being placed in the auction
	 */
	public void addBid(final Bidder theBidder, final Bid theBid) {
		if (isAllowingNewBid(theBidder)) {
			
			// new bidder instantiate empty set of bids
			if (!myBids.containsKey(theBidder)) {
				myBids.put(theBidder, new HashSet<>());
			}
		
			// should we check for duplicate bid?
		
			// add this bid to set
			myBids.get(theBidder).add(theBid);
		}
	}
	
	/**
	 * Checks if the bidder has already placed the maximum allowed number of bids.
	 * @param theBidder to check against
	 * @return true if the bidder may add another
	 */
	public boolean isAllowingNewBid(final Bidder theBidder) {
		
		// we need to check if the cut-off time for new bids has passed.
		
		// assuming time is okay then check if bidder is at limit.
		if (!myBids.containsKey(theBidder)) {
			return true;
		} else {
			return (myBids.get(theBidder).size() <= myMaximumBidsFromUniqueBidder);
		}
	}
	
	
	public boolean isAllowingNewItem() {
		return (myItems.size() <= myMaximumItems);
	}
	
	public Collection<Item> getAllItems() {
		return myItems;
	}
	
	public Collection<Item> getAllItemsWithBidder(final Bidder theBidder) {
		Collection<Item> bidderItems = new HashSet<>();
		
		for (Bid e : myBids.get(theBidder)) {
			bidderItems.add(e.getItem());
		}
		return bidderItems;
	}
	
	public Date getDate() {
		return myDate;
	}
}
