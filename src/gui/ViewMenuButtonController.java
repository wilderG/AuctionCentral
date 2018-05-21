package gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ViewMenuButtonController {

	/**
	 * The hex value for the default color of myleftInfoPane.
	 */
	private static final String DEFAULT_LEFT_INFO_PANE_COLOR = "#0FD171";
	
	/**
	 * The hex value for the default on hover color of myLeftInfoPane.
	 */
	private static final String ON_HOVER_DEFAULT_INFO_PANE_COLOR = "#17BA97";
	
	@FXML
	private Label myTitle;
	
	@FXML
	private Pane myPane;
	
	@FXML
	private void initialize() {
		myTitle.setText("");
	}
	
	
	@FXML
	private void onMouseEntered() {
		myPane.setBackground(
				new Background(new BackgroundFill(Color.web(DEFAULT_LEFT_INFO_PANE_COLOR),
						CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	@FXML
	private void onMouseExited() {
		myPane.setBackground(
				new Background(new BackgroundFill(Color.web(ON_HOVER_DEFAULT_INFO_PANE_COLOR),
						CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setText(String theText) {
		myTitle.setText(theText);
	}
	
}
