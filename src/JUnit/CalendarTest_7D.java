package JUnit;

/**
 * Tests that no auction can be scheduled more than
 * a set number of days from the current date.  
 * 
 * @author Jared Malone (5/3/2018)
 */
import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Auction;
import model.AuctionCalendar;
import model.AuctionDate;
import model.NonProfitContact;

public class CalendarTest_7D {

	/** 
	 * The maximum number of days in the future that an
	 * auction be scheduled.
	 */
	private static final int MAXIMUM_DAYS = 60;
	
	/** Test fixtures. **/
	private AuctionCalendar calendar;
	private LocalDate today;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		calendar = new AuctionCalendar();
		today = LocalDate.now();
	}
	
	
	@Test
	public void 
	isDateWithinEligableRange_OneDayLessThanMaximumFromCurrentDate_ReturnsTrue() {
		LocalDate testDate = today.plusDays(MAXIMUM_DAYS - 1);
		
		AuctionDate auctionDateOneDayBeforeMax = 
				new AuctionDate(testDate.getDayOfMonth(),
						testDate.getMonthValue(),
						testDate.getDayOfYear());
		
		assertTrue(calendar.isDateWithinEligableRange(auctionDateOneDayBeforeMax));
	}

	
	@Test
	public void 
	isDateWithinEligableRange_DayOfMaximumFromCurrentDate_ReturnsTrue() {
		LocalDate testDate = today.plusDays(MAXIMUM_DAYS);
		
		AuctionDate auctionDateDayOfMax = 
				new AuctionDate(testDate.getDayOfMonth(),
						testDate.getMonthValue(),
						testDate.getDayOfYear());
		
		assertTrue(calendar.isDateWithinEligableRange(auctionDateDayOfMax));
	}
	
	
	@Test
	public void 
	isDateWithinEligableRange_OneDayAfterMaximumFromCurrentDate_ReturnsFalse() {
		LocalDate testDate = today.plusDays(MAXIMUM_DAYS + 1);
		
		AuctionDate auctionDateOneDayAfterMax = 
				new AuctionDate(testDate.getDayOfMonth(),
						testDate.getMonthValue(),
						testDate.getDayOfYear());
		
		assertFalse(calendar.isDateWithinEligableRange(auctionDateOneDayAfterMax));
	}
}
