/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionItem;
import model.Bid;
import model.Bidder;
import model.NonProfitContact;

/**
 * @author Jim Rosales
 * JUnit Tests
 */
public class BidderTest {
	private Bidder bidderWithMaxBids;
	private Bidder bidderWithBelowMaxBids;

	/** NonProfit test fixture. **/
	private final NonProfitContact auctionOwner = new NonProfitContact("test", "Test User");
	
	/**
	 * Constructs the necessary objects for the executed tests.
	 */
	@Before
    public void setUp() {
		bidderWithMaxBids = new Bidder("bidder99", "John Smith");
		bidderWithBelowMaxBids = new Bidder("bidder98", "John Smith");

        AuctionItem item1 = new AuctionItem(BigDecimal.valueOf(20.00), 
                "Bowling Ball");
        AuctionItem item2 = new AuctionItem(BigDecimal.valueOf(20.00),
                "Canoe");
        AuctionItem item3 = new AuctionItem(BigDecimal.valueOf(20.00),
                "Pencil");
        AuctionItem item4 = new AuctionItem(BigDecimal.valueOf(20.00),
                "Coffee cup with Ed sullivan on it");
        AuctionItem item5 = new AuctionItem(BigDecimal.valueOf(20.00),
                "A Subaru Outback");
        AuctionItem item6 = new AuctionItem(BigDecimal.valueOf(20.00),
                "PF Flyers");
        AuctionItem item7 = new AuctionItem(BigDecimal.valueOf(20.00),
                "Magnifying Glass");
        AuctionItem item8 = new AuctionItem(BigDecimal.valueOf(20.00),
                "A Canoli");
        AuctionItem item9 = new AuctionItem(BigDecimal.valueOf(20.00),
                "Swiffer Duster");
        AuctionItem item10 = new AuctionItem(BigDecimal.valueOf(20.00),
                "A Golf ball");
        AuctionItem item11 = new AuctionItem(BigDecimal.valueOf(20.00),
                "Regular Duster");
        AuctionItem item12 = new AuctionItem(BigDecimal.valueOf(20.00),
                "A Tennis ball");
        
        
        
        TreeSet<AuctionItem> auction1Items = new TreeSet<>();
        auction1Items.add(item1);
        auction1Items.add(item2);
        auction1Items.add(item3);
        auction1Items.add(item4);
        auction1Items.add(item5);
        auction1Items.add(item6);
        auction1Items.add(item7);
        auction1Items.add(item8);
        auction1Items.add(item9);
        auction1Items.add(item10);
        auction1Items.add(item11);
        auction1Items.add(item12);
        
        
        TreeSet<Bid> bidSetbidder1auction1 = new TreeSet<>();
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item1,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item2,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item3,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item4,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item5,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item6,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item7,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item8,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item9,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item10,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item11,
                BigDecimal.valueOf(21.00)));
        bidSetbidder1auction1.add(new Bid(bidderWithMaxBids, item12,
                BigDecimal.valueOf(21.00)));

        
        
        TreeSet<Bid> bidSetbidder2auction1 = new TreeSet<>();
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item1,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item2,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item3,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item4,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item5,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item6,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item7,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item8,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item9,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item10,
                BigDecimal.valueOf(21.00)));
        bidSetbidder2auction1.add(new Bid(bidderWithBelowMaxBids, item11,
                BigDecimal.valueOf(21.00)));
        
        
        
        TreeMap<Bidder, TreeSet<Bid>> bidSet1 = new TreeMap<>();
        bidSet1.put(bidderWithMaxBids, bidSetbidder1auction1);
        bidSet1.put(bidderWithBelowMaxBids, bidSetbidder2auction1);
        
        Auction auction1 = new Auction(LocalDate.now().plusDays(1),
                auctionOwner, auction1Items, bidSet1);
        
        bidderWithBelowMaxBids.addAuction(auction1);
        bidderWithMaxBids.addAuction(auction1);
       
	}

	/**
	 * Tests whether a new bid is able to be placed
	 *  when a bidder already has the max number of bids allowed.
	 * The expected outcome is that
	 *  the bidder is not able to place a new bid.
	 */
	@Test
	public void isNewBidAllowable_bidderWithMaxBids_False() {
		assertFalse("A bidder with a max number of bids"
				+ " as expected is not able to place a bid", 
				bidderWithMaxBids.isNewBidAllowed());
	}
	
	/**
	 * Tests whether a new bid is able to be placed 
	 * when a bidder already one less than the max number of bids
	 * allowed.
	 * The expected outcome is that the bidder is able to place a new bid.
	 */
	@Test
	public void isNewBidAllowable_bidderWithOneBidBelowTheMax_True() {
		assertTrue("A bidder with one less than "
				+ "the number of max bids is able to place a new bid as expected", 
				bidderWithBelowMaxBids.isNewBidAllowed());
	}

}
