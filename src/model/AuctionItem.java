/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The class contains information of Auction Item
 * 
 * @author Yohei Sato
 *
 */
public class AuctionItem implements Serializable, Comparable<AuctionItem> {

	/**
	 * Generate Serial Version UID.
	 */
	private static final long serialVersionUID = -8055041786068385780L;
	
	/**
	 * The minimum acceptable bid value for an item.
	 */
	private BigDecimal myMinimumAcceptableBidValue;
	
	/**
	 * A description for an auction item.
	 */
	private String myDescription;
	
	/**
	 * The number of bids currently placed on an item
	 */
	private int myBidCount;
	
	/**
	 * Constructor for an auction item. Initializes the item with a minimum bid value and 
	 * a description using the given values. 
	 * @param theMinimumBid acceptable for the auction item.
	 * @param theDescription for the item.
	 */
	public AuctionItem(BigDecimal theMinimumBid, String theDescription) {
		myMinimumAcceptableBidValue = theMinimumBid;
		myDescription = theDescription;
		myBidCount = 0;
	}
	
	public BigDecimal getMinimumAcceptableBidValue() {
		return myMinimumAcceptableBidValue;
	}
	
	
	public String getDescription() {
		return myDescription;
	}
	
	/**
	 * Returns whether or not the given bid is valid.
	 * 
	 * @param theAmount for the bid that is being checked.
	 * @return True if the given amount is equal to or greater than the minimum acceptable
	 * bid value for the item. False otherwise.
	 */
	public boolean isBidAmountValid(BigDecimal theAmount) {
		if(theAmount.compareTo(myMinimumAcceptableBidValue) >= 0) {
			return true;
		} else {
			return false;
		}
	
	}
	
	
	public int getBidCount() {
		return myBidCount;
	}

	/**
	 * Compares an auction to another via their description.
	 *  
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(AuctionItem theOther) {
		return myDescription.compareTo(theOther.myDescription);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return myDescription;
	}
}
