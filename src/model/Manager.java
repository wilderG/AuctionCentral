/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Outlines the common functionality for a manager.
 * @author Jim Rosales
 *
 */
public interface Manager extends Serializable {
	
	/**
	 * Returns a collection of auctions that the bidder may bid on.
	 * @param theBidder 
	 * @return the auctions open to user for bidding
	 */
	Collection<Auction> getAvailableAuctions(Bidder theBidder);
	
	/**
	 * Returns the stored user.
	 * @param theUsername the username to return
	 * @return User object associated with the username
	 */
	User getUser(String theUsername);
	
	/**
	 * Pre-check if a non-profit contact is permitted to add a new auction.
	 * @param theUser non-profit contact
	 * @return true if auction request is allowed for the user
	 */
	boolean isNewAuctionRequestAllowed(NonProfitContact theUser); 
	
	/**
	 * Submits a new auction using the given auction request.
	 * @param theAuctionRequest
	 */
	Auction processNewAuctionRequest(NewAuctionRequest theAuctionRequest);
	
	/**
	 * Using the provided new user request submits a new user.
	 * @param theUserRequest
	 */
	User processNewUser(NewUserRequest theUserRequest);
		
	/**
	 * Pre-check if an auction may add another item.
	 * @param theAuction
	 * @return true if an item request is allowed
	 */
	boolean isNewItemRequestAllowed(Auction theAuction);
		
	/**
	 * Submits a new item using the given request.
	 * @param theNewItemRequest
	 */
	AuctionItem processNewItem(NewItemRequest theNewItemRequest);
	
	/**
	 * Pre-check if a new bid request is allowed for a given auction and bidder.	
	 * @param theAuction of the new bid
	 * @param theBidder making the new bid
	 * @return true if the bid request is allowed
	 */
	boolean isNewBidRequestAllowed(Auction theAuction, Bidder theBidder);
	
	/**
	 * Submits a new bid request using the given request.
	 * @return a new Bid object
	 */
	Bid processNewBid(NewBidRequest theNewBidRequest);

}
