package gui;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bidder;
import model.NewBidRequest;


/**
 * The form is for displaying new Bid form.
 * 
 * @author Yohei Sato
 * @version 25, May 2018
 */
public class NewBidFormController {

	/**
	 * The auction where the new bid will be submitted to.
	 */
	private Auction myAuction;
	
	/**
	 * The item being bid on.
	 */
	private AuctionItem myItem;
	
	/**
	 * Label used to present a warning label to the user.
	 */
	@FXML
	private Label myWarningLabel;
	
	/**
	 * Label used to present a success label to the user.
	 */
	@FXML
	private Label mySuccessfulLabel;
	
	/**
	 * TextField used to allow a user to enter their bid for the item.
	 */
	@FXML
	private TextField myBidLabel;
	
	/**
	 * Button used to submit the users bid for an item.
	 */
	@FXML
	private Button mySubmitButton;
	
	
	@FXML
	private void initialize() {
		myWarningLabel.setVisible(false);
		mySuccessfulLabel.setVisible(false);
		mySubmitButton.setOnMouseClicked(event -> {
			makeBid();
		});
	}
	
	/**
	 * Method called when a user is focused on the myBidLabel and presses the enter key. This allows a user to submit
	 * their new bid request without needing to press the submit button.
	 * 
	 * Post-Condition: The users bid will be processed.
	 */
	@FXML
	private void onEnter() {
		makeBid();
	}
	
	/**
	 * Method processes the value found in the myBidLabel to create a NewBidRequest for the user on a particular item.
	 * If the given value given by the user is not value (i.e not a double) an error message will be presented to the
	 * user. This error message will also be presented if their bid is not above the minimum bid value for the item.
	 * Otherwise if a valid value is given in the text field then a new bid will be submitted to the system.
	 * 
	 * Pre-Condition: 
	 * Post-Condition:
	 */
	@FXML
	public void makeBid() {
		String userInput = myBidLabel.getText();
		double bidValue = Double.valueOf(userInput);	
		try {
		BigDecimal userBidValue = new BigDecimal(bidValue);
		
		if(userBidValue.compareTo(myItem.getMinimumAcceptableBidValue()) < 0) {
			myWarningLabel.setVisible(true);
			mySuccessfulLabel.setVisible(false);
		} else {
			myWarningLabel.setVisible(false);
			mySuccessfulLabel.setVisible(true);
			Bidder bidder = (Bidder) SessionController.getUser();
			
			NewBidRequest theNewBidRequest = new NewBidRequest(bidder, myAuction, myItem, userBidValue);
			AuctionManager theAuctionManager = SessionController.getManager();
			theAuctionManager.processNewBid(theNewBidRequest);
		}
		} catch (Exception e) {
			System.out.println("It's not valid numbers!");
		}
		
	}
	
	public void setAuction(final Auction theAuction) {
		myAuction = theAuction;
	}
	
	public void setItem(final AuctionItem theItem) {
		myItem = theItem;
	}
	
	
}
