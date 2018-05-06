package view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import backend.StorageIO;
import model.Auction;
import model.AuctionCalendar;
import model.AuctionItem;
import model.Bid;
import model.Bidder;
import model.NonProfitContact;

public class DummyData {

	public static void main(String[] args) {
		String fileName = "data/storage.dat"; //auction manager points to data/storage.dat by default
		
		// make items
		AuctionItem item5 = new AuctionItem(BigDecimal.valueOf(1200.00), "A Subaru Outback");
		AuctionItem item6 = new AuctionItem(BigDecimal.valueOf(67.00), "PF Flyers");
		AuctionItem item7 = new AuctionItem(BigDecimal.valueOf(5.00), "Magnifying Glass");
		AuctionItem item8 = new AuctionItem(BigDecimal.valueOf(0.05), "A Canoli");
		AuctionItem item9 = new AuctionItem(BigDecimal.valueOf(13.00), "Swiffer Duster");
		
		
		// make users
		NonProfitContact nonProfit1 = new NonProfitContact("nonprof1", "The Human Fund");
		Bidder bidder1 = new Bidder("bidder1", "Peter Piper");
		
		
		// make auctions
		
		// auction 3
		HashSet<AuctionItem> itemSet3 = new HashSet<>(); 
		itemSet3.add(item5);
		itemSet3.add(item6);
		itemSet3.add(item7);
		itemSet3.add(item8);
		itemSet3.add(item9);
		
		HashSet<Bid> bidSetbidder1auction3 = new HashSet<>();
		bidSetbidder1auction3.add(new Bid(bidder1, item5, BigDecimal.valueOf(1300)));
		bidSetbidder1auction3.add(new Bid(bidder1, item6, BigDecimal.valueOf(70)));
		bidSetbidder1auction3.add(new Bid(bidder1, item7, BigDecimal.valueOf(6)));
		bidSetbidder1auction3.add(new Bid(bidder1, item8, BigDecimal.valueOf(1)));
				
		HashMap<Bidder, HashSet<Bid>> bidSet3 = new HashMap<>();
		bidSet3.put(bidder1, bidSetbidder1auction3);
		
		Auction auction3 = new Auction(LocalDate.of(2018, 6, 9), 10, 4, 
				nonProfit1.getDisplayName(), itemSet3, bidSet3);
		
		
		
		//STORE
		StorageIO storage = new StorageIO(fileName);
		AuctionCalendar calendar = storage.getCalendar();
		
		//add auctions to user and calendar
		bidder1.addAuction(auction3);
		LocalDate theDate = auction3.getDate();
		calendar.submitAuction(auction3, theDate.getDayOfMonth(), theDate.getMonthValue(), theDate.getYear());
		
		//final store
		storage.storeUser(bidder1);
		storage.setCalendar(calendar);
	}

}
