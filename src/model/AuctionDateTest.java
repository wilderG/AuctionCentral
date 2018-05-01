/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;




/**
 * Tests the AuctionDate class functionality.
 * 
 * @author Steven Golob 
 * @version April 30, 2018
 */
public class AuctionDateTest {

    /**
     * Tests the addAuction method. Specifically, determines that an auction was added when no
     * auctions are scheduled yet on a date.
     */
    @Test
    public void addAuction_noAuctionsOnRequestedDate_newAuctionAdded() {
        AuctionDate dateTest = new AuctionDate(30, 5, 2018);
        Auction auction1 = new Auction();
        assertFalse("Should not be at capacity", dateTest.isAtCapacity());
        dateTest.addAuction(auction1);
        assertSame("date should hold this auction now", auction1, dateTest.getAuctions().get(0));
    }

    /**
     * Tests the addAuction method. Specifically, determines that an auction was added 
     * when less than the capacity number of auctions are scheduled for a date.
     */
    @Test
    public void addAuction_lessThanMaxAuctionsOnRequestedDate_newAuctionAdded() {
        AuctionDate dateTest = new AuctionDate(30, 5, 2018);
        Auction auction1 = new Auction();
        Auction auction2 = new Auction();
        dateTest.addAuction(auction1);
        assertFalse("Should not be at capacity", dateTest.isAtCapacity());
        dateTest.addAuction(auction2);
        assertSame("date should hold this auction now", auction1, dateTest.getAuctions().get(0));
        assertSame("date should hold this auction now", auction2, dateTest.getAuctions().get(1));
    }

    /**
     * Tests the addAuction method. Specifically, checks that an exception is thrown when 
     * attempting to schedule an auction when a date is at capacity.
     */
    @Test (expected = IllegalArgumentException.class)
    public void addAuction_maxAuctionsOnRequestedDate_ExceptionThrown() {
        AuctionDate dateTest = new AuctionDate(30, 5, 2018);
        dateTest.addAuction(new Auction());
        dateTest.addAuction(new Auction());
        dateTest.addAuction(new Auction());
    }

    /**
     * Tests the isAtCapacity method. Specifically, checks that isAtCapacity returns the 
     * correct value when it is and is not at capacity.
     */
    @Test
    public void isAtCapacity_maxAuctionsOnRequestedDate_shouldBeTrue() {
        AuctionDate dateTest = new AuctionDate(30, 5, 2018);
        for (int i = 0; i < AuctionDate.MAX_AUCTIONS; i++) {
            assertFalse("Date should not yet be at capacity", dateTest.isAtCapacity());
            dateTest.addAuction(new Auction());
        }
        assertTrue("Date should be at capacity", dateTest.isAtCapacity());
    }
}