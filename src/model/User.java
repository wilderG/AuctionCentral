/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provides common implementations of some user behaviors.
 * @author Jim Rosales
 */
public class User implements Serializable {
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -2193665280820986783L;
	
	/**
	 * The username associated with the user.
	 */
	private String myUsername;
	
	/**
	 * The displayed name of the user
	 */
	private String myDisplayName;
	
	/**
	 * List that will keep track of all the auctions associated with the user.
	 */
	private List<Auction> myAuctions;
	
	
	
	/**
	 * Constructs a user.
	 * @param theUserName The username that the user will be initialized with
	 * @param theDisplayName for the user.
	 */
	public User(String theUsername, String theDisplayName) {
		myUsername = theUsername;
		myDisplayName = theDisplayName;
		myAuctions = new ArrayList<>();
	}
	
	/**
	 * Getter for the username associated with the respective user.
	 * @return The username for the user.
	 */
	public String getUsername() {
		return myUsername;
	}
	
	/**
	 * Getter for the name of the user.
	 * @return The name of the user.
	 */
	public String getDisplayName() {
		return myDisplayName;
	}

	/**
	 * Adds the given auction to the users list of associated auctions.
	 * @param theAuction that will be associated with the user.
	 */
	public void addAuction(Auction theAuction) {
		myAuctions.add(theAuction);
	}
	
	/**
	 * Getter for all the auctions associated with the respective user.
	 * @return A list of all the auctions associated with the respective user.
	 */
	public Collection<Auction> getMyAuctions() {
		return myAuctions;
	}
	
}
