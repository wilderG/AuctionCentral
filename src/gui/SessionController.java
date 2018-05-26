package gui;


import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;


import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionManager;
import model.Bidder;
import model.Employee;
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

	/**
	 * The informationViewController used in conjunction with the infoViewContainer being displayed.
	 */
	private static InformationContainerViewController infoViewController;
	
	private static UserViewController myUserViewController;
	/**
	 * The file name for the LoginView.
	 */
	private static final String LOGIN_VIEW = "LoginView.fxml";

	/**
	 * The file name for the UserView.
	 */
	private static final String USER_VIEW = "UserView.fxml";

	/**
	 * The file name for the InformationContainerView.
	 */
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
	
	public static InformationContainerViewController getInformationContainerView() {
		return infoViewController;
	}

	/**
	 * Logs the given user into the system and initializes the appropraite informationContainer depending on the
	 * users type.
	 * Pre-Condition: theUser != null
	 * Post-Condition: An informationContainer will be initializes to present the appropraite user interface.
	 * @param theUser that will be logged into the system.
	 */
	public static void userLogin(User theUser) {
		myUser = theUser;
		myUserViewController = loadUserView();
		infoViewController = loadInformationContainerView(myUserViewController);
		if (theUser instanceof Bidder) {
			loadBidderMenu(myUserViewController);
		} else if (theUser instanceof NonProfitContact) {
			loadNonProfitMenu(myUserViewController);
		} else if (theUser instanceof Employee) {
			loadEmployeeMenu(myUserViewController);
		}

	}
	
	public static UserViewController getUserViewController() {
	    return myUserViewController;
	}

	/**
	 * Loads a menu that contains all the main controls that can be used by a bidder
	 * Pre-Condition: theController != null
	 * Post-Condition: A menu that is appropriate for a bidder user will be present in theUserView
	 * @param theController associated with the UserView where the bidder menu will be added.
	 */
	private static void loadBidderMenu(final UserViewController theController) {
		AnchorPane viewAuctionsButton = MenuButton.newMenuButton("View Auctions");
		AnchorPane viewBidsButton = MenuButton.newMenuButton("View Bids");
		AnchorPane logOutButton = MenuButton.newMenuButton("Log Out");
		
		ArrayList<AnchorPane> buttons = new ArrayList<>();
		buttons.add(viewAuctionsButton);
		buttons.add(viewBidsButton);
		buttons.add(logOutButton);
		
		viewAuctionsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			removeActiveClassFromButtons(buttons);
			infoViewController.showAuctions(myManager.getAvailableAuctions((Bidder) myUser));
			addActiveCssClass(viewAuctionsButton);
		});
		theController.addMenuButton(viewAuctionsButton);
		

		viewBidsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			removeActiveClassFromButtons(buttons);
			infoViewController.showAuctionBids(myUser.getMyAuctions());
			addActiveCssClass(viewBidsButton);
		});
		theController.addMenuButton(viewBidsButton);
		
		logOutButton.setOnMouseClicked(event -> {
			SessionController.userLogout();
		});
		theController.addMenuButton(logOutButton);
		
		// go to default screen
		infoViewController.showAuctions(myManager.getAvailableAuctions((Bidder) myUser));
		addActiveCssClass(viewAuctionsButton);
		SubMenuFactory.createSubMenu(myUser, theController);
	}
	
	private static void removeActiveClassFromButtons(ArrayList<AnchorPane> thePanes) {
		
		for (AnchorPane pane: thePanes) {
			Node button = pane.getChildren().get(0);
			button.getStyleClass().clear(); // Removes all class styles from the button
			// Add the default
			button.getStyleClass().add("menuButton");
		}
	}
	
	private static void addActiveCssClass(AnchorPane thePane) {
		Node button = thePane.getChildren().get(0);
		button.getStyleClass().add("active");
	}
	
	/**
	 * Loads a menu that contains all the main controls that can be used by a non profit contact
	 * Pre-Condition: theController != null
	 * Post-Condition: A menu that is appropriate for a non profit contact user will be present in theUserView
	 * @param theController associated with the UserView where the non profit contact menu will be added.
	 */
	private static void loadNonProfitMenu(final UserViewController theController) {
		AnchorPane viewAuctionsButton = MenuButton.newMenuButton("View Auctions");
		AnchorPane requestNewAuctionButton = MenuButton.newMenuButton("Request Auction");
		AnchorPane requestNewItemButton = MenuButton.newMenuButton("Add Item");
		AnchorPane logOutButton = MenuButton.newMenuButton("Log Out");
		
		ArrayList<AnchorPane> buttons = new ArrayList<>();
		buttons.add(viewAuctionsButton);
		buttons.add(requestNewAuctionButton);
		buttons.add(requestNewItemButton);
		buttons.add(logOutButton);
		
		viewAuctionsButton.setOnMouseClicked(event -> {
			removeActiveClassFromButtons(buttons);
			infoViewController.showAuctions(myUser.getMyAuctions());
			addActiveCssClass(viewAuctionsButton);
		});
		
		
		theController.addMenuButton(viewAuctionsButton);
		
		
		requestNewAuctionButton.setOnMouseClicked(event -> {
			removeActiveClassFromButtons(buttons);
			infoViewController.showNewAuctionRequest();
			addActiveCssClass(requestNewAuctionButton);
		});
		

		theController.addMenuButton(requestNewAuctionButton);
		SubMenuFactory.createSubMenu(myUser, theController);
		
		requestNewAuctionButton.setOnMouseClicked(event -> {
			Auction auction = ((NonProfitContact) myUser).getFutureAuction();
			if (auction != null && SessionController.getManager().isNewItemRequestAllowed(auction)) {
				removeActiveClassFromButtons(buttons);
				infoViewController.showNewItemRequest(auction);
				addActiveCssClass(requestNewItemButton);
			}
		});
		
		
		theController.addMenuButton(requestNewItemButton);
		
		
		
		logOutButton.setOnMouseClicked(event -> {
			SessionController.userLogout();
		});
		theController.addMenuButton(logOutButton);
		
		// go to default screen
		infoViewController.showAuctions(myUser.getMyAuctions());
		addActiveCssClass(viewAuctionsButton);
	}
	
	/**
	 * Loads a menu and a sub-menu that contains all the main controls that can be used by an employee
	 * Pre-Condition: theController != null
	 * Post-Condition: A menu and sub-menu that is appropriate for an employee user will be present in theUserView
	 * @param theController associated with the UserView where the employee menus will be added.
	 */
	private static void loadEmployeeMenu(final UserViewController theController) {
		ArrayList<AnchorPane> buttons = new ArrayList<>();
		AnchorPane viewAuctionsButton = MenuButton.newMenuButton("View Auctions");
		AnchorPane modifySystemButton = MenuButton.newMenuButton("Modify System");
		buttons.add(viewAuctionsButton);
		buttons.add(modifySystemButton);
		viewAuctionsButton.setOnMouseClicked(event -> {
			infoViewController.showAdminAuctions(myManager.getAllAuctionsSorted(), myManager);
			SubMenuFactory.createSubMenu(myUser, theController);
			removeActiveClassFromButtons(buttons);
			addActiveCssClass(viewAuctionsButton);
		});
		
		
		modifySystemButton.setOnMouseClicked(event -> {
			
			removeActiveClassFromButtons(buttons);
			infoViewController.showModifySystemView();
			addActiveCssClass(viewAuctionsButton);
		});
		
		
		theController.addMenuButton(viewAuctionsButton);
		theController.addMenuButton(modifySystemButton);
		
		AnchorPane logOutButton = MenuButton.newMenuButton("Log Out");
		logOutButton.setOnMouseClicked(event -> {
			SessionController.userLogout();
		});
		theController.addMenuButton(logOutButton);
		
		// go to default screen
		infoViewController.showAdminAuctions(myManager.getAllAuctionsSorted(), myManager);
		SubMenuFactory.createSubMenu(myUser, theController);
		addActiveCssClass(viewAuctionsButton);

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
		
		ScrollPane scrollPane = theUserViewController.getMyScrollPane();
		scrollPane.setContent(informationContainerView);
		informationContainerView.prefWidthProperty().bind(scrollPane.widthProperty().subtract(0));
		informationContainerView.prefHeightProperty().bind(scrollPane.heightProperty().subtract(20));
		informationContainerViewController = 
				(InformationContainerViewController) informationContainerLoader.getController();
		
		return informationContainerViewController;
	}

}
