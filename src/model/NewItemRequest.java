package model;

import java.math.BigDecimal;

/**
 * Class represents requests for any new items.
 * @author Jim Rosales
 */
public class NewItemRequest {
	
	/**
	 * The description of the new item.
	 */
	private String myDescription;
	
	/**
	 * The minimum value that will be accepted for any bids placed for the new item.
	 */
	private BigDecimal myMinimumBid;
	
	/**
	 * The auction that will be associated with the new item.
	 */
	private Auction myAuction;
	
	
	/**
	 * Constructor for a new item request.
	 * @param theDescription for the new item.
	 * @param theMinimumBid that will be accepted for the new item.
	 * @param theAuction that will be associated with the new item.
	 */
	public NewItemRequest(String theDescription, BigDecimal theMinimumBid, Auction theAuction) {
		myDescription = theDescription;
		myMinimumBid = theMinimumBid;
		myAuction = theAuction;
	}
	
	/**
	 * Getter for the description for the new item.
	 * @return The description for the new item.
	 */
	public String getMyDescription() {
		return myDescription;
	}
	
	/**
	 * Getter for the minimum bid for the item.
	 * @return The minimum bid value that will be accepted for the new item.
	 */
	public BigDecimal getMyMinimumBid() {
		return myMinimumBid;
	}
	
	
	/**
	 * Getter for the auction that will associated for the new item.
	 * @return The auction that will be associated for the new item.
	 */
	public Auction getMyAuction() {
		return myAuction;
	}
}
