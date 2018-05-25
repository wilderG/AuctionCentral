package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NonProfitContactTest {

	/**
	 * Test for checking if This non-profit has no prior auctions.
	 */
	@Test
	public void isDateOneYearAfterAuction_doesHaveNoPriorAuctions_true() {
		NonProfitContact NPFTest = 
				new NonProfitContact("Doragon", "Doragon");
		assertTrue("This non-profit has "
				+ "prior auctions", NPFTest.isPriorExist());
	}
	
	/**
	 * Test for checking if The prior auction for this non-profit 
	 * was on the date exactly one year before the requested auction.
	 */
	@Test
	public void isDateOneYearAfterAuction_isExactlyOneYearSinceLast_true() {
		AuctionDate dateTest = new AuctionDate(2, 5, 2017);
		NonProfitContact NPFTest = new NonProfitContact("Doragon", "Doragon");
		assertSame("The prior auctions date was "
				+ "exactly one year before", dateTest, NPFTest.getPriorDate());
	}
	
	/**
	 * Test for checking if The prior auction for this non-profit was
	 *  on the date one day after exactly one
	 *   year before the requested auction.
	 */
	@Test
	public void 
	isDateOneYearAfterAuction_isExactlyOneDayAfterOneYearSinceLast_true() {
		AuctionDate dateTest = new AuctionDate(3, 5, 2017);
		NonProfitContact NPFTest = new NonProfitContact("Doragon", "Doragon");
		assertSame("The prior auctions date was exactly one year before",
				dateTest, NPFTest.getPriorDate());
	}

}
