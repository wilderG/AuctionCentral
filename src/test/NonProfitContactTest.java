package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionCalendar;
import model.NonProfitContact;

/**
 * 
 * @author Yohei Sato
 * @author Jim Rosales
 */
public class NonProfitContactTest {
	private NonProfitContact myNonProfitWithAPreviousAuction;
	private NonProfitContact myNonProfitWithNoPreviousAuction;
	private LocalDate myDateToday;
	private LocalDate dateForPreviousAuction;
	private Auction previousAuction;
	private Auction proposedAuction;
	
	/** NonProfit test fixture. **/
	private final NonProfitContact auctionOwner = new NonProfitContact("test", "Test User");
		
	@Before
	public void setUp() throws Exception {
		myDateToday = LocalDate.now();
		
		dateForPreviousAuction = LocalDate.now();
		dateForPreviousAuction = 
				dateForPreviousAuction.minusMonths(AuctionCalendar.MIN_MONTHS_BETWEEN_AUCTIONS_FOR_NONPROF);
		
		proposedAuction =  new Auction(myDateToday, auctionOwner);
		myNonProfitWithAPreviousAuction = new NonProfitContact("auctionAdmin99", "John Smith");
		myNonProfitWithNoPreviousAuction = new NonProfitContact("nonProfitAdmin100", "Janet Smith");
	}

	@Test
	public void isDateForProposedAuctionValid_doesNotHavePriorAuctions_true() {
		assertTrue("Unable to add an auction with no previous auctions",
				myNonProfitWithNoPreviousAuction.isDateForProposedAuctionValid(proposedAuction));
	}

	@Test
	public void isDateForProposedAuctionValid_hasAuctionWithMinElapsedTimeSinceLastAuction_true() {
		previousAuction = new Auction(dateForPreviousAuction, auctionOwner);
		myNonProfitWithAPreviousAuction.addAuction(previousAuction);
		assertTrue("Unable to add an auction when the required elapsed time for the proposed auction has "
				+ "occured from the nonprofits most recent auction",
				myNonProfitWithAPreviousAuction.isDateForProposedAuctionValid(proposedAuction));
	}
	
	@Test
	public void isDateForProposedAuctionValid_isExactlyOneDayAfterMinElapsedTimeSinceLastAuction_false() {
		dateForPreviousAuction = dateForPreviousAuction.plusDays(1);
		previousAuction = new Auction(dateForPreviousAuction, auctionOwner);
		myNonProfitWithAPreviousAuction.addAuction(previousAuction);
		assertFalse("An auction with a date less that the required elapsed time was able to be added",
				myNonProfitWithAPreviousAuction.isDateForProposedAuctionValid(proposedAuction));
	}

}
