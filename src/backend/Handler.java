/**
 * 
 */
package backend;

import java.io.IOException;
import java.io.Serializable;

import model.AuctionCalendar;
import model.User;

/**
 * Interface for a data handle that outlines all needed functionality.
 * 
 * @author Jim Rosales
 *
 */
public interface Handler extends Serializable {
	
	/**
	 * Stores a user.
	 * @param theUser data that will be used to store the user.
	 * @return The username for the user.
	 */
	void storeUser(User theUser);
	
	/**
	 * Returns a user associated with the given username.
	 * @param theUsername that will be used to retrieve the user.
	 * @return The user associated with the given username.
	 * @throws IOException if user is not found.
	 */
	User getUser(String theUsername) throws IllegalArgumentException;
	
	/**
	 * Returns the calendar.
	 * @return The calendar.
	 */
	AuctionCalendar getCalendar();

	/**
	 * Overrides the existing calendar with a new one.
	 * @param theCalendar
	 */
	void setCalendar(AuctionCalendar theCalendar);
	
	/**
	 * Saves the data onto storage.
	 */
	void writeData();

	
}
