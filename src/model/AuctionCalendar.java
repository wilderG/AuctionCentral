/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;




/**
 * Represents the system that keeps track of auction dates holding Auctions. Is responsible
 * for determining if a new auction may be scheduled.
 * 
 * @author Steven Golob 
 * @version April 30, 2018
 */
public class AuctionCalendar implements Serializable {

    /** A generated serialVersion UID for persistent storage. */
    private static final long serialVersionUID = -800808585738004105L;
    
    /** Maximum days out that an auction can be scheduled. */
    public static final int MAXIMUM_DAYS_OUT = 60;
    
    /** Minimum days out that an auction can be scheduled. */
    public static final int MINIMUM_DAYS_OUT = 14;
    
    /** Maximum number of auctions in the future that can be scheduled at a time. */
    public static final int MAXIMUM_FUTURE_AUCTIONS = 25;
    
    /** All of the dates used currently by the calendar. */
    private final List<AuctionDate> myDates;
    
    /** The number of auctions that are in the future, including today. */
    private int myNumberOfFutureAuctions;
    
    /** The current date. */
    private LocalDate myCurrentDate;
    
    /**
     * A management system for storing auctions by their dates. Handles rules for 
     * submitting new auctions.
     */
    public AuctionCalendar() {
        myDates = new LinkedList<>();
        myNumberOfFutureAuctions = 0;
        myCurrentDate = LocalDate.now();
    }
    
    //_________________________________________________________________________________________
    
    /**
     * Schedules a new auction if all the rules are met for scheduling an auction, and the 
     * date to be scheduled is available for new auctions.
     * 
     * @param theAuction the new auction to be scheduled
     * @param theDay the day of month to add an auction
     * @param theMonth the month to add an auction
     * @param theYear the year to add an auction
     * @throws IllegalArgumentException if date is already at capacity
     */
    public void submitAuction(final Auction theAuction, 
                              final int theDay, final int theMonth, final int theYear) 
                                                          throws IllegalArgumentException {
        AuctionDate dateForAuction = getAuctionDate(theDay, theMonth, theYear);
        if (!isAllowingNewAuction())
            throw new IllegalArgumentException("Already at maximum amount of auctions!");
        if (!isDateWithinEligableRange(dateForAuction))
            throw new IllegalArgumentException("Specified date (" 
                                + dateForAuction.format() 
                                + ") out of eligable range");
        dateForAuction.addAuction(theAuction);
        myNumberOfFutureAuctions++;
    }
    
    /**
     * Determines if the schedule is allowing new auctions to be scheduled at this time. 
     * Determined based on having less than maximum number of auctions.
     * 
     * @return whether or not the schedule is at capacity yet
     */
    public boolean isAllowingNewAuction() {
        return myNumberOfFutureAuctions < MAXIMUM_FUTURE_AUCTIONS;
    }
    
    /**
     * Determines if the given date is within the eligible range for scheduling a new auction.
     * 
     * @param theDay the day of month to add an auction
     * @param theMonth the month to add an auction
     * @param theYear the year to add an auction
     * @return whether or not given date is in eligible range to schedule auction
     * @throws IllegalArgumentException if given date is not valid
     */
    public boolean isDateWithinEligableRange(final int theDay, 
                                             final int theMonth, final int theYear) 
                                                     throws IllegalArgumentException {
        AuctionDate date = new AuctionDate(theDay, theMonth, theYear);
        return isDateWithinEligableRange(date);
    }
    /**
     * Determines if the given date is within the eligible range for scheduling a new auction.
     * 
     * @param theAuctionDate the date to be considered
     * @return whether or not given date is in eligible range to schedule auction
     */
    public boolean isDateWithinEligableRange(final AuctionDate theAuctionDate) {
        boolean result = true;
        
        if (theAuctionDate.getDate().isAfter(myCurrentDate.plusDays(MAXIMUM_DAYS_OUT + 1))) {
        		result = false;
        } else if (theAuctionDate.getDate().isBefore(myCurrentDate.plusDays(MINIMUM_DAYS_OUT + 1))) {
        		result = false;
        }

        return result;
    }
    
    /**
     * Updates the current date and current number of future auctions.
     */
    public void updateDate() {
//        Calendar now = Calendar.getInstance();
//        myCurrentDate = getAuctionDate(now.get(Calendar.DAY_OF_MONTH), 
//                                       now.get(Calendar.MONTH) + 1, 
//                                       now.get(Calendar.YEAR));
    		myCurrentDate = LocalDate.now();
        myNumberOfFutureAuctions = 0;
        for (AuctionDate date : myDates) {
            if (date.compareToToday() >= 0)
                myNumberOfFutureAuctions += date.getNumberOfAuctions();
        }
    }
    
    /**
     * Gets the number of future auctions scheduled, including today.         
     * 
     * @return the number of future auctions
     */
    public int getFutureNumberOfAuction() {
        return myNumberOfFutureAuctions;
    }
    
    /**
     * This method returns all future auctions, not including today.
     * 
     * @return a list of all future auctions after today
     */
    public List<Auction> getFutureAuctions() {
        List<Auction> futureAuctions = new LinkedList<>();
        for (AuctionDate date : myDates) {
            if (date.compareToToday() > 0)
                futureAuctions.addAll(date.getAuctions());
        }
        return futureAuctions;
    }
    
    /**
     * This method returns an auctionDate that can hold auctions. If the date does not yet 
     * exist in the calendar, it creates a date and adds it to the calendar first.
     * 
     * @param theDay the day of the month
     * @param theMonth the month (1 - 12)
     * @param theYear the year
     * @return the auction date that holds auctions
     * @throws IllegalArgumentException if date is invalid
     */
    public AuctionDate getAuctionDate(final int theDay, final int theMonth, final int theYear)
                                                    throws IllegalArgumentException {
        AuctionDate newDate = new AuctionDate(theDay, theMonth, theYear);
        for (AuctionDate date : myDates) {
            if (newDate.getDate().compareTo(date.getDate()) == 0)
                // date already exists in calendar
                return date;
        }
        myDates.add(newDate);
        return newDate;
    }   
    
    public void forceAddAuctionInThePast(Auction theAuction, int theAmountOfYearsInThePast) {
    		myCurrentDate.minusYears(theAmountOfYearsInThePast);
		LocalDate pastDate = theAuction.getDate();
		submitAuction(theAuction, pastDate.getDayOfMonth(), pastDate.getMonthValue(), pastDate.getYear());
		myCurrentDate = LocalDate.now();
    }
   
    
}
