package gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * A controller for the auctionTileView
 * @author Jim Rosales
 * @version May 19, 2018
 */
public class TileViewController {
	
	/**
	 * The hex value for the default color of myleftInfoPane.
	 */
	private static final String DEFAULT_LEFT_INFO_PANE_COLOR = "#0FD171";
	
	/**
	 * The hex value for the default on hover color of myLeftInfoPane.
	 */
	private static final String ON_HOVER_DEFAULT_INFO_PANE_COLOR = "#17BA97";

	/**
	 * The label used for the auctions title.
	 */
	@FXML
	private Label topRightField;

	/**
	 * Used to display the day that the auction was/will be held.
	 */
	@FXML
	private Label topLeftField;
	
	/**
	 * The pane that is used to display the date information for an auction
	 */
	@FXML
	private SplitPane myLeftInfoPane;
	
	@FXML
	private SplitPane myRightInfoPane;

	/**
	 * Used to display the Month and Year on which the auction was/will be held.
	 */
	@FXML
	private Label bottomLeftField;

	/**
	 * Used to display the Item count of the auction.
	 */
	@FXML
	private Label bottomRightField;


	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public TileViewController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		topRightField.setText("");
		topLeftField.setText("");
		bottomLeftField.setText("");
		bottomRightField.setText("");
	}

	/**
	 * Sets the auctionTiles titleField to the given string. 
	 * Pre-Condition: String != null
	 * Post-Condition: The labels title will be set to the given string.
	 * @param theTitle
	 */
	public void setTopRight(String theFieldValue) {
		topRightField.setWrapText(true);
		
		topRightField.setText(theFieldValue);

		topRightField.setTextAlignment(TextAlignment.LEFT);

	}

	/**
	 * Sets the auctionsTiles ItemInfoCount label using the given count number.
	 * Pre-Condition: theCount != null
	 * Post-Condition: The label will be set to ITEM_COUNT_DIALOG + theCount
	 * @param theCount The number of items that the auction has.
	 */
	public void setBottomRight(String theFieldValue) {
		bottomRightField.setWrapText(true);
		bottomRightField.setText(theFieldValue);
	}

	/**
	 * Sets the auctionTiles date labels using the given date.
	 * Pre-Condition: theDate != null
	 * Post-Condition: The auctionTile will display the fill date for the auction
	 * @param theDate when the auction was held or will be held.
	 */
	public void setTopLeft(String theFieldValue) {
		topLeftField.setText(theFieldValue);
	}

	/**
	 * Sets auctionTiles day information
	 * Pre-Condition: theDate != null
	 * Post-Condition: The auctionTiles dateInfo label's text will be set to 
	 * the day that the auction was/will be held.
	 * @param theDate that the auction was held/will be held.
	 */
	public void setBottomLeft(String theFieldValue) {
		bottomLeftField.setText(theFieldValue);
	}

	@FXML
	private void onMouseEntered() {
		myLeftInfoPane.setBackground(
				new Background(new BackgroundFill(Color.web(DEFAULT_LEFT_INFO_PANE_COLOR),
						CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	@FXML
	private void onMouseExited() {
		myLeftInfoPane.setBackground(
				new Background(new BackgroundFill(Color.web(ON_HOVER_DEFAULT_INFO_PANE_COLOR),
						CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
}
