package console;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;

import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bidder;
import model.NewItemRequest;
import model.NonProfitContact;
import model.User;

/**
 * Main driver for the AuctionCentral application.
 * 
 * @author Jared Malone
 * @author Jim Rosales
 * @author Steven Kenneth Golob
 * @author Wilder Emanuel Garcia Y Garcia
 * @author Yohei Sato
 * @version 5/8/2018
 */
public class ConsoleDriver {

	private static Scanner input = new Scanner(System.in);
	
	/** This class should not be constructed. **/
	private ConsoleDriver() {}
	
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
		final int mainMenuMaxOptions = 4;
		
		do {
			System.out.println("\nMAIN MENU:");
			System.out.println("  1. View all auctions/items for " 
								+ theUser.getDisplayName());
			System.out.println("  2. Submit an auction request");
			System.out.println("  3. Modify auction inventory");
			System.out.println("  4. Logout");
			System.out.print("Choice: ");
			option = getNextInt(mainMenuMaxOptions);
			
			if (option == 1) {
				ViewAuction viewAuctions = new ViewAuction();
				int userResponse =
						viewAuctions .showAllAuctions(theScanner, theUser, theManager);
				if (userResponse != 0) {
					Auction auction =
							getAuctionFromUserResponse(theUser, userResponse);
					ViewItems viewItems = new ViewItems();
					viewItems.showItemsForNonProfAuction(theScanner, auction);
				}
			} 
			
			if (option ==2) {
			    new AuctionRequest(theUser, theManager);
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
					System.out.println("You cannot add more items to your future auction on " 
					        + futureAuction.getDate().toString() + ".");
				} else {
					// show NewItemRequest screen					
					System.out.println("\nAdding inventory item for auction " +
							"scheduled on " +
							formatDate(futureAuction.getDate()));
					System.out.println("(You may enter up to " + 
					 futureAuction.getAvailableSpace() + " more items)");
						
					//NewItemRequest
					String itemDescription;
					Double itemMinimumBid = 0.0;
					
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
							System.out.println(
									"Invalid input please try again.");
						}
					} while (invalidChoice);
					
					
					AuctionItem newItem = 
						theManager.processNewItem(new NewItemRequest
							(itemDescription,
									BigDecimal.valueOf(itemMinimumBid), 
							 futureAuction));
					
					System.out.println("Thank You! " + 
					   newItem.getDescription() 
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

		final int mainMenuOptions = 3;
		do {
			System.out.println("\nMAIN MENU:");
			System.out.println("  1. Search for auctions to bid on");
			System.out.println("  2. View all of my bids");
			System.out.println("  3. Logout");
			option = getNextInt(mainMenuOptions);
			
			if (option == 1) {
				// call auction view passing
				int userItemChoice = 0;
				int auctionUserChoice = 
				 viewAuctions.showAllAuctions(scanner, theUser, theManager);
				Auction theChosenAuction = null;
				if (auctionUserChoice != 0) {
					theChosenAuction = 
					   getNewAuctionForUserFromResponse(theUser,
							   auctionUserChoice, theManager);
					userItemChoice = 
							viewItems.showItems(scanner, theChosenAuction);					
				}
				
				if (userItemChoice != 0) {
					// Show bid menu
					ViewBidMenu viewBidMenu = new ViewBidMenu();
					AuctionItem chosenItem = 
							getChosenItemUserWantsToBidOn(theChosenAuction,
									userItemChoice);
					viewBidMenu.placeBid(theUser, chosenItem,
							scanner, theManager, theChosenAuction);
				}
		
				
			} 
			
			if (option == 2) {
				
				if (theUser.getMyAuctions().isEmpty()) {
					System.out.println("You have no bids to view.");
				} else {
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
						viewItems.showBiddersItems(scanner, 
								theUser, userAuctionChoice);
					}
				}
			}
			
		} while(option!=3);
		endSession();
 	}

	
	/**
	 * Prompts the user to login and determines if user is a Bidder or
	 * a NonProfitContact. 
	 * @param theManager instance of AuctionManager
	 * @author Jared Malone
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
			nonProfitContactScreen((NonProfitContact) user,
					theManager, scanner);
		} else {
			endSession();
			scanner.close();
		}
	}
	
	/**
	 * Displays the welcome message when the program is executed.
	 */
	private static void showWelcomeMessage() {
		System.out.println("Welcome to Auction Central!");
		System.out.println("--------------------------------");
		System.out.println("Today is " + formatDate(LocalDate.now()));
	}
	
	/**
	 * Displays an exit message and terminates the program.
	 */
	private static void endSession() {
		System.out.println("Session has ended.");
		System.exit(0);
	}
	
	/**
	 * Returns a string value containing the formatted date. The format follows
	 * the pattern of "January 1, 2018".
	 * @param theDate LocalDate instance
	 * @return formatted String
	 * @author Jared Malone
	 */
	public static String formatDate(final LocalDate theDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, u");
		return theDate.format(formatter);
	}
	
	/**
	 * Returns a string value containing the formatted currency. The format
	 * uses the local default format of the user's computer.
	 * @param theAmount to format
	 * @return the formatted string
	 * @author Jared Malone
	 */
	public static String formatCurrency(final BigDecimal theAmount) {
		return NumberFormat.getCurrencyInstance(Locale.getDefault()).
				format(theAmount);
	}
	
	
	private static Auction getAuctionFromUserResponse(
			User theUser, int theResponse) {
		Auction[] auctions =
				theUser.getMyAuctions().toArray(
						new Auction[theUser.getMyAuctions().size()]);
		return auctions[theResponse - 1];
	}
	
	private static Auction getNewAuctionForUserFromResponse(User theUser,
			int theResponse, AuctionManager theManager) {
		Auction[] auctions = 
				theManager.getAvailableAuctions((Bidder) theUser).toArray(
						new Auction[theUser.getMyAuctions().size()]);
		return auctions[theResponse - 1];
	}
	
	private static AuctionItem getChosenItemUserWantsToBidOn
	(Auction theChosenAuction, int theItemChoice) {
		Collection<AuctionItem> theItems =
				theChosenAuction.getAllItems();
		AuctionItem[] indexedItems =
				theItems.toArray(new AuctionItem[theItems.size()]);
		return indexedItems[theItemChoice - 1];
	}

	/**
	 * Prompts the user to enter a value. Valid input is an integer between
	 * 0 and theMax. Any other input prompts the user to try again.
	 * @param theMax the maximum integer
	 * @return user selection between 0 and theMax
	 * @author Jared Malone
	 */
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
