package demodata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

import backend.StorageIO;
import model.Auction;
import model.AuctionCalendar;
import model.AuctionItem;
import model.Bid;
import model.Bidder;
import model.Employee;
import model.NonProfitContact;

/**
 * Demo Data which is similar to the DemoData class except modified for Deliverable 3
 * @author Jim Rosales
 * @version May 20, 2018
 */
public class Deliverable3DemoData {
	/**
	 * The main method for running Demo Data.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//auction manager points to data/storage.dat by default
		String fileName = "data/storage.dat"; 
		
		StorageIO storage = new StorageIO(fileName, true);
		AuctionCalendar calendar = storage.getCalendar();
		
		// make items
		TreeSet<AuctionItem> auction1Items = new TreeSet<>();
		TreeSet<AuctionItem> auction2Items = new TreeSet<>();
		TreeSet<AuctionItem> auction3Items = new TreeSet<>();
		TreeSet<AuctionItem> auction4Items = new TreeSet<>();
		TreeSet<AuctionItem> auction5Items = new TreeSet<>();
		TreeSet<AuctionItem> auction6Items = new TreeSet<>();
		
		AuctionItem item1 = new AuctionItem(BigDecimal.valueOf(20.00), 
				"Bowling Ball");
		AuctionItem item2 = new AuctionItem(BigDecimal.valueOf(300.00),
				"Canoe");
		auction1Items.add(item1);
		auction1Items.add(item2);
		
		AuctionItem item3 = new AuctionItem(BigDecimal.valueOf(35.00),
				"Pencil");
		AuctionItem item4 = new AuctionItem(BigDecimal.valueOf(83.00),
				"Coffee cup with Ed sullivan on it");
		auction2Items.add(item3);
		auction2Items.add(item4);
		
		AuctionItem item5 = new AuctionItem(BigDecimal.valueOf(1200.00),
				"A Subaru Outback");
		AuctionItem item6 = new AuctionItem(BigDecimal.valueOf(67.00),
				"PF Flyers");
		AuctionItem item7 = new AuctionItem(BigDecimal.valueOf(5.00),
				"Magnifying Glass");
		AuctionItem item8 = new AuctionItem(BigDecimal.valueOf(0.05),
				"A Canoli");
		AuctionItem item9 = new AuctionItem(BigDecimal.valueOf(13.00),
				"Swiffer Duster");
		auction3Items.add(item5);
		auction3Items.add(item6);
		auction3Items.add(item7);
		auction3Items.add(item8);
		auction3Items.add(item9);
		
		AuctionItem item10 = new AuctionItem(BigDecimal.valueOf(8.00),
				"A Golf ball");
		AuctionItem item11 = new AuctionItem(BigDecimal.valueOf(0.01),
				"An IHeartNewYork T-shirt");
		auction4Items.add(item10);
		auction4Items.add(item11);
		
		AuctionItem item12 = new AuctionItem(BigDecimal.valueOf(500.00),
				"A European Carry-all");
		auction5Items.add(item12);
		
		AuctionItem item13 = new AuctionItem(BigDecimal.valueOf(1.00),
				"A Big Salad");
		AuctionItem item14 = new AuctionItem(BigDecimal.valueOf(18.00),
				"Gum");
		AuctionItem item15 = new AuctionItem(BigDecimal.valueOf(4000.00),
				"A Bow-staff");
		AuctionItem item16 = new AuctionItem(BigDecimal.valueOf(10.00),
				"A picture of a picture");
		AuctionItem item17 = new AuctionItem(BigDecimal.valueOf(75.00),
				"Fish oil");
		AuctionItem item18 = new AuctionItem(BigDecimal.valueOf(10.00),
				"Thingamajig");
		AuctionItem item19 = new AuctionItem(BigDecimal.valueOf(10000.00),
				"Whosywhatsie");
		AuctionItem item20 = new AuctionItem(BigDecimal.valueOf(875000.00),
				"Time Machine");
		AuctionItem item21 = new AuctionItem(BigDecimal.valueOf(12.00),
				"A glow stick");
		auction6Items.add(item13);
		auction6Items.add(item14);
		auction6Items.add(item15);
		auction6Items.add(item16);
		auction6Items.add(item17);
		auction6Items.add(item18);
		auction6Items.add(item19);
		auction6Items.add(item20);
		auction6Items.add(item21);
	
		
		
		// make users
		NonProfitContact nonProfit1 = new NonProfitContact("nonprof1",
				"The Human Fund");
		NonProfitContact nonProfit2 = new NonProfitContact("nonprof2",
				"The People Fund");
		NonProfitContact nonProfit3 = new NonProfitContact("nonprof3",
				"The Fund for People");
		NonProfitContact nonProfit4 = new NonProfitContact("nonprof4",
				"The Fund for Humans");
		NonProfitContact nonProfit5 = new NonProfitContact("nonprof5",
				"The Whale Fund");
		NonProfitContact nonProfit6 = new NonProfitContact("nonprof6",
				"The Fund for Whales");
		NonProfitContact nonProfit7 = new NonProfitContact("nonprof7",
				"The Scuba Fund");
		NonProfitContact nonProfit8 = new NonProfitContact("nonprof8",
				"The Fund for Scuba");
		NonProfitContact nonProfit9 = new NonProfitContact("nonprof9",
				"The Fish Fund");
		NonProfitContact nonProfit10 = new NonProfitContact("nonprof10",
				"The Fund for Fish");
		NonProfitContact nonProfit11 = new NonProfitContact("nonprof11",
				"The Bird Fund");
		NonProfitContact nonProfit12 = new NonProfitContact("nonprof12",
				"The Fund for Birds");
		NonProfitContact nonProfit13 = new NonProfitContact("nonprof13",
				"UNICEF");
		NonProfitContact nonProfit14 = new NonProfitContact("nonprof14",
				"Human Rights Watch");
		NonProfitContact nonProfit15 = new NonProfitContact("nonprof15",
				"ACLU");
		NonProfitContact nonProfit16 = new NonProfitContact("nonprof16",
				"The Old Fund");
		NonProfitContact nonProfit17 = new NonProfitContact("nonprof17",
				"The Newer Old Fund");

		Bidder bidder1 = new Bidder("bidder1", "Peter Piper");
		Bidder bidder2 = new Bidder("bidder2", "Jane Doe");
		Bidder bidder3 = new Bidder("bidder3", "John Jacob");
		Bidder bidder4 = new Bidder("bidder4", "Art Vandelay");
		
		Employee employee1 = new Employee("employee1", "Jane Jacob");
		
		
		// Auction 1
		TreeSet<Bid> bidSetbidder1auction1 = new TreeSet<>();
		bidSetbidder1auction1.add(new Bid(bidder1, item1,
				BigDecimal.valueOf(13.00)));
		bidSetbidder1auction1.add(new Bid(bidder1, item2,
				BigDecimal.valueOf(5000.00)));
		
		TreeSet<Bid> bidSetbidder2auction1 = new TreeSet<>();
		bidSetbidder2auction1.add(new Bid(bidder2, item1,
				BigDecimal.valueOf(13.00)));
		bidSetbidder2auction1.add(new Bid(bidder2, item2,
				BigDecimal.valueOf(5000.00)));
		
		// Auction 2
		TreeSet<Bid> bidSetbidder1auction2 = new TreeSet<>();
		bidSetbidder1auction2.add(new Bid(bidder1, item3,
				BigDecimal.valueOf(35.00)));
		bidSetbidder1auction2.add(new Bid(bidder1, item4, 
				BigDecimal.valueOf(100.00)));
		
		TreeSet<Bid> bidSetbidder2auction2 = new TreeSet<>();
		bidSetbidder2auction2.add(new Bid(bidder2, item3,
				BigDecimal.valueOf(35.00)));
		bidSetbidder2auction2.add(new Bid(bidder2, item4,
				BigDecimal.valueOf(100.00)));
		
		// Auction 3
		TreeSet<Bid> bidSetbidder1auction3 = new TreeSet<>();
		bidSetbidder1auction3.add(new Bid(bidder1, item5,
				BigDecimal.valueOf(1300.00)));
		bidSetbidder1auction3.add(new Bid(bidder1, item6,
				BigDecimal.valueOf(70.00)));
		bidSetbidder1auction3.add(new Bid(bidder1, item7,
				BigDecimal.valueOf(6.00)));
		bidSetbidder1auction3.add(new Bid(bidder1, item8,
				BigDecimal.valueOf(1.00)));
		
		TreeSet<Bid> bidSetbidder2auction3 = new TreeSet<>();
		bidSetbidder2auction3.add(new Bid(bidder2, item5,
				BigDecimal.valueOf(1300.00)));
		bidSetbidder2auction3.add(new Bid(bidder2, item6,
				BigDecimal.valueOf(70.00)));
		bidSetbidder2auction3.add(new Bid(bidder2, item7,
				BigDecimal.valueOf(6.00)));
		bidSetbidder2auction3.add(new Bid(bidder2, item8,
				BigDecimal.valueOf(1.00)));
	
		TreeSet<Bid> bidSetbidder4auction3 = new TreeSet<>();
		bidSetbidder4auction3.add(new Bid(bidder4, item5,
				BigDecimal.valueOf(1300.00)));
		bidSetbidder4auction3.add(new Bid(bidder4, item6,
				BigDecimal.valueOf(70.00)));
		bidSetbidder4auction3.add(new Bid(bidder4, item7,
				BigDecimal.valueOf(6.00)));
		bidSetbidder4auction3.add(new Bid(bidder4, item8,
				BigDecimal.valueOf(1.00)));
		
		// Auction 4
		TreeSet<Bid> bidSetbidder1auction4 = new TreeSet<>();
		bidSetbidder1auction4.add(new Bid(bidder1, item10,
				BigDecimal.valueOf(9.00)));
		bidSetbidder1auction4.add(new Bid(bidder1, item11,
				BigDecimal.valueOf(1.00)));
		
		TreeSet<Bid> bidSetbidder2auction4 = new TreeSet<>();
		bidSetbidder2auction4.add(new Bid(bidder2, item10,
				BigDecimal.valueOf(9.00)));
		bidSetbidder2auction4.add(new Bid(bidder2, item11,
				BigDecimal.valueOf(1.00)));
		
		// Auction 5
		TreeSet<Bid> bidSetbidder1auction5 = new TreeSet<>();
		bidSetbidder1auction5.add(new Bid(bidder1, item12,
				BigDecimal.valueOf(700.00)));
		
		TreeSet<Bid> bidSetbidder2auction5 = new TreeSet<>();
		bidSetbidder2auction5.add(new Bid(bidder2, item12,
				BigDecimal.valueOf(700.00)));
		
		// Auction 6
		TreeSet<Bid> bidSetbidder1auction6 = new TreeSet<>();
		bidSetbidder1auction6.add(new Bid(bidder1, item13,
				BigDecimal.valueOf(10.00)));
		bidSetbidder1auction6.add(new Bid(bidder1, item14,
				BigDecimal.valueOf(60.00)));
		
		TreeSet<Bid> bidSetbidder2auction6 = new TreeSet<>();
		bidSetbidder2auction6.add(new Bid(bidder2, item13,
				BigDecimal.valueOf(10.00)));
		bidSetbidder2auction6.add(new Bid(bidder2, item14,
				BigDecimal.valueOf(60.00)));
		bidSetbidder2auction6.add(new Bid(bidder2, item15,
				BigDecimal.valueOf(4500.00)));
		
		TreeSet<Bid> bidSetbidder4auction6 = new TreeSet<>();
		bidSetbidder4auction6.add(new Bid(bidder4, item13,
				BigDecimal.valueOf(10.00)));
		bidSetbidder4auction6.add(new Bid(bidder4, item14,
				BigDecimal.valueOf(60.00)));
		bidSetbidder4auction6.add(new Bid(bidder4, item15,
				BigDecimal.valueOf(4500.00)));
				
		// Maps For Auction
		TreeMap<Bidder, TreeSet<Bid>> bidSet1 = new TreeMap<>();
		bidSet1.put(bidder1, bidSetbidder1auction1);
		bidSet1.put(bidder2, bidSetbidder2auction1);
		
		TreeMap<Bidder, TreeSet<Bid>> bidSet2 = new TreeMap<>();
		bidSet2.put(bidder1, bidSetbidder1auction2);
		bidSet2.put(bidder2, bidSetbidder2auction2);
		
		TreeMap<Bidder, TreeSet<Bid>> bidSet3 = new TreeMap<>();
		bidSet3.put(bidder1, bidSetbidder1auction3);
		bidSet3.put(bidder2, bidSetbidder2auction3);
		bidSet3.put(bidder4, bidSetbidder4auction3);
		
		TreeMap<Bidder, TreeSet<Bid>> bidSet4 = new TreeMap<>();
		bidSet4.put(bidder1, bidSetbidder1auction4);
		bidSet4.put(bidder2, bidSetbidder2auction4);

		TreeMap<Bidder, TreeSet<Bid>> bidSet5 = new TreeMap<>();
		bidSet5.put(bidder1, bidSetbidder1auction5);
		bidSet5.put(bidder2, bidSetbidder2auction5);
		
		TreeMap<Bidder, TreeSet<Bid>> bidSet6 = new TreeMap<>();
		bidSet6.put(bidder1, bidSetbidder1auction6);
		bidSet6.put(bidder2, bidSetbidder2auction6);
		bidSet6.put(bidder4, bidSetbidder4auction6);
		
		
		// Make Auctions
		Auction auction0 = new Auction(LocalDate.of(2018, 4, 10),
				nonProfit1);
		Auction auction1 = new Auction(LocalDate.of(2017, 5, 30),
				nonProfit2, auction1Items, bidSet1);
		Auction auction2 = new Auction(LocalDate.of(2018, 5, 8),
				nonProfit3, auction2Items, bidSet2);
		Auction auction3 = new Auction(LocalDate.of(2018, 5, 9),
				nonProfit1, auction3Items, bidSet3);
		Auction auction4 = new Auction(LocalDate.of(2018, 6, 4),
				nonProfit4, auction4Items, bidSet4);
		Auction auction5 = new Auction(LocalDate.of(2018, 6, 4),
				nonProfit5, auction5Items, bidSet5);
		Auction auction6 = new Auction(LocalDate.of(2018, 6, 5),
				nonProfit6, auction6Items, bidSet6);
		Auction auction7 = new Auction(LocalDate.of(2018, 6, 6),
				nonProfit13);
		Auction auction8 = new Auction(LocalDate.of(2018, 6, 7),
				nonProfit14);
		Auction auction9 = new Auction(LocalDate.of(2018, 6, 8),
				nonProfit15);
        Auction auction2222 = new Auction(LocalDate.of(2017, 5, 8),
                nonProfit16, new TreeSet<AuctionItem>(), new TreeMap<Bidder, TreeSet<Bid>>());
        Auction auction3333 = new Auction(LocalDate.of(2017, 5, 9),
                nonProfit17, new TreeSet<AuctionItem>(), new TreeMap<Bidder, TreeSet<Bid>>());
		
		
		
		
		
		// Add Auctions to user and calendar
		bidder1.addAuction(auction1);
		bidder1.addAuction(auction2);
		bidder1.addAuction(auction3);
		bidder1.addAuction(auction4);
		bidder1.addAuction(auction5);
		bidder1.addAuction(auction6);
		
		bidder2.addAuction(auction1);
		bidder2.addAuction(auction2);
		bidder2.addAuction(auction3);
		bidder2.addAuction(auction4);
		bidder2.addAuction(auction5);
		bidder2.addAuction(auction6);
		
		bidder4.addAuction(auction3);
		bidder4.addAuction(auction6);
		
		nonProfit1.addAuction(auction0);
		nonProfit1.addAuction(auction3);
		nonProfit2.addAuction(auction1);
		nonProfit3.addAuction(auction2);
		nonProfit4.addAuction(auction4);
		nonProfit5.addAuction(auction5);
		nonProfit6.addAuction(auction6);
		nonProfit13.addAuction(auction7);
        nonProfit14.addAuction(auction8);
        nonProfit15.addAuction(auction9);
        nonProfit16.addAuction(auction2222);
        nonProfit17.addAuction(auction3333);
		
		
		
		
		
		LocalDate auction4Date = auction4.getDate();
		LocalDate auction5Date = auction5.getDate();
		LocalDate auction6Date = auction6.getDate();
        LocalDate auction7Date = auction7.getDate();
        LocalDate auction8Date = auction8.getDate();
        LocalDate auction9Date = auction9.getDate();
		
		calendar.forceAddAuctionInThePast(auction0);
		calendar.forceAddAuctionInThePast(auction1);
		calendar.forceAddAuctionInThePast(auction2);
		calendar.forceAddAuctionInThePast(auction3);
        calendar.forceAddAuctionInThePast(auction2222);
        calendar.forceAddAuctionInThePast(auction3333);
		calendar.submitAuction(auction4, auction4Date.getDayOfMonth(),
				auction4Date.getMonthValue(), auction4Date.getYear());
		calendar.submitAuction(auction5, auction5Date.getDayOfMonth(),
				auction5Date.getMonthValue(), auction5Date.getYear());
		calendar.submitAuction(auction6, auction6Date.getDayOfMonth(),
				auction6Date.getMonthValue(), auction6Date.getYear());
        calendar.submitAuction(auction7, auction7Date.getDayOfMonth(),
        		auction7Date.getMonthValue(), auction7Date.getYear());
        calendar.submitAuction(auction8, auction8Date.getDayOfMonth(),
        		auction8Date.getMonthValue(), auction8Date.getYear());
        calendar.submitAuction(auction9, auction9Date.getDayOfMonth(), 
        		auction9Date.getMonthValue(), auction9Date.getYear());
				
		//final store
        storage.storeUser(employee1);
		storage.storeUser(bidder1);
		storage.storeUser(bidder2);
		storage.storeUser(bidder3);
		storage.storeUser(bidder4);
		storage.storeUser(nonProfit1);
		storage.storeUser(nonProfit2);
		storage.storeUser(nonProfit3);
		storage.storeUser(nonProfit4);
		storage.storeUser(nonProfit5);
		storage.storeUser(nonProfit6);
		storage.storeUser(nonProfit7);
		storage.storeUser(nonProfit8);
		storage.storeUser(nonProfit9);
		storage.storeUser(nonProfit10);
        storage.storeUser(nonProfit11);
		storage.storeUser(nonProfit12);
		storage.storeUser(nonProfit13);
		storage.storeUser(nonProfit14);
		storage.storeUser(nonProfit15);
		storage.storeUser(nonProfit16);
		storage.storeUser(nonProfit17);
		
		storage.setCalendar(calendar);
	}
}
