package model;

import java.io.Serializable;

/**
 * Class represents an auction 
 * @author Jared Malone
 *
 */
public class Auction implements Serializable {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = -5386584822819727993L;
	
	/**
	 * Test Field
	 */
	private final String myName;
	
	/**
	 * Constructor that will be used to initialize an auction with the given name.
	 * @param theName that will be associated with the auction
	 */
	public Auction(String theName) {
		myName = theName;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return myName;
	}
	
}
