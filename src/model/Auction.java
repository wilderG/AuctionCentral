package model;

import java.io.Serializable;

public class Auction implements Serializable {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = -5386584822819727993L;
	
	/** Test Field **/
	private final String myName;
	
	/** Constructor takes a test string. **/
	public Auction(String name) {
		myName = name;
	}
	
	public String toString() {
		return myName;
	}
	
}
