/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package model;

import static org.junit.Assert.*;

import javax.print.attribute.standard.JobOriginatingUserName;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;




/**
 * Tests the AuctionItem class and its functionality.
 * 
 * @author Steven Golob 
 * @version April 30, 2018
 */
public class AuctionItemTest {
    
    /**
     * Test the isBidValid method. Specifically, checks that a bid is valid for the 
     * amount equal to minimum bid amount .
     */
    @Test
    public void isBidValid_equalToMinimumBid_shouldBeTrue() {
        AuctionItem item = new AuctionItem(35);
        assertTrue("Equal bid to minimum amount is valid", item.isBidValid(35));
    }

    /**
     * Test the isBidValid method. Specifically, checks that a bid is valid for an amount 
     * greater than the minimum bid amount.
     */
    @Test
    public void isBidValid_greaterThanMinimumBid_shouldBeTrue() {
        AuctionItem item = new AuctionItem(35);
        assertTrue("Equal bid to minimum amount is valid", item.isBidValid(35.01));
    }

    /**
     * Test the isBidValid method. Specifically, checks that a bid is not valid for an 
     * amount less than the minimum bid amount.
     */
    @Test
    public void isBidValid_lessThanMinimumBid_shouldBeFalse() {
        AuctionItem item = new AuctionItem(35);
        assertFalse("Equal bid to minimum amount is valid", item.isBidValid(34.99));
    }

    /**
     * Test the placeBid method. Specifically, checks that a bid was placed with the 
     * amount equal to minimum bid amount.
     */
    @Test
    public void placeBid_equalToMinimumBid_bidPlaced() {
        AuctionItem item = new AuctionItem(35);
        item.placeBid(new Bidder("bidder99", "John Smith"), 35);
        assert...
    }

    /**
     * Test the placeBid method. Specifically, checks that a bid was placed with the 
     * amount greater than minimum bid amount.
     */
    @Test
    public void placeBid_greaterThanMinimumBid_bidPlaced() {
        AuctionItem item = new AuctionItem(35);
        item.placeBid(new Bidder("bidder99", "John Smith"), 35.01);
        assert...
    }

    /**
     * Test the placeBid method. Specifically, checks that an exception is thrown when 
     * attempting to place a bid with the amount less than minimum bid amount.
     */
    @Test (expected = IllegalArgumentException.class)
    public void placeBid_lessThanMinimumBid_exceptionThrown() {
        AuctionItem item = new AuctionItem(35);
        item.placeBid(new Bidder("bidder99", "John Smith"), 34.99);
    }

}
