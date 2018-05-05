/**
 * 
 */
package JUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.AuctionCalendar;
import model.AuctionDate;

/**
 * @author Jim Rosales
 *
 */
public class AuctionCalendarTests {
	private AuctionCalendar theCalendar;
	private AuctionDate acceptableDateMinimumDaysOutAndWithinMaximumDaysOut;
	private AuctionDate acceptableDatePastMinimumDaysOutAndWithinMaximumDaysOut;
	private AuctionDate nonAcceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		LocalDate today = LocalDate.now();
		
		LocalDate todayPlusMinDaysOut = today.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
		acceptableDateMinimumDaysOutAndWithinMaximumDaysOut = 
				new AuctionDate(todayPlusMinDaysOut.getDayOfMonth(),
								todayPlusMinDaysOut.getMonthValue(),
								todayPlusMinDaysOut.getYear());
		
		LocalDate oneDayAfterTodayPlusMinDaysOut = today.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT + 1);
		acceptableDatePastMinimumDaysOutAndWithinMaximumDaysOut = 
				new AuctionDate(oneDayAfterTodayPlusMinDaysOut.getDayOfMonth(),
								oneDayAfterTodayPlusMinDaysOut.getMonthValue(),
								oneDayAfterTodayPlusMinDaysOut.getYear());
		
		LocalDate oneDayBeforeTodayPlusMinDaysOut = today.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT - 1);
		nonAcceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut =
				new AuctionDate(oneDayBeforeTodayPlusMinDaysOut.getDayOfMonth(),
								oneDayBeforeTodayPlusMinDaysOut.getMonthValue(),
								oneDayBeforeTodayPlusMinDaysOut.getYear());

		theCalendar = new AuctionCalendar();
	}

	@Test
	public void isDateWithinEligableRange_DateWithMinimumDaysOut_True() {
		assertTrue("The date with the minumum days out from the current date is not acceptable", 
				theCalendar.isDateWithinEligableRange(acceptableDateMinimumDaysOutAndWithinMaximumDaysOut));
	}
	
	@Test
	public void isDateWithinEligableRange_DateMoreThanMinimumDaysOut_True() {
		assertTrue("The date more than the minumum days out from the current date is acceptable", 
				theCalendar.isDateWithinEligableRange(acceptableDatePastMinimumDaysOutAndWithinMaximumDaysOut));
	}
	
	@Test
	public void isDateWithinEligableRange_DateBeforeTheMinimumDaysOut_False() {
		assertFalse("The date less than the minumum days out from the current date is acceptable", 
				theCalendar.isDateWithinEligableRange(nonAcceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut));
	}

}
