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
public class AuctionItem implements Serializable {

	/**
	 * Generate Serial Version UID.
	 */
	private static final long serialVersionUID = -8055041786068385780L;
	
	private BigDecimal myMinimumAcceptableBidValue;
	
	private String myDescription;
	
	public AuctionItem(BigDecimal theMinimumBid, String theDescription) {
		myMinimumAcceptableBidValue = theMinimumBid;
		myDescription = theDescription;
	}
	
	public BigDecimal getMinimumAcceptableBidValue() {
		return myMinimumAcceptableBidValue;
	}
	
	public String getDescription() {
		return myDescription;
	}
	
	public boolean isBidAmountValid(BigDecimal theAmount) {
		if(theAmount.compareTo(myMinimumAcceptableBidValue) >= 0) {
			return true;
		} else {
			return false;
		}
	
	}
}
