package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
				loginUser(usernameField.getText());
			}
		});
		
		usernameField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent theEvent) {
				clearUserDisplay();
				
				if (theEvent.getCode().equals(KeyCode.ENTER)) {
					loginUser(usernameField.getText());
				}
			}
		});
		
	}
	
	private void clearUserDisplay() {
		actionTarget.setText("");
	}
	
	private void loginUser(final String theUsername) {
		User user;
		FXMLLoader loader;
		MainViewController controller;
		Parent root;
		
		try {
			user = myManager.getUser(theUsername);
		} catch (Exception e) {
			actionTarget.setText("User not found.");
			return;
		}
		
		controller = new MainViewController(user, myManager);
		loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
		loader.setController(controller);
		
		try {
			root = (Parent) loader.load();
			anchor.getScene().setRoot(root);
		} catch (IOException e) {
			System.err.println("Unable to load MainView.fxml");
			e.printStackTrace();
		}
			
	}

	
	
}
