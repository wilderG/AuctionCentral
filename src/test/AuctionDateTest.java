/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionDate;




/**
 * Tests the AuctionDate class functionality.
 * 
 * @author Steven Golob 
 * @version April 30, 2018
 */
public class AuctionDateTest {
    
    private LocalDate oneMonthFromToday;
    
    private AuctionDate dateWithNoAuctions;
    
    private AuctionDate dateWithOneLessThanCapacityAuctions;
    
    private AuctionDate dateWithCapacityAuctions;
    
    @Before
    public void setup() {
        oneMonthFromToday = LocalDate.now().plusMonths(1);
        dateWithNoAuctions = new AuctionDate(
                oneMonthFromToday.getDayOfMonth(), 
                oneMonthFromToday.getMonthValue(),
                oneMonthFromToday.getYear());
        
        dateWithOneLessThanCapacityAuctions = new AuctionDate(
                oneMonthFromToday.getDayOfMonth(),
                oneMonthFromToday.getMonthValue(),
                oneMonthFromToday.getYear());
        for (int i = 0; i < AuctionDate.MAX_AUCTIONS - 1; i++)
            dateWithOneLessThanCapacityAuctions.addAuction(
            		new Auction(LocalDate.now().plusDays(30), "ACLU"));
        
        dateWithCapacityAuctions = new AuctionDate(
                oneMonthFromToday.getDayOfMonth(),
                oneMonthFromToday.getMonthValue(),
                oneMonthFromToday.getYear());
        for (int i = 0; i < AuctionDate.MAX_AUCTIONS; i++)
            dateWithCapacityAuctions.addAuction(
            		new Auction(LocalDate.now().plusDays(30), "NPR"));
    }

    /**
     * Determines that an auction was added when no
     * auctions are scheduled yet on a date.
     */
    @Test
    public void addAuction_noAuctionsOnRequestedDate_newAuctionAdded() {
        assertEquals("date should hold no auctions yet", 0, 
                dateWithNoAuctions.getAuctions().size());
        dateWithNoAuctions.addAuction(
        		new Auction(LocalDate.now().plusDays(30), "Sierra Club"));
        assertEquals("date should hold one auction now", 1, 
                dateWithNoAuctions.getAuctions().size());
    }

    /**
     * Determines that an auction was added when less than the 
     * capacity number of auctions are scheduled for a date.
     */
    @Test
    public void addAuction_lessThanMaxAuctionsOnRequestedDate_newAuctionAdded() {
        dateWithOneLessThanCapacityAuctions.addAuction(
        		new Auction(LocalDate.now().plusDays(30), "Green Peace"));
        
        assertEquals("date should hold capacity auctions now", 
                AuctionDate.MAX_AUCTIONS, 
                dateWithOneLessThanCapacityAuctions.getAuctions().size());
        assertTrue("Should be at capacity", 
                dateWithOneLessThanCapacityAuctions.isAtCapacity());
    }

    /**
     * Checks that an exception is thrown when attempting to 
     * schedule an auction when a date is at capacity.
     */
    @Test (expected = IllegalArgumentException.class)
    public void addAuction_maxAuctionsOnRequestedDate_ExceptionThrown() {
        dateWithCapacityAuctions.addAuction(
        		new Auction(LocalDate.now().plusDays(30),
        				"St. Jude's Children's Research Hospital"));
    }

    /**
     * Checks that isAtCapacity returns the 
     * correct value when it is and is not at capacity.
     */
    @Test
    public void isAtCapacity_maxAuctionsOnRequestedDate_shouldBeTrue() {
        assertTrue("Date should be at capacity", 
                dateWithCapacityAuctions.isAtCapacity());
    }
}