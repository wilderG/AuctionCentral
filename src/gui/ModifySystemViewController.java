package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AuctionManager;

public class ModifySystemViewController {
	
	@FXML
	private TextField myChangeMaxUpcomingAuctionsField;
	
	@FXML
	private Button mySubmitButton;
	
	@FXML
	private Label myMessageLabel;
	
	@FXML
	private void initialize() {
		AuctionManager manager = SessionController.getManager();
		myMessageLabel.setVisible(false);
		mySubmitButton.setVisible(false);
		myChangeMaxUpcomingAuctionsField.setPromptText("Current Max: " + manager.getFutureAuctionCapacity());
		mySubmitButton.setOnMouseClicked(event -> {
			processNewMaxCapacity();
		});
	}
	
	@FXML
	private void onEnter() {
		System.out.println("On enter called");
		processNewMaxCapacity();
	}
	
	private void processNewMaxCapacity() {
		mySubmitButton.setVisible(false);
		try {
			int newMax = Integer.valueOf(myChangeMaxUpcomingAuctionsField.getText());
			if (newMax <= 0) {
				throw new NumberFormatException();
			}
			AuctionManager manager = SessionController.getManager();
			manager.setFutureAuctionCapacity(newMax);
			myMessageLabel.setText("Maximum Number Set Successfully!");
			myChangeMaxUpcomingAuctionsField.setPromptText("Current Max: " + manager.getFutureAuctionCapacity());
			myChangeMaxUpcomingAuctionsField.clear();
			myChangeMaxUpcomingAuctionsField.getParent().requestFocus();
			myMessageLabel.getStyleClass().removeAll();
			myMessageLabel.getStyleClass().add("success");
			
		} catch (Exception e) {
			myMessageLabel.setText("Invalid, Please enter a positive number!");
			myChangeMaxUpcomingAuctionsField.clear();
			myMessageLabel.getStyleClass().removeAll();
			myMessageLabel.getStyleClass().add("error");
			myMessageLabel.setVisible(true);
		}
		
	}
	
	@FXML
	private void showSubmitButton() {
		System.out.println("Method called");
		if (!myChangeMaxUpcomingAuctionsField.getText().isEmpty()) {
			mySubmitButton.setVisible(true);
		}
		
	}
}
