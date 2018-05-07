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
	}
	
	/**
	 * Presents the menu for a non-profit contact and controls
	 * their user experience.
	 * @param theUser a NonProfitContact
	 */
	private static void nonProfitContactScreen(final NonProfitContact theUser,
			final AuctionManager theManager, Scanner theScanner) {
		int option;
		
		do {
			System.out.println("\nMAIN MENU:");
			System.out.println("  1. View all auctions/items for " 
								+ theUser.getDisplayName());
			System.out.println("  2. Submit an auction request");
			System.out.println("  3. Modify auction inventory");
			System.out.println("  4. Logout");
			System.out.print("Choice: ");
			option = getNextInt(4);
			
			if (option == 1) {
				ViewAuction viewAuctions = new ViewAuction();
				int userResponse = viewAuctions .showAllAuctions(theScanner, theUser, theManager);
				if (userResponse != 0) {
					Auction auction = getAuctionFromUserResponse(theUser, userResponse);
					ViewItems viewItems = new ViewItems();
					viewItems.showItemsForNonProfAuction(theScanner, auction);
				}
				// get an auction from view screen
				
				// call item view screen
				
			} 
			
			if (option ==2) {
//				// pre-check with manager
//				theManager.isNewAuctionRequestAllowed(); //handle false
//				
//				// call NewAuctionRequest screen
			    new auctionRequest(theUser, theManager);
				
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
					Double itemMinimumBid = 0.0;
					
					//get input
					input.nextLine();
					
					System.out.print("Please enter the new item's name: ");
					itemDescription = input.nextLine();
					boolean invalidChoice = true;
					do {
						try {
							System.out.print("Please enter the minimum bid: ");
							itemMinimumBid = input.nextDouble();
							invalidChoice = false;
						} catch (Exception e) {
							System.out.println("Invalid input please try again.");
						}
					} while (invalidChoice);
					
					
					
					
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
			System.out.println("  2. View current bids");
			System.out.println("  3. Logout");
			System.out.print("Choice: ");
			option = getNextInt(3);
			
			if (option == 1) {
				// call auction view passing
				int userItemChoice = 0;
				int auctionUserChoice = viewAuctions.showAllAuctions(scanner, theUser, theManager);
				Auction theChosenAuction = null;
				if (auctionUserChoice != 0) {
					theChosenAuction = getNewAuctionForUserFromResponse(theUser, auctionUserChoice, theManager);
					userItemChoice = viewItems.showItems(scanner, theChosenAuction);					
				}
				
				if (userItemChoice != 0) {
					// Show bid menu
					ViewBidMenu viewBidMenu = new ViewBidMenu();
					AuctionItem chosenItem = getChosenItemUserWantsToBidOn(theChosenAuction, userItemChoice);
					viewBidMenu.placeBid(theUser, chosenItem, scanner, theManager, theChosenAuction);
				}
		
				
			} 
			
			if (option == 2) {
				ViewBids viewBids = new ViewBids();
				int userViewChoice = 0;
				int userAuctionChoice = 0;
				userViewChoice = viewBids.viewOptions(scanner);
				switch (userViewChoice) {
				case 1:
					userAuctionChoice = viewAuctions.showBiddersAuctions(scanner, theUser);
					break;
				case 2:
					viewItems.showBiddersItemsForAllAuctions(scanner, theUser);
					break;
				default:
					break;
				}
				
				if (userAuctionChoice != 0) {
					viewItems.showBiddersItems(scanner, theUser, userAuctionChoice);
				}
//				int userResponse = 0;
//				userResponse = viewItems.showItems(scanner, theUser.getMyAuctions().iterator().next());
//				if (userResponse != 0) {
//					AuctionItem[] items = theUser.getMyAuctions().toArray(new AuctionItem[theUser.getMyAuctions().size()]); 
//					AuctionItem theChosenItem = items[userResponse + 1];
//				}
//				userResponse = viewItems.showItems(scanner, theUser.getMyAuctions().iterator().next());
//				if (userResponse != 0) {
//					AuctionItem[] items = theUser.getMyAuctions().toArray(new AuctionItem[theUser.getMyAuctions().size()]); 
//					AuctionItem theChosenItem = items[userResponse + 1];
//				}
				
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
		Scanner scanner = new Scanner(System.in);
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
			nonProfitContactScreen((NonProfitContact) user, theManager, scanner);
		} else {
			endSession();
			scanner.close();
		}
	}
	
	
	private static void showWelcomeMessage() {
		System.out.println("Welcome to Auction Central!");
		System.out.println("--------------------------------");
		System.out.println("Today is " + formatDate(LocalDate.now()));
	}
	
	
	private static void endSession() {
		System.out.println("Session has ended.");
		System.exit(0);
	}
	
	
	public static String formatDate(final LocalDate theDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, u");
		return theDate.format(formatter);
	}
	
	private static Auction getAuctionFromUserResponse(User theUser, int theResponse) {
		Auction[] auctions = theUser.getMyAuctions().toArray(new Auction[theUser.getMyAuctions().size()]);
		return auctions[theResponse - 1];
	}
	
	private static Auction getNewAuctionForUserFromResponse(User theUser, int theResponse, AuctionManager theManager) {
		Auction[] auctions = theManager.getAvailableAuctions((Bidder) theUser).toArray(new Auction[theUser.getMyAuctions().size()]);
		return auctions[theResponse - 1];
	}
	
	private static AuctionItem getChosenItemUserWantsToBidOn(Auction theChosenAuction, int theItemChoice) {
		Collection<AuctionItem> theItems = theChosenAuction.getAllItems();
		AuctionItem[] indexedItems = theItems.toArray(new AuctionItem[theItems.size()]);
		return indexedItems[theItemChoice - 1];
	}

	public static int getNextInt(final int theMax) {
		int result = -1;
		
		do {
			try {
				System.out.print("Enter a choice: ");
				result = input.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				input.nextLine();
			}
		} while (result < 0 || result > theMax);
		return result;
	}
	
}
