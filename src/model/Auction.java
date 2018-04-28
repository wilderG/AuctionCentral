package model;

import static org.junit.Assert.assertNotNull;

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
public class Auction implements Serializable {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = -5386584822819727993L;
	
	/** The maximum number of items allowed. **/
	private int myMaximumItems;
	
	/** The maximum number of bids allowed from a single bidder. **/
	private int myMaximumBidsFromSingleBidder;
	
	/** The date of this auction. **/
	private final Date myDate;
	
	/** The collection of items for this auction. **/
	private Collection<Item> myItems;
	
	/** The collection of bids for this auction. **/
	private HashMap<Bidder, Collection<Bid>> myBids;
	
	/**
	 * Constructor that will be used to initialize an auction.
	 * 
	 */
	public Auction(final Date theDate, final int theMaxItemCount, final int theMaxBidCount) {
		myDate = theDate;
		myMaximumItems = theMaxItemCount;
		myMaximumBidsFromSingleBidder = theMaxBidCount;
		
		myItems = new HashSet<>();
		myBids = new HashMap<>();
	}
	
	public void addItem(final Item theItem) {
		
	}
	
	public void addBid(final Bidder theBidder, final Bid theBid) {
		
	}
	
	public boolean isAllowingNewBid(final Bidder theBidder) {
		return false;
	}
	
	public boolean isAllowingNewItem() {
		return false;
	}
	
	public Collection<Item> getAllItems() {
		return myItems;
	}
	
	public Collection<Item> getAllItemsWithBidder(final Bidder theBidder) {
		return null;
	}
}
