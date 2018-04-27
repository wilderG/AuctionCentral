package controller;

import model.Auction;
import model.AuctionCalendar;
import model.Bid;
import model.Item;
import model.NewAuctionRequest;
import model.NewBidRequest;
import model.NewItemRequest;
import model.NewUserRequest;
import model.User;

/**
 * Manages new object requests from the front-end and 
 * @author Jared
 *
 */
public class AuctionManager implements Manager {
	
	/** The default maximum auction items for an auction. **/
	private static final int MAXIMUM_AUCTION_ITEMS = 10;
	
	/** The default number of bids a bidder may place in any one auction. **/
	private static final int MAXIMUM_BIDDER_BIDS_PER_AUCTION = 4;
		
	/** The maximum number of future auctions that may be scheduled. **/
	private static final int MAXIMUM_FUTURE_AUCTIONS = 25;
	
	/** The maximum number of auctions allowed on any day. **/
	private static final int MAXIMUM_AUCTIONS_PER_DAY = 2;
	
	/** The maximum number of days in the future that a new auction may be scheduled. **/
	private static final int MAXIMUM_DAYS_OUT = 60;
	
	/** The minimum number of days in the future that a new auction may be scheduled. **/
	private static final int MINIMUM_DAYS_OUT = 14;
		
	/** The maximum number of bids a bidder may place. **/
	private static final int MAXIMUM_BIDDER_BIDS = 10;
		
	/** The schedule of all past and future auctions. **/
	private AuctionCalendar myCalendar;
	
	/** Generated Serial Version UID. */
	private static final long serialVersionUID = 4646613498773772086L;

	public AuctionManager() {
		// not connected to StorageIO
		myCalendar = new AuctionCalendar(); 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(String theUsername) {
		// not connected to StorageIO
		return new User(theUsername, "Test User");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Auction submitNewAuctionRequest(NewAuctionRequest theAuctionRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User submitNewUser(NewUserRequest theUserRequest) {
		// not connected to StorageIO
		return new User(theUserRequest.getMyUsername(), "Test User"); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item submitNewItem(NewItemRequest theNewItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bid submitNewBid(NewBidRequest theNewBidRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
