package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import model.AuctionManager;
import model.Bidder;

public class MenuBarController {
	
	@FXML
	private TitledPane myMainMenu;
	
	@FXML 
	private Button myViewAuctionsButton;
	
	//@FXML
	//private Button myUsernameField;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		
	}
	
	public Button getMyViewAuctionsButton() {
		return myViewAuctionsButton;
	}

	
	
}
