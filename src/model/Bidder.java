package model;

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
		return myBidCount < MY_MAX_BID_COUNT;
	}
	
	/**
	 * Increments the bid count for the bidder if it is allowed. If not the bid count is not modified.
	 */
	public void incrementBidCount() {
		if (isNewBidAllowed()) {
			myBidCount++;
		}
	}
	
	/**
	 * Sets the bidders bid count to the given value
	 * @param theBidCount that will be used to set the bidders bid count.
	 */
	public void setBidCount(int theBidCount) {
		myBidCount = theBidCount;
	}
	
}
