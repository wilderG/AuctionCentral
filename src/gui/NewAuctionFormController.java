package gui;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;
import model.AuctionCalendar;
import model.AuctionDate;
import model.AuctionManager;
import model.NewAuctionRequest;
import model.NonProfitContact;
/**
 * Controller for the new Auction Form that is used y a nonprofit contact to submit a new auction.
 * @author Wilder Garcia
 * @author Steven Golob
 *
 */
public class NewAuctionFormController {
		
	/**
	 * The users NewAuctionRequest.
	 */
    private NewAuctionRequest myForm;
    
    /**
     * The user who is logged into the system and submitting a new auction request form.
     */
    private NonProfitContact myUser; 

    /**
     * Date Picker that allows a user to choose a valid date for the new auction they are requesting.
     */
	@FXML
	private DatePicker myDatePicker;
	
	/**
	 * Button used to submit the users new auction request.
	 */
	@FXML
	private Button mySubmitButton;
	
	/**
	 * Local Date used to capture the chosen date by the user for the new auction.
	 */
	@FXML
    private LocalDate myDate;
	
	/**
	 * Label that is used to present an error message to the user.
	 */
	@FXML
    private Label myErrorLableMsg;
	
    /**
     * Label that is used to present an error message to the user.
     */
    @FXML
    private Label myAtCapacityLabel;
	
	/**
	 * Label that is used to present a success message to the user.
	 */
	@FXML
	private Label mySucessMsg;

	private final AuctionManager myManager = SessionController.getManager();
	
	private boolean myAllDatesDisabled;
	

	/**
	 * Initializes the auqion request form.
	 */
	@FXML
	private void initialize() {
		myUser = (NonProfitContact)SessionController.getUser();
		
		mySubmitButton.setOnMouseClicked(event -> {
			myDate = myDatePicker.getValue();
			SubmitNewAuctionRequestEvents();
		});
		mySucessMsg.setVisible(false);
		myErrorLableMsg.setVisible(false);
		myDatePicker.setOnAction(event -> {
			myErrorLableMsg.setVisible(false);
		});
		disableOutOfRangeDaysOnDatePicker();
		myAllDatesDisabled = SessionController.getManager().isAtCapacity();
	    myAtCapacityLabel.setVisible(myAllDatesDisabled);
		
	    myDatePicker.setValue(getEarliestAvailableDate());
	}
	
	
	/**
	 * Returns the earliest date that an auction may be requested.
	 * @return LocalDate earliest valid date
	 */
	private LocalDate getEarliestAvailableDate() {
		LocalDate result = myUser.getSoonestPossibleNewAuctionDate();
		
		while (myManager.getAuctionDate(result).isAtCapacity()) {
			result = result.plusDays(1);
		}
		return result;
	}
		
	
    /**
     * Disables possibility of choosing dates out of eligible range from current date.
     * 
     * Pre-Condition: myDatePicker != null
     * Post-Condition: Dates on myDatePicker will be disabled if they are within the range eligible
     * for submitting an auction.
     */
    private void disableOutOfRangeDaysOnDatePicker() {
        myDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
      
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate theDate, boolean empty) {
                        super.updateItem(theDate, empty);
                        String failureMessage = getIneligableDateMessage(theDate);
                        if (failureMessage != null) {
                            getStyleClass().add("disabled");
                            setTooltip(new Tooltip(failureMessage));
                        }
                    }
                    
                    /**
                     * Gets the message to be displayed for a date if the date
                     * is unavailable to be scheduled on for a new auction.
                     * 
                     * @param theDate the date to get message for.
                     * @return the error message if there is an error.
                     */
                    private String getIneligableDateMessage(LocalDate theDate) {
                        String message = null;
                        AuctionDate auctionDate = myManager.getAuctionDate(theDate);
                        myUser = (NonProfitContact)SessionController.getUser();
                        if (myAllDatesDisabled) {
                            message = "Auction central is not accepting auctions at this time due to capacity constraints.";
                        } else if (theDate.isAfter(LocalDate.now().plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT))) {
                            message = "Auction cannot be scheduled more than "
                                    + AuctionCalendar.MAXIMUM_DAYS_OUT
                                    + " days out";
                        } else if (theDate.isBefore(LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT))) {
                            message = "Auction cannot be scheduled less than "
                                    + AuctionCalendar.MINIMUM_DAYS_OUT
                                    + " days out";
                        } else if (theDate.isBefore(myUser.getLatestDate().plusMonths(
                                AuctionCalendar.MIN_MONTHS_BETWEEN_AUCTIONS_FOR_NONPROF))) {
                            message = "Auction cannot be scheduled within "
                                    + AuctionCalendar.MIN_MONTHS_BETWEEN_AUCTIONS_FOR_NONPROF
                                    + " months of previous auction";
                        } else if (auctionDate.isAtCapacity()) {
                            message = "This date already holds the maximum amount of auctions";
                        }
                        return message;
                    }
                };
            }
        });  
    }


	/**
	 * Submits the users new auction request.
	 */
	@FXML
	public void SubmitNewAuctionRequestEvents() {
	
		try {
			myForm = new NewAuctionRequest(myUser, myDate);
			myManager.processNewAuctionRequest(myForm);
			mySucessMsg.setVisible(true);
		
		} catch(Exception theEvent) {
			myErrorLableMsg.setVisible(true);
			mySubmitButton.setDisable(true);
			
		}
	}

}
