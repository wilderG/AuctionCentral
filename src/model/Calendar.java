/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 
 *
 */
public interface Calendar extends Serializable {
	
	/**
	 * Returns the upcoming auctions.
	 * @return A list of all auction ids that are upcoming
	 */
	List<AuctionID> getAllFutureAuction();

	/**
	 * Returns all the auction from the given date.
	 * @param theDate whose auctions will be retrieved
	 * @return A list of all the auctions ids on the given date.
	 */
	List<AuctionID> getAuctionsFromDate(Date theDate);
}
