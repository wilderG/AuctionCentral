package view;

import java.util.Scanner;

import model.Auction;

import model.Bidder;

public class auctionBidSearch {
	Bidder bidder = new Bidder("Alex", "Alex");

	Auction test = new Auction(null, 0, 0, "Some non profit");

	public void auctionMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("Options:");
		System.out.println("1.View all auctions in which I have placed bids");
		System.out.println("2.View all items that I have placed bids on");
		System.out.print("Choice: ");
		Integer name = input.nextInt();
		if (name == 1) {
			System.out.println(test.getAllItemsWithBidder(bidder));// we need to pass proper variables.
			
		
			System.out.println("View details for an auction (if so, enter auction number. Otherwise, "
					+ "enter anything else to return to the Main Menu)?\n ");
		}
		else if(name == 2) {
			System.out.println(test.getAllItems());
		}
	}
}
