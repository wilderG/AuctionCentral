package model;

import java.math.BigDecimal;

/**
 * Class represents a new request for new bids.
 * @author Jim Rosales
 *
 */
public class NewBidRequest {
	
	/**
	 * The Bidder associated with the requested bid.
	 */
	private Bidder myBidder;
	
	/**
	 * The Auction associated with the requested bid.
	 */
	private Auction myAuction;
	
	/**
	 * The item for which a bid was placed.
	 */
	private AuctionItem myItem;
	
	/**
	 * The amount of the bid placed.
	 */
	private BigDecimal myAmount;
	
	/**
	 * Constructor for a new bid request.
	 * @param theBidder that will be associated with the new bid request.
	 * @param theAuction that will be associated with the new bid request.
	 * @param theItem that will be associated with the new bid request.
	 * @param theAmount that will be associated with the new bid request.
	 */
	public NewBidRequest(Bidder theBidder, Auction theAuction,
			AuctionItem theItem, BigDecimal theAmount) {
		myBidder = theBidder;
		myAuction = theAuction;
		myItem = theItem;
		myAmount = theAmount;
	}
	
	/**
	 * Getter for the bidder associated with the new bid request.
	 * @return The bidder
	 */
	public Bidder getMyBidder() {
		return myBidder;
	}
	
	/**
	 * Getter for the auction associated with the new bid request.
	 * @return
	 */
	public Auction getMyAuction() {
		return myAuction;
	}
	
	/**
	 * Getter for the item associated with the new requested bid.
	 * @return The item
	 */
	public AuctionItem getMyItem() {
		return myItem;
	}
	
	/**
	 * Getter for the amount of the new requested bid.
	 * @return The amount of the bid.
	 */
	public BigDecimal getMyAmount() {
		return myAmount;
	}
	
}
