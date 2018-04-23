/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * Provides common implementations of some user behaviors.
 * @author Jim Rosales
 */
public abstract class AbstractUser implements Serializable {
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -2193665280820986783L;
	
	/**
	 * The username associated with the user.
	 */
	protected String myUsername;
	
	/**
	 * The name of the user
	 */
	protected String myName;
	
	/**
	 * Constructs a user.
	 * @param theUserName The username that the user will be initialized with
	 */
	protected AbstractUser(String theUsername, String theName) {
		myUsername = theUsername;
		myName = theName;
	}
	
	/**
	 * Returns the username associated with the respective user.
	 * @return The username for the user.
	 */
	public String getUsername() {
		return myUsername;
	}
	
	/**
	 * Returns the name of the user.
	 * @return The name of the user.
	 */
	public String getName() {
		return myName;
	}
	
	
}
