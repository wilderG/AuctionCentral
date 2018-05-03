/**
 * 
 */
package model;

import java.time.LocalDate;

/**
 * Class represents a contact person for a non profit organization.
 * @author Jim Rosales
 */
public class NonProfitContact extends User {
//	AuctionDate myDate = new AuctionDate();


	/**
	 * {@inheritDoc}
	 */
	public NonProfitContact(String theUsername, String theDisplayName) {
		super(theUsername, theDisplayName);
	}
	
	/**
	 * Determines whether the given date is one year after the non profits most recent auction.
	 * @return True if a year has elapsed for the non profits last auction.
	 */
	public boolean isDateOneYearAfterPreviousAuction(LocalDate theDate) {
		LocalDate mostRecentDate = getPriorDate();
		
		// Found the most recent date that the non profit had an auction. Add a year to this date
		// Now theDate has to occur on this date of after for it to be a viable date. 
		mostRecentDate.plusYears(1); 
		return mostRecentDate.isEqual(theDate) || theDate.isAfter(mostRecentDate);
	}
	
	/**
	 * Checks if the priorExist or not.
	 * @return
	 */
	public boolean isPriorExist() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Gets the date of the non profits most recent auction that has passed.
	 * @return A LocalDate that represents the date of the non profits most recent auction.
	 */
	public LocalDate getPriorDate() {
		LocalDate mostRecentDate = LocalDate.MIN;
		LocalDate currentDate = LocalDate.now();
		for (Auction auction: super.getMyAuctions()) {
			LocalDate auctionsDate = auction.getDate();
			if (auctionsDate.isBefore(currentDate) && auctionsDate.compareTo(mostRecentDate) > 0) {
				mostRecentDate = auctionsDate;
			}
		}
		return mostRecentDate;
	}
	

}
