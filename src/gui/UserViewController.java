package gui;


import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.ResourceBundle;

import org.junit.experimental.theories.Theories;

import javafx.util.Callback;
import javafx.scene.control.DateCell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
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
	 * The date picker used to allow the user to enter a start date for a range of dates.
	 */
	private DatePicker myStartRangeDatePicker;

	/**
	 * The date picker used to allow the user to enter an end date for a range of dates.
	 */
	private DatePicker myEndRangeDatePicker;
	
	/**
	 * The label used to add an action description to the date pickers.
	 */
	private Label myDatePickerTitle;

	/**
	 * LocalDate used to capture the date chosen by the user using the myStartRangeDatePicker.
	 */
	private LocalDate myStartRangeDate;
	
	/**
	 * LocalDate used to capture the date chosen by the user using the myEndRangeDatePicker.
	 */
	private LocalDate myEndRangeDate;
	
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
		mySubMenuBar.getStyleClass().add("subMenuBar");
		initializeDateObjects();		
		
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
	 * 
	 * Pre-Condition: 
	 * Post-Condition:
	 * @param infoViewController
	 * @param myManager
	 */
	public void showDatePicker(InformationContainerViewController infoViewController, AuctionManager myManager) {
        addActionsToDatePickers(infoViewController, myManager);
        
        mySubMenuBar.getChildren().add(myDatePickerTitle);
        mySubMenuBar.getChildren().add(myStartRangeDatePicker);
        mySubMenuBar.getChildren().add(myEndRangeDatePicker);
		myStartRangeDatePicker.setVisible(true);
		myEndRangeDatePicker.setVisible(true);
		

	}
	
	/**
	 * By default the startDate picker is in focus when the view is loaded.
	 * This method removed focus from the myStartDatePicker and places it on myGridPane
	 * Pre-Condition: myGridPane != null & myStartRangeDatePicker != null
	 * Post-Condition: Focused will be removed from myStartDatePicker on view load.
	 */
	private void removeFocusFromStarDatePicker() {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		myStartRangeDatePicker.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                myGridPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
	}
	
	/**
	 * Adds actions to date picker in order to allow a user to filter auctions by a date range.
	 * 
	 * Pre-Condition: myStartRangeDatePicker != null & myEndRangeDatePicker != null
	 * 			      myStartRangeDate != null & myEndRangeDate != null
	 * Post-Condition: Both date pickers will have actions events for initiating the limit auctions by range method
	 * @param theInfoViewController that contains all the tiles being displayed. 
	 * @param theManager to be used to get auctions within a certain date range.
	 */
	private void addActionsToDatePickers(InformationContainerViewController theInfoViewController, AuctionManager theManager) {
		myStartRangeDatePicker.setOnAction(event -> {
			myStartRangeDate = myStartRangeDatePicker.getValue();
			disableDaysOnMyEndRangeDatePicker();
			System.out.println("The start date chosen is " + myStartRangeDate);
			attemptToGrabAuctionsWithinRange(theInfoViewController, theManager);
		});
		
		myEndRangeDatePicker.setOnAction(event -> {
			myEndRangeDate = myEndRangeDatePicker.getValue();
			disableDaysOnMyStartRangeDatePicker();
			attemptToGrabAuctionsWithinRange(theInfoViewController, theManager);
		});
				
	}
	
	/**
	 * Retrieves auctions within myStartRangeDate and myEndRangeDate is possible, and then using said auctions
	 * displays then using theInfoViewController.
	 * Post-Condition: theInfoViewContainer associated with theInfoViewController will display auctions within
	 * a certain date range if possible
	 * @param theInfoViewController used to display the new auctionTiles if applicable
	 * @param theManager used to retrieve auctions withing a certain date range if applicable
	 */
	private void attemptToGrabAuctionsWithinRange(InformationContainerViewController theInfoViewController, AuctionManager theManager) {
	    Collection<Auction> auctionsWithinRange;
	    if (myStartRangeDate != null && myEndRangeDate != null) {
	        auctionsWithinRange = theManager.getAuctionsWithinRange(myStartRangeDate, myEndRangeDate);
	        theInfoViewController.showAdminAuctions(auctionsWithinRange, theManager);
	    }
	}
	
	private void initializeDateObjects() {
		myStartRangeDatePicker = new DatePicker();
		myStartRangeDatePicker.setPromptText("Start Date");
		
		myEndRangeDatePicker = new DatePicker();
		myEndRangeDatePicker.setPromptText("End Date");
		
		myDatePickerTitle = new Label("Filter Auctions by Date Range:");
		
		myStartRangeDate = null;
		myEndRangeDate = null;
		
		
		
		removeFocusFromStarDatePicker();
	}
	
	/**
	 * After a date has been chosen using the myStartRangeDatePicker, disables and dates in the myEndRangeDatePicker
	 * that might conflict with the date chosen in myStartRangeDatePicker.
	 * Pre-Condition: myStartRangeDatePicker != null
	 * Post-Condition: Dates on myEndRangeDatePicker will be disabled if they are before the value chosen by
	 * myStartRangeDatePicker.
	 */
	private void disableDaysOnMyEndRangeDatePicker() {
		final Callback<DatePicker, DateCell> endDatePickerCellFactory = 
	            new Callback<DatePicker, DateCell>() {
	                @Override
	                public DateCell call(final DatePicker datePicker) {
	                    return new DateCell() {
	                        @Override
	                        public void updateItem(LocalDate item, boolean empty) {
	                            super.updateItem(item, empty);
	                            if (item.isBefore(
	                                    myStartRangeDatePicker.getValue())
	                                ) {
	                                    setDisable(true);
	                                    setStyle("-fx-background-color: #ffc0cb;");
	                            }
	                    }
	                };
	            }
	        };
	        myEndRangeDatePicker.setDayCellFactory(endDatePickerCellFactory);
	        
	}
	
	/**
	 * After a date has been chosen using the myEndRangeDatePicker, disables and dates in the myStartRangeDatePicker
	 * that might conflict with the date chosen in myEndRangeDatePicker.
	 * Pre-Condition: myEndRangeDatePicker != null
	 * Post-Condition: Dates on myStartRangeDatePicker will be disabled if they are after the value chosen by
	 * myEndRangeDatePicker.
	 */
	private void disableDaysOnMyStartRangeDatePicker () {
		final Callback<DatePicker, DateCell> startDatePickerCellFactory = 
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isAfter(
                                        myEndRangeDatePicker.getValue())
                                    ) {
                                        setDisable(true);
                                        setStyle("-fx-background-color: #ffc0cb;");
                                }
                        }
                    };
                }
            };
            myStartRangeDatePicker.setDayCellFactory(startDatePickerCellFactory);
	}

	
}

