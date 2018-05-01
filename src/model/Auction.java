package model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Auction implements Serializable {

	/** Class Serial ID -- Changing this will corrupt the data storage! */
	private static final long serialVersionUID = -5386584822819727993L;
	
	public static final int MAX_ITEM_COUNT = 10;
	//private final Date myDate;
	
	//private List<AuctionItem> myInventory;
	
	/** Constructor takes a test string. **/
	public Auction() {
	    //myDate = theDate;
	    //myInventory = new LinkedList<>();
	}
	
	@Override
	public String toString() {
	    return "";
	}
	
}
