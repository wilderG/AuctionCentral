package view;

import java.util.Scanner;

public class ViewBids {

	public int viewOptions(Scanner theScanner) {
	    int choice;
		System.out.println("Options:");
        System.out.println("  0. To go back to the previous screen");
		System.out.println("  1. View all auctions in which I have placed bids");
		System.out.println("  2. View all items that I have placed bids on");
		do {
		    choice = mainDriver.getNextInt();
		} while (choice > 2);
		return choice;
	}
}