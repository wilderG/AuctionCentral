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
    
    /** The nonprofit attempting to submit an auction request. */
    private final NonProfitContact myNonprof;
    
    /** The manager of AuctionCentral in charge of scheduling. */
    private final AuctionManager myManager;
    
    /** A scanner to accept standard input from user. */
    private final Scanner stdin;
    
    /**
     * Constructs a view screen for submitting an auctionrequest, or
     * displaying any problems if one cannot be requested.
     * 
     * @param theContact the nonprofit attempting to submit a request
     * @param theManager the object in charge of scheduling auctions
     */
    public auctionRequest(NonProfitContact theContact, AuctionManager theManager) {
        myNonprof = theContact;
        myManager = theManager;
        stdin = new Scanner(System.in);
        nonProfitAuctionRequest();
    }
    
    /**
     * Displays why an auction may not be scheduled for this nonprofit at this time,
     * otherwise, prompts the user for date, checks to see if it is available,
     * then books the auction if so.
     */
	public void nonProfitAuctionRequest() {
	    if (isSchedulingAllowedForNonprof()) {
	        displayPrompt();
            LocalDate chosenDate;
            do {
                chosenDate = getChosenDateFromUser();
            } while (chosenDate == null);
            try {
                attemptToScheduleAuction(chosenDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
	    } else {
	        displayPrecheckError();
	    }
	}

	/**
	 * Displays the instructions for the non-profit to enter a date to schedule 
	 * an auction on.
	 */
	private void displayPrompt() {
	    LocalDate today = LocalDate.now();
        System.out.println("Please enter the date you would like to schedule "
                + "an auction on.");
        System.out.println("(Must be between " + getSoonestPossibleDate().toString() 
                + " and " + today.plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT) + ")");
        System.out.println("\"YYYY-MM-DD\":");
    }

	/**
	 * Attempts to schedule an auction based on the chosen date by the user.
	 * 
	 * @param theChosenDate the date the user chose the schedule
	 * @throws IllegalArgumentException if the chosen date is not available
	 */
    private void attemptToScheduleAuction(LocalDate theChosenDate) 
            throws IllegalArgumentException {
        System.out.println();
        NewAuctionRequest auctionRequest = new NewAuctionRequest(myNonprof, theChosenDate);
        myManager.processNewAuctionRequest(auctionRequest);
        
        //___________________
        
        System.out.println("Auction for " + myNonprof.getDisplayName() 
        + " successfully scheduled for " + theChosenDate.toString() + "!");
        System.out.println("\n");
    }

    /**
	 * Prompts the user for a date to schedule an auction on.
	 * 
	 * @return the input as a date from the user
	 */
	private LocalDate getChosenDateFromUser() {
	    String input = stdin.nextLine();
	    LocalDate chosenDate;
        try {
            chosenDate = LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            chosenDate = null;
            System.out.println("Sorry, could not read your date, "
                    + "please try again with the format \"YYYY-MM-DD\"");
        }
        return chosenDate;
    }

	/**
	 * Displays why the pre-check failed for a non-profit 
	 * attempting to schedule an auction.
	 */
    private void displayPrecheckError() {
	    if (!myManager.isNewAuctionRequestAllowed()) {
            System.out.println("We're sorry, but AuctionCentral is currently not \n"
                    + "accepting new auction requests at this time due to capacity \n"
                    + "constraints. Please check back later to see if new auctions \n"
                    + "are available. We apologize for the inconvenience.");
        } else if (!myNonprof.isDateOneYearAfterPreviousAuction(
                        LocalDate.now().plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT))) {
            System.out.println("We're sorry, but there is already an auction for \n"
                    + "your organization (on " + myNonprof.getLatestDate().toString()
                    + ") within 1 year from any possible date you \n"
                    + "may schedule. AuctionCentral, as policy, only accepts one \n"
                    + "auction per organization per year.");
        }
    }

    /**
	 * Pre-checks that a non-profit is eligible to schedule a new auction
	 * at this time.
	 * 
	 * @return if the non-profit may schedule a new auction now
	 */
	private boolean isSchedulingAllowedForNonprof() {
	    LocalDate latestAvailableDateInCalendar = 
	            LocalDate.now().plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT);
	    return myManager.isNewAuctionRequestAllowed() 
	            && myNonprof.isDateOneYearAfterPreviousAuction(latestAvailableDateInCalendar);
    }

    /**
	 * Gets the soonest available date for a nonprofit to schedule an auction,
	 * after one year from previous auction and after the calendar's minimum 
	 * number of days out from today.
	 * 
	 * @param theToday the current date
	 * @return the soonest possible date for the nonprofit to book an auction.
	 */
    private LocalDate getSoonestPossibleDate() {
        LocalDate today = LocalDate.now();
        LocalDate oneYearFromPrevAuction = myNonprof.getLatestDate().plusYears(1);
        LocalDate minDaysOutFromToday = today.plusDays(AuctionCalendar.MINIMUM_DAYS_OUT);
        LocalDate latestDate;
        if (minDaysOutFromToday.isAfter(oneYearFromPrevAuction))
            latestDate = minDaysOutFromToday;
        else 
            latestDate = oneYearFromPrevAuction;
        return latestDate;
    }
}
