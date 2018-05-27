/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;




/**
 * Represents the system that keeps track
 *  of auction dates holding Auctions. Is responsible
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
    
    /** The number of months between auctions for a nonprofit. */
    public static final int MIN_MONTHS_BETWEEN_AUCTIONS_FOR_NONPROF = 12;
    
    /** Maximum number of auctions
     *  in the future that can be scheduled at a time. */
    public static final int DEFAULT_MAXIMUM_FUTURE_AUCTIONS = 25;
    
    /** All of the dates used currently by the calendar. */
    private final List<AuctionDate> myDates;
    
    /** The number of auctions that are in the future, including today. */
    private int myNumberOfFutureAuctions;
    
    /** The current date. */
    private LocalDate myCurrentDate;
    
    /** The current maximum number of upcoming auctions accepted. */
    private int myMaximumUpcomingAuctions;
    
    /**
     * A management system for storing auctions by 
     * their dates. Handles rules for submitting new auctions.
     */
    public AuctionCalendar() {
        myDates = new LinkedList<>();
        myNumberOfFutureAuctions = 0;
        myCurrentDate = LocalDate.now();
        myMaximumUpcomingAuctions = DEFAULT_MAXIMUM_FUTURE_AUCTIONS;
    }
    
    //______________________________________________________________________________
    
    /**
     * Schedules a new auction if all the rules are met for
     *  scheduling an auction, and the 
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
            throw new IllegalArgumentException(
            		"Already at maximum amount of auctions!");
        if (!isDateWithinEligableRange(dateForAuction))
            throw new IllegalArgumentException("Specified date (" 
                                + dateForAuction.format() 
                                + ") out of eligable range");
        dateForAuction.addAuction(theAuction);
        myNumberOfFutureAuctions++;
    }
    
    /**
     * Determines if the schedule is 
     * allowing new auctions to be scheduled at this time. 
     * Determined based on having less than maximum number of auctions.
     * 
     * @return whether or not the schedule is at capacity yet
     */
    public boolean isAllowingNewAuction() {
        return myNumberOfFutureAuctions < myMaximumUpcomingAuctions;
    }
    
    /**
     * Determines if the given date is within 
     * the eligible range for scheduling a new auction.
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
     * Determines if the given date is within 
     * the eligible range for scheduling a new auction.
     * 
     * @param theAuctionDate the date to be considered
     * @return whether or not given date 
     * is in eligible range to schedule auction
     */
    public boolean isDateWithinEligableRange(
    		final AuctionDate theAuctionDate) {
        boolean result = true;
        
        if (theAuctionDate.getDate().isAfter(
        		myCurrentDate.plusDays(MAXIMUM_DAYS_OUT))) {
        		result = false;
        } else if (theAuctionDate.getDate().isBefore(
        		myCurrentDate.plusDays(MINIMUM_DAYS_OUT))) {
        		result = false;
        }

        return result;
    }
    
    /**
     * Updates the current date and current number of future auctions.
     */
    public void updateDate() {
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
     * This method returns an auctionDate 
     * that can hold auctions. If the date does not yet 
     * exist in the calendar, it creates a date 
     * and adds it to the calendar first.
     * 
     * @param theDay the day of the month
     * @param theMonth the month (1 - 12)
     * @param theYear the year
     * @return the auction date that holds auctions
     * @throws IllegalArgumentException if date is invalid
     */
    public AuctionDate getAuctionDate(final int theDay,
    		final int theMonth, final int theYear)
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
    
    public AuctionDate getAuctionDate(final LocalDate theDate) {
        return getAuctionDate(theDate.getDayOfMonth(), 
                theDate.getMonthValue(), theDate.getYear());
    }
    
    
    public void forceAddAuctionInThePast(Auction theAuction) {
        LocalDate pastDate = theAuction.getDate();
        AuctionDate dateForAuction = getAuctionDate(pastDate.getDayOfMonth(),
                                pastDate.getMonthValue(), pastDate.getYear());
        dateForAuction.addAuction(theAuction);
        //myNumberOfFutureAuctions++;

        myCurrentDate = LocalDate.now();
    }
   
    /**
     * Changes the current maximum number of auctions being accepted for the future.
     * 
     * pre-condition: the new maximum must be a positive integer
     * post-condition: the calendar will now accept future auctions up until 
     * reaching the new maximum.
     * 
     * @param theNewMax the new number of future auctions accepted
     * @throws IllegelArgumentException if the number is not positive
     */
    public void setMaximumUpcomingAuctions(final int theNewMax) {
        if (theNewMax <= 0) 
            throw new IllegalArgumentException("Maximum must be a positive number!");
        myMaximumUpcomingAuctions = theNewMax;
    }
    
    /**
     * Gets the current number of future auctions being accepted.
     * 
     * @return the current capacity of future auctions.
     */
    public int getMaximumUpcomingAuctions() {
        return myMaximumUpcomingAuctions;
    }
    
    /**
     * Gets all auctions within a specified range of dates, inclusive.
     * 
     * pre-condition: Start date is before or equal to end date.
     * post_condition: returns all auctions in-between the given dates.
     * 
     * @param theStart the initial date of range
     * @param theEnd the closing date of range
     * @return all auctions between the two dates inclusive
     * @throws IllegalArgumentException if start date is after end date
     */
    public Collection<Auction> getAuctionsWithinRange(final LocalDate theStart, final LocalDate theEnd) {
        if (theStart.isAfter(theEnd))
            throw new IllegalArgumentException("Start Date is after end date!");
        
        Set<Auction> auctionsWithinRange = new TreeSet<>();
        for (AuctionDate date : myDates) {
            if ((date.getDate().plusDays(1)).isAfter(theStart) 
                    && (date.getDate().minusDays(1)).isBefore(theEnd)) {
                auctionsWithinRange.addAll(date.getAuctions());
            }
        }
        return auctionsWithinRange;
    }

    /**
     * Gets all auctions in the calendar, past, present, and future.
     * The auctions will be returned in sorted order.
     * 
     * pre-condition: 
     * post-Condition: all auctions in the calendar returned in sorted order
     * 
     * @return all auctions in the calendar
     */
    public Collection<Auction> geAllAuctionsSorted() {
        Set<Auction> allAuctions = new TreeSet<>();
        for (AuctionDate date : myDates) {
            allAuctions.addAll(date.getAuctions());
        }
        return allAuctions;
    }
    
    /**
     * Deletes an auction from the calendar if it has no bids currently.
     * 
     * pre-condition: auction given must exist in the calendar, and
     * must not contain any bids.
     * post-condition: auction will no longer exist in calendar
     * 
     * @param theAuction the auction to be removed
     * @throws Exception if auction is not in calendar
     * @throws Exception if the Auction has bids
     */
    public void deleteAuction(final Auction theAuction) {
        LocalDate dateOfAuctionToBeRemoved = theAuction.getDate();
        AuctionDate dateContainingAuction = getAuctionDate(dateOfAuctionToBeRemoved.getDayOfMonth(), 
                dateOfAuctionToBeRemoved.getMonthValue(), dateOfAuctionToBeRemoved.getYear());
        
        if (!dateContainingAuction.getAuctions().contains(theAuction)) 
            throw new IllegalArgumentException("Auction was not in Calendar!");
        
        dateContainingAuction.removeAuction(theAuction);
        updateDate(); // updates number of upcoming auctions
    }
}
