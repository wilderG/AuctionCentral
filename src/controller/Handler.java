/**
 * 
 */
package controller;

import java.io.Serializable;

import model.Calendar;
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
	 * @param theUsername username to reference the user.
	 * @param theUser data that will be used to store the user.
	 * 
	 */
	void storeUser(String theUsername, User theUser);
	
	
	/**
	 * Returns a user associated with the given username.
	 * @param theUsername that will be used to retrieve the user.
	 * @return The user associated with the given username.
	 */
	User getUser(String theUsername);
	
	
	/**
	 * Returns the calendar.
	 * @return The calendar.
	 */
	Calendar getCalendar();
	
	
	/**
	 * Saves the data onto storage.
	 */
	void writeData();
}
