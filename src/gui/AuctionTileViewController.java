package gui;

import java.time.LocalDate;


import console.ConsoleDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;

/**
 * A controller for the auctionTileView
 * @author Jim Rosales
 * @version May 19, 2018
 */
public class AuctionTileViewController {

	/**
	 * The string dialog used to present the item count to the user.
	 */
	private static final String ITEM_COUNT_DIALOG = "Number of Items: ";

	/**
	 * The label used for the auctions title.
	 */
	@FXML
	private Label titleField;

	/**
	 * Used to display the day that the auction was/will be held.
	 */
	@FXML
	private Label dateInfoDay;

	/**
	 * Used to display the Month and Year on which the auction was/will be held.
	 */
	@FXML
	private Label dateInfoMonthYear;

	/**
	 * Used to display the Item count of the auction.
	 */
	@FXML
	private Label myItemCountField;


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
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Sets the auctionTiles titleField to the given string. 
	 * Pre-Condition: String != null
	 * Post-Condition: The labels title will be set to the given string.
	 * @param theTitle
	 */
	public void setTitle(String theTitle) {
		titleField.setWrapText(true);
		
		titleField.setText(theTitle);

		titleField.setTextAlignment(TextAlignment.LEFT);

	}

	/**
	 * Sets the auctionsTiles ItemInfoCount label using the given count number.
	 * Pre-Condition: theCount != null
	 * Post-Condition: The label will be set to ITEM_COUNT_DIALOG + theCount
	 * @param theCount The number of items that the auction has.
	 */
	public void setItemInfoCount(int theCount) {
		myItemCountField.setWrapText(true);
		myItemCountField.setText(ITEM_COUNT_DIALOG + theCount);
	}

	/**
	 * Sets the auctionTiles date labels using the given date.
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
	 * Pre-Condition: theDate != null
	 * Post-Condition: The auctionTiles dateInfo label's text will be set to 
	 * the day that the auction was/will be held.
	 * @param theDate that the auction was held/will be held.
	 */
	private void setDayInfo(LocalDate theDate) {
		dateInfoDay.setText(theDate.getDayOfMonth() + "");
	}

	/**
	 * Sets the auctionTIles month and year information.
	 * Pre-Condition: theDate != null
	 * Post-Condition: The aucitonTiles dateInfoMonthYear label's text will be set to
	 * the month and year that the auction was/will be held.
	 * @param theDate that the auction was held/will be held. 
	 */
	private void setMonthYearInfo(LocalDate theDate) {
		dateInfoMonthYear.setText(ConsoleDriver.formatDateMonthYear(theDate));
	}



}
