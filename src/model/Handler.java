/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * Interface for a data handle that outlines all needed functionality.
 * 
 * @author Jim Rosales
 *
 */
public interface Handler extends Serializable {
	
	/**
	 * Creates an auction
	 * @param theAuction data that will be used to create an auction.
	 * @return The serial version UID for the auction
	 */
	long createAuction(Auction theAuction);
	
	/**
	 * Creates a bid.
	 * @param theBid data that will be used to create the bid.
	 * @return The serial version UID for the
	 */
	long createBid(Bid theBid);
	
	/**
	 * Creates an item.
	 * @param theItem data that will be used to create an item.
	 * @return The serial version UID for the
	 */
	long createItem(Item theItem);
	
	/**
	 * Creates a user.
	 * @param theUser data that will be used to create the user.
	 * @return The username for the user.
	 */
	String createUser(User theUser);
	
	/**
	 * Returns an auction associated the given serial version UID.
	 * @param theUID that will be used to retrieve the auction
	 * @return The Auction associated with the given serial version UID
	 */
	Auction getAuction(long theUID);
	
	/**
	 * Returns a bid associated the given serial version UID.
	 * @param theUID associated with theBid that will be returned.
	 * @return The Bid associated with the given serial version UID.
	 */
	Bid getBid(long theUUID);
	
	/**
	 * Returns an item associated the given serial version UID.
	 * @param theUID associated with theItem that will be returned
	 * @return The Item associated with the given serial version UID.
	 */
	Item getItem(long theUID);
	
	/**
	 * Returns a user associated with the given username.
	 * @param theUsername that will be used to retrieve the user.
	 * @return The user associated with the given username.
	 */
	User getUser(String theUsername);
	
	/**
	 * Saves the data onto storage.
	 */
	void saveData();
}
