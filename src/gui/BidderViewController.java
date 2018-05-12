package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.AuctionManager;
import model.Bidder;

public class BidderViewController implements Initializable {

	private Bidder myBidder;
	
	private AuctionManager myManager;
	
	@FXML
	Label userDisplayName;
	
	@FXML
	Button logoutButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		myBidder = null;
		configureEventListeners();
	}

	/*
	 * Setup listeners for login field and button press.
	 */
	private void configureEventListeners() {
				
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent theEvent) {
				Platform.exit();
			}
		});
		
	
		
	}
	
	
	/*
	 * Returns a user from the manager. Returns null if user not found.
	 */
	public void setBidder(final Bidder theBidder) {
		myBidder = theBidder;
		updateDisplayName();
	}

	public void setManager(final AuctionManager theManager) {
		myManager = theManager;
	}
	
	private void updateDisplayName() {
		userDisplayName.setText("Welcome " + myBidder.getDisplayName());
	}
	
}
