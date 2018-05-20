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

/**
 * Controller manages the various views that will eb presented depending on user interactions.
 * @author Jared Malone
 * @author Jim Rosales
 * @version May 19, 2018
 */
public class SessionController {

	/**
	 * The auction manager that will be used for data queries or modifications requested by the user.
	 */
	private static AuctionManager myManager = new AuctionManager();

	/**
	 * The state used by the session controller.
	 */
	private static Stage myStage;

	/**
	 * The user who will login to this current session.
	 */
	private static User myUser;

	/**
	 * The file name for the LoginView
	 */
	private static final String LOGIN_VIEW = "LoginView.fxml";

	/**
	 * The file name for the UserView
	 */
	private static final String USER_VIEW = "UserView.fxml";

	private static final String AUCTION_TILE_VIEW = "AuctionTileView.fxml";
	
	private static final String INFORMATION_CONTAINER_VIEW = "InformationContainerView.fxml";
	
	/**
	 * Initializes the session controller by setting the stage to the given stage.
	 * Loads the loginView to start the session
	 * Pre-Condition: theStage != null
	 * Post-Condition: The session controller will be initializes and the loginView will be constructed
	 * ready for the user to interact with.
	 * @param theStage
	 */
	public static void initialize(final Stage theStage) {
		myStage = theStage;
		loadScene(LOGIN_VIEW);
	}

	/**
	 * Getter for the sesionControllers user for the session.
	 * Post-Condition: The user returned will be the one currently being used
	 * by the session controller
	 * @return theUser for the session.
	 */
	public static User getUser() {
		return myUser;
	}

	/**
	 * Getter for the sessionControllers auctionManager.
	 * Post-Condition: The auctionManager for the sessionController is returned
	 * @return The sessionControllers auctionManager
	 */
	public static AuctionManager getManager() {
		return myManager;
	}

	/**
	 * Logs the given user into the system and initializes the appropraite informationContainer depending on the
	 * users type.
	 * Pre-Condition: theUser != null
	 * Post-Condition: An informationContainer will be initialies to present the appropraite user interface.
	 * @param theUser that will be logged into the sytem.
	 */
	public static void userLogin(User theUser) {
		myUser = theUser;
		InformationContainerViewController informationContainerController = loadAuctionInfo(USER_VIEW);
		if (theUser instanceof Bidder) {
			loadBidderInformation(informationContainerController);	
		} else if (theUser instanceof NonProfitContact) {
			//			loadNonProfitAuctionInformation(USER_VIEW);
		} else if (theUser instanceof Manager) {
			//			loadNonProfitAuctionInformation(USER_VIEW);
		}

	}

	/**
	 * Logs the user out of the current session.
	 * Post-Condition: 
	 */
	public static void userLogout() {
		myUser = null;
		loadScene(LOGIN_VIEW);
	}

	/**
	 * 
	 * Pre-Condition:
	 * Post-Condition:
	 * @param theScene
	 */
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

	/**
	 * 
	 * Pre-Condition:
	 * Post-Condition:
	 * @param thecController
	 */
	private static void loadBidderInformation(InformationContainerViewController theController) {
		for (Auction auction: myUser.getMyAuctions()) {
			FXMLLoader auctionTileLoader = new FXMLLoader();

			auctionTileLoader.setLocation(InformationContainerViewController.class.getResource(AUCTION_TILE_VIEW));
			SplitPane auctionTileView = null;
			try {
				auctionTileView = (SplitPane) auctionTileLoader.load();
			} catch (IOException e) {
				System.err.println("Error in Method: loadBidderInformation, Class: SessionController");
				e.printStackTrace();
			}
			AuctionTileViewController auctionTileController = (AuctionTileViewController) auctionTileLoader.getController();
			auctionTileController.setTitle(auction.getName());
			LocalDate date = auction.getDate();
			auctionTileController.setDate(date);
			auctionTileController.setItemInfoCount(auction.getAllItems().size());
			theController.addNode(auctionTileView);
		}
	}

	/**
	 * 
	 * Pre-Condition:
	 * Post-Condition:
	 * @param theScene
	 * @return
	 */
	private static InformationContainerViewController loadAuctionInfo(final String theScene) {
		InformationContainerViewController informationContainerViewController = null;
		try {
			FXMLLoader userViewLoader = new FXMLLoader(SessionController.class.getResource(theScene));
			myStage.setScene(new Scene((Pane) userViewLoader.load()));

			UserViewController userViewController = (UserViewController) userViewLoader.getController();


			// Load the information container
			FXMLLoader informationContainerLoader = new FXMLLoader();
			informationContainerLoader.setLocation(UserViewController.class.getResource(INFORMATION_CONTAINER_VIEW));


			FlowPane informationContainerView = (FlowPane) informationContainerLoader.load();
			userViewController.addToGrid(informationContainerView, 0, 2);
			GridPane gridPane = userViewController.getMyGrid();
			informationContainerView.prefWidthProperty().bind(gridPane.widthProperty().subtract(20));
			informationContainerView.prefHeightProperty().bind(gridPane.heightProperty().subtract(20));
			informationContainerViewController = (InformationContainerViewController) informationContainerLoader.getController();

		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("The stage is null");
			} 
			System.err.println("Unable to load scene: " + theScene);
			e.printStackTrace();
		}
		return informationContainerViewController;
	}

}
