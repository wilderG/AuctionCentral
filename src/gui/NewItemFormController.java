package gui;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Auction;
import model.NewItemRequest;

public class NewItemFormController {

	private Auction myAuction;

    @FXML
	private Label myItemDescription;

    @FXML
	private Label myMinimumBid;

    @FXML
    private Button mySubmitButton;
    
    @FXML
    private Label myErrorLabel;
    
	@FXML
	private void initialize() {
        myErrorLabel.setVisible(false);
	}
	
	public void setAuction(final Auction theAuction) {
		myAuction = theAuction;
	}
	
	@FXML
	private void pressButton() {
	    System.out.println(" hi ");
	    try {
	        validateInput(myItemDescription, myMinimumBid);
	        myErrorLabel.setVisible(false);
	        BigDecimal minBidValue = new BigDecimal(myMinimumBid.getText());
	        NewItemRequest itemRequest = new NewItemRequest(myItemDescription.getText(), minBidValue, myAuction);
	        SessionController.getManager().processNewItem(itemRequest);
	    } catch (IllegalArgumentException e) {
	        myErrorLabel.setText(e.getMessage());
	        myErrorLabel.setVisible(true);
	    }
	}
	
	// we need code here to create a NewItemRequest and send it to manager.
	// if it throws an exception then display an error so the user can try
	// again.

    private void validateInput(Label theItemDescription, Label theMinimumBid) throws NumberFormatException {
        // exception 1
        if (theItemDescription == null) {
            throw new IllegalArgumentException("Item Description is missing");
        }
        // exception 2
        if (theMinimumBid == null) {
            throw new IllegalArgumentException("Minimum bid is missing");
        }
        // exception 3
        try {
            new BigDecimal(myMinimumBid.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Minimum bid entered is not formattable, please re-enter.");
        }
    }
}