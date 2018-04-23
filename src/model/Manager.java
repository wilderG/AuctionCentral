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
	 * Submits a new auction using the given auction request.
	 * @param theAuctionRequest
	 * @return The ID for the new Auction
	 */
	long submitNewAuction(AuctionRequest theAuctionRequest);
	

	/**
	 * Using the provided new user request submits a new user.
	 * @param theUserRequest
	 * @return The username for the user.
	 */
	String submitNewUser(NewUserRequest theUserRequest);
	
	
	/**
	 * Submits a new item using the given request.
	 * @param theNewItemRequest
	 * @return The ID for the new Item.
	 */
	long submitNewItem(NewItemRequest theNewItemRequest);
	
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
