package gui;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bid;
import model.Bidder;
import model.NewBidRequest;
import model.User;


/**
 * The form is for displaying new Bid form.
 * 
 * @author Yohei Sato
 * @version 25, May 2018
 */
public class NewBidFormController {

	private Auction myAuction;
	
	private AuctionItem myItem;
	
	@FXML
	private Label myWarningLabel;
	
	@FXML
	private Label mySuccessfulLabel;
	
	@FXML
	private TextField myBidLabel;
	
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
	
	@FXML
	private void onEnter() {
		makeBid();
	}
	
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
			AuctionManager theAuctionManager = new AuctionManager();
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
	
	// we need code here to create a NewBidRequest and send it to manager.
	// if it throws an exception then display an error so the user can try
	// again.
	
}
