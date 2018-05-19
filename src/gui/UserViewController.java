package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bidder;
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
	
	@FXML
	FlowPane informationDisplay;
	
	@FXML
	GridPane myGridPane;
	
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
		showBidderAuctionInformation();
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

	
	private void showBidderAuctionInformation() {
		
        // Load person overview.
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(MainApp.class.getResource("ScreenDemo.fxml"));
//        AnchorPane screenDemo = (AnchorPane) loader.load();
//
//        // Set person overview into the center of root layout.
//        rootLayout.setCenter(screenDemo);
        
			
		try {

			FXMLLoader loader = new FXMLLoader();
			// Give the controller access to the main app.
	        InformationContainerController controller = (InformationContainerController) loader.getController();
	        controller.setMainApp(this);
//	        controller.loadAuctionInformation(myManager.getAvailableAuctions((Bidder) myUser));

			FlowPane tile = (FlowPane) loader.load(UserViewController.class.getResource("InformationContainer.fxml"));
			myGridPane.add(tile, 0, 2);
			tile.prefWidthProperty().bind(myGridPane.widthProperty().subtract(20));
			tile.prefHeightProperty().bind(myGridPane.heightProperty().subtract(20));
		
		} catch (IOException e) {
			e.printStackTrace();
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
