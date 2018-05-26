package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
		
		myChangeMaxUpcomingAuctionsField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	mySubmitButton.setVisible(!newValue.isEmpty());
		    	myMessageLabel.setVisible(newValue.isEmpty());
		    }
		});
		
		myChangeMaxUpcomingAuctionsField.setOnMouseClicked(event -> {
			myMessageLabel.setVisible(false);
			myMessageLabel.getStyleClass().clear();;
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
			myMessageLabel.getStyleClass().clear();;
			myMessageLabel.getStyleClass().add("success");
			myMessageLabel.setVisible(true);
			
		} catch (Exception e) {
			myMessageLabel.getStyleClass().add("error");
			myMessageLabel.setVisible(true);
			myMessageLabel.setText("Invalid, Please enter a positive number!");
			myChangeMaxUpcomingAuctionsField.clear();
			
		}
		
	}
	
}
