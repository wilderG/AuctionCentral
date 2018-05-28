package backend;

import java.io.Serializable;
import java.util.HashMap;

import model.AuctionCalendar;
import model.User;

/**
 * Capsule class that is used to store all the users in the system and the current auciton calendar.
 * 
 * @author Jared Malone
 * @version May 5, 2018
 */
public class StorageCapsule implements Serializable {

	/**
	 * Serial version UID for serialization of the capsule.
	 */
	private static final long serialVersionUID = -3002383126736586664L;
	
	/**
	 * Map used to store all the users in the system. The given string is used as a retrieval key for 
	 * a stored user object.
	 */
	public HashMap<String, User> myUsers;
	
	/**
	 * The current calendar used by the system that will be stored/serialized.
	 */
	public AuctionCalendar myCalendar;
	
	/**
	 * Constructor for a Storage capsule. Initializes the users and the calendar with the given objects.
	 * @param theUsers currently in the system that will be serialized.
	 * @param theCalendar currently used by the system that will be serialized.
	 */
	public StorageCapsule(HashMap<String, User> theUsers,
			AuctionCalendar theCalendar) {
		myUsers = theUsers;
		myCalendar = theCalendar;
	}
	
}
