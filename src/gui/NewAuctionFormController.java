package gui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import model.AuctionCalendar;
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
	 * Label that is used to present a success message to the user.
	 */
	@FXML
	private Label mySucessMsg;
	

	@FXML
	private void initialize() {
		mySubmitButton.setOnMouseClicked(event -> {
			SubmitNewAuctionRequestEvents();
		});
		mySucessMsg.setVisible(false);
		mySubmitButton.setDisable(true);
		myErrorLableMsg.setVisible(false);
		myDatePicker.setOnAction(event -> {
			myDate = myDatePicker.getValue();
			myErrorLableMsg.setVisible(false);
			mySubmitButton.setDisable(false);
		});
		disableOutOfRangeDaysOnDatePicker();
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
                    public void updateItem(LocalDate item, boolean empty) {
                        myUser = (NonProfitContact)SessionController.getUser();
                        super.updateItem(item, empty);
                        if (item.isAfter(LocalDate.now().plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT))
                            || item.isBefore(myUser.getSoonestPossibleNewAuctionDate())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
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
			myUser = (NonProfitContact)SessionController.getUser();
			myForm = new NewAuctionRequest(myUser, myDate);
			AuctionManager manager = SessionController.getManager();
			manager.processNewAuctionRequest(myForm);
			mySucessMsg.setVisible(true);
		
		} catch(Exception theEvent) {
			myErrorLableMsg.setVisible(true);
			mySubmitButton.setDisable(true);
			
		}
	}

}


