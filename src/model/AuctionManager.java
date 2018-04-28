package model;

import java.util.Collection;

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
		
	/** The maximum number of bids a bidder may place. **/
	private static final int MAXIMUM_BIDDER_BIDS = 10;
		
	/** The minimum days between two auctions for the same non-profit. **/
	private static final int MINIMUM_DAYS_BETWEEN_USER_AUCTIONS = 365;
	
	/** The schedule of all past and future auctions. **/
	private AuctionCalendar myCalendar;
	
	/** Generated Serial Version UID. */
	private static final long serialVersionUID = 4646613498773772086L;

	/**
	 * Constructs a new manager object.
	 */
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
	public Collection<Auction> getAvailableAuctions(Bidder theBidder) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNewAuctionRequestAllowed(NonProfitContact theUser) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Auction processNewAuctionRequest(NewAuctionRequest theAuctionRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User processNewUser(NewUserRequest theUserRequest) {
		// not connected to StorageUI
		return getUser(theUserRequest.getMyUsername());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNewItemRequestAllowed(Auction theAuction) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item processNewItem(NewItemRequest theNewItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNewBidRequestAllowed(Auction theAuction, Bidder theBidder) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bid processNewBid(NewBidRequest theNewBidRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
