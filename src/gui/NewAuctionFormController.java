package gui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import model.Auction;
import model.AuctionCalendar;
import model.AuctionItem;
import model.AuctionManager;
import model.NewAuctionRequest;
import model.NonProfitContact;

public class NewAuctionFormController {

	private Auction myAuction;
	private AuctionItem myItem;
    private NewAuctionRequest myForm;
    private NonProfitContact myUser; 

	@FXML
	private DatePicker myDatePicker;
	@FXML
	private Button mySubmitButton;
	@FXML
    private LocalDate myDate;
	@FXML
    private Label myErrorLableMsg;

	@FXML
	private void initialize() {
		mySubmitButton.setOnMouseClicked(event -> {
			SubmitNewAuctionRequestEvents();
		});
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
                        super.updateItem(item, empty);
                        if (item.isAfter(LocalDate.now().plusDays(AuctionCalendar.MAXIMUM_DAYS_OUT))
                            || item.isBefore(LocalDate.now().plusDays(AuctionCalendar.MINIMUM_DAYS_OUT))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        });     
    }

	public void setAuction(final Auction theAuction) {
		myAuction = theAuction;
	}

	public void setItem(final AuctionItem theItem) {
		myItem = theItem;
	}
	@FXML
	public void SubmitNewAuctionRequestEvents() {
	
		try {
			myUser = (NonProfitContact)SessionController.getUser();
			myForm = new NewAuctionRequest(myUser, myDate);
			AuctionManager manager = SessionController.getManager();
			manager.processNewAuctionRequest(myForm);
		
		} catch(Exception theEvent) {
			myErrorLableMsg.setVisible(true);
			mySubmitButton.setDisable(true);
			
		}
	}

}


