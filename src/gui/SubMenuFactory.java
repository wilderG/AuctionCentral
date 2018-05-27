package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import model.Auction;
import model.AuctionManager;
import model.Bid;
import model.Bidder;
import model.User;

public class SubMenuFactory {
	
	/**
	 * The date picker used to allow the user to enter a start date for a range of dates.
	 */
	private static DatePicker myStartRangeDatePicker;

	/**
	 * The date picker used to allow the user to enter an end date for a range of dates.
	 */
	private static DatePicker myEndRangeDatePicker;
	
	/**
	 * The label used to add an action description to the date pickers.
	 */
	private static Label myDatePickerTitle;

	/**
	 * LocalDate used to capture the date chosen by the user using the myStartRangeDatePicker.
	 */
	private static LocalDate myStartRangeDate;
	
	/**
	 * LocalDate used to capture the date chosen by the user using the myEndRangeDatePicker.
	 */
	private static LocalDate myEndRangeDate;
	
	/**
	 * The Flow pane that will be used as a container for all the controls added.
	 */
	private static FlowPane mySubMenu;
	
	private static FlowPane mySubMenuContainer;
	
	private static Label myMessageLabel;


	private static final String SUB_MENU_VIEW = "SubMenuView.fxml";
	
	public static void createSubMenu(User theUser, UserViewController theUserView) {
		mySubMenu = theUserView.getMySubMenuBar();
//		mySubMenuContainer = theUserView.getMySubMenuBarContainer();
		mySubMenu.getChildren().clear();
	}
	
	public static void showAdminSubMenu() {
		mySubMenu.getChildren().clear();
		showDatePicker();

		
	}
	
	
	public static void showBidderAuctionSubMenu() {
		mySubMenu.getChildren().clear();
		Button viewAllAuctionsButton = new Button("View All Auctions");
		Button viewAllBiddableAuctions = new Button("View All Biddable Auctions");
		
		
		mySubMenu.getChildren().add(viewAllAuctionsButton);
		mySubMenu.getChildren().add(viewAllBiddableAuctions);
		
		// Remove default focus from the viewAllAuctionsButton
		viewAllAuctionsButton.getParent().requestFocus();
				
		viewAllAuctionsButton.setOnMouseClicked(event -> {
			InformationContainerViewController informationContainerViewController =
					SessionController.getInformationContainerView();
			AuctionManager manager = SessionController.getManager();
			informationContainerViewController.showAuctions(manager.getAllAuctionsSorted());
		});		
		
		viewAllBiddableAuctions.setOnMouseClicked(event -> {
			InformationContainerViewController informationContainerViewController =
					SessionController.getInformationContainerView();
			AuctionManager manager = SessionController.getManager();
			Bidder bidder = (Bidder) SessionController.getUser();
			informationContainerViewController.showAuctions(manager.getAvailableAuctions(bidder));
		});
	}
	
	
	
	public static void showBidderBidSubMenu() {
		mySubMenu.getChildren().clear();
		
		Button viewAllBids = new Button("View All Items");
		
		viewAllBids.setOnMouseClicked(event -> {
			InformationContainerViewController infoViewController = SessionController.getInformationContainerView();
			Bidder user = (Bidder) SessionController.getUser();
			ArrayList<Bid> allBids = new ArrayList<>();
			for (Auction auction: user.getMyAuctions()) {
				allBids.addAll(auction.getAllBidsWithBidder(user));
			}
			infoViewController.showBids(allBids);
		});
		
		mySubMenu.getChildren().add(viewAllBids);
	}
	
