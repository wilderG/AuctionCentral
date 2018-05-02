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
		// get all future auctions
		Collection<Auction> result = myCalendar.getFutureAuctions();
		
		// remove auctions such that bidder is not allowed
		for (Auction e : result) {
			if (!e.isAllowingNewBid(theBidder))
				result.remove(e);
		}
				
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNewAuctionRequestAllowed(NonProfitContact theUser) {
		// check if previous auction was too recent
		
		// check if future auction is scheduled
		
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
		// check auction
		theAuction.isAllowingNewItem();
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuctionItem processNewItem(NewItemRequest theNewItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Checks if the auction is allowing bids from the bidder. Checks if
	 * the bidder is allowed to make new bids.
	 */
	@Override
	public boolean isNewBidRequestAllowed(Auction theAuction, Bidder theBidder) {
		boolean result;
		
		// check auction status
		result = theAuction.isAllowingNewBid(theBidder);
						
		// check bidder status
		result = result && theBidder.isNewBidAllowed();
		
		return result;
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
