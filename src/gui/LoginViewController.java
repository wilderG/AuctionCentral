package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.AuctionManager;
import model.User;

public class LoginViewController implements Initializable {
	
	
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
		myManager = SessionController.getManager();
		configureEventListeners();
	}

	/*
	 * Setup listeners for login field and button press.
	 */
	private void configureEventListeners() {
				
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent theEvent) {
				loginUser(usernameField.getText());
			}
		});
		
	}
	
	/**
	 * On an enter event the username is processed
	 * @param theActionEvent that is being fired by the textfield
	 */
	@FXML
    public void onEnter(ActionEvent theActionEvent){
		clearUserDisplay();

		loginUser(usernameField.getText());
    }
	
	
	private void clearUserDisplay() {
		actionTarget.setText("");
	}
	
	private void loginUser(final String theUsername) {
		User user;
		
		try {
			user = myManager.getUser(theUsername);
		} catch (Exception e) {
			actionTarget.setText("User not found.");
			return;
		}
		
		SessionController.userLogin(user);
	}


	
	
}
