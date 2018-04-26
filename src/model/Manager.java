/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * Outlines the common functionality for a manager.
 * @author Jim Rosales
 *
 */
public interface Manager extends Serializable {
	
	/**
	 * Returns the stored user.
	 * @param theUsername the username to return
	 * @return User object associated with the username
	 */
	User getUser(String theUsername);
	
	/**
	 * Submits a new auction using the given auction request.
	 * @param theAuctionRequest
	 */
	void submitNewAuctionRequest(NewAuctionRequest theAuctionRequest);
	

	/**
	 * Using the provided new user request submits a new user.
	 * @param theUserRequest
	 */
	void submitNewUser(NewUserRequest theUserRequest);
	
	
	/**
	 * Submits a new item using the given request.
	 * @param theNewItemRequest
	 */
	void submitNewItem(NewItemRequest theNewItemRequest);
	
	/**
	 * Submits a new bid request using the given request.
	 * @return The bids id.
	 */
	
	/**
	 * Submits a new bid request using the given request. 
	 * @param theNewBidRequest
	 * @return The ID for the new Bid.
	 */
	long submitNewBid(NewBidRequest theNewBidRequest);

}
