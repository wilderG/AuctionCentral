package gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
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
	private static final String DEFAULT_LEFT_INFO_PANE_COLOR = "#53B799";
	
	/**
	 * The hex value for the default on hover color of myLeftInfoPane.
	 */
	private static final String ON_HOVER_DEFAULT_INFO_PANE_COLOR = "#59C4A4";

	/**
	 * The label used for the auctions title.
	 */
	@FXML
	private Label myTopRightField;

	/**
	 * Used to display the day that the auction was/will be held.
	 */
	@FXML
	private Label myTopLeftField;
	
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
	private Label myBottomLeftField;

	/**
	 * Used to display the Item count of the auction.
	 */
	@FXML
	private Label myFirstBottomRightField;
	
	/**
	 * Used to display the number of bids in an auction.
	 */
	@FXML
	private Label mySecondBottomRightField;
	
	/**
	 * The delete icon used for the adminAuctionTile
	 */
	@FXML
	private ImageView myDelteIcon;


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
		myTopRightField.setText("");
		myTopLeftField.setText("");
		myBottomLeftField.setText("");
		myFirstBottomRightField.setText("");
		mySecondBottomRightField.setText("");
		mySecondBottomRightField.setVisible(false);
	}

	/**
	 * Sets the auctionTiles titleField to the given string. 
	 * Pre-Condition: String != null
	 * Post-Condition: The labels title will be set to the given string.
	 * @param theTitle
	 */
	public void setTopRightLabel(String theFieldValue) {
		myTopRightField.setWrapText(true);
		
		myTopRightField.setText(theFieldValue);

		myTopRightField.setTextAlignment(TextAlignment.LEFT);

	}

	/**
	 * Sets the auctionsTiles ItemInfoCount label using the given count number.
	 * Pre-Condition: theCount != null
	 * Post-Condition: The label will be set to ITEM_COUNT_DIALOG + theCount
	 * @param theCount The number of items that the auction has.
	 */
	public void setFirstBottomRightLabel(String theFieldValue) {
		myFirstBottomRightField.setWrapText(true);
		myFirstBottomRightField.setText(theFieldValue);
	}
	
	public void setSecondBottomRightLabel(String theFieldValue) {
		mySecondBottomRightField.setWrapText(true);
		mySecondBottomRightField.setText(theFieldValue);
		mySecondBottomRightField.setVisible(true);
	}

	/**
	 * Sets the auctionTiles date labels using the given date.
	 * Pre-Condition: theDate != null
	 * Post-Condition: The auctionTile will display the fill date for the auction
	 * @param theDate when the auction was held or will be held.
	 */
	public void setTopLeftLabel(String theFieldValue) {
		myTopLeftField.setText(theFieldValue);
	}

	/**
	 * Sets auctionTiles day information
	 * Pre-Condition: theDate != null
	 * Post-Condition: The auctionTiles dateInfo label's text will be set to 
	 * the day that the auction was/will be held.
	 * @param theDate that the auction was held/will be held.
	 */
	public void setBottomLeft(String theFieldValue) {
		myBottomLeftField.setText(theFieldValue);
	}

	@FXML
	private void onMouseEntered() {
		myLeftInfoPane.setBackground(
				new Background(new BackgroundFill(Color.web(ON_HOVER_DEFAULT_INFO_PANE_COLOR),
						CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	@FXML
	private void onMouseExited() {
		myLeftInfoPane.setBackground(
				new Background(new BackgroundFill(Color.web(DEFAULT_LEFT_INFO_PANE_COLOR),
						CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
}
