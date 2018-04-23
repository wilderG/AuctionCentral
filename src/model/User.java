package model;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8113376313259596043L;

	public String myName;
	
	private Auction myAuction;
	
	public User(final String username, final Auction theAuction) {
		myName = username;
		myAuction = theAuction;
	}
	
	public String toString() {
		return "User: " + myName + ", Auction: " + myAuction.toString();
	}
	
}
