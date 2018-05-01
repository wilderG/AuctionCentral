package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuctionCalendarTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void addAuction_LessThanSixtyDaysFromCurrentDate_ShouldSucceed() {
		fail("Not yet implemented");
	}

	
	@Test
	public void addAuction_ExactlySixtyDaysFromCurrentDate_ShouldSucceed() {
		fail("Not yet implemented");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAuction_MoreThanSixtyDaysFromCurrentDate_ShouldThrowException() {
		fail("Not yet implemented");
	}

}
