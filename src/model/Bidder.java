package model;

/**
 * Class that represents an abstract user
 * @author Jared Malone
 *
 */
public class Bidder extends User {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = 8113376313259596043L;

	private static final int MY_MAX_BID_COUNT = 10;
	
	private int myBidCount;
	
	public Bidder(String theUsername, String theDisplayName) {
		super(theUsername, theDisplayName);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isNewBidAllowed() {
		return false;
	}
	
	public void incrementBidCount() {
		
	}
	
}