	public static void showNonProfitAuctionViewSubMenu() {
		mySubMenu.getChildren().clear();
		
		Button viewAllAuctionsButton = new Button("View All My Auctions");
		
		viewAllAuctionsButton.setOnMouseClicked(event -> {
			InformationContainerViewController infoViewController = SessionController.getInformationContainerView();
			User user = SessionController.getUser();
			infoViewController.showAuctions(user.getMyAuctions());
		});
		
//		Button auctionRequestButton = new Button("Request New Auction");
//		
//		auctionRequestButton.setOnMouseClicked(event -> {
//			InformationContainerViewController infoViewController = SessionController.getInformationContainerView();
//			NonProfitContact user = (NonProfitContact) SessionController.getUser();
//			Auction auction = user.getFutureAuction();
//			if (auction == null) {
//				infoViewController.showNewAuctionRequest();
//			}
//		});
		mySubMenu.getChildren().add(viewAllAuctionsButton);
//		mySubMenu.getChildren().add(auctionRequestButton);
	}
	
	
	

	
	/**
	 * Adds actions to date picker in order to allow a user to filter auctions by a date range.
	 * 
	 * Pre-Condition: myStartRangeDatePicker != null & myEndRangeDatePicker != null
	 * 			      myStartRangeDate != null & myEndRangeDate != null
	 * Post-Condition: Both date pickers will have actions events for initiating the limit auctions by range method
	 */
	private static void addActionsToDatePickers() {
		myStartRangeDatePicker.setOnAction(event -> {
			myStartRangeDate = myStartRangeDatePicker.getValue();
			disableDaysOnMyEndRangeDatePicker();
			System.out.println("The start date chosen is " + myStartRangeDate);
			attemptToGrabAuctionsWithinRange();
		});
		
		myEndRangeDatePicker.setOnAction(event -> {
			myEndRangeDate = myEndRangeDatePicker.getValue();
			disableDaysOnMyStartRangeDatePicker();
			attemptToGrabAuctionsWithinRange();
		});
				
	}
	
	/**
	 * 
	 * Pre-Condition: 
	 * Post-Condition:
	 * @param infoViewController
	 * @param myManager
	 */
	public static void showDatePicker() {
		initializeDateObjects();
        addActionsToDatePickers();
        
        mySubMenu.getChildren().add(myDatePickerTitle);
        mySubMenu.getChildren().add(myStartRangeDatePicker);
        mySubMenu.getChildren().add(myEndRangeDatePicker);
		myStartRangeDatePicker.setVisible(true);
		myEndRangeDatePicker.setVisible(true);
		

	}
	
	/**
	 * By default the startDate picker is in focus when the view is loaded.
	 * This method removed focus from the myStartDatePicker and places it on myGridPane
	 * Pre-Condition: myGridPane != null & myStartRangeDatePicker != null
	 * Post-Condition: Focused will be removed from myStartDatePicker on view load.
	 */
	private static void removeFocusFromStarDatePicker() {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		myStartRangeDatePicker.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
            	myStartRangeDatePicker.getParent().requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
	}
	
	
	
	/**
	 * Retrieves auctions within myStartRangeDate and myEndRangeDate if possible, and then using said auctions
	 * displays then using theInfoViewController.
	 * Post-Condition: theInfoViewContainer associated with theInfoViewController will display auctions within
	 * a certain date range if possible
	 */
	private static void attemptToGrabAuctionsWithinRange() {
	    Collection<Auction> auctionsWithinRange;
	    InformationContainerViewController theinfoController = SessionController.getInformationContainerView();
	    AuctionManager manager = SessionController.getManager();
	    
	    if (myStartRangeDate != null && myEndRangeDate != null) {
	        auctionsWithinRange = manager.getAuctionsWithinRange(myStartRangeDate, myEndRangeDate);
	        theinfoController.showAdminAuctions(auctionsWithinRange, manager);
	    }
	}
	
	private static void initializeDateObjects() {
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
	private static void disableDaysOnMyEndRangeDatePicker() {
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
	private static void disableDaysOnMyStartRangeDatePicker () {
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
	
	/**
	 * Adds a label with a message that will be presented to the user in the subMenu
	 * Post-Condition: A label with the given message will be added to the subMenu
	 * @param theMessage that will be used to create a label
	 */
	public static void addMessage(String theMessage) {
		if (myMessageLabel == null) {
			myMessageLabel = new Label(theMessage);
			mySubMenu.getChildren().add(myMessageLabel);
		} else {
			myMessageLabel.setText(theMessage);
		}

	}
	
	
}
