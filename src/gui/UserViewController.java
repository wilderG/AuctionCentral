package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
	private Label theUserDisplayName;
	

	/**
	 * The button used by the user to logout.
	 */
	@FXML
	private Label theLogoutButton;

	/**
	 * The container that will hold the information presented to the user.
	 */
	@FXML
	private FlowPane informationContainerView;
	
	/**
	 * The grid pane that will hold the views various components.
	 */
	@FXML
	private GridPane myGridPane;
	
	
	
	/**
	 * Initializes the view by constructing all appropriate view components. 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		myUser = SessionController.getUser();
		configureEventListeners();
		updateDisplayName();
	}
	
	/**
	 * Configures all the necessary logout buttons event listeners.
	 * Pre-Condition: theLogOutButton != null
	 * Post-Condition: Event handler for house clicks will be added to the logout button
	 */
	private void configureEventListeners() {
				
		theLogoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent theEvent) {
				SessionController.userLogout();
			}
		});

	}
	
	/**
	 * Updates theUserDisplay label to show the current users name.s
	 * Pre-Condition: theUserDisplayName != null
	 * Post-Condition: theUserDisplay label will be initialized with the current users name.
	 */
	private void updateDisplayName() {
		theUserDisplayName.setText(myUser.getDisplayName());
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
	
}
