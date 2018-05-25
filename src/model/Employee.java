package model;

/**
 * Represents an employee of Auction Central that has administrative privileges. 
 * @author Jim Rosales
 *
 */
public class Employee extends User{

	/**
	 * Generated SerialVersionUID that is used for object serialization.
	 */
	private static final long serialVersionUID = -452457520157232588L;

	/**
	 * Constructor for an Employee that initializes the employees username and display name.
	 * @param theUsername
	 * @param theDisplayName
	 */
	public Employee(String theUsername, String theDisplayName) {
		super(theUsername, theDisplayName);
		// TODO Auto-generated constructor stub
	}

}
