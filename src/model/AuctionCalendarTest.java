/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;




/**
 * Tests the AuctionCalendar class functionality.
 * 
 * @author Steven Golob 
 * @version April 30, 2018
 */
public class AuctionCalendarTest {

    /**
     * Tests the submitAuction method. Specifically, checks that an auction is scheduled 
     * if the requested date has no auctions on it.
     */
    @Test
    public void submitAuction_noAuctionsOnRequestedDate_newAuctionAdded() {
        AuctionCalendar testCalendar = new AuctionCalendar();
        Auction testAuction = new Auction();
        testCalendar.submitAuction(testAuction, 30, 5, 2018);
        assertEquals("Should have 1 auction", 1, testCalendar.getFutureNumberOfAuction());
        assertSame("Should be same auction scheduled", testAuction, testCalendar.getFutureAuctions().get(0));
    }

    /**
     * Tests the submitAuction method. Specifically, checks that an auction is scheduled if 
     * the requested date has less than the maximum number of auctions on it.
     */
    @Test
    public void submitAuction_lessThanMaxAuctionsOnRequestedDate_newAuctionAdded() {
        AuctionCalendar testCalendar = new AuctionCalendar();
        testCalendar.submitAuction(new Auction(), 30, 5, 2018);
        Auction testAuction = new Auction();
        testCalendar.submitAuction(testAuction, 30, 5, 2018);
        assertEquals("Should have 1 auction", 2, testCalendar.getFutureNumberOfAuction());
        assertSame("Should be same auction scheduled", testAuction, testCalendar.getFutureAuctions().get(1));
    }

    /**
     * Tests the submitAuction method. Specifically, checks that an exception is thrown when 
     * attempting to schedule an auction on a date that already has the maximum number of 
     * auctions.
     */
    @Test (expected = IllegalArgumentException.class)
    public void submitAuction_maxAuctionsOnRequestedDate_ExceptionThrown() {
        AuctionCalendar testCalendar = new AuctionCalendar();
        testCalendar.submitAuction(new Auction(), 30, 5, 2018);
        testCalendar.submitAuction(new Auction(), 30, 5, 2018);
        testCalendar.submitAuction(new Auction(), 30, 5, 2018);
    }
}