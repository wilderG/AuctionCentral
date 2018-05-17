package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.User;

public class MainViewController implements Initializable {

	private User myUser;
	
	private AuctionManager myManager;
	
	@FXML
	Label userDisplayName;
	
	@FXML
	Label logoutButton;
	
	@FXML
	FlowPane tileDisplay;
	
	public MainViewController(final User theUser, final AuctionManager theManager) {
		myUser = theUser;
		myManager = theManager;
		
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
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
				ViewController.loadLoginScreen();
			}
		});
		
	
		
	}
	
	private void updateDisplayName() {
		userDisplayName.setText(myUser.getDisplayName());
	}

	
	private void showTiles() {
		for (int count = 0; count < 5; count++) {
			
			try {
				SplitPane tile = (SplitPane) FXMLLoader.load(MainViewController.class.getResource("auctionTile.fxml"));
				tileDisplay.getChildren().add(tile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void showAuctions() {
		for (Auction e : myUser.getMyAuctions()) {
			Label label = new Label(e.toString());
			label.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent theEvent) {
					showItems(e);
				}
			});
			tileDisplay.getChildren().add(label);
		}
	}
	
	private void showItems(final Auction theAuction) {
		tileDisplay.getChildren().clear();
		for (AuctionItem e : theAuction.getAllItems()) {
			tileDisplay.getChildren().add(new Label(e.toString()));
		}
	}
	
}
