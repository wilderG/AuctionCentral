package model;

/**
 * Class that represents an abstract user
 * @author Jared Malone
 *
 */
public class Bidder extends AbstractUser {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = 8113376313259596043L;

	
	/** Test auction reference. **/
	private Auction myAuction;
	
	/** Constructor takes a username and auction. **/
	
	/**
	 * Constructor that initializes theUser with the given username, name, and auction data.
	 * @param theUsername The username that will be associated with the user.
	 * @param theName The name of the user.
	 * @param theAuction The Auction data associated with the user.
	 */
	public Bidder(final String theUsername, final String theName, final Auction theAuction) {
		super(theUsername, theName);
		myAuction = theAuction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "User: " + myUsername + ", Auction: " + myAuction.toString();
	}
	
}
