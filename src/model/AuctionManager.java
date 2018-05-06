package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import backend.StorageIO;

/**
 * Manages new object requests from the front-end and 
 * @author Jared Malone
 * @version 5/5/2018
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
		return storage.getUser(theUsername);
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
	public Auction processNewAuctionRequest(NewAuctionRequest theAuctionRequest) 
		throws IllegalArgumentException {
		NonProfitContact sponsor = theAuctionRequest.getMySponsor();
		LocalDate auctionDate = theAuctionRequest.getMyDate();
		
		Auction newAuction = new Auction(auctionDate, MAXIMUM_AUCTION_ITEMS,
				MAXIMUM_BIDDER_BIDS_PER_AUCTION, sponsor.getDisplayName());
		
		if (!sponsor.isDateForProposedAuctionValid(newAuction)) {
			throw new IllegalArgumentException("You may not add an auction on this date."
			        + "It is within one year of your latest auction.");
		}
		
		myCalendar.submitAuction(newAuction, auctionDate.getDayOfMonth(), 
				auctionDate.getMonthValue(), auctionDate.getYear());
				
		sponsor.addAuction(newAuction);
		storage.writeData();
		return newAuction;
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
		Auction auction = theNewItemRequest.getMyAuction();
		String description = theNewItemRequest.getMyDescription();
		BigDecimal minimumAmount = theNewItemRequest.getMyMinimumBid();
		
//		if (!isNewItemRequestAllowed(auction)) {
//			throw new IllegalArgumentException("New item is not allowed.");
//		}
		
		AuctionItem newItem = new AuctionItem(minimumAmount, description);
		auction.addItem(newItem);
		storage.writeData();
		
		return newItem;
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
	public Bid processNewBid(NewBidRequest theNewBidRequest) 
			throws IllegalArgumentException {
		
		AuctionItem item = theNewBidRequest.getMyItem();
		BigDecimal bidValue = theNewBidRequest.getMyAmount();
		Bidder bidder = theNewBidRequest.getMyBidder();
		Auction auction = theNewBidRequest.getMyAuction();
		
		if (!item.isBidAmountValid(bidValue)) {
			throw new IllegalArgumentException("Bid is less than the minimum "
					+ "acceptable value.");
		}
		
		Bid newBid = new Bid(bidder, item, bidValue);
		auction.addBid(bidder, newBid);
		storage.writeData();
		return newBid;
	}

}
