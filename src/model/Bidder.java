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
	public static final int MY_MAX_BID_COUNT = 10;
	
	/**
	 * The current bid count for the bidder for all future auctions.
	 */
	private int myBidCount;
	
	/**
	 * Constructor for a bidder
	 * @param theUsername that will be associated with the newly created bidder.
	 * @param theDisplayName that will be associated with the newly created bidder.
	 */
	public Bidder(String theUsername, String theDisplayName) {
		super(theUsername, theDisplayName);
		myBidCount = 0;
	}
	
	/**
	 * Determines whether or not a new bid is allowed for the bidder
	 * @return True if the current bidCount for the user is less than the max bid count allowed. Else 
	 * returns false.
	 */
	public boolean isNewBidAllowed() {
		updateBidCount();
		return myBidCount < MY_MAX_BID_COUNT;
	}
	
	/**
	 * Increments the bid count for the bidder if it is allowed. If not the bid count is not modified.
	 */
	private void incrementBidCount() {
		myBidCount++;
	}
	
	/**
	 * Adds the given auction to the bidders list of associated auctions. Increases the bid count as well.
	 * @param theAuction that will be associated with the user.
	 */
	@Override
	public void addAuction(Auction theAuction) {
		if (isNewBidAllowed()) {
			incrementBidCount();
			super.addAuction(theAuction);
		}
	}
	
	/**
	 * Sets the bidders bid count to the given value
	 * @param theBidCount that will be used to set the bidders bid count.
	 */
	public void setBidCount(int theBidCount) {
		myBidCount = theBidCount;
	}
	
	/**
	 * Updates the current bid count for a bidder to reflect only the auctions that are upcoming.
	 */
	private void updateBidCount() {
		int currentBidCount = 0;
		LocalDate today = LocalDate.now();
		for (Auction auction: this.getMyAuctions()) {
			if (auction.getDate().isAfter(today)) {
				currentBidCount += auction.getMyBids(this).size();
			}
		}
		myBidCount = currentBidCount;
	}
	
}
