package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Represents an Auction event. The object contains a collection of Items and
 * a collection of Bids. 
 * @author Jared Malone
 * @version 5/5/2018
 *
 */
public final class Auction implements Serializable, Comparable<Auction> {

	/** Generated Serial ID **/
	private static final long serialVersionUID = -5386584822819727993L;
	
	/** The maximum number of items allowed for this auction. **/
	public static final int MAXIMUM_ITEM_COUNT = 10;
	
	/** The maximum number of bids allowed from a single bidder. **/
	public static final int MAXIMUM_BID_COUNT_EACH_BIDDER = 8;
	
	/** The date of this auction. **/
	private final LocalDate myDate;
	
	/** The collection of items for this auction. **/
	private TreeSet<AuctionItem> myItems;
	
	/** The collection of bids for this auction. **/
	private TreeMap<Bidder, TreeSet<Bid>> myBids;
	
	/** The non-profit associated with this auction. **/
	private NonProfitContact myOwner;
	
	/**
	 * Creates a new auction.
	 * @param theDate date of the auction.
	 * @param theMaxItemCount the maximum number of items allowed.
	 * @param theMaxBidCount the maximum number of bids allowed from a unique bidder.
	 */
	public Auction(final LocalDate theDate, NonProfitContact theNonProfit) {
        this(theDate, theNonProfit, new TreeSet<AuctionItem>(), 
                new TreeMap<Bidder, TreeSet<Bid>>());
	}
	
	/**
	 * Overloaded constructor for auction with a predefined set of items and bids.
	 * 
	 * @param theDate
	 * @param theNonProfit
	 * @param theItems
	 * @param theBids
	 */
	public Auction(final LocalDate theDate, NonProfitContact theNonProfit,
			final TreeSet<AuctionItem> theItems, final TreeMap<Bidder, TreeSet<Bid>> theBids) {
        myDate = theDate;
        myOwner = theNonProfit;
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
		return myOwner.getDisplayName();
	}
	
	/**
	 * Gets the nonprofit associated with this auction.
	 * 
	 * @return the auction owner
	 */
	public NonProfitContact getOwner() {
		return myOwner;
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
		
		if (theBid.getValue().compareTo(theBid.getAuctionItem().
				getMinimumAcceptableBidValue()) < 0) {
			throw new IllegalArgumentException(
					"This bid is below the minimum accepted bid for the item.");
		}
		
		// new bidder instantiate empty set of bids
		if (!myBids.containsKey(theBidder)) {
			myBids.put(theBidder, new TreeSet<>());
		}
		
		// check for existing bid and remove
		Collection<Bid> bids = myBids.get(theBidder);
		
		for (Bid e : bids) {
			if (e.getAuctionItem().equals(theBid.getAuctionItem())) {
				bids.remove(e);
				break;
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
		
		if (!myDate.isAfter(LocalDate.now())) {
			result = false;
		} else if (myBids.containsKey(theBidder)) {
			int bidCount = myBids.get(theBidder).size(); 
			result = bidCount < MAXIMUM_BID_COUNT_EACH_BIDDER;
		} 

		return result;
	}
	
	/**
	 * Checks if the auction is allowing new items.
	 * @return true if a new item may be added.
	 */
	public boolean isAllowingNewItem() {
		return (myItems.size() < MAXIMUM_ITEM_COUNT);
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
	public Collection<AuctionItem> getAllItemsWithBidder
	         (final Bidder theBidder) {
		Collection<AuctionItem> bidderItems = new TreeSet<>();
		
		if (myBids.containsKey(theBidder)) {
			for (Bid e : myBids.get(theBidder)) {
				bidderItems.add(e.getAuctionItem());
			}
		}
		return bidderItems;
	}
	
	/**
	 * Returns a collection of bids for a bidder.
	 * @param theBidder to reference
	 * @return a collection of bids from the bidder.
	 */
	public Collection<Bid> getAllBidsWithBidder
	         (final Bidder theBidder) {
		Collection<Bid> bidderBids = new TreeSet<>();
		
		if (myBids.containsKey(theBidder)) {
			bidderBids.addAll(myBids.get(theBidder));
		}
		return bidderBids;
	}
	
	/**
	 * Returns the date of this auction.
	 * @return the date of this auction.
	 */
	public LocalDate getDate() {
		return myDate;
	}

	/**
	 * Gets the number of items left available to be added to this auction 
	 * currently.
	 * @return
	 */
	public int getAvailableSpace() {
		return MAXIMUM_ITEM_COUNT - myItems.size();
	}
	
	/**
	 * Gets all bids for this auction for this bidder.
	 * 
	 * @param theBidder the bidder with the bids.
	 * @return the bids for the bidder
	 */
	public TreeSet<Bid> getMyBids(final Bidder theBidder) {		
		return myBids.get(theBidder);
	}
	
	/**
	 * Gets this bidder's bid for the particular item.
	 * 
	 * @param theBidder that has the bid
	 * @param theItem the item that is bid on
	 * @return
	 */
	public Bid getBidForItem(final Bidder theBidder, AuctionItem theItem) {
		Bid bid = null;
		for (Bid potentialBid: myBids.get(theBidder)) {
			if (potentialBid.getAuctionItem().equals(theItem)) {
				bid = potentialBid;
				break;
			}
		}
		return bid;
	}

	/**
	 * Gets whether or not bids yet exist for this auction.
	 * 
	 * @return whether or not this auction yet has bids
	 */
	public boolean isEmptyBids() {
		return myBids.isEmpty();
	}

	/**
	 * Compares auctions by date, then by owner.
	 */
	@Override
	public int compareTo(Auction theOther) {
		if (myDate.equals(theOther.myDate)) {
			return myOwner.compareTo(theOther.myOwner);
		} else {
			return myDate.compareTo(theOther.myDate);
		}
	}
	
	/**
     * Gets whether or not bids yet exist for this auction.
     * 
     * @return whether or not this auction yet has bids
	 */
	public boolean isContaingBids() {
	    int bidCount = 0;
	    for (Bidder bidder : myBids.keySet()) {
	        bidCount += myBids.get(bidder).size();
	    }
	    return bidCount != 0;
	}
	
}
