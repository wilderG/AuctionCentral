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
	 * Returns all auctions within a date range.
	 * @param theStart start date of range (inclusive)
	 * @param theEnd end date of range (inclusive)
	 * @return A collection of all auctions that are within the range
	 */
	Collection<Auction> getAuctionsInRange(Date theStart, Date theEnd);

	/**
	 * Returns all the auction from the given date.
	 * @param theDate whose auctions will be retrieved
	 * @return A collection of all the auctions on the given date.
	 */
	Collection<Auction> getAuctionsOnDate(Date theDate);
	
	/**
	 * Adds an auction to the specified date.
	 * @param theDate the date the auction will be scheduled on.
	 * @param theAuction the auction to add.
	 */
	void addAuction(Date theDate, Auction theAuction);
}
