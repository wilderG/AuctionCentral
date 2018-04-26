package model;

import java.io.Serializable;

public class AuctionCalendar implements Serializable {

	/**
	 * Calendar
	 */
	private static final long serialVersionUID = 1895764042180154001L;

	/** Test string for username. **/	
	public String myName;
	
	/** Test auction reference. **/
	public Auction myAuction;
	
	/** Constructor takes a username and auction. **/
	public AuctionCalendar(final String username, final Auction theAuction) {
		myName = username;
		myAuction = theAuction;
	}
	
	public String toString() {
		return "User: " + myName + ", Auction: " + myAuction.toString();
	}
	
}
