/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;




/**
 * Represents a date that can have an auction. Can only hold a certain amount of auctions, 
 * and is responsible for determining if it can schedule more.
 * 
 * @author Steven Golob 
 * @version April 30, 2018
 */
public class AuctionDate implements Comparable<AuctionDate>, Serializable {

    /** A generated serialVersion UID for persistent storage. */
    private static final long serialVersionUID = 609626313571103034L;
    
    /** Maximum number of auctions for a day. */
    public static final int MAX_AUCTIONS = 2;
    
    /** Number of milliseconds per day. */
    public static final Long MILLIS_PER_DAY = (long) (1000 * 60 * 60 * 24);
    
    /** The day of the month. */
    private final int myDay;
    
    /** The month of this date. */
    private final int myMonth;
    
    /** The year of this date. */
    private final int myYear;
    
    /** The auctions scheduled on this day. */
    private final List<Auction> myAuctions;
    
    /**
     * Determines if given date is valid before instantiating. Initially, has 
     * no auctions scheduled for this date.
     * 
     * @param theDay the day of the month 
     * @param theMonth the month
     * @param theYear the year
     */
    public AuctionDate(final int theDay, final int theMonth, final int theYear) {                                    // magic numbers??????????????
        if (theMonth == 1 || theMonth == 3 || theMonth == 5 || 
                theMonth == 7 || theMonth == 8 || theMonth == 10 || theMonth == 12) {
            if (theDay > 31 || theDay < 1)
                throw new IllegalArgumentException(
                        "Must be between 1 and 31 days for month " + theMonth);
        } else if (theMonth == 4 || theMonth == 6 || theMonth == 9 || theMonth == 11) {
            if (theDay > 30 || theDay < 1)
                throw new IllegalArgumentException(
                        "Must be between 1 and 30 days for month " + theMonth);
        } else if (theMonth == 2) {
            if (isLeapYear(theYear)) {
                if (theDay > 29 || theDay < 1)
                    throw new IllegalArgumentException(
                            "Must be between 1 and 29 days for month " + theMonth 
                            + ", and year " + theYear);
            } else {
                if (theDay > 28 || theDay < 1)
                    throw new IllegalArgumentException(
                            "Must be between 1 and 28 days for month "
                            + theMonth + ", and year " + theYear); 
            }
        } else if (theMonth > 12 || theMonth < 1) {
            throw new IllegalArgumentException(
                    "Invalid month: " + theMonth + " (must be between 1 and 12)");
        }
        
        myDay = theDay;
        myMonth = theMonth;
        myYear = theYear;
        myAuctions = new LinkedList<>();
    }
    
    //_________________________________________________________________________________________
    
    /**
     * Gets the number of auctions scheduled for this date.
     * 
     * @return the number of auctions for this date
     */
    public int getNumberOfAuctions() {
        return myAuctions.size();
    }
    
    /**
     * Determines if this day can take on any more auctions or not based on maximum.
     * 
     * @return whether or not can schedule more auctions
     */
    public boolean isAtCapacity() {
        return myAuctions.size() >= MAX_AUCTIONS;
    }
    
    /**
     * Attempts to add auction to this date. Must not be at capacity in order to succeed.
     * 
     * @param theAuction the auction to be added
     */
    public void addAuction(final Auction theAuction) {
        if (isAtCapacity())
            throw new IllegalArgumentException(this.format() + " is already at capacity!");
        myAuctions.add(theAuction);
    }
    
    /**
     * Gets all auctions for this date.
     * 
     * @return the auctions scheduled for this day
     */
    public List<Auction> getAuctions() {
        return myAuctions;
    }
    
    /**
     * This method indicates the number of days in between today and the given date. If
     * the given date is in the future, this method will return a positive number.
     * 
     * @return the comparison between this date and today's date
     */
    public int compareToToday() {
        Calendar now = Calendar.getInstance();
        return this.compareTo(new AuctionDate(now.get(Calendar.DAY_OF_MONTH), 
                                              now.get(Calendar.MONTH) + 1, 
                                              now.get(Calendar.YEAR)));
    }
    
    /**
     * Determines if the given year is a leap year.
     * 
     * @param theYear the year to be considered
     * @return whether is leap year or not
     */
    public static boolean isLeapYear(final int theYear) {
        return theYear % 4 == 0 && (theYear % 100 != 0 || theYear % 400 == 0);                                                     //magic numbers??
    }

    /**
     * Determines if this date's year is a leap year.
     * 
     * @return whether is leap year or not
     */
    public boolean isLeapYear() {
        return myYear % 4 == 0 && (myYear % 100 != 0 || myYear % 400 == 0);
    }
    
    /**
     * Gets the day of month of this date.
     * 
     * @return day of month of this date
     */
    public int getDayOfMonth() {
        return myDay;
    }
    
    /**
     * Gets the month of this date.
     * 
     * @return the month of this date
     */
    public int getMonth() {
        return myMonth;
    }
    
    /**
     * Gets the year of this date.
     * 
     * @return the year of this date
     */
    public int getYear() {
        return myYear;
    }
    
    /**
     * Formats this date's date into yyyy/mm/dd
     * 
     * @return the formatted string of this date's date
     */
    public String format() {
        String thisDate = this.myYear + "/" + this.myMonth + "/" + this.myDay;
        return thisDate;
    }
    
    /**
     * Compares dates by their date only, and not time.
     * 
     * @return the number of days between dates
     */
    @Override
    public int compareTo(final AuctionDate theDate) {
        Calendar thisDay = Calendar.getInstance();
        Calendar thatDay = Calendar.getInstance();
        thisDay.set(myYear, myMonth - 1, myDay);
        thatDay.set(theDate.myYear, theDate.myMonth - 1, theDate.myDay);
        Long thisTime = thisDay.getTimeInMillis();
        Long thatTime = thatDay.getTimeInMillis();
        
        return (int) ((thisTime - thatTime) / MILLIS_PER_DAY);
    }
}