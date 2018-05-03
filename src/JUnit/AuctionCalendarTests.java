/**
 * 
 */
package JUnit;

import static org.junit.Assert.*;

import java.util.Calendar;

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
	private AuctionDate acceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		Calendar currentDate = Calendar.getInstance();
		
		acceptableDateMinimumDaysOutAndWithinMaximumDaysOut = 
				new AuctionDate(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + AuctionCalendar.MINIMUM_DAYS_OUT,
				currentDate.get(Calendar.getInstance().get(Calendar.MONTH)), currentDate.get(Calendar.YEAR));
		acceptableDatePastMinimumDaysOutAndWithinMaximumDaysOut = 
				new AuctionDate(currentDate.get(Calendar.DAY_OF_MONTH) + AuctionCalendar.MINIMUM_DAYS_OUT + 1,
				currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
		acceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut = 
				new AuctionDate(currentDate.get(Calendar.DAY_OF_MONTH) + AuctionCalendar.MINIMUM_DAYS_OUT - 1,
				currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
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
				theCalendar.isDateWithinEligableRange(acceptableDateBeforeMinimumDaysOutAndWithinMaximumDaysOut));
	}

}
