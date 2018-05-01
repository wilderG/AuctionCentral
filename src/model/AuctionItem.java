/**
 * 
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.sun.xml.internal.ws.api.client.ThrowableInPacketCompletionFeature;

/**
 * 
 * @author 
 *
 */
public class AuctionItem implements Serializable {

	/**
	 * Generate Serial Version UID.
	 */
	private static final long serialVersionUID = -8055041786068385780L;
	
	private BigDecimal myMinimumBid;
	
	private List<Bid> myBids;
	
	private String myDescription;
	
	public AuctionItem(BigDecimal theMinimumBid, List<Bid> theBids, String theDescription) {
		myMinimumBid = theMinimumBid;
		myBids = theBids;
		myDescription = theDescription;
	}
	
	public BigDecimal getMinimumBid() {
		return myMinimumBid;
	}
	
	
	
	public String getDescription() {
		return myDescription;
	}
	
	public List<Bid> getBids() {
		return myBids;
	}
	
	public void placeBid(Bidder theBidder, BigDecimal theAmount) {
		if(!isBidValid(theBidder, theAmount)) {
			throw new IllegalArgumentException();
		} 
		Bid theBid = new Bid(theBidder, this, theAmount);
		
		myBids.add(theBid);
	}
 	
	public boolean isBidValid(Bidder theBidder, BigDecimal theAmount) {
		if(theAmount.compareTo(myMinimumBid) >= 0) {
			return true;
		} else {
			return false;
		}
	
	}
}
