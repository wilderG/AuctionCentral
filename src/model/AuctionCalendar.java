package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class AuctionCalendar implements Serializable, Calendar {

	/** Generate Serial Version UID. */
	private static final long serialVersionUID = -7640027391971367459L;

	/** The maximum number of future auctions that may be scheduled. **/
	private static final int MAXIMUM_FUTURE_AUCTIONS = 25;
	
	/** The maximum number of auctions allowed on any day. **/
	private static final int MAXIMUM_AUCTIONS_PER_DAY = 2;
	
	/** The maximum number of days in the future that a new auction may be scheduled. **/
	private static final int MAXIMUM_DAYS_OUT = 60;
	
	/** The minimum number of days in the future that a new auction may be scheduled. **/
	private static final int MINIMUM_DAYS_OUT = 14;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isAllowingNewAuctions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Auction> getFutureAuctions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addAuction(Date theDate, Auction theAuction, NonProfitContact theUser) {
		// TODO Auto-generated method stub
		
	}
	
}
