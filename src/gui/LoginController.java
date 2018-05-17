package gui;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.AuctionManager;
import model.User;


import gui.MainApp;;
//import ch.makery.address.model.Person;

public class LoginController {
//    @FXML
//    private TableView<Person> personTable;
//    @FXML
//    private TableColumn<Person, String> firstNameColumn;
//    @FXML
//    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TextField userNameField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private TextField successField;
    
    @FXML
    private TextField titleField;
    
    @FXML
    private SplitPane auctionTile;
    
    @FXML
    private Label dateInfoDay;
    
    @FXML
    private Label dateInfoMonthYear;
    
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
//        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
//        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    	
//    	loginButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
//        successField.setDisable(true);
        
        successField.setVisible(false);
        resizeTextFieldToFitContent();
        
//        titleField.setDisable(true);
     
        // Add observable list data to the table
        
//        personTable.setItems(mainApp.getPersonData());
        
    }
    
    @FXML
    public void loginButtonPressed() {
    	processUsername();
    }
    
    @FXML
    public void onEnter(ActionEvent ae){
       processUsername();
    }
    
    private void processUsername() {
    	String givenUserName = userNameField.getText();
    	AuctionManager manager = mainApp.getManager().get();
    	User user;
    	try {
    		user = manager.getUser(givenUserName);
    		System.out.println("Got the username!");
    		successField.setText("Success! Welcome " + user.getDisplayName());
    		
    		successField.setVisible(true);
    		dateInfoDay.setText("12");
   
    		dateInfoMonthYear.setText("Oct 2018");
		} catch (Exception e) {
			System.out.println("Invalid username. Please try again.");
		}
    	System.out.println("The button was pressed");
    }
    
    private void resizeTextFieldToFitContent() {
    	// Set Max and Min Width to PREF_SIZE so that the TextField is always PREF
        successField.setMinWidth(Region.USE_PREF_SIZE);
        successField.setMaxWidth(Region.USE_PREF_SIZE);
        successField.textProperty().addListener((ov, prevText, currText) -> {
            // Do this in a Platform.runLater because of Textfield has no padding at first time and so on
            Platform.runLater(() -> {
                Text text = new Text(currText);
                text.setFont(successField.getFont()); // Set the same font, so the size is the same
                double width = text.getLayoutBounds().getWidth() // This big is the Text in the TextField
                        + successField.getPadding().getLeft() + successField.getPadding().getRight() // Add the padding of the TextField
                        + 2d; // Add some spacing
                successField.setPrefWidth(width); // Set the width
                successField.positionCaret(successField.getCaretPosition()); // If you remove this line, it flashes a little bit
            });
        });
	}
}