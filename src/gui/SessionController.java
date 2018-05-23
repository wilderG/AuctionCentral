package gui;


import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AuctionManager;
import model.Bidder;
import model.Employee;
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
	 * Post-Condition: An informationContainer will be initializes to present the appropraite user interface.
	 * @param theUser that will be logged into the system.
	 */
	public static void userLogin(User theUser) {
		myUser = theUser;
		UserViewController userViewController = loadUserView();
		infoViewController = loadInformationContainerView(userViewController);
		if (theUser instanceof Bidder) {
			loadBidderMenu(userViewController);
			//loadBidderAuctionInformation();	
		} else if (theUser instanceof NonProfitContact) {
			loadNonProfitMenu(userViewController);
		} else if (theUser instanceof Employee) {
			loadEmployeeMenu(userViewController);
			//loadNonProfitAuctionInformation(infoViewController);
		}

	}

	private static void loadBidderMenu(final UserViewController theController) {
		AnchorPane viewAuctionsButton = MenuButton.newMenuButton("View Auctions");
		viewAuctionsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			infoViewController.showAuctions(myManager.getAvailableAuctions((Bidder) myUser));
		});
		theController.addMenuButton(viewAuctionsButton);
		
		
		AnchorPane viewBidsButton = MenuButton.newMenuButton("View Bids");
		viewBidsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			infoViewController.showAuctionBids(myUser.getMyAuctions());
		});
		theController.addMenuButton(viewBidsButton);
		
		AnchorPane logOutButton = MenuButton.newMenuButton("Log Out");
		logOutButton.setOnMouseClicked(event -> {
			SessionController.userLogout();
		});

		theController.addMenuButton(logOutButton);
	}
	
	private static void loadNonProfitMenu(final UserViewController theController) {
		AnchorPane viewAuctionsButton = MenuButton.newMenuButton("View Auctions");
		viewAuctionsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent theEvent) {
				infoViewController.showAuctions(myUser.getMyAuctions());
				
			}
		});
		theController.addMenuButton(viewAuctionsButton);
		
		
		AnchorPane logOutButton = MenuButton.newMenuButton("Log Out");
		logOutButton.setOnMouseClicked(event -> {
			SessionController.userLogout();
		});
		theController.addMenuButton(logOutButton);
	}
	
	private static void loadEmployeeMenu(final UserViewController theController) {
		AnchorPane viewAuctionsButton = MenuButton.newMenuButton("View Auctions");
		viewAuctionsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent theEvent) {
				infoViewController.showAdminAuctions(myManager.getAllAuctionsSorted(), myManager);
				theController.showDatePicker(infoViewController, myManager);
			}
		});
		theController.addMenuButton(viewAuctionsButton);
		
		
		AnchorPane logOutButton = MenuButton.newMenuButton("Log Out");
		logOutButton.setOnMouseClicked(event -> {
			SessionController.userLogout();
		});
		theController.addMenuButton(logOutButton);
	}
	
	
//	private static void addRangeButton(final UserViewController theController) {
//		AnchorPane viewAuctionsByRangeButton = MenuButton.newMenuButton("View Auctions by Date Range");
//		viewAuctionsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent theEvent) {
//				infoViewController.showAdminAuctions(myManager.getAllAuctionsSorted(), myManager);
//			}
//		});
//		theController.addMenuButton(viewAuctionsButton);
//		
//		
//		AnchorPane logOutButton = MenuButton.newMenuButton("Log Out");
//		logOutButton.setOnMouseClicked(event -> {
//			SessionController.userLogout();
//		});
//		theController.addMenuButton(logOutButton);
//	}

	

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
		informationContainerView.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
		informationContainerViewController = 
				(InformationContainerViewController) informationContainerLoader.getController();
		
		return informationContainerViewController;
	}
	
	private static class MenuButton {
		
		private static AnchorPane newMenuButton(String theTitle) {
			FXMLLoader buttonLoader = new FXMLLoader(SessionController.class.getResource("ViewMenuButton.fxml"));
			AnchorPane button = null;
			try {
				button = (AnchorPane) buttonLoader.load();
			} catch (IOException e) {
				System.err.println("Button Error");
				//e.printStackTrace();
			}	
			
			ViewMenuButtonController buttonCtrl = (ViewMenuButtonController) buttonLoader.getController();
			buttonCtrl.setText(theTitle);
			return button;
		}
		
	}

}
