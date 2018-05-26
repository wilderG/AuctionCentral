package gui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Auction;
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


