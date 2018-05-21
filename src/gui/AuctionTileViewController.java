package gui;

import java.time.LocalDate;


import console.ConsoleDriver;
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
public class AuctionTileViewController {
	
	/**
	 * The hex value for the default color of myleftInfoPane.
	 */
	public static final String DEFAULT_LEFT_INFO_PANE_COLOR = "#53B799";
	
	/**
	 * The hex value for the default on hover color of myLeftInfoPane.
	 */
	public static final String ON_HOVER_DEFAULT_INFO_PANE_COLOR = "#59C4A4";

	/**
	 * The string dialog used to present the item count to the user.
	 */
	private static final String ITEM_COUNT_DIALOG = "Number of Items: ";
	
	/**
	 * The string dialog used to present the bid count in an auction
	 */
	private static final String BID_COUNT_DIALOG = "Number of Bids: ";

	/**
	 * The label used for the auctions title.
	 */
	@FXML
	private Label myTitleField;

	/**
	 * Used to display the day that the auction was/will be held.
	 */
	@FXML
	private Label myDateInfoDay;
	
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
	private Label myDateInfoMonthYear;

	/**
	 * Used to display the Item count of the auction.
	 */
	@FXML
	private Label myItemCountField;
	
	@FXML
	private Label myBidCountField;


	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public AuctionTileViewController() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 *
	 * Pre-Condition:
	 * Post-Condition:
	 */
	@FXML
	private void initialize() {
		myTitleField.setText("");
		myDateInfoDay.setText("");
		myDateInfoMonthYear.setText("");
		myItemCountField.setText("");
		myBidCountField.setText("");
		// Only needed for a employee
		myBidCountField.setVisible(false);
	}

	/**
	 * Sets the auctionTiles titleField to the given string. 
	 * 
	 * Pre-Condition: String != null
	 * Post-Condition: The labels title will be set to the given string.
	 * @param theTitle
	 */
	public void setTitle(String theTitle) {
		myTitleField.setWrapText(true);
		
		myTitleField.setText(theTitle);

		myTitleField.setTextAlignment(TextAlignment.LEFT);

	}

	/**
	 * Sets the auctionsTiles ItemInfoCount label using the given count number.
	 * 
	 * Pre-Condition: theCount != null
	 * Post-Condition: The label will be set to ITEM_COUNT_DIALOG + theCount
	 * @param theCount The number of items that the auction has.
	 */
	public void setItemInfoCount(int theCount) {
		myItemCountField.setWrapText(true);
		myItemCountField.setText(ITEM_COUNT_DIALOG + theCount);
	}

	/**
	 * Sets the auctionsTiles bidCountField label using the given count number.
	 *
	 * Pre-Condition: theCount != null
	 * Post-Condition: The label will be set to BID_COUNT_DIALOG + theCount
	 * @param theCount The number of bids that the auction has/
	 */
	public void setBidCountField(int theCount) {
		myBidCountField.setText(BID_COUNT_DIALOG + theCount);
	}
	
	/**
	 * Sets the auctionTiles date labels using the given date.
	 * 
	 * Pre-Condition: theDate != null
	 * Post-Condition: The auctionTile will display the fill date for the auction
	 * @param theDate when the auction was held or will be held.
	 */
	public void setDate(LocalDate theDate) {
		setDayInfo(theDate);
		setMonthYearInfo(theDate);
	}

	/**
	 * Sets auctionTiles day information
	 * 
	 * Pre-Condition: theDate != null
	 * Post-Condition: The auctionTiles dateInfo label's text will be set to 
	 * the day that the auction was/will be held.
	 * @param theDate that the auction was held/will be held.
	 */
	private void setDayInfo(LocalDate theDate) {
		myDateInfoDay.setText(theDate.getDayOfMonth() + "");
	}

	/**
	 * Sets the auctionTIles month and year information.
	 * 
	 * Pre-Condition: theDate != null
	 * Post-Condition: The aucitonTiles dateInfoMonthYear label's text will be set to
	 * the month and year that the auction was/will be held.
	 * @param theDate that the auction was held/will be held. 
	 */
	private void setMonthYearInfo(LocalDate theDate) {
		myDateInfoMonthYear.setText(ConsoleDriver.formatDateMonthYear(theDate));
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
