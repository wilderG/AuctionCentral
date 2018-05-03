package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class NonProfitContactTest {
	private NonProfitContact myNonProfitWithAPreviousAuction;
	private NonProfitContact myNonProfitWithNoPreviousAuction;
	private LocalDate myDateToday;
	private LocalDate dateForPreviousAuction;
	private Auction previousAuction;
	private Auction proposedAuction;
	
	@Before
	public void setUp() throws Exception {
		myDateToday = LocalDate.now();
		
		dateForPreviousAuction = LocalDate.now();
		dateForPreviousAuction = dateForPreviousAuction.minusYears(1);
		
		proposedAuction =  new Auction(myDateToday, 0, 0);
		myNonProfitWithAPreviousAuction = new NonProfitContact("auctionAdmin99", "John Smith");
		myNonProfitWithNoPreviousAuction = new NonProfitContact("nonProfitAdmin100", "Janet Smith");
	}

	@Test
	public void isDateForProposedAuctionValid_doesNotHavePriorAuctions_true() {
		assertTrue("Unable to add an auction with no previous auctions",
				myNonProfitWithNoPreviousAuction.isDateForProposedAuctionValid(proposedAuction));
	}

	@Test
	public void isDateForProposedAuctionValid_hasAuctionExactlyOneYearSinceLast_true() {
		previousAuction = new Auction(dateForPreviousAuction, 0, 0);
		myNonProfitWithAPreviousAuction.addAuction(previousAuction);
		assertTrue("Unable to add an auction when the required elapsed time for the proposed auction has "
				+ "occured from the nonprofits most recent auction",
				myNonProfitWithAPreviousAuction.isDateForProposedAuctionValid(proposedAuction));
	}
	
	@Test
	public void isDateForProposedAuctionValid_isExactlyOneDayAfterOneYearSinceLast_false() {
		dateForPreviousAuction = dateForPreviousAuction.plusDays(1);
		previousAuction = new Auction(dateForPreviousAuction, 0, 0);
		myNonProfitWithAPreviousAuction.addAuction(previousAuction);
		assertFalse("An auction with a date less that the required elapsed time was able to be added",
				myNonProfitWithAPreviousAuction.isDateForProposedAuctionValid(proposedAuction));
	}

}
