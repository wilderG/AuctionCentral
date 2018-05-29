package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AuctionManager;

/**
 * Class represents a view which contains all the modifications an admin user can make to the system.
 * @author Jim Rosales
 *
 */
public class ModifySystemViewController {
	
	/**
	 * TextField which allows a user to enter the new max capacity for the system for upcoming future auctions.
	 */
	@FXML
	private TextField myChangeMaxUpcomingAuctionsField;
	
	/**
	 * Submit button that allows the user to submit their desired change.
	 */
	@FXML
	private Button mySubmitButton;
	
	/**
	 * A generic label used to deliver success or error messages to the user.
	 */
	@FXML
	private Label myMessageLabel;
	
	/**
	 * Method that is called when the view is constructed.
	 */
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
	
	/**
	 * Method that is called when a user is focused on the myChangeMaxUpcomingAuctions field and they press enter. This
	 * can be used instead of pressing the submit button to process their request.
	 * Post-Condition: The value in the myChangeMaxUpcomingAuctions will be processed and if a valid input is given
	 * will be applied.
	 */
	@FXML
	private void onEnter() {
		processNewMaxCapacity();
	}
	
	/**
	 * Processes the text in the myChangeMaxUpcomingAuctionsField.
	 * Pre-Condition: myChangeMaxUpcomingAuctionsField != null
	 * Post-Condition: If an valid value is found in the text field the max capacity for the system will be applied.
	 * Otherwise the max capacity won't be modified and a message to the user will be given notifying them of thier
	 * invalid input.
	 */
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
