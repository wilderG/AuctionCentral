package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.AuctionManager;
import model.User;

public class MainViewController implements Initializable {

	private User myUser;
	
	private AuctionManager myManager;
	
	@FXML
	Label userDisplayName;
	
	@FXML
	Button logoutButton;
	
	public MainViewController(final User theUser, final AuctionManager theManager) {
		myUser = theUser;
		myManager = theManager;
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		configureEventListeners();
		updateDisplayName();
	}

	/*
	 * Setup listeners for login field and button press.
	 */
	private void configureEventListeners() {
				
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent theEvent) {
				
			}
		});
		
	
		
	}
	
	private void updateDisplayName() {
		userDisplayName.setText("Welcome " + myUser.getDisplayName());
	}
	
}
