package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class AuctionCalendar implements Serializable, Calendar {

	/**
	 * Generate Serial Version UID.
	 */
	private static final long serialVersionUID = -7640027391971367459L;

	@Override
	public Collection<Auction> getAuctionsInRange(Date theStart, Date theEnd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Auction> getAuctionsOnDate(Date theDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAuction(Date theDate, Auction theAuction) {
		// TODO Auto-generated method stub
		
	}
	
}
