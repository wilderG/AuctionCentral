package model;

import java.time.LocalDate;


/**
 * Class that represents a bidder
 * @author Jared Malone
 *
 */
public class Bidder extends User {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = 8113376313259596043L;

	/**
	 * The default max number of bids that a bidder can have.
	 */
	public static final int MY_MAX_BID_COUNT = 12;
	
	/**
	 * Constructor for a bidder
	 * @param theUsername that will be
	 *  associated with the newly created bidder.
	 * @param theDisplayName that will be 
	 * associated with the newly created bidder.
	 */
	public Bidder(String theUsername, String theDisplayName) {
		super(theUsername, theDisplayName);
	}
	
	/**
	 * Determines whether or not a new bid is allowed for the bidder
	 * @return True if the current bidCount for the 
	 * user is less than the max bid count allowed. Else 
	 * returns false.
	 */
	public boolean isNewBidAllowed() {
		return getBidCount() < MY_MAX_BID_COUNT;
	}
	
	/**
	 * Adds the given auction to the bidders list of associated
	 *  auctions. Increases the bid count as well.
	 * @param theAuction that will be associated with the user.
	 */
	@Override
	public void addAuction(Auction theAuction) {
		if (isNewBidAllowed()) {
			super.addAuction(theAuction);
		}
	}
	
	/**
	 * Returns the current future bid count for a bidder. 
	 */
	private int getBidCount() {
		int currentBidCount = 0;
		LocalDate today = LocalDate.now();
		for (Auction auction: this.getMyAuctions()) {
			if (auction.getDate().isAfter(today)) {
				currentBidCount += auction.getMyBids(this).size();
			}
		}
		return currentBidCount;
	}
	
}
