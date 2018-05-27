package gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * Controller for the sub menu view that allows you to add nodes programmatically. 
 * @author Jim Rosales
 *
 */
public class SubMenuViewController {
	
	@FXML
	private FlowPane mySubMenu;
	
	@FXML
	private void initialize() {
		mySubMenu.setAlignment(Pos.CENTER_LEFT);
	}

	/**
	 * The flow pane that will holds all the sub menu controls.
	 */
	@FXML
	private FlowPane myFlowPane;
	// Needs to be added at 0,1
	
	
	/**
	 * Allows you to add a node (control) to the sub menu view
	 * Pre-Condition: 
	 * Post-Condition:
	 * @param theMenuControl
	 */
	public void addNode(Node theMenuControl) {
		myFlowPane.getChildren().add(theMenuControl);
	}
	
	
	
}
