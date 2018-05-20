package gui;

import java.io.IOException;
import java.time.LocalDate;

import javax.sound.midi.MidiDevice.Info;

import console.ConsoleDriver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionManager;
import model.User;

public class SessionController {

	private static AuctionManager myManager = new AuctionManager();
	
	private static Stage myStage;
	
	private static User myUser;
	
	private static final String LOGIN_VIEW = "LoginView.fxml";
	
	private static final String USER_VIEW = "UserView.fxml";
	
	private static AuctionCentralMain myAuctionCentralMain;
	
	public static void initialize(final Stage theStage, AuctionCentralMain theAuctionCentralMain) {
		myStage = theStage;
		myAuctionCentralMain = theAuctionCentralMain;
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
		loadBidderAuctionInformation(USER_VIEW);
//		loadScene(USER_VIEW);
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
	
	public static void voidShowAuctionInformation() {
		
	}
	
	private static void loadBidderAuctionInformation(final String theScene) {
		try {
			FXMLLoader userViewLoader = new FXMLLoader(SessionController.class.getResource(theScene));
			myStage.setScene(new Scene((Pane) userViewLoader.load()));
			
			UserViewController userViewController = (UserViewController) userViewLoader.getController();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(UserViewController.class.getResource("InformationContainer.fxml"));
			
			FlowPane informationContainerView = (FlowPane) loader.load();
			userViewController.myGridPane.add(informationContainerView, 0, 2);
			informationContainerView.prefWidthProperty().bind(userViewController.myGridPane.widthProperty().subtract(20));
			informationContainerView.prefHeightProperty().bind(userViewController.myGridPane.heightProperty().subtract(20));
			InformationContainerController informationContainerController = (InformationContainerController) loader.getController();
			for (Auction auction: myUser.getMyAuctions()) {
				  FXMLLoader auctionTileLoader = new FXMLLoader();
				  
				  auctionTileLoader.setLocation(InformationContainerController.class.getResource("AuctionTile.fxml"));
				  SplitPane auctionTileView = (SplitPane) auctionTileLoader.load();
//				  auctionTileLoader.setLocation(UserViewController.class.getResource("InformationContainer.fxml"));
				  AuctionTileController auctionTileController = (AuctionTileController) auctionTileLoader.getController();
				  auctionTileController.setTitle(auction.getName());
				  LocalDate date = auction.getDate();
				  auctionTileController.setDate(date);
				  auctionTileController.setItemInfoCount(auction.getAllItems().size());
				  informationContainerController.addNode(auctionTileView);
			  }
			
			//InformationContainerController informationContainerController = (InformationContainerController) loader.getController();
			//informationContainerController.loadAuctionInformation(myUser.getMyAuctions(), theScene);
		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("ViewController stage is null");
			} 
			System.err.println("Unable to load scene: " + theScene);
			e.printStackTrace();
		}
	}
	
}
