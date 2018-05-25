package gui;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Auction;
import model.AuctionItem;


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
	private Label myBidLabel;
	
	@FXML
	private Button mySubmitButton;
	
	@FXML
	private void initialize() {
		myWarningLabel.setVisible(false);
		mySubmitButton.setOnMouseClicked(event -> {
			makeBid();
		});
	}
	
	
	@FXML
	public void makeBid() {
		String userInput = myBidLabel.getText();
		int bidValue = Integer.valueOf(userInput);	
		BigDecimal bV = new BigDecimal(bidValue);
		if(bV.compareTo(myItem.getMinimumAcceptableBidValue()) >=1) {	
		} else {
			myWarningLabel.setVisible(true);
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
