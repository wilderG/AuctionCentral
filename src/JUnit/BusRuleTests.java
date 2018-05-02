/**
 * 
 */
package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Bidder;

/**
 * @author Jim Rosales
 * JUnit Tests
 */
public class BusRuleTests {
	Bidder bidderWithMaxBids;
	Bidder bidderWithBelowMaxBids;

	/**
	 * Constructs the necessary objects for the executed tests.
	 */
	@Before
    public void setUp() {
		bidderWithMaxBids = new Bidder("bidder99", "John Smith");
		bidderWithBelowMaxBids = new Bidder("bidder99", "John Smith");
	}
	
	/**
	 * Tests whether a new bid is able to be placed when a bidder already has the max number of bids allowed.
	 * The expected outcome is that the bidder is not able to place a new bid.
	 */
	@Test
	public void isNewBidAllowable_bidderWithMaxBids_False() {
		bidderWithMaxBids.setBidCount(Bidder.MY_MAX_BID_COUNT);
		assertFalse("A bidder with a max number of bids as expected is not able to place a bid", 
				bidderWithMaxBids.isNewBidAllowed());
	}
	
	/**
	 * Tests whether a new bid is able to be placed when a bidder already one less than the max number of bids
	 * allowed.
	 * The expected outcome is that the bidder is able to place a new bid.
	 */
	@Test
	public void isNewBidAllowable_bidderWithOneBidBelowTheMax_True() {
		bidderWithBelowMaxBids.setBidCount(Bidder.MY_MAX_BID_COUNT - 1);
		assertTrue("A bidder with one less than the number of max bids is able to place a new bid as expected", 
				bidderWithBelowMaxBids.isNewBidAllowed());
	}

}
