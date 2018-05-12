package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.AuctionManager;
import model.User;

public class UserLoginController implements Initializable {

	private AuctionManager myManager;
	
	@FXML
	private AnchorPane anchor;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Text actionTarget;
	
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
		
		if (user instanceof model.Bidder) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BidderView.fxml"));
			
			try {
				Parent root = (Parent) loader.load();
				anchor.getScene().setRoot(root);
				
				BidderViewController controller = loader.<BidderViewController>getController();
				controller.setBidder((model.Bidder) user);
				controller.setManager(myManager);
			} catch (Exception ex) {
	            Logger.getLogger(AuctionCentralMain.class.getName())
	            		.log(Level.SEVERE, null, ex);
	        }
		} 
	
		return user;
	}
	
}
