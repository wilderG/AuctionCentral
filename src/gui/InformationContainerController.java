package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

import console.ConsoleDriver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Auction;
import model.AuctionManager;
import model.User;

public class InformationContainerController {

  @FXML
  private SplitPane auctionTile;
    
  @FXML
  private TextField titleField;
    
  @FXML
  private Label dateInfoDay;
  
  @FXML
  private Label dateInfoMonthYear;
  
  // Reference to the main application.
  private UserViewController userViewController;

  @FXML
  private FlowPane myFlowPane;
  
  /**
   * The constructor.
   * The constructor is called before the initialize() method.
   */
  public InformationContainerController() {
	// TODO Auto-generated constructor stub
  }

  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   */
  @FXML
  private void initialize() {
	 
//	  for (int count = 0; count < 5; count++) {
//		  FXMLLoader loader = new FXMLLoader();
//	      loader.setLocation(InformationContainerController.class.getResource("auctionTile.fxml"));
//	      try {
//			auctionTile = (SplitPane) loader.load();
//			myFlowPane.getChildren().add(auctionTile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	  }
	

      // Set person overview into the center of root layout.
//      rootLayout.setCenter(screenDemo);
  }
  
  public void loadAuctionInformation(Collection<Auction> theAuctions) {
	  for (Auction auction: theAuctions) {
		  FXMLLoader loader = new FXMLLoader();
	      loader.setLocation(InformationContainerController.class.getResource("auctionTile.fxml"));
		  try {
			  auctionTile = (SplitPane) loader.load();
			  titleField.setText(auction.getName());
			  LocalDate date = auction.getDate();
			  dateInfoDay.setText(date.getDayOfMonth() + "");
			  dateInfoMonthYear.setText(ConsoleDriver.formatDateMonthYear(date));
			  myFlowPane.getChildren().add(auctionTile);
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
	  }
  }

  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param mainApp
   */
  public void setMainApp(UserViewController mainApp) {
      this.userViewController = mainApp;

      
//      titleField.setDisable(true);
   
      // Add observable list data to the table
      
//      personTable.setItems(mainApp.getPersonData());
      
  }
  
  @FXML
  public void loginButtonPressed() {
//  	processUsername();
  }
  
  @FXML
  public void onEnter(ActionEvent ae){
//     processUsername();
  }
  
  
  private void resizeTextFieldToFitContent() {
  	// Set Max and Min Width to PREF_SIZE so that the TextField is always PREF
//      successField.setMinWidth(Region.USE_PREF_SIZE);
//      successField.setMaxWidth(Region.USE_PREF_SIZE);
//      successField.textProperty().addListener((ov, prevText, currText) -> {
          // Do this in a Platform.runLater because of Textfield has no padding at first time and so on
//          Platform.runLater(() -> {
//              Text text = new Text(currText);
//              text.setFont(successField.getFont()); // Set the same font, so the size is the same
//              double width = text.getLayoutBounds().getWidth() // This big is the Text in the TextField
//                      + successField.getPadding().getLeft() + successField.getPadding().getRight() // Add the padding of the TextField
//                      + 2d; // Add some spacing
//              successField.setPrefWidth(width); // Set the width
//              successField.positionCaret(successField.getCaretPosition()); // If you remove this line, it flashes a little bit
//          });
//      });
	}
}
