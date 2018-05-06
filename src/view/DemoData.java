package view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

import backend.StorageIO;
import model.Auction;
import model.AuctionCalendar;
import model.AuctionItem;
import model.Bid;
import model.Bidder;
import model.NonProfitContact;

public class DemoData {

	public static void main(String[] args) {
		String fileName = "data/storage.dat"; //auction manager points to data/storage.dat by default
		
		// make items
		HashSet<AuctionItem> auction1Items = new HashSet<>();
		HashSet<AuctionItem> auction2Items = new HashSet<>();
		HashSet<AuctionItem> auction3Items = new HashSet<>();
		HashSet<AuctionItem> auction4Items = new HashSet<>();
		HashSet<AuctionItem> auction5Items = new HashSet<>();
		HashSet<AuctionItem> auction6Items = new HashSet<>();
		
		AuctionItem item1 = new AuctionItem(BigDecimal.valueOf(20.00), "Bowling Ball");
		AuctionItem item2 = new AuctionItem(BigDecimal.valueOf(20.00), "Canoe");
		auction1Items.add(item1);
		auction1Items.add(item2);
		
		AuctionItem item3 = new AuctionItem(BigDecimal.valueOf(20.00), "Pencil");
		AuctionItem item4 = new AuctionItem(BigDecimal.valueOf(20.00), "Coffee cup with Ed sullivan on it");
		auction2Items.add(item3);
		auction2Items.add(item4);
		
		AuctionItem item5 = new AuctionItem(BigDecimal.valueOf(1200.00), "A Subaru Outback");
		AuctionItem item6 = new AuctionItem(BigDecimal.valueOf(67.00), "PF Flyers");
		AuctionItem item7 = new AuctionItem(BigDecimal.valueOf(5.00), "Magnifying Glass");
		AuctionItem item8 = new AuctionItem(BigDecimal.valueOf(0.05), "A Canoli");
		AuctionItem item9 = new AuctionItem(BigDecimal.valueOf(13.00), "Swiffer Duster");
		auction3Items.add(item5);
		auction3Items.add(item6);
		auction3Items.add(item7);
		auction3Items.add(item8);
		auction3Items.add(item9);
		
		AuctionItem item10 = new AuctionItem(BigDecimal.valueOf(20.00), "A Golf ball");
		AuctionItem item11 = new AuctionItem(BigDecimal.valueOf(20.00), "An IHeartNewYork T-shirt");
		auction4Items.add(item10);
		auction4Items.add(item11);
		
		AuctionItem item12 = new AuctionItem(BigDecimal.valueOf(20.00), "A European Carry-all");
		auction5Items.add(item12);
		
		AuctionItem item13 = new AuctionItem(BigDecimal.valueOf(20.00), "A Big Salad");
		AuctionItem item14 = new AuctionItem(BigDecimal.valueOf(20.00), "Gum");
		AuctionItem item15 = new AuctionItem(BigDecimal.valueOf(20.00), "A Bow-staff");
		AuctionItem item16 = new AuctionItem(BigDecimal.valueOf(20.00), "A picture of a picture");
		AuctionItem item17 = new AuctionItem(BigDecimal.valueOf(20.00), "Fish oil");
		AuctionItem item18 = new AuctionItem(BigDecimal.valueOf(20.00), "Thingamajig");
		AuctionItem item19 = new AuctionItem(BigDecimal.valueOf(20.00), "Whosywhatsie");
		AuctionItem item20 = new AuctionItem(BigDecimal.valueOf(20.00), "Time Machine");
		AuctionItem item21 = new AuctionItem(BigDecimal.valueOf(20.00), "A glow stick");
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
		NonProfitContact nonProfit1 = new NonProfitContact("nonprof1", "The Human Fund");
		NonProfitContact nonProfit2 = new NonProfitContact("nonprof2", "The People Fund");
		NonProfitContact nonProfit3 = new NonProfitContact("nonprof3", "The Fund for People");
		NonProfitContact nonProfit4 = new NonProfitContact("nonprof4", "The Fund for Humans");
		NonProfitContact nonProfit5 = new NonProfitContact("nonprof5", "The Whale Fund");
		NonProfitContact nonProfit6 = new NonProfitContact("nonprof6", "The Fund for Whales");
		NonProfitContact nonProfit7 = new NonProfitContact("nonprof7", "The Scuba Fund");
		NonProfitContact nonProfit8 = new NonProfitContact("nonprof8", "The Fund for Scuba");
		NonProfitContact nonProfit9 = new NonProfitContact("nonprof9", "The Fish Fund");
		NonProfitContact nonProfit10 = new NonProfitContact("nonprof10", "The Fund for Fish");
		NonProfitContact nonProfit11 = new NonProfitContact("nonprof11", "The Bird Fund");
		NonProfitContact nonProfit12 = new NonProfitContact("nonprof12", "The Fund for Birds");
		NonProfitContact nonProfit13 = new NonProfitContact("nonprof13", "UNICEF");
		NonProfitContact nonProfit14 = new NonProfitContact("nonprof14", "Human Rights Watch");
		NonProfitContact nonProfit15 = new NonProfitContact("nonprof15", "ACLU");
//		NonProfitContact nonProfit16 = new NonProfitContact("nonprof16", "Doctors Without Borders");
//		NonProfitContact nonProfit17 = new NonProfitContact("nonprof17", "Sierra Club");
//		NonProfitContact nonProfit18 = new NonProfitContact("nonprof18", "NPR");
//		NonProfitContact nonProfit19 = new NonProfitContact("nonprof19", "American Red Cross");
//		NonProfitContact nonProfit20 = new NonProfitContact("nonprof20", "Teach for America");
//		NonProfitContact nonProfit21 = new NonProfitContact("nonprof21", "St. Jude's Children's Research Hospital");
//		NonProfitContact nonProfit22 = new NonProfitContact("nonprof22", "Save the Children");
//		NonProfitContact nonProfit23 = new NonProfitContact("nonprof23", "Make-A-Wish");
//		NonProfitContact nonProfit24 = new NonProfitContact("nonprof24", "Habitat for Humanity");
//		NonProfitContact nonProfit25 = new NonProfitContact("nonprof25", "Mozilla");
//		NonProfitContact nonProfit26 = new NonProfitContact("nonprof26", "EFF");
//		NonProfitContact nonProfit27 = new NonProfitContact("nonprof27", "Green Peace");
//		NonProfitContact nonProfit28 = new NonProfitContact("nonprof28", "World Wildlife Fund");

		Bidder bidder1 = new Bidder("bidder1", "Peter Piper");
		Bidder bidder2 = new Bidder("bidder2", "Jane Doe");
		Bidder bidder3 = new Bidder("bidder3", "John Jacob");
		Bidder bidder4 = new Bidder("bidder4", "Art Vandelay");
		
		
		// Auction 1
		HashSet<Bid> bidSetbidder1auction1 = new HashSet<>();
		bidSetbidder1auction1.add(new Bid(bidder1, item1, BigDecimal.valueOf(13.00)));
		bidSetbidder1auction1.add(new Bid(bidder1, item2, BigDecimal.valueOf(5000.00)));
		
		HashSet<Bid> bidSetbidder2auction1 = new HashSet<>();
		bidSetbidder2auction1.add(new Bid(bidder2, item1, BigDecimal.valueOf(13.00)));
		bidSetbidder2auction1.add(new Bid(bidder2, item2, BigDecimal.valueOf(5000.00)));
		
		// Auction 2
		HashSet<Bid> bidSetbidder1auction2 = new HashSet<>();
		bidSetbidder1auction2.add(new Bid(bidder1, item3, BigDecimal.valueOf(35.00)));
		bidSetbidder1auction2.add(new Bid(bidder1, item4, BigDecimal.valueOf(100.00)));
		
		HashSet<Bid> bidSetbidder2auction2 = new HashSet<>();
		bidSetbidder2auction2.add(new Bid(bidder2, item3, BigDecimal.valueOf(35.00)));
		bidSetbidder2auction2.add(new Bid(bidder2, item4, BigDecimal.valueOf(100.00)));
		
		// Auction 3
		HashSet<Bid> bidSetbidder1auction3 = new HashSet<>();
		bidSetbidder1auction3.add(new Bid(bidder1, item5, BigDecimal.valueOf(1300.00)));
		bidSetbidder1auction3.add(new Bid(bidder1, item6, BigDecimal.valueOf(70.00)));
		bidSetbidder1auction3.add(new Bid(bidder1, item7, BigDecimal.valueOf(6.00)));
		bidSetbidder1auction3.add(new Bid(bidder1, item8, BigDecimal.valueOf(1.00)));
		
		HashSet<Bid> bidSetbidder2auction3 = new HashSet<>();
		bidSetbidder2auction3.add(new Bid(bidder2, item5, BigDecimal.valueOf(1300.00)));
		bidSetbidder2auction3.add(new Bid(bidder2, item6, BigDecimal.valueOf(70.00)));
		bidSetbidder2auction3.add(new Bid(bidder2, item7, BigDecimal.valueOf(6.00)));
		bidSetbidder2auction3.add(new Bid(bidder2, item8, BigDecimal.valueOf(1.00)));
	
		HashSet<Bid> bidSetbidder4auction3 = new HashSet<>();
		bidSetbidder4auction3.add(new Bid(bidder4, item5, BigDecimal.valueOf(1300.00)));
		bidSetbidder4auction3.add(new Bid(bidder4, item6, BigDecimal.valueOf(70.00)));
		bidSetbidder4auction3.add(new Bid(bidder4, item7, BigDecimal.valueOf(6.00)));
		bidSetbidder4auction3.add(new Bid(bidder4, item8, BigDecimal.valueOf(1.00)));
		
		// Auction 4
		HashSet<Bid> bidSetbidder1auction4 = new HashSet<>();
		bidSetbidder1auction4.add(new Bid(bidder1, item10, BigDecimal.valueOf(9.00)));
		bidSetbidder1auction4.add(new Bid(bidder1, item11, BigDecimal.valueOf(1.00)));
		
		HashSet<Bid> bidSetbidder2auction4 = new HashSet<>();
		bidSetbidder2auction4.add(new Bid(bidder2, item10, BigDecimal.valueOf(9.00)));
		bidSetbidder2auction4.add(new Bid(bidder2, item11, BigDecimal.valueOf(1.00)));
		
		// Auction 5
		HashSet<Bid> bidSetbidder1auction5 = new HashSet<>();
		bidSetbidder1auction5.add(new Bid(bidder1, item12, BigDecimal.valueOf(700.00)));
		
		HashSet<Bid> bidSetbidder2auction5 = new HashSet<>();
		bidSetbidder2auction5.add(new Bid(bidder1, item12, BigDecimal.valueOf(700.00)));
		
		// Auction 6
		HashSet<Bid> bidSetbidder1auction6 = new HashSet<>();
		bidSetbidder1auction6.add(new Bid(bidder1, item13, BigDecimal.valueOf(10.00)));
		bidSetbidder1auction6.add(new Bid(bidder1, item14, BigDecimal.valueOf(60.00)));
		
		HashSet<Bid> bidSetbidder2auction6 = new HashSet<>();
		bidSetbidder2auction6.add(new Bid(bidder2, item13, BigDecimal.valueOf(10.00)));
		bidSetbidder2auction6.add(new Bid(bidder2, item14, BigDecimal.valueOf(60.00)));
		bidSetbidder2auction6.add(new Bid(bidder2, item15, BigDecimal.valueOf(4500.00)));
		
		HashSet<Bid> bidSetbidder4auction6 = new HashSet<>();
		bidSetbidder4auction6.add(new Bid(bidder4, item13, BigDecimal.valueOf(10.00)));
		bidSetbidder4auction6.add(new Bid(bidder4, item14, BigDecimal.valueOf(60.00)));
		bidSetbidder4auction6.add(new Bid(bidder4, item15, BigDecimal.valueOf(4500.00)));
		
	
//		HashSet<Bid> bidSetbidder1auction3 = new HashSet<>();
//		bidSetbidder1auction3.add(new Bid(bidder1, item5, BigDecimal.valueOf(1300)));
//		bidSetbidder1auction3.add(new Bid(bidder1, item6, BigDecimal.valueOf(70)));
//		bidSetbidder1auction3.add(new Bid(bidder1, item7, BigDecimal.valueOf(6)));
//		bidSetbidder1auction3.add(new Bid(bidder1, item8, BigDecimal.valueOf(1)));
				
		// Maps For Auction
		HashMap<Bidder, HashSet<Bid>> bidSet1 = new HashMap<>();
		bidSet1.put(bidder1, bidSetbidder1auction1);
		bidSet1.put(bidder2, bidSetbidder2auction1);
		
		HashMap<Bidder, HashSet<Bid>> bidSet2 = new HashMap<>();
		bidSet2.put(bidder1, bidSetbidder1auction2);
		bidSet2.put(bidder2, bidSetbidder2auction2);
		
		HashMap<Bidder, HashSet<Bid>> bidSet3 = new HashMap<>();
		bidSet3.put(bidder1, bidSetbidder1auction3);
		bidSet3.put(bidder2, bidSetbidder2auction3);
		bidSet3.put(bidder3, bidSetbidder4auction3);
		
		HashMap<Bidder, HashSet<Bid>> bidSet4 = new HashMap<>();
		bidSet4.put(bidder1, bidSetbidder1auction5);
		bidSet4.put(bidder2, bidSetbidder2auction5);

		HashMap<Bidder, HashSet<Bid>> bidSet5 = new HashMap<>();
		bidSet5.put(bidder1, bidSetbidder1auction5);
		bidSet5.put(bidder2, bidSetbidder2auction5);
		
		HashMap<Bidder, HashSet<Bid>> bidSet6 = new HashMap<>();
		bidSet6.put(bidder1, bidSetbidder1auction6);
		bidSet6.put(bidder2, bidSetbidder2auction6);
		bidSet6.put(bidder4, bidSetbidder4auction6);
		
//		HashMap<Bidder, HashSet<Bid>> bidSet3 = new HashMap<>();
//		bidSet3.put(bidder1, bidSetbidder1auction3);
		
		// Make Auctions
		Auction auction0 = new Auction(LocalDate.of(2018, 4, 10), 0, 0, "The Human Fund");
		Auction auction1 = new Auction(LocalDate.of(2017, 5, 30), 5, 5, "The People Fund", auction1Items, bidSet1);
		Auction auction2 = new Auction(LocalDate.of(2018, 5, 8), 10, 10, "The Fund For the People", auction2Items, bidSet2);
		Auction auction3 = new Auction(LocalDate.of(2018, 5, 9), 10, 10, "The Human Fund", auction3Items, bidSet3);
		Auction auction4 = new Auction(LocalDate.of(2018, 6, 4), 10, 10, "The Fund for Humans", auction4Items, bidSet4);
		Auction auction5 = new Auction(LocalDate.of(2018, 6, 4), 10, 10, "The Whale Fund", auction5Items, bidSet5);
		Auction auction6 = new Auction(LocalDate.of(2018, 6, 5), 10, 10, "The Fund for Whales", auction6Items, bidSet6);
		Auction auction7 = new Auction(LocalDate.of(2018, 6, 6), 10, 10, "N\\A");
		Auction auction8 = new Auction(LocalDate.of(2018, 6, 7), 10, 10, "N\\A");
		Auction auction9 = new Auction(LocalDate.of(2018, 6, 8), 10, 10, "N\\A");
		
		
//		Auction auction1 = new Auction(theDate, theMaxItemCount, theMaxBidCount, theNonProfit, theItems, theBids)
		
//		Auction auction3 = new Auction(LocalDate.of(2018, 5, 9), 10, 4, 
//				nonProfit1.getDisplayName(), itemSet3, bidSet3);
		
		
		
		//STORE
		StorageIO storage = new StorageIO(fileName);
		AuctionCalendar calendar = storage.getCalendar();
		
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
		
		
		
		
		
		
		//
		
		LocalDate auction1Date = auction1.getDate();
		LocalDate auction2Date = auction2.getDate();
		LocalDate auction3Date = auction3.getDate();
		LocalDate auction4Date = auction4.getDate();
		LocalDate auction5Date = auction5.getDate();
		LocalDate auction6Date = auction6.getDate();
		
		calendar.forceAddAuctionInThePast(auction0, 2018, 3, 20);
		calendar.forceAddAuctionInThePast(auction1, 2017, 5, 15);
		calendar.forceAddAuctionInThePast(auction2, 2018, 4, 20);
		calendar.forceAddAuctionInThePast(auction3, 2018, 4, 20);
		calendar.submitAuction(auction4, auction4Date.getDayOfMonth(), auction4Date.getMonthValue(), auction4Date.getYear());
		calendar.submitAuction(auction5, auction5Date.getDayOfMonth(), auction5Date.getMonthValue(), auction5Date.getYear());
		calendar.submitAuction(auction6, auction6Date.getDayOfMonth(), auction6Date.getMonthValue(), auction6Date.getYear());
				
		//final store
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
//		storage.storeUser(nonProfit16);
//		storage.storeUser(nonProfit17);
//		storage.storeUser(nonProfit18);
//		storage.storeUser(nonProfit19);
//		storage.storeUser(nonProfit20);
//		storage.storeUser(nonProfit21);
//		storage.storeUser(nonProfit22);
//		storage.storeUser(nonProfit23);
//		storage.storeUser(nonProfit24);
//		storage.storeUser(nonProfit25);
//		storage.storeUser(nonProfit26);
//		storage.storeUser(nonProfit27);
//		storage.storeUser(nonProfit28);
		
		storage.setCalendar(calendar);
	}

}
