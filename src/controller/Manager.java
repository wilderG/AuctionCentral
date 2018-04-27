/**
 * 
 */
package controller;

import java.io.Serializable;

import model.Auction;
import model.Bid;
import model.Item;
import model.NewAuctionRequest;
import model.NewBidRequest;
import model.NewItemRequest;
import model.NewUserRequest;
import model.User;

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
	Auction submitNewAuctionRequest(NewAuctionRequest theAuctionRequest);
	

	/**
	 * Using the provided new user request submits a new user.
	 * @param theUserRequest
	 */
	User submitNewUser(NewUserRequest theUserRequest);
	
	
	/**
	 * Submits a new item using the given request.
	 * @param theNewItemRequest
	 */
	Item submitNewItem(NewItemRequest theNewItemRequest);
	
	/**
	 * Submits a new bid request using the given request.
	 * @return The bids id.
	 */
	
	/**
	 * Submits a new bid request using the given request. 
	 * @param theNewBidRequest
	 * @return The ID for the new Bid.
	 */
	Bid submitNewBid(NewBidRequest theNewBidRequest);

}
