package model;

import java.util.Date;

/**
 * Class represents a request for a new auction.
 * @author Jim Rosales
 */
public class NewAuctionRequest {
	
	/**
	 * The sponsor for the new auction.
	 */
	User mySponsor;
	
	/**
	 * The date for the requested auction.
	 */
	Date myDate;
	
	/**
	 * Constructor for a new auction request.
	 * @param theSponsor that will be associated with the newly requested auction.
	 * @param theDate on which the newly requested auction be held.
	 */
	public NewAuctionRequest(User theSponsor, Date theDate) {
		mySponsor = theSponsor;
		myDate = theDate;
	}
	
	/**
	 * Getter for the sponsor for the newly requested auction.
	 * @return The sponsor for the newly requested auction.
	 */
	public User getMySponsor() {
		return mySponsor;
	}
	
	/**
	 * Getter for the date on which the newly requested auction will be held.
	 * @return The date on which the new auction will be held.
	 */
	public Date getMyDate() {
		return myDate;
	}
}

