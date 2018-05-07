package view;

import java.util.Scanner;

public class ViewBids {

	public int viewOptions(Scanner theScanner) {

		System.out.println("Options:");
		System.out.println("  1. View all auctions in which I have placed bids");
		System.out.println("  2. View all items that I have placed bids on");
		System.out.println("To go back to the previous screen enter 0");
		System.out.print("Choice: ");
		return theScanner.nextInt();
	}

}
