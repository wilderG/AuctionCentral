package gui;


import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.util.Callback;
import javafx.scene.control.DateCell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.AuctionManager;
import model.Auction;
import model.User;

/**
 * View constructs a user view interface where the various users of the system can accomplish all necessary tasks
 * @author Jared Malone
 * @author Jim Rosales
 *
 */
public class UserViewController implements Initializable {

	/**
	 * The user who logged into the system.
	 */
	private User myUser;
	
	/**
	 * The label that is used to display the users name.
	 */
	@FXML
	private Label myUserDisplayName;
	
	/**
	 * The root pane for the UserView being displayed.
	 */
	@FXML
	private AnchorPane myRootPane;

	/**
	 * The button used by the user to logout.
	 */
	@FXML
	private Label myLogoutButton;
	
	/**
	 * The scroll pane used by the UserView to hold the myInformationContainerView
	 */
	@FXML
	private ScrollPane myScrollPane;
	
	/**
	 * The container that will hold the information presented to the user.
	 */
	@FXML
	private FlowPane myInformationContainerView;
	
	/**
	 * The grid pane that will hold the views various components.
	 */
	@FXML
	private GridPane myGridPane;
	
	/**
	 * The main menu bar that holds the main controls for any particular user.
	 */
	@FXML
	private FlowPane myMenuButtonBar;
	
	/**
	 * The menu bar that holds the sub controls for any particular user.
	 */
	@FXML
	private FlowPane mySubMenuBar;
	
	
	
	
	/**
	 * Initializes the view by constructing all appropriate view components. 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		myUser = SessionController.getUser();
		updateDisplayName();
		myRootPane.getStyleClass().add("rootPane");
		myScrollPane.getStyleClass().add("rootPane");
		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		myScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
//		mySubMenuBarContainer.prefWidthProperty().bind(myGridPane.widthProperty());
//		mySubMenuBarContainer.prefHeightProperty().bind(myGridPane.get);

//		mySubMenuBar.getStyleClass().add("subMenuBar");
//		initializeDateObjects();		
		
	}
	
	

	/**
	 * Updates theUserDisplay label to show the current users name.s
	 * Pre-Condition: theUserDisplayName != null
	 * Post-Condition: theUserDisplay label will be initialized with the current users name.
	 */
	private void updateDisplayName() {
		myUserDisplayName.setText(myUser.getDisplayName());
	}

	/**
	 * Adds a given node to the views grid pane at the designated column and row.
	 * Pre-Condition: theNode and theGridPane are not null.
	 * Post-Condition: The views grid will load the given node to its designated location.
	 * @param theNode that will be added to the views grid pane.
	 * @param theColumn where the node will be added.
	 * @param theRow where the node will be added.
	 */
	public void addToGrid(Node theNode, int theColumn, int theRow) {
		myGridPane.add(theNode, theColumn, theRow);
		
	}
	
	/**
	 * Getter for the views grid pane.
	 * Post-Condition: The object returned will the the one used by the view.
	 * @return The views grid pane.
	 */
	public GridPane getMyGrid() {
		return myGridPane;
	}
	
	/**
	 * Adds the given node to the userViews menuButtonBar
	 * Pre-Condition: 
	 * Post-Condition:
	 * @param theButton
	 */
	public void addMenuButton(final Node theButton) {
		myMenuButtonBar.getChildren().add(theButton);
	}
	
	/**
	 * Getter for the views scroll pane.
	 * Post-Condition: The object returned will the the one used by the view.
	 * @return The views scroll pane.
	 */
	public ScrollPane getMyScrollPane() {
		return myScrollPane;
	}
	
	/**
	 * Getter for the views sub menu bar flow pane.
	 * Post-Condition: The object returned will the the one used by the view.
	 * @return The views flow pane.
	 */
	public FlowPane getMySubMenuBar() {
		return mySubMenuBar;
	}
	
	
	/**
	 * Getter for the views sub menu bar container flow pane.
	 * Post-Condition: The object returned will the the one used by the view.
	 * @return The views container flow pane for the sub menu bar.
	 */
//	public FlowPane getMySubMenuBarContainer() {
//		return mySubMenuBarContainer;
//	}
	
	
	

		
	
}

