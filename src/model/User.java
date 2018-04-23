package model;

import java.io.Serializable;

public class User implements Serializable {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = 8113376313259596043L;

	/** Test string for username. **/	
	public String myName;
	
	/** Test auction reference. **/
	private Auction myAuction;
	
	/** Constructor takes a username and auction. **/
	public User(final String username, final Auction theAuction) {
		myName = username;
		myAuction = theAuction;
	}
	
	public String toString() {
		return "User: " + myName + ", Auction: " + myAuction.toString();
	}
	
}
