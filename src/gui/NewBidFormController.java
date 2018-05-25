package gui;

import javafx.fxml.FXML;
import model.Auction;
import model.AuctionItem;

public class NewBidFormController {

	private Auction myAuction;
	
	private AuctionItem myItem;
	
	@FXML
	private void initialize() {
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
