package view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;

import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bidder;
import model.NewItemRequest;
import model.NonProfitContact;
import model.User;

/**
 * The Auction Central program...
 * @author Jared Malone
 * @author Jim Rosales
 * @author Steven Kenneth Golob
 * @author Wilder Emanuel Garcia Y Garcia
 * @author Yohei Sato
 * @version 5/8/2018
 */
public class mainDriver {

	private static Scanner input = new Scanner(System.in);
	
	/** This class should not be constructed. **/
	private mainDriver() {}
	
	public static void main(String[] theArgs) {
		showWelcomeMessage();
		userLogon(new AuctionManager());
		endSession();
	}
	
	/**
	 * Presents the menu for a non-profit contact and controls
	 * their user experience.
	 * @param theUser a NonProfitContact
	 */
	private static void nonProfitContactScreen(final NonProfitContact theUser,
			final AuctionManager theManager) {
		int option;
		
		do {
			System.out.println("\nMAIN MENU:");
			System.out.println("  1. View all auctions/items for " 
								+ theUser.getDisplayName());
			System.out.println("  2. Submit an auction request");
			System.out.println("  3. Modify auction inventory");
			System.out.println("  4. Logout");
			System.out.print("Choice: ");
			option = input.nextInt();
			
			if (option == 1) {
				// get an auction from view screen
				
				// call item view screen
				
			} 
			
			if (option ==2) {
				// pre-check with manager
				theManager.isNewAuctionRequestAllowed(); //handle false
				
				// call NewAuctionRequest screen
				
			} 
			
			if(option ==3) {
				Collection<Auction> existingAuctions = theUser.getMyAuctions();
				Auction futureAuction = null;
				
				if (!existingAuctions.isEmpty()) {
					for (Auction e : existingAuctions) {
						if (e.getDate().isAfter(LocalDate.now())) {
							futureAuction = e;
						}
					}
				}
				
				if (futureAuction == null) {
					System.out.println("You do not have a current auction.");
				} else if (!futureAuction.isAllowingNewItem()) {
					System.out.println("You cannot add more items to the auction.");
				} else {
					// show NewItemRequest screen					
					System.out.println("\nAdding inventory item for auction " +
							"scheduled on " + formatDate(futureAuction.getDate()));
					System.out.println("(You may enter up to " + 
							futureAuction.getAvailableSpace() + " more items)");
						
					//NewItemRequest n
					String itemDescription;
					Double itemMinimumBid;
					
					//get input
					input.nextLine();
					
					System.out.print("Please enter the new item's name: ");
					itemDescription = input.nextLine();
					System.out.print("Please enter the minimum bid: ");
					itemMinimumBid = input.nextDouble();
					
					AuctionItem newItem = 
						theManager.processNewItem(new NewItemRequest
							(itemDescription, BigDecimal.valueOf(itemMinimumBid), 
							 futureAuction));
					
					System.out.println("Thank You! " + newItem.getDescription() 
						+ " has been added to your auction.");
				}
		
			}
			
			} while(option!=4);
			endSession();
	}
	
	
	/**
	 * Presents the menu for a bidder and controls their user experience.
	 * @param theUser a Bidder
	 */
	private static void bidderScreen(final Bidder theUser, 
		final AuctionManager theManager) {
		int option;
		Scanner scanner = new Scanner(System.in);
		ViewItems viewItems = new ViewItems();
		ViewAuction viewAuctions = new ViewAuction();

		
		do {
			System.out.println("\nMAIN MENU:");
			System.out.println("  1. Search for auctions to bid on");
			System.out.println("  2. View items I have bid on");
			System.out.println("  3. Logout");
			System.out.print("Choice: ");
			option = input.nextInt();
			
			if (option == 1) {
				// call auction view passing
				theManager.getAvailableAuctions(theUser);
				int userResponse = 0;
				userResponse = viewAuctions.showAuctions(scanner, theUser);
				if (userResponse != 0) {
					Auction[] auctions = theUser.getMyAuctions().toArray(new Auction[theUser.getMyAuctions().size()]);
					Auction theChosenAuction = auctions[userResponse + 1];
				}
				userResponse = viewItems.showItems(scanner, theUser.getMyAuctions().iterator().next());
				if (userResponse != 0) {
					AuctionItem[] items = theUser.getMyAuctions().toArray(new AuctionItem[theUser.getMyAuctions().size()]); 
					AuctionItem theChosenItem = items[userResponse + 1];
				}
				
			} 
			
			if (option ==2) {
				
				theManager.getAvailableAuctions(theUser);
				int userResponse = 0;
				userResponse = viewItems.showItems(scanner, theUser.getMyAuctions().iterator().next());
				if (userResponse != 0) {
					AuctionItem[] items = theUser.getMyAuctions().toArray(new AuctionItem[theUser.getMyAuctions().size()]); 
					AuctionItem theChosenItem = items[userResponse + 1];
				}
				userResponse = viewItems.showItems(scanner, theUser.getMyAuctions().iterator().next());
				if (userResponse != 0) {
					AuctionItem[] items = theUser.getMyAuctions().toArray(new AuctionItem[theUser.getMyAuctions().size()]); 
					AuctionItem theChosenItem = items[userResponse + 1];
				}
				
				//submenu?
				// story 1 all auctions with bids
				
				
				// story 2 all items I have bid on in an auction
				// select an auction and display items with bids?
				// someAuction.getAllItemsWithBidder(theUser)
				
				
				// or display all items from all auctions 
				// merge collection of items and display all with item viewer?
				
			} 
		} while(option!=3);
		endSession();
 	}

	
	/**
	 * Prompts the user to login and determines if user is a Bidder or
	 * a NonProfitContact. 
	 * @param theManager instance of AuctionManager
	 */
	private static void userLogon(final AuctionManager theManager) {
		User user = null;
		System.out.print("\rPlease enter username to login: ");
		
		try {
			user = theManager.getUser(input.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid username. Please try again.");
			userLogon(theManager);
		}
		
		System.out.println("Welcome " + user.getDisplayName() + ".");
				
		if (user instanceof model.Bidder) {
			bidderScreen((Bidder) user, theManager);
		} else if (user instanceof model.NonProfitContact) {
			nonProfitContactScreen((NonProfitContact) user, theManager);
		} else {
			endSession();
		}
	}
	
	
	private static void showWelcomeMessage() {
		System.out.println("Welcome to Auction Central!");
		System.out.println("--------------------------------");
		System.out.println("Today is " + formatDate(LocalDate.now()));
	}
	
	
	private static void endSession() {
		System.out.println("Sesion has ended.");
		System.exit(0);
	}
	
	
	private static String formatDate(final LocalDate theDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, u");
		return theDate.format(formatter);
	}
	
}
