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
	 * Stores an auction
	 * @param theAuction data that will be used to store an auction.
	 * @return The serial version UID for the auction
	 */
	long storeAuction(Auction theAuction);
	
	/**
	 * Stores a bid.
	 * @param theBid data that will be used to store the bid.
	 * @return The serial version UID for the
	 */
	long storeBid(Bid theBid);
	
	/**
	 * Stores an item.
	 * @param theItem data that will be used to store an item.
	 * @return The serial version UID for the
	 */
	long storeItem(AuctionItem theItem);
	
	/**
	 * Stores a user.
	 * @param theUser data that will be used to store the user.
	 * @return The username for the user.
	 */
	String storeUser(User theUser);
	
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
	AuctionItem getItem(long theUID);
	
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
