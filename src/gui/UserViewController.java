package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.User;

public class UserViewController implements Initializable {

	private User myUser;
	
	private AuctionManager myManager;
	
	@FXML
	Label userDisplayName;
	
	@FXML
	Label logoutButton;
	
	@FXML
	FlowPane tileDisplay;
	
//	public MainViewController(final User theUser, final AuctionManager theManager) {
//		myUser = theUser;
//		myManager = theManager;
//		
//	}
//	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		myUser = SessionController.getUser();
		myManager = SessionController.getManager();
		configureEventListeners();
		updateDisplayName();
		showTiles();
	}

	/*
	 * Setup listeners for login field and button press.
	 */
	private void configureEventListeners() {
				
		logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent theEvent) {
				SessionController.userLogout();
			}
		});
		
	
		
	}
	
	private void updateDisplayName() {
		userDisplayName.setText(myUser.getDisplayName());
	}

	
	
	
	private void showTiles() {
		for (int count = 0; count < 9; count++) {
			
			// maybe we could make a TileFactory static class that returns a SplitPane
			// or a Collection<SplitPane> and has methods for each of our needs (Auction, Item, Bid)?
			
			try {
				SplitPane tile = (SplitPane) FXMLLoader.load(UserViewController.class.getResource("auctionTile.fxml"));
				tileDisplay.getChildren().add(tile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
