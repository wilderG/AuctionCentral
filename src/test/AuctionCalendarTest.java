/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Auction;
import model.AuctionCalendar;
import model.AuctionDate;

/**
 * 
 * @author Jared Malone (5/3/2018)
 * @author Jim Rosales (5/5/2018)
 * @author Steven Golob
 * @version 5/5/2018
 */
public class AuctionCalendarTest {
	
	/** Test fixtures. **/
	private LocalDate theCurrentDay;
	
	private AuctionCalendar theCalendar;
	private AuctionDate theAcceptableDateMinimumDaysOutAndWithinMaximumDaysOut;
	private AuctionDate theAcceptableDatePastMinimumDaysOutAndWithinMaximumDaysOut;
	private AuctionDate theNonAcceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		theCurrentDay = LocalDate.now();
		
		LocalDate todayPlusMinDaysOut = theCurrentDay.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
		theAcceptableDateMinimumDaysOutAndWithinMaximumDaysOut = 
				new AuctionDate(todayPlusMinDaysOut.getDayOfMonth(),
								todayPlusMinDaysOut.getMonthValue(),
								todayPlusMinDaysOut.getYear());
		
		LocalDate oneDayAfterTodayPlusMinDaysOut = theCurrentDay.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT + 1);
		theAcceptableDatePastMinimumDaysOutAndWithinMaximumDaysOut = 
				new AuctionDate(oneDayAfterTodayPlusMinDaysOut.getDayOfMonth(),
								oneDayAfterTodayPlusMinDaysOut.getMonthValue(),
								oneDayAfterTodayPlusMinDaysOut.getYear());
		
		LocalDate oneDayBeforeTodayPlusMinDaysOut = theCurrentDay.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT - 1);
		theNonAcceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut =
				new AuctionDate(oneDayBeforeTodayPlusMinDaysOut.getDayOfMonth(),
								oneDayBeforeTodayPlusMinDaysOut.getMonthValue(),
								oneDayBeforeTodayPlusMinDaysOut.getYear());

		theCalendar = new AuctionCalendar();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
     * Tests the submitAuction method. Specifically, checks that an auction is scheduled 
     * if the requested date has no auctions on it.
     */
    @Test
    public void submitAuction_noAuctionsOnRequestedDate_newAuctionAdded() {
        Auction testAuction = new Auction(null, "");
        theCalendar.submitAuction(testAuction, 30, 6, 2018);
        assertEquals("Should have 1 auction", 1, theCalendar.getFutureNumberOfAuction());
        assertSame("Should be same auction scheduled", testAuction, theCalendar.getFutureAuctions().get(0));
    }

    /**
     * Tests the submitAuction method. Specifically, checks that an auction is scheduled if 
     * the requested date has less than the maximum number of auctions on it.
     */
    @Test
    public void submitAuction_lessThanMaxAuctionsOnRequestedDate_newAuctionAdded() {
    		theCalendar.submitAuction(new Auction(null, ""), 30, 6, 2018);
        Auction testAuction = new Auction(null, "");
        theCalendar.submitAuction(testAuction, 30, 6, 2018);
        assertEquals("Should have 1 auction", 2, theCalendar.getFutureNumberOfAuction());
        assertSame("Should be same auction scheduled", testAuction, theCalendar.getFutureAuctions().get(1));
    }

    /**
     * Tests the submitAuction method. Specifically, checks that an exception is thrown when 
     * attempting to schedule an auction on a date that already has the maximum number of 
     * auctions.
     */
    @Test (expected = IllegalArgumentException.class)
    public void submitAuction_maxAuctionsOnRequestedDate_ExceptionThrown() {
    		theCalendar.submitAuction(new Auction(null, ""), 30, 6, 2018);
    		theCalendar.submitAuction(new Auction(null, ""), 30, 6, 2018);
    		theCalendar.submitAuction(new Auction(null, ""), 30, 6, 2018);
    }
	
	@Test
	public void 
	isDateWithinEligableRange_OneDayLessThanMaximumFromCurrentDate_ReturnsTrue() {
		LocalDate testDate = theCurrentDay.plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT - 1);
		
		AuctionDate auctionDateOneDayBeforeMax = 
				new AuctionDate(testDate.getDayOfMonth(),
						testDate.getMonthValue(),
						testDate.getYear());
		
		assertTrue(theCalendar.isDateWithinEligableRange(auctionDateOneDayBeforeMax));
	}

	
	@Test
	public void 
	isDateWithinEligableRange_dayOfMaximumFromCurrentDate_ReturnsTrue() {
		LocalDate testDate = theCurrentDay.plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT);
		
		AuctionDate auctionDateDayOfMax = 
				new AuctionDate(testDate.getDayOfMonth(),
						testDate.getMonthValue(),
						testDate.getYear());
		
		assertTrue(theCalendar.isDateWithinEligableRange(auctionDateDayOfMax));
	}
	
	
	@Test
	public void 
	isDateWithinEligableRange_OneDayAfterMaximumFromCurrentDate_ReturnsFalse() {
		LocalDate testDate = theCurrentDay.plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT + 1);
		
		AuctionDate auctionDateOneDayAfterMax = 
				new AuctionDate(testDate.getDayOfMonth(),
						testDate.getMonthValue(),
						testDate.getYear());
		
		assertFalse(theCalendar.isDateWithinEligableRange(auctionDateOneDayAfterMax));
	}

	@Test
	public void isDateWithinEligableRange_DateWithMinimumDaysOut_True() {
		assertTrue("The date with the minumum days out from the current date is not acceptable", 
				theCalendar.isDateWithinEligableRange(theAcceptableDateMinimumDaysOutAndWithinMaximumDaysOut));
	}
	
	@Test
	public void isDateWithinEligableRange_DateMoreThanMinimumDaysOut_True() {
		assertTrue("The date more than the minumum days out from the current date is acceptable", 
				theCalendar.isDateWithinEligableRange(theAcceptableDatePastMinimumDaysOutAndWithinMaximumDaysOut));
	}
	
	@Test
	public void isDateWithinEligableRange_DateBeforeTheMinimumDaysOut_False() {
		assertFalse("The date less than the minumum days out from the current date is acceptable", 
				theCalendar.isDateWithinEligableRange(theNonAcceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut));
	}

}
