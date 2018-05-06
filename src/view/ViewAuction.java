package view;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

import model.Auction;
import model.NonProfitContact;
import model.User;

public class ViewAuction {

	public int showAuctions(Scanner theScanner, User theUser) {
		if (theUser instanceof NonProfitContact) {
			nonProfitIntroMessage(theUser);
		} else {
			bidderIntroMessage();
		}
		
		Collection<Auction> auctionCollection = theUser.getMyAuctions();
		Iterator<Auction> iterator = auctionCollection.iterator();
		for (int count = 0; count < auctionCollection.size(); count++) {
			Auction auction = iterator.next();
			System.out.print("  " + (count + 1) + ". ");
			if (theUser instanceof NonProfitContact) {
				nonProfitOutputMessage(auction);
			} else {
				bidderOutputMessage(auction);
			}
		}
		
		System.out.println("Select an auction number for which you would like to view items\n"
				+ "(Enter 0 to return to the main menu)");
	
		return theScanner.nextInt();
	}
	
	private void bidderIntroMessage() {
		System.out.println("Auctions available to bid on:\n");
	}
	
	private void nonProfitIntroMessage(User theUser) {
		System.out.println("All auctions for " + theUser.getDisplayName() + "\n");
	}
	
	private void bidderOutputMessage(Auction theAuction) {
		System.out.println("With " + theAuction.getName() + ", " 
				+ theAuction.getDate() + " (" + theAuction.getAllItems().size() + ")");
	}
	
	private void nonProfitOutputMessage(Auction theAuction) {
		Locale local = Locale.US ; // Or Locale.US
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate( FormatStyle.LONG )
		                                       .withLocale(local) ;
		String dateOutput = theAuction.getDate().format( formatter );
		System.out.println(dateOutput + " (" + theAuction.getAllItems().size() + " Items)");
	}

}
