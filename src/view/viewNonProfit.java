package view;

import java.util.Scanner;

public class viewNonProfit {
	static Scanner input = new Scanner(System.in);
	public void disPlay() {
		auctionBidSearch test = new auctionBidSearch();
		ViewItems test2 = new ViewItems();
		int option;
		System.out.println("MAIN MENU");
		System.out.println("	1.View all auctions/items for Salvation Army");
		System.out.println("	2.Submit an auction request");
		System.out.println("	3.Modify auction inventory");
		System.out.println("	4.logout");
		System.out.print("Choice: ");
		option = input.nextInt();
		do {
		if (option == 1) {
		
		} 
		if (option ==2) {
			
		} 
		if(option ==3) {
		}
		if(option ==4) {
		}
		} while(option!=4);
		System.out.println("Sesion has end");
	}
	}

