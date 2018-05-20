package gui;



import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

public class InformationContainerController {

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

  }
  
  public void addNode(Node theNode) {
	  myFlowPane.getChildren().add(theNode);
  }
  
  
//  public void loadAuctionInformation(Collection<Auction> theAuctions, final String theScene) {
//	  for (Auction auction: theAuctions) {
//		  FXMLLoader loader = new FXMLLoader(SessionController.class.getResource(theScene));
//	      loader.setLocation(InformationContainerController.class.getResource("auctionTile.fxml"));
//		  try {
//			  auctionTile = (SplitPane) loader.load();
//			  titleField.setText(auction.getName());
//			  LocalDate date = auction.getDate();
//			  dateInfoDay.setText(date.getDayOfMonth() + "");
//			  dateInfoMonthYear.setText(ConsoleDriver.formatDateMonthYear(date));
//			  myFlowPane.getChildren().add(auctionTile);
//		  } catch (IOException e) {
//			  e.printStackTrace();
//		  }
//	  }
//  }

  
}
