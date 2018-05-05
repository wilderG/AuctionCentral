package model;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import backend.ObjectCloner;
import backend.StorageIO;

/**
 * Manages new object requests from the front-end and 
 * @author Jared
 *
 */
public class AuctionManager implements Manager {
	/** Generated Serial Version UID. */
	private static final long serialVersionUID = 4646613498773772086L;
	
	/** The filename to store persistent data. **/
	private static final String FILE_NAME = "data/storage.dat";
	
	/** The storage object to save and load persistent data. **/
	private StorageIO storage;
		
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
	
	
	/**
	 * Constructs a new manager object.
	 */
	public AuctionManager() {
		storage = new StorageIO(FILE_NAME);
		myCalendar = storage.getCalendar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(String theUsername) throws IllegalArgumentException {
		// not connected to StorageIO
		// return new User(theUsername, "Test User");
		
		User user = storage.getUser(theUsername);
		User copyOfUser;
		
		try {
			copyOfUser = (User) ObjectCloner.deepCopy(user);
		} catch (Exception e) {
			throw new IllegalArgumentException("User not found.");
		}

		if (copyOfUser == null) {
			throw new IllegalArgumentException("User not found.");
		} else {
			return user;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Auction> getAvailableAuctions(Bidder theBidder) {
		Collection<Auction> result;
		
		if (theBidder.isNewBidAllowed()) {
			result = myCalendar.getFutureAuctions();	
		} else {
			result = new HashSet<>();
		}
		
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
	public boolean isNewAuctionRequestAllowed() {
		return myCalendar.isAllowingNewAuction();
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
