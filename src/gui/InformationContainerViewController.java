package gui;



import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * A controller for the informationContainer which is used to hold various auctionTiles.
 * @author Jim Rosales
 * @version May 19, 2018
 */
public class InformationContainerViewController {

	/**
	 * The flowpane used by the informationContainer to hold auctionTiles.
	 */
	@FXML
	private FlowPane myFlowPane;
	
	/**
	 * Constructor for the controller. It is called before the initialize() method.
	 */
	public InformationContainerViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Adds the given node to the containers flow pane.
	 * Pre-Condition: theNode != null
	 * Post-Condition: The given node will be added to the nodes flow pane.
	 * @param theNode that will be added to the InformationContainerControllers flow pane.
	 */
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
