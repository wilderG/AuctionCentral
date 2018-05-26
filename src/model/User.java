/**
 * 
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Observable;
import java.util.TreeSet;


/**
 * Provides common implementations of some user behaviors.
 * @author Jim Rosales
 */
public class User extends Observable implements Serializable, Comparable<User> {
	
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
	private TreeSet<Auction> myAuctions;
	
	
	
	/**
	 * Constructs a user.
	 * @param theUserName The username that the user will be initialized with
	 * @param theDisplayName for the user.
	 */
	public User(String theUsername, String theDisplayName) {
		super();
		myUsername = theUsername;
		myDisplayName = theDisplayName;
		myAuctions = new TreeSet<>();
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
		setChanged();
		notifyObservers("hasAuction");
	}
	
	/**
	 * Getter for all the auctions associated with the respective user.
	 * @return A list of all the auctions associated with the respective user.
	 */
	public Collection<Auction> getMyAuctions() {
		return myAuctions;
	}
	
	/**
	 * Returns true if a user has at least one auction it is interested in.
	 * @return
	 */
	public boolean isUserHasAuction() {
		return (!myAuctions.isEmpty());
	}
	
	public boolean isUserHasFutureAuction() {
		for (Auction e : myAuctions) {
			if (e.getDate().isAfter(LocalDate.now())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(User theOther) {
		if (myDisplayName.equals(theOther.myDisplayName)) {
			return myUsername.compareTo(theOther.myUsername);
		} else {
			return myDisplayName.compareTo(theOther.myDisplayName);
		}
	}
	
	public void removeAuction(final Auction theAuction) {
		myAuctions.remove(theAuction);
    }
}
