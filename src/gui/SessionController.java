package gui;

import java.io.IOException;
import java.time.LocalDate;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionManager;
import model.Bidder;
import model.Manager;
import model.NonProfitContact;
import model.User;

public class SessionController {

	private static AuctionManager myManager = new AuctionManager();
	
	private static Stage myStage;
	
	private static User myUser;
	
	private static final String LOGIN_VIEW = "LoginView.fxml";
	
	private static final String USER_VIEW = "UserView.fxml";
	
	
	public static void initialize(final Stage theStage, AuctionCentralMain theAuctionCentralMain) {
		myStage = theStage;
		loadScene(LOGIN_VIEW);
	}
	
	public static User getUser() {
		return myUser;
	}
	
	public static AuctionManager getManager() {
		return myManager;
	}
	
	public static void userLogin(User theUser) {
		myUser = theUser;
		InformationContainerController informationContainerController = loadAuctionInfo(USER_VIEW);
		if (theUser instanceof Bidder) {
			loadBidderInformation(informationContainerController);	
		} else if (theUser instanceof NonProfitContact) {
//			loadNonProfitAuctionInformation(USER_VIEW);
		} else if (theUser instanceof Manager) {
//			loadNonProfitAuctionInformation(USER_VIEW);
		}
		
	}
	
	public static void userLogout() {
		myUser = null;
		loadScene(LOGIN_VIEW);
	}
	
	private static void loadScene(final String theScene) {
		try {
			FXMLLoader loader = new FXMLLoader(SessionController.class.getResource(theScene));
			myStage.setScene(new Scene((Pane) loader.load()));
		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("ViewController stage is null");
			} 
			System.err.println("Unable to load scene: " + theScene);
			e.printStackTrace();
		}
	}
	
	
	private static void loadBidderInformation(InformationContainerController thecController) {
		for (Auction auction: myUser.getMyAuctions()) {
			FXMLLoader auctionTileLoader = new FXMLLoader();
  
			auctionTileLoader.setLocation(InformationContainerController.class.getResource("AuctionTile.fxml"));
			SplitPane auctionTileView = null;
			try {
				auctionTileView = (SplitPane) auctionTileLoader.load();
			} catch (IOException e) {
				System.err.println("Error in Method: loadBidderInformation, Class: SessionController");
				e.printStackTrace();
			}
			AuctionTileController auctionTileController = (AuctionTileController) auctionTileLoader.getController();
			auctionTileController.setTitle(auction.getName());
			LocalDate date = auction.getDate();
			auctionTileController.setDate(date);
			auctionTileController.setItemInfoCount(auction.getAllItems().size());
			thecController.addNode(auctionTileView);
		  }
	}
	
	private static InformationContainerController loadAuctionInfo(final String theScene) {
		InformationContainerController informationContainerController = null;
		try {
			FXMLLoader userViewLoader = new FXMLLoader(SessionController.class.getResource(theScene));
			myStage.setScene(new Scene((Pane) userViewLoader.load()));
			
			UserViewController userViewController = (UserViewController) userViewLoader.getController();
			
			
			// Load the information container
			FXMLLoader informationContainerLoader = new FXMLLoader();
			informationContainerLoader.setLocation(UserViewController.class.getResource("InformationContainer.fxml"));
			
			
			FlowPane informationContainerView = (FlowPane) informationContainerLoader.load();
			userViewController.addToGrid(informationContainerView, 0, 2);
			GridPane gridPane = userViewController.getMyGrid();
			informationContainerView.prefWidthProperty().bind(gridPane.widthProperty().subtract(20));
			informationContainerView.prefHeightProperty().bind(gridPane.heightProperty().subtract(20));
			informationContainerController = (InformationContainerController) informationContainerLoader.getController();
			
		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("Information Container Controller is null");
			} 
			System.err.println("Unable to load scene: " + theScene);
			e.printStackTrace();
		}
		return informationContainerController;
	}
	
}
