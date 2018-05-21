package gui;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bidder;
import model.Manager;
import model.NonProfitContact;
import model.User;

/**
 * Controller manages the various views that will be presented depending on user interactions.
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

	
	private static InformationContainerViewController infoViewController;
	
	/**
	 * The file name for the LoginView
	 */
	private static final String LOGIN_VIEW = "LoginView.fxml";

	/**
	 * The file name for the UserView
	 */
	private static final String USER_VIEW = "UserView.fxml";

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
		UserViewController userViewController = loadUserView();
		infoViewController = loadInformationContainerView(userViewController);
		if (theUser instanceof Bidder) {
			loadBidderAuctionInformation();	
		} else if (theUser instanceof NonProfitContact) {
			loadNonProfitAuctionInformation(infoViewController);
		} else if (theUser instanceof Manager) {
			loadNonProfitAuctionInformation(infoViewController);
		}

	}

	

	/**
	 * Logs the user out of the current session.
	 * Post-Condition: The load screen which will allow a user to login in will be loaded.
	 */
	public static void userLogout() {
		myUser = null;
		loadScene(LOGIN_VIEW);
	}

	/**
	 * Loads the given scene onto the stage.
	 * Pre-Condition: myStage != null
	 * Post-Condition: The given scene will be loaded and set as the current scene.
	 * @param theScene that will be loaded.
	 */
	private static void loadScene(final String theScene) {
		try {
			FXMLLoader loader = new FXMLLoader(SessionController.class.getResource(theScene));
			myStage.setScene(new Scene((Pane) loader.load()));
		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("Stage is null");
			} 
			System.err.println("Unable to load scene: " + theScene);
			e.printStackTrace();
		}
	}

	/**
	 * Loads all the auctions associated with the current bidder onto an InformationContainerView.
	 * Pre-Condition: theController != null
	 * Post-Condition: An autionTile will be created for each auction associated with the user and added to
	 * the flowPane of the InformationContainerView
	 * @param theController associated with the InformationContainerView where all the bidders information will
	 * be appended. 
	 */
	private static void loadBidderAuctionInformation() {
		for (Auction auction: myUser.getMyAuctions()) {
			AnchorPane tile = TileFactory.auctionTile(auction);
			
			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent theEvent) {
					showItems(auction);
				}
			});
			
			infoViewController.addNode(tile);
		}
	}
	
	
	private static void loadNonProfitAuctionInformation(
			InformationContainerViewController theController) {
		for (Auction auction: myUser.getMyAuctions()) {
			AnchorPane tile = TileFactory.auctionTile(auction);
			
			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent theEvent) {
					showItems(auction);
				}
			});
			
			infoViewController.addNode(tile);
		}
		
	}
	
	private static void loadAdmin(
			InformationContainerViewController theController) {
		for (Auction auction: myUser.getMyAuctions()) {
			theController.addNode(TileFactory.auctionTile(auction));
		}
		
	}

	public static void showItems(final Auction theAuction) {
		infoViewController.clear();
		for (AuctionItem item : theAuction.getAllItems()) {
			infoViewController.addNode(TileFactory.itemTile(item));
		}
	}
	
	/**
	 * Loads a userView onto myStage.
	 * Pre-Condition: myStage != null
	 * Post-Condition: A userView will be set as a scene in myStage
	 * @return A UserViewController associated with the newly created UserView.
	 */
	private static UserViewController loadUserView() {
		UserViewController userViewController = null;
		try {
			FXMLLoader userViewLoader = new FXMLLoader(SessionController.class.getResource(USER_VIEW));
			myStage.setScene(new Scene((Pane) userViewLoader.load()));

			userViewController = (UserViewController) userViewLoader.getController();

		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("The stage is null");
			} 
			System.err.println("Unable to load scene: " + USER_VIEW);
			e.printStackTrace();
		}
		return userViewController;
	}
	

	/**
	 * Loads an InformationContainerView into theUserView associated with the given userViewController.
	 * Pre-Condition: theUserViewController != null
	 * Post-Condition: An informationViewContainerView will be added onto the userView's grid that is 
	 * associated with the given
	 * userViewController
	 * @param theUserViewController that will be used to add the newly created InformationContainerView.
	 * @return An InformationContainerViewController associated with the newly created InformationContainerView.
	 */
	private static InformationContainerViewController loadInformationContainerView(
			UserViewController theUserViewController) {
		InformationContainerViewController informationContainerViewController = null;
		FXMLLoader informationContainerLoader = new FXMLLoader();
		informationContainerLoader.setLocation(UserViewController.class.getResource(INFORMATION_CONTAINER_VIEW));


		FlowPane informationContainerView = null;
		try {
			informationContainerView = (FlowPane) informationContainerLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		theUserViewController.addToGrid(informationContainerView, 0, 2);
		GridPane gridPane = theUserViewController.getMyGrid();
		informationContainerView.prefWidthProperty().bind(gridPane.widthProperty().subtract(20));
		informationContainerView.prefHeightProperty().bind(gridPane.heightProperty().subtract(20));
		informationContainerViewController = 
				(InformationContainerViewController) informationContainerLoader.getController();
		
		return informationContainerViewController;
	}
	
	

}
