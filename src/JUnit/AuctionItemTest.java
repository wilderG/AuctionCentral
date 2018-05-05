/*
 * TCSS 360 - Software Development & Quality Techniques
 * Group 1
 * AuctionCentral
 */
package JUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.AuctionItem;
import model.Bid;
import model.Bidder;




/**
 * Tests the AuctionItem class and its functionality.
 * 
 * @author Steven Golob 
 * @version April 30, 2018
 */
public class AuctionItemTest {
    
    private AuctionItem testItem;
    
    private Bidder bidderMakingBidsOnTestItem;
    
    @Before
    public void setup() {
        testItem = new AuctionItem(BigDecimal.valueOf(35.0), new LinkedList<Bid>(), "BB-gun");
        bidderMakingBidsOnTestItem = new Bidder("bidder1", "StevenTest");
    }
    
    /**
     * Test the isBidValid method. Specifically, checks that a bid is valid for the 
     * amount equal to minimum bid amount .
     */
    @Test
    public void isBidValid_equalToMinimumBid_shouldBeTrue() {
        assertTrue("Equal bid to minimum amount is valid", testItem.isBidValid(bidderMakingBidsOnTestItem, BigDecimal.valueOf(35.0)));
    }

    /**
     * Test the isBidValid method. Specifically, checks that a bid is valid for an amount 
     * greater than the minimum bid amount.
     */
    @Test
    public void isBidValid_greaterThanMinimumBid_shouldBeTrue() {
        assertTrue("Equal bid to minimum amount is valid", testItem.isBidValid(bidderMakingBidsOnTestItem, BigDecimal.valueOf(35.01)));
    }

    /**
     * Test the isBidValid method. Specifically, checks that a bid is not valid for an 
     * amount less than the minimum bid amount.
     */
    @Test
    public void isBidValid_lessThanMinimumBid_shouldBeFalse() {
        assertFalse("Equal bid to minimum amount is valid", testItem.isBidValid(bidderMakingBidsOnTestItem, BigDecimal.valueOf(34.99)));
    }
}