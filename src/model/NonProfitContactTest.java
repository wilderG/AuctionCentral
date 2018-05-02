package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NonProfitContactTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void isDateOneYearAfterAuction_doesHaveNoPriorAuctions_true() {
		NonProfitContact NPFTest = new NonProfitContact("Doragon", "Doragon");
		assertTrue("This non-profit has prior auctions", NPFTest.isPriorExist());
	}

	@Test
	public void isDateOneYearAfterAuction_isExactlyOneYearSinceLast_true() {
		AuctionDate dateTest = new AuctionDate(2, 5, 2017);
		NonProfitContact NPFTest = new NonProfitContact("Doragon", "Doragon");
		assertSame("The prior auctions date was exactly one year before", dateTest, NPFTest.getPriorDate());
	}
	
	@Test
	public void isDateOneYearAfterAuction_isExactlyOneDayAfterOneYearSinceLast_true() {
		AuctionDate dateTest = new AuctionDate(3, 5, 2017);
		NonProfitContact NPFTest = new NonProfitContact("Doragon", "Doragon");
		assertSame("The prior auctions date was exactly one year before", dateTest, NPFTest.getPriorDate());
	}

}
