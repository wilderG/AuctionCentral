package gui;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Auction;
import model.NewItemRequest;

/**
 * The form in which a user will submit an item to an auction that is valid.
 * 
 * @author Steven Golob 
 * @version 25, May 2018
 */
public class NewItemFormController {

    /** The auction item is being added to. */
	private Auction myAuction;

    /** The description of the item to be added. */
    @FXML
	private TextField myItemDescription;

    /** The minimum bid of the item to be added. */
    @FXML
	private TextField myMinimumBid;

    /** The button to submit the new item. */
    @FXML
    private Button mySubmitButton;

    /** The error message. */
    @FXML
    private Label myErrorLabel;
    
    /** The success message. */
    @FXML
    private Label mySuccessLabel;

    
	@FXML
	private void initialize() {
        myErrorLabel.setVisible(false);
        mySuccessLabel.setVisible(false);
        mySubmitButton.setOnMouseClicked(event -> {
            pressButton();
        });
	}
	
	/**
	 * Sets the auction to be added to.
	 * 
	 * @param theAuction the auction to be added to 
	 */
	public void setAuction(final Auction theAuction) {
		myAuction = theAuction;
	}
	
	/**
	 * Submits the new item for this auction if text fields are valid.
	 */
	@FXML
	private void pressButton() {
        myErrorLabel.setVisible(false);
        mySuccessLabel.setVisible(false);
	    try {
	        validateInput(myItemDescription, myMinimumBid);
	        BigDecimal minBidValue = new BigDecimal(myMinimumBid.getText());
	        NewItemRequest itemRequest = new NewItemRequest(myItemDescription.getText(), minBidValue, myAuction);
	        SessionController.getManager().processNewItem(itemRequest);
	        
	        myItemDescription.setText("");
	        myMinimumBid.setText("");
            mySuccessLabel.setText("Item successfully submitted to your auction!");
            mySuccessLabel.setVisible(true);
            
            if (!myAuction.isAllowingNewItem()) {
                myErrorLabel.setText("Auction now has the maximum number of items.");
                myErrorLabel.setVisible(true);
            }
//            Node button = SessionController.getUserViewController().getMenuButtonBar().getChildren().get(0);
//            button.fireEvent();
	    } catch (IllegalArgumentException e) {
	        myErrorLabel.setText(e.getMessage());
            myErrorLabel.setVisible(true);
	    }
	}
	
	// we need code here to create a NewItemRequest and send it to manager.
	// if it throws an exception then display an error so the user can try
	// again.

	/**
	 * Checks that the entered data is valid.
	 * 
	 * @param theItemDescription the item description
	 * @param theMinimumBid the Minimum bid value of the item
	 */
    private void validateInput(TextField theItemDescription, TextField theMinimumBid) {
        // exception 1
        if (!myAuction.isAllowingNewItem()) {
            throw new IllegalArgumentException("Auction already has the maximum number of items!");
        }
        // exception 2
        if (theItemDescription.getText().equals("")) {
            throw new IllegalArgumentException("Item Description is missing!");
        }
        // exception 3
        if (theMinimumBid.getText().equals("")) {
            throw new IllegalArgumentException("Minimum bid is missing!");
        }
        // exception 4
        BigDecimal minBidValue;
        try {
            minBidValue = new BigDecimal(myMinimumBid.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Minimum bid entered is not formattable!");
        }
        // exception 5
        if (minBidValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot have a negative minimum bid value!");
        }
    }
}