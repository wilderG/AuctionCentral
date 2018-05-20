/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Auction;
import model.AuctionCalendar;
import model.AuctionDate;
import model.NonProfitContact;

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

	private final NonProfitContact auctionOwner = new NonProfitContact("test", "Test User");

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
        Auction testAuction = new Auction(null, auctionOwner);
        LocalDate acceptableDate = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        theCalendar.submitAuction(testAuction, acceptableDate.getDayOfMonth(),
                acceptableDate.getMonthValue(), acceptableDate.getYear());
        assertEquals("Should have 1 auction", 1, theCalendar.getFutureNumberOfAuction());
        assertSame("Should be same auction scheduled", testAuction, theCalendar.getFutureAuctions().get(0));
    }

    /**
     * Tests the submitAuction method. Specifically, checks that an auction is scheduled if 
     * the requested date has less than the maximum number of auctions on it.
     */
    @Test
    public void submitAuction_lessThanMaxAuctionsOnRequestedDate_newAuctionAdded() {
        LocalDate acceptableDate = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        theCalendar.submitAuction(new Auction(null, auctionOwner), acceptableDate.getDayOfMonth(),
                acceptableDate.getMonthValue(), acceptableDate.getYear());
        Auction testAuction = new Auction(null, auctionOwner);
        theCalendar.submitAuction(testAuction, acceptableDate.getDayOfMonth(),
                acceptableDate.getMonthValue(), acceptableDate.getYear());
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
        LocalDate acceptableDate = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        for (int i = 0; i <= AuctionDate.MAX_AUCTIONS; i++) {
            theCalendar.submitAuction(new Auction(null, auctionOwner), acceptableDate.getDayOfMonth(),
                    acceptableDate.getMonthValue(), acceptableDate.getYear());
        }
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
	
	@Test (expected = IllegalArgumentException.class)
	public void setMaxUpcomingAuction_zeroGiven_exceptionThrown() {
	    theCalendar.setMaximumUpcomingAuctions(0);
	}
	
    @Test (expected = IllegalArgumentException.class)
    public void setMaxUpcomingAuction_negativeGiven_exceptionThrown() {
        theCalendar.setMaximumUpcomingAuctions(-1);
    }

    @Test
    public void 
    setMaxUpcomingAuction_numberGreaterThanExistingFutureAuctions_passes() {
        for (int i = 0; i < theCalendar.getMaximumUpcomingAuctions(); i++) {
            LocalDate testDate = LocalDate.now().plusDays(i + AuctionCalendar.MINIMUM_DAYS_OUT);
            theCalendar.submitAuction(new Auction(null, auctionOwner), 
                    testDate.getDayOfMonth(), testDate.getMonthValue(), 
                    testDate.getYear());
        }
        assertFalse(theCalendar.isAllowingNewAuction());
        assertEquals(theCalendar.getMaximumUpcomingAuctions(), AuctionCalendar.DEFAULT_MAXIMUM_FUTURE_AUCTIONS);
        
        theCalendar.setMaximumUpcomingAuctions(theCalendar.getMaximumUpcomingAuctions() + 1);
        assertTrue(theCalendar.isAllowingNewAuction());
        assertEquals(theCalendar.getMaximumUpcomingAuctions(), AuctionCalendar.DEFAULT_MAXIMUM_FUTURE_AUCTIONS + 1);
    }
    
    @Test
    public void setMaxUpcomingAuction_numberGivenLessThanCurrentCapacity_passes() {
        for (int i = 0; i < theCalendar.getMaximumUpcomingAuctions(); i++) {
            LocalDate testDate = LocalDate.now().plusDays(i + AuctionCalendar.MINIMUM_DAYS_OUT);
            theCalendar.submitAuction(new Auction(null, auctionOwner), 
                    testDate.getDayOfMonth(), testDate.getMonthValue(), 
                    testDate.getYear());
        }
        assertFalse(theCalendar.isAllowingNewAuction());
        theCalendar.setMaximumUpcomingAuctions(theCalendar.getMaximumUpcomingAuctions() - 10);
        assertFalse(theCalendar.isAllowingNewAuction());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void getAuctionsWithinRange_secondDateBeforeFirst_exceptionThrown() {
        theCalendar.getAuctionsWithinRange(LocalDate.now(), LocalDate.now().minusDays(1));
    }

    @Test
    public void getAuctionsWithinRange_secondDateEqualsFirst_getsAuctions() {
        Auction testAuction1 = new Auction(null, auctionOwner);
        Auction testAuction2 = new Auction(null, auctionOwner);
        Auction testAuction3 = new Auction(null, auctionOwner);
        LocalDate testDate1 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        LocalDate testDate2 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT + 1);
        LocalDate testDate3 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT + 2);
        theCalendar.submitAuction(testAuction1, testDate1.getDayOfMonth(), testDate1.getMonthValue(), testDate1.getYear());
        theCalendar.submitAuction(testAuction2, testDate2.getDayOfMonth(), testDate2.getMonthValue(), testDate2.getYear());
        theCalendar.submitAuction(testAuction3, testDate3.getDayOfMonth(), testDate3.getMonthValue(), testDate3.getYear());
        
        Collection<Auction> auctionsInRange = theCalendar.getAuctionsWithinRange(testDate2, testDate2);
        assertEquals(1, auctionsInRange.size());
        assertEquals(testAuction2, auctionsInRange.iterator().next());
    }
    
    @Test
    public void getAuctionsWithinRange_secondDateAfterFirst_getsAuctions() {
        Auction testAuction1 = new Auction(null, auctionOwner);
        Auction testAuction2 = new Auction(null, auctionOwner);
        Auction testAuction3 = new Auction(null, auctionOwner);
        Auction testAuction4 = new Auction(null, auctionOwner);
        Auction testAuction5 = new Auction(null, auctionOwner);
        LocalDate testDate1 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        LocalDate testDate2 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT + 1);
        LocalDate testDate3 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT + 2);
        LocalDate testDate4 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT + 3);
        theCalendar.submitAuction(testAuction1, testDate1.getDayOfMonth(), testDate1.getMonthValue(), testDate1.getYear());
        theCalendar.submitAuction(testAuction2, testDate2.getDayOfMonth(), testDate2.getMonthValue(), testDate2.getYear());
        theCalendar.submitAuction(testAuction3, testDate3.getDayOfMonth(), testDate3.getMonthValue(), testDate3.getYear());
        theCalendar.submitAuction(testAuction4, testDate3.getDayOfMonth(), testDate3.getMonthValue(), testDate3.getYear());
        theCalendar.submitAuction(testAuction5, testDate4.getDayOfMonth(), testDate4.getMonthValue(), testDate4.getYear());
        Collection<Auction> expectedAuctionsInRange = new LinkedList<>();
        expectedAuctionsInRange.add(testAuction2);
        expectedAuctionsInRange.add(testAuction3);
        expectedAuctionsInRange.add(testAuction4);
        
        Collection<Auction> auctionsInRange = theCalendar.getAuctionsWithinRange(testDate2, testDate3);
        assertEquals(expectedAuctionsInRange.size(), auctionsInRange.size());
        assertTrue(auctionsInRange.containsAll(expectedAuctionsInRange));
    }

    @Test
    public void getAuctionsWithinRange_rangeContainsPastActions_getsAuctions() {
        LocalDate testDate1 = LocalDate.now().minusDays(20);
        LocalDate testDate2 = LocalDate.now().minusDays(19);
        LocalDate testDate3 = LocalDate.now().minusDays(18);
        LocalDate testDate4 = LocalDate.now().minusDays(17);
        Auction testAuction1 = new Auction(testDate1, auctionOwner);
        Auction testAuction2 = new Auction(testDate2, auctionOwner);
        Auction testAuction3 = new Auction(testDate2, auctionOwner);
        Auction testAuction4 = new Auction(testDate3, auctionOwner);
        Auction testAuction5 = new Auction(testDate4, auctionOwner);
        theCalendar.forceAddAuctionInThePast(testAuction1);
        theCalendar.forceAddAuctionInThePast(testAuction2);
        theCalendar.forceAddAuctionInThePast(testAuction3);
        theCalendar.forceAddAuctionInThePast(testAuction4);
        theCalendar.forceAddAuctionInThePast(testAuction5);
        Collection<Auction> expectedAuctionsInRange = new LinkedList<>();
        expectedAuctionsInRange.add(testAuction2);
        expectedAuctionsInRange.add(testAuction3);
        expectedAuctionsInRange.add(testAuction4);
        
        Collection<Auction> auctionsInRange = theCalendar.getAuctionsWithinRange(testDate2, testDate3);
        assertEquals(expectedAuctionsInRange.size(), auctionsInRange.size());
        assertTrue(auctionsInRange.containsAll(expectedAuctionsInRange));
    }
    
    @Test
    public void 
    getAllAuctionsSorted_hasMultipleAuctionsWithDifferentDatesAddedInUnsortedOrder_returnsAllSorted() {
        for (int i = 1; i <= 5; i++) {
            theCalendar.forceAddAuctionInThePast(new Auction(LocalDate.now().minusDays(i), auctionOwner));
            LocalDate futureDate = LocalDate.now().plusDays(20 - i);
            theCalendar.submitAuction(new Auction(futureDate, auctionOwner), 
                    futureDate.getDayOfMonth(), futureDate.getMonthValue(), futureDate.getYear());
        }
        theCalendar.forceAddAuctionInThePast(new Auction(LocalDate.now(), auctionOwner));
        
        Collection<Auction> allAuctionsSorted = theCalendar.geAllAuctionsSorted();
        assertEquals(11, allAuctionsSorted.size());
        
        Iterator<Auction> itr = allAuctionsSorted.iterator();
        Auction prev = itr.next();
        Auction next;
        while (!itr.hasNext()) {
            next = itr.next();
            assertTrue(next.getDate().isAfter(prev.getDate()));
            prev = next;
        }
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void deleteAuction_auctionWasNotInCalendar_exceptionThrown() {
        theCalendar.deleteAuction(new Auction(LocalDate.now().plusDays(15), auctionOwner));
    }
    
    @Test
    public void deleteAuction_auctionAndOthersInCalendar_auctionDeleted() {
        LocalDate testDate1 = LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        LocalDate testDate2 = LocalDate.now().minusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        Auction testAuction1 = new Auction(testDate1, auctionOwner);
        Auction testAuction2 = new Auction(testDate1, new NonProfitContact("", ""));
        Auction testAuction3 = new Auction(testDate2, auctionOwner);
        Auction testAuction4 = new Auction(testDate2, new NonProfitContact("", ""));
        theCalendar.submitAuction(testAuction1, 
                testDate1.getDayOfMonth(), testDate1.getMonthValue(), testDate1.getYear());
        theCalendar.submitAuction(testAuction2, 
                testDate1.getDayOfMonth(), testDate1.getMonthValue(), testDate1.getYear());
        theCalendar.forceAddAuctionInThePast(testAuction3);
        theCalendar.forceAddAuctionInThePast(testAuction4);
        
        assertTrue("1", theCalendar.geAllAuctionsSorted().contains(testAuction1));
        assertTrue("2", theCalendar.geAllAuctionsSorted().contains(testAuction2));
        assertTrue("3", theCalendar.geAllAuctionsSorted().contains(testAuction3));
        assertTrue("4", theCalendar.geAllAuctionsSorted().contains(testAuction4));
        
        theCalendar.deleteAuction(testAuction1);
        assertFalse("5", theCalendar.geAllAuctionsSorted().contains(testAuction1));
        theCalendar.deleteAuction(testAuction3);
        assertFalse("6", theCalendar.geAllAuctionsSorted().contains(testAuction3));
        
        // verify that other auctions on the same day were not deleted
        assertTrue("7", theCalendar.geAllAuctionsSorted().contains(testAuction2));
        assertTrue("8", theCalendar.geAllAuctionsSorted().contains(testAuction4));
    }
}
