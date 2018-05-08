/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


import java.time.LocalDate;




/**
 * Represents a date that can have an auction. 
 * Can only hold a certain amount of auctions, 
 * and is responsible for determining if it can schedule more.
 * 
 * @author Steven Golob 
 * @author Jim Rosales
 * @version May 2, 2018
 */
public class AuctionDate implements Serializable {

    /** A generated serialVersion UID for persistent storage. */
    private static final long serialVersionUID = 609626313571103034L;
    
    /** Maximum number of auctions for a day. */
    public static final int MAX_AUCTIONS = 2;
    
    /**
     * The date that the auction will be held.
     */
    private LocalDate myDate;
   
    /** The auctions scheduled on this day. */
    private final List<Auction> myAuctions;
    
    /**
     * Determines if given date is valid before instantiating. Initially, has 
     * no auctions scheduled for this date.
     * @precondition: 
     * @param theDay the day of the month 
     * @param theMonth the month
     * @param theYear the year
     * @throws DateTimeException if the given parameters are invalid
     */
    public AuctionDate(final int theDay,
    		final int theMonth, final int theYear) {        
        try {
			myDate = LocalDate.of(theYear, theMonth, theDay);
		} catch (Exception DateTimeExepction) {
			System.err.println("The given date is invalid, please try again.");
		}
        myAuctions = new LinkedList<>();
    }
    
    
    /**
     * Gets the number of auctions scheduled for this date.
     * 
     * @return the number of auctions for this date
     */
    public int getNumberOfAuctions() {
        return myAuctions.size();
    }
    
    /**
     * Determines if this day can take on any
     *  more auctions or not based on maximum.
     * 
     * @return whether or not can schedule more auctions
     */
    public boolean isAtCapacity() {
        return myAuctions.size() >= MAX_AUCTIONS;
    }
    
    /**
     * Attempts to add auction to this date.
     *  Must not be at capacity in order to succeed.
     * 
     * @param theAuction the auction to be added
     * @throws IllegalArgumentExpection if an 
     * auction is added when the date is already at capacity.
     */
    public void addAuction(final Auction theAuction) {
        if (isAtCapacity())
            throw new IllegalArgumentException(this.format() +
            		" is already at capacity!");
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
     * This method indicates the number of days
     *  in between today and the given date. If
     * the given date is in the future,
     *  this method will return a positive number.
     * 
     * @return the comparison between this date and today's date
     */
    public int compareToToday() {
    		return myDate.compareTo(LocalDate.now());
    }
    

    /**
     * Determines if this date's year is a leap year.
     * 
     * @return whether is leap year or not
     */
    public boolean isLeapYear() {
        return myDate.isLeapYear();
    }
    
    /**
     * Gets the day of month of this date.
     * 
     * @return day of month of this date
     */
    public int getDayOfMonth() {
        return myDate.getDayOfMonth();
    }
    
    /**
     * Gets the month of this date.
     * 
     * @return the month of this date
     */
    public int getMonth() {
        return myDate.getDayOfMonth();
    }
    
    /**
     * Gets the year of this date.
     * 
     * @return the year of this date
     */
    public int getYear() {
        return myDate.getDayOfYear();
    }
   
    /**
     * Gets the date for the auction.
     * @return The date of the auction.
     */
    public LocalDate getDate() {
    		return myDate;
    }
    
    /**
     * Formats this date's date into yyyy/mm/dd
     * 
     * @return the formatted string of this date's date
     */
    public String format() {
        String thisDate = myDate.getYear() + "/" +
                myDate.getMonthValue() + "/" + myDate.getDayOfMonth();
        return thisDate;
    }
}