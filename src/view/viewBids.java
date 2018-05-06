package view;

import java.util.Scanner;

import model.AuctionManager;
import model.Bidder;

public class viewBids {
	Bidder bidder = new Bidder("Alex", "Alex");
	AuctionManager test = new AuctionManager();
	Scanner input = new Scanner(System.in);
	int option = 0;
	public void MenuOption2() {
		int size = test.getAvailableAuctions(bidder).size();
		System.out.println("Auctions available to bid on:");
		System.out.println("");
		if(test.getAvailableAuctions(bidder).isEmpty()) {
			System.out.println("There is no available acutions");
		} else {
			for(int i = 0; i<= size; i++) {
				System.out.println(i + ".With <" + ">" + ""); //We need to fix formating
			}
		System.out.println("Select an auction number for which you would like to view items (enter anything else to return to main menu");
		System.out.print("Choice: ");
		option = input.nextInt();
		System.out.println(option + test.[option].getName + "<description>");
		System.out.println("Minimum bid:" + test.);
		
		
		}
	}
	
}
