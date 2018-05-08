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
	
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The minumum amount of years needed to have elapsed between two auctions
	 */
	public static final int MIN_YEAR_ELAPSED = 1;

	/**
	 * {@inheritDoc}
	 */
	public NonProfitContact(String theUsername, String theDisplayName) {
		super(theUsername, theDisplayName);
	}
	
	/**
	 * Determines whether or not the given auction can be
	 *  added for the nonprofit. This is determined by the
	 * MAX time that must have passed since the nonprofits last auction. 
	 * @param theRequestedAuction whose date will be evaluted 
	 * to see if a the required elapsed time has occured
	 * since the nonprofits last auction.
	 * @return True if a the required time since 
	 * the nonprofits last auction has elapsed. False otherwise.
	 */
	public boolean isDateForProposedAuctionValid(Auction theRequestedAuction) {
		return isDateSpecifiedTimeAfterPreviousAuction(theRequestedAuction.getDate());
	}
	
	/**
	 * Determines whether the given date is 
	 * one year after the non profits most recent auction.
	 * @return True if a year has elapsed for the non profits last auction.
	 */
	public boolean isDateSpecifiedTimeAfterPreviousAuction(LocalDate theDate) {
		
//		// Found the most recent date that
//		the non profit had an auction. Add a year to this date
		// Now theDate has to occur on this 
//		date of after for it to be a viable date. 
		LocalDate latestAuctionDate = getLatestDate().plusMonths(
		        AuctionCalendar.MIN_MONTHS_BETWEEN_AUCTIONS_FOR_NONPROF); 
		return latestAuctionDate.isEqual(theDate) ||
				theDate.isAfter(latestAuctionDate);
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
	 * @return A LocalDate that represents the 
	 * date of the non profits most recent auction.
	 */
	public LocalDate getPriorDate() {
		LocalDate mostRecentDate = LocalDate.MIN;
		LocalDate currentDate = LocalDate.now();
		for (Auction auction: super.getMyAuctions()) {
			LocalDate auctionsDate = auction.getDate();
			if (auctionsDate.isBefore(currentDate) && 
					auctionsDate.compareTo(mostRecentDate) > 0) {
				mostRecentDate = auctionsDate;
			}
		}
		return mostRecentDate;
	}
	

    /**
     * Gets the date of the non profits latest auction in the past or future.
     * @return A LocalDate that represents 
     * the date of the non profits most recent auction.
     */
    public LocalDate getLatestDate() {
        LocalDate mostRecentDate = LocalDate.MIN;
        for (Auction auction: super.getMyAuctions()) {
            LocalDate auctionsDate = auction.getDate();
            if (auctionsDate.compareTo(mostRecentDate) > 0) {
                mostRecentDate = auctionsDate;
            }
        }
        return mostRecentDate;
    }
}
