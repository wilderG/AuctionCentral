package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.AuctionCalendar;
import model.AuctionManager;
import model.NewAuctionRequest;
import model.NonProfitContact;




/**
 * Menu for nonprofit organization attempting to submit an auction request.
 * 
 * @author Steven Golob 
 * @version May 6, 2018
 */
public class auctionRequest {
    
    private final NonProfitContact myNonprof;
    
    private final AuctionManager myManager;
    
    private final Scanner stdin;
    
    public auctionRequest(NonProfitContact theContact, AuctionManager theManager) {
        myNonprof = theContact;
        myManager = theManager;
        stdin = new Scanner(System.in);
        nonProfitAuctionRequest();
    }
    
//    public static void main(String[] theArgs) {
//        new auctionRequest(new NonProfitContact("", ""), new AuctionManager());
//    }
    
	public void nonProfitAuctionRequest() {
	    LocalDate today = LocalDate.now();
	    LocalDate soonestDayAvailable = getSoonestPossibleDate(today);
	    if (!myManager.isNewAuctionRequestAllowed()) {
	        System.out.println("We're sorry, but AuctionCentral is currently not \n"
	                + "accepting new auction requests at this time due to capacity \n"
	                + "constraints. Please check back later to see if new auctions \n"
	                + "are available. We apologize for the inconvenience.");
	    } else if (!myNonprof.isDateOneYearAfterPreviousAuction(
	                    today.plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT))) {
	        System.out.println("We're sorry, but there is already an auction for \n"
	                + "your organization within a year from any possible date you \n"
	                + "may schedule. AuctionCentral, as policy, only accepts one \n"
	                + "auction per organization per year.");
	    } else {
        		System.out.println("Please enter the date you would like to schedule "
        		        + "an auction on \"YYYY-MM-DD\":");
            System.out.println("(Must be between " + soonestDayAvailable.toString() 
            + " and " + today.plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT) + ")");
        		LocalDate chosenDate;
        		do {
            	    String input = stdin.nextLine();
            		try {
            		    chosenDate = LocalDate.parse(input);
            		} catch (DateTimeParseException e) {
            		    chosenDate = null;
            		    System.out.println("Sorry, could not read your date, "
            		            + "please try again with ther format \"YYYY-MM-DD\"");
            		}
        		} while (chosenDate == null);
        		
        		System.out.println();
        		try {
        		    NewAuctionRequest auctionRequest = new NewAuctionRequest(myNonprof, chosenDate);
        		    myManager.processNewAuctionRequest(auctionRequest);
//        		validateDate(chosenDate, today);
        		    
        		    System.out.println("Auction for " + myNonprof.getDisplayName() 
        		    + " successfully scheduled for " + chosenDate.toString() + "!");
        		    System.out.println("\n\n");
        		} catch (IllegalArgumentException e) {
        		    System.out.println(e.getMessage());
        		}
	    }
	}

    private LocalDate getSoonestPossibleDate(LocalDate theToday) {
        LocalDate oneYearFromPrevAuction = myNonprof.getLatestDate().plusYears(1);
        LocalDate minDaysOutFromToday = theToday.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        LocalDate latestDate;
        if (minDaysOutFromToday.isAfter(oneYearFromPrevAuction))
            latestDate = minDaysOutFromToday;
        else 
            latestDate = oneYearFromPrevAuction;
        return latestDate;
    }

//    private void validateDate(LocalDate theDate, LocalDate theToday) {
//        if (!myNonprof.isDateOneYearAfterPreviousAuction(theDate))
//            System.out.println("Sorry, but this date is within one year of your previous auction.");
//        else if (!theDate.isAfter(theToday))
//    }
}
