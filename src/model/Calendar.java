/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author 
 *
 */
public interface Calendar extends Serializable {
	
	/**
	 * Checks if the calendar is allowing future auctions to be scheduled.
	 * @return True if calendar is not at capacity
	 */
	Boolean isAllowingNewAuctions();

	/**
	 * Returns all future auctions from today until the end of range.
	 * @return A collection of all future auctions.
	 */
	Collection<Auction> getFutureAuctions();
	
	/**
	 * Adds an auction to the specified date. Will check calendar rules and
	 * throw exception if auction cannot be added on specified date.
	 * @param theDate the date the auction will be scheduled on.
	 * @param theAuction the auction to add.
	 * @param theUser the nonprofit contact associated with the auction.
	 */
	void addAuction(Date theDate, Auction theAuction, NonProfitContact theUser);
}
