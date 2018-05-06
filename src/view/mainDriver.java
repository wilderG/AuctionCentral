package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.AuctionManager;
import model.Bidder;
import model.NonProfitContact;
import model.User;

public class mainDriver {

	private static Scanner input = new Scanner(System.in);
	
	private mainDriver() {}
	
	public static void main(String[] theArgs) {
		AuctionManager manager = new AuctionManager();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, u");
		
		User user;
		
		System.out.println("Welcome to Auction Central!");
		System.out.println("--------------------------------");
		System.out.println("Today is " + LocalDate.now().format(formatter));
		
		System.out.print("\rPlease enter username to login: ");
		user = manager.getUser(input.nextLine());
		
		if (user instanceof model.Bidder) {
			bidderScreen((Bidder) user);
		} else if (user instanceof model.NonProfitContact) {
			nonProfitContactScreen((NonProfitContact) user);
		} else {
			System.exit(1);
		}
		
	}
	
	private static void nonProfitContactScreen(final NonProfitContact theUser) {
		int option;
		System.out.println("Welcome " + theUser.getDisplayName() + ".");
		
		
		do {
			
			System.out.println("MAIN MENU:");
			System.out.println("  1. View all auctions/items for " + theUser.getDisplayName());
			System.out.println("  2. Submit an auction request");
			System.out.println("  3. Modify auction inventory");
			System.out.println("  4. Logout");
			System.out.print("Choice: ");
			option = input.nextInt();
			if (option == 1) {
				//test2.MenuOption2();
			} 
			if (option ==2) {
				//test.auctionMenu();
				
			} 
			if(option ==3) {
			}
			} while(option!=4);
			System.out.println("Sesion has ended.");
			
	}
	
	
	private static void bidderScreen(final Bidder theUser) {
		int option;
		System.out.println("\rWelcome " + theUser.getDisplayName() + ".\r");
				
		do {
			
			
			System.out.println("MAIN MENU:");
			System.out.println("  1.Search for auctions to bid on");
			System.out.println("  2.view current bids");
			System.out.println("  3.logout");
			System.out.print("Choice: ");
			option = input.nextInt();
			if (option == 1) {
				//test2.MenuOption2();
			} 
			if (option ==2) {
				//test.auctionMenu();
				
			} 
			if(option ==3) {
			}
			} while(option!=3);
			System.out.println("Sesion has ended.");
 	}

}
