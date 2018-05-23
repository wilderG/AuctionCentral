package gui;


import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

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
	
	@FXML
	private AnchorPane rootPane;

	/**
	 * The button used by the user to logout.
	 */
	@FXML
	private Label theLogoutButton;
	
	@FXML
	private ScrollPane myScrollPane;

	@FXML
	private FlowPane subMenuBar;
	
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
	
	@FXML
	private FlowPane menuButtonBar;
	
	@FXML
	private DatePicker myStartRangeDatePicker;
	
	@FXML
	private DatePicker myEndRangeDatePicker;
	
	@FXML
	private Label myDatePickerTitle;
	
	private SimpleObjectProperty<LocalDate> myStartRangeDate;
	
	private SimpleObjectProperty<LocalDate> myEndRangeDate;
	
	/**
	 * Initializes the view by constructing all appropriate view components. 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		myUser = SessionController.getUser();
	//	configureEventListeners();
		updateDisplayName();
		rootPane.getStyleClass().add("rootPane");
		myScrollPane.getStyleClass().add("rootPane");
		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		myScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		subMenuBar.getStyleClass().add("subMenuBar");

		myStartRangeDatePicker.setVisible(false);
		initializeDatePickers();
		myEndRangeDatePicker.setVisible(false);
		myDatePickerTitle.setVisible(false);
		myStartRangeDate = null;
		myEndRangeDate = null;
		
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
	
	public void addMenuButton(final Node theButton) {
		menuButtonBar.getChildren().add(theButton);
	}
	
	/**
	 * Getter for the views scroll pane.
	 * Post-Condition: The object returned will the the one used by the view.
	 * @return The views scroll pane.
	 */
	public ScrollPane getMyScrollPane() {
		return myScrollPane;
	}
	
	public void showDatePicker(InformationContainerViewController infoViewController, AuctionManager myManager) {
		myDatePickerTitle.setVisible(true);
		myStartRangeDatePicker.setVisible(true);
		myEndRangeDatePicker.setVisible(true);
		
//		myStartRangeDate.addListener(new ChangeListener<Boolean>() {
//            public void changed(ObservableValue ov,Boolean old_val, Boolean new_val) {
//                    System.out.println(new_val);
//            }
//        });
	}
	
	private void removeFocusFromStarDatePicker() {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		myStartRangeDatePicker.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                myGridPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
	}
	
	private void addActionsToDatePickers() {
		myStartRangeDatePicker.setOnAction(event -> {
//			myStartRangeDate = myStartRangeDatePicker.getValue();
			myStartRangeDate.set(myStartRangeDatePicker.getValue());
			disableDaysOnMyEndRangeDatePicker();
			System.out.println("The start date chosen is " + myStartRangeDate);
		});
		
		myEndRangeDatePicker.setOnAction(event -> {
//			myEndRangeDate = myEndRangeDatePicker.getValue();
			myEndRangeDate.set(myEndRangeDatePicker.getValue());
			disableDaysOnMyStartRangeDatePicker();
		});
				
	}
	
	private void initializeDatePickers() {
		removeFocusFromStarDatePicker();
		addActionsToDatePickers();
	}
	
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
	                                    myStartRangeDatePicker.getValue().plusDays(1))
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

