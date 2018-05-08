package view;

import java.util.Scanner;

/**
 * Displaying the option menu.
 * 
 * @author Jared Malone
 * @author Jim Rosales
 * @author Steven Kenneth Golob
 * @author Wilder Emanuel Garcia Y Garcia
 * @author Yohei Sato
 * @version 5/8/2018
 */
public class ViewBids {
    
	/**
	 * showing choices for users.
	 * 
	 * @param theScanner
	 * @return the number which users choose.
	 */
	public int viewOptions(Scanner theScanner) {
	    final int maxChoices = 2;
		System.out.println("Options:");
        System.out.println("  0. To go back to the previous screen");
		System.out.println("  1. View all auctions in which"
				+ " I have placed bids");
		System.out.println("  2. View all items that I have placed bids on");
		return MainDriver.getNextInt(maxChoices);
	}
}