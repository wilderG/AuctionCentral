package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import model.AuctionManager;
import model.User;

public class AuctionCentralController implements Initializable {

	private AuctionManager myManager;
	
	@FXML
	TextField usernameField;
	
	@FXML
	Button loginButton;
	
	@FXML
	Text actionTarget;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		myManager = new AuctionManager();
		configureEventListeners();
	}

	
	/*
	 * Setup listeners for login field and button press.
	 */
	private void configureEventListeners() {
				
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent theEvent) {
				updateUserDisplay(usernameField.getText());
			}
		});
		
		usernameField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent theEvent) {
				clearUserDisplay();
				
				if (theEvent.getCode().equals(KeyCode.ENTER)) {
					updateUserDisplay(usernameField.getText());
				}
			}
		});
		
	}
	
	private void clearUserDisplay() {
		actionTarget.setText("");
	}
	
	/*
	 * Displays the user name in the text field.
	 */
	private void updateUserDisplay(final String theUsername) {
		clearUserDisplay();
		
		if (theUsername.isEmpty()) {
			return;
		}
		
		User user = getUser(theUsername);
		
		if (user == null) {
			actionTarget.setText("User not found.");
		} else {
			actionTarget.setText("Hello " + user.getDisplayName());
		}
	}
	
	
	/*
	 * Returns a user from the manager. Returns null if user not found.
	 */
	private User getUser(final String theUsername) {
		User user;
		
		try {
			user = myManager.getUser(theUsername);
		} catch (Exception e) {
			return null;
		}
		
		return user;
		
//		if (user instanceof model.Bidder) {
//			bidderScreen((Bidder) user, theManager);
//		} else if (user instanceof model.NonProfitContact) {
//			nonProfitContactScreen((NonProfitContact) user,
//					theManager, scanner);
//		} else {
//			endSession();
//			scanner.close();
//		}
	
	}
	
}
