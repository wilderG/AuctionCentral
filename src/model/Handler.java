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
	 * 
	 * @param theAuction
	 * @return
	 */
	long createAuction(Auction theAuction);
	
	/**
	 * 
	 * @param theBid
	 * @return
	 */
	long createBid(Bid theBid);
	
	/**
	 * 
	 * @param theItem
	 * @return
	 */
	long createItem(Item theItem);
	
	/**
	 * 
	 * @param theUser
	 * @return
	 */
	String createUser(User theUser);
	
	/**
	 * 
	 * @param theUUID
	 * @return
	 */
	Auction getAuction(long theUUID);
	
	/**
	 * 
	 * @param theUUID
	 * @return
	 */
	Bid getBid(long theUUID);
	
	/**
	 * 
	 * @param theUUID
	 * @return
	 */
	Item getItem(long theUUID);
	
	/**
	 * 
	 * @param theUsername
	 * @return
	 */
	User getUser(String theUsername);
	
	/**
	 * 
	 */
	void saveData();
}
