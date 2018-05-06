package view;

import java.util.Scanner;

import model.AuctionManager;
import model.Bidder;

public class mainDriver {


	public static void main(String[] theArgs) {
		auctionBidSearch test = new auctionBidSearch();
		viewBids test2 = new viewBids();
		Scanner input = new Scanner(System.in);
		int option;
		do {
		System.out.println("1.Search for auctions to bid on");
		System.out.println("2.view current bids");
		System.out.println("3.logout");
		System.out.print("Choice: ");
		option = input.nextInt();
		if (option == 1) {
			test2.MenuOption2();
		} 
		if (option ==2) {
			test.auctionMenu();
			
		} 
		if(option ==3) {
		}
		} while(option!=3);
		System.out.println("Sesion has end");
	}

}
