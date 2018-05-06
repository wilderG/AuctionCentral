package view;

import java.util.Collection;
import java.util.Scanner;

import model.AuctionItem;
import model.AuctionManager;
import model.Bidder;

public class mainDriver {

	static Scanner input = new Scanner(System.in);
	public static void main(String[] theArgs) {
		AuctionManager manager = new AuctionManager();
		auctionBidSearch test = new auctionBidSearch();
		Scanner scanner = new Scanner(System.in);
		Bidder bidder1 = (Bidder) manager.getUser("bidder1");
		ViewItems viewItems = new ViewItems();
		ViewAuction viewAuctions = new ViewAuction();

	
		int option;
		int userResponse = 0;
		do {
			System.out.println("MAIN MENU");
			System.out.println("	1.Search for auctions to bid on");
			System.out.println("	2. View items I've bid on");
			System.out.println("	3.logout");
			System.out.print("Choice: ");
			option = input.nextInt();
		if (option == 1) {
			userResponse = viewItems.showItems(scanner, bidder1.getMyAuctions().iterator().next());
			if (userResponse != 0) {
				AuctionItem[] items = bidder1.getMyAuctions().toArray(new AuctionItem[bidder1.getMyAuctions().size()]); 
				AuctionItem theChosenItem = items[userResponse + 1];
			}
		} 
		if (option == 2) {
			test.auctionMenu();
			
		} 
		if(option ==3) {
		}
		} while(option!=3);
		System.out.println("Sesion has end");
	}

}
