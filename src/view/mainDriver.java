package view;

import java.util.Scanner;

public class mainDriver {


	public static void main(String[] theArgs) {
		auctionBidSearch test = new auctionBidSearch();
		Scanner input = new Scanner(System.in);
		int option;
		do {
		System.out.println("1.Search for auctions to bid on");
		System.out.println("2.view current bids");
		System.out.println("3.logout");
		option = input.nextInt();
		if (option == 1) {
			test.auctionMenu();
		} 
		if (option ==2) {
		} 
		if(option ==3) {
		}
		} while(option!=3);
		System.out.println("Sesion has end");
	}

}
