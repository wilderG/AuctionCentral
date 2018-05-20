package gui;

import java.time.LocalDate;


import console.ConsoleDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;

public class AuctionTileController {
	  @FXML
	  private SplitPane auctionTile;
	    
	  @FXML
	  private Label titleField;
	    
	  @FXML
	  private Label dateInfoDay;
	  
	  @FXML
	  private Label dateInfoMonthYear;
	  
	  @FXML
	  private Label myItemCountField;
	  
	  @FXML
	  private FlowPane myFlowPane;
	  
	  /**
	   * The constructor.
	   * The constructor is called before the initialize() method.
	   */
	  public AuctionTileController() {
		// TODO Auto-generated constructor stub
	  }

	  /**
	   * Initializes the controller class. This method is automatically called
	   * after the fxml file has been loaded.
	   */
	  @FXML
	  private void initialize() {

	  }
	  
	  public SplitPane getAuctionTile() {
		  return auctionTile;
	  }
	  
	  public void setTitle(String theTitle) {
		titleField.setWrapText(true);
		titleField.setText(theTitle);
		
		titleField.setTextAlignment(TextAlignment.LEFT);
		
	  }
	  
	  public void setItemInfoCount(int theCount) {
		  myItemCountField.setWrapText(true);
		  myItemCountField.setText(myItemCountField.getText() + theCount);
	  }
	  
	  public void setDate(LocalDate theDate) {
		  setDayInfo(theDate);
		  setMonthYearInfo(theDate);
	  }
	  
	  private void setDayInfo(LocalDate theDate) {
		  dateInfoDay.setText(theDate.getDayOfMonth() + "");
	  }
	  
	  private void setMonthYearInfo(LocalDate theDate) {
		  dateInfoMonthYear.setText(ConsoleDriver.formatDateMonthYear(theDate));
	  }
	  


}
