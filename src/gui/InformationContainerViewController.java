package gui;



import java.util.Collection;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bid;
import model.Bidder;

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
	private void addNode(Node theNode) {
		myFlowPane.getChildren().add(theNode);
	}
	
	/**
	 * Removes the given node to the containers flow pane.
	 * Pre-Condition: theNode != null
	 * Post-Condition: The given node will be removed from the flow pane.
	 * @param theNode that will be removed from the InformationContainerControllers flow pane.
	 */
	private void removeNode(Node theNode) {
		myFlowPane.getChildren().remove(theNode);
	}

	private void clear() {
		myFlowPane.getChildren().clear();
	}
	
	
	public void showItems(final Collection<AuctionItem> theItems) {
		this.clear();
		for (AuctionItem item : theItems) {
			this.addNode(TileFactory.createItemTile(item));
		}
	}
	
	/**
	 * Loads all the auctions associated with the current bidder onto an InformationContainerView.
	 * Pre-Condition: theController != null
	 * Post-Condition: An autionTile will be created for each auction associated with the user and added to
	 * the flowPane of the InformationContainerView
	 * @param theController associated with the InformationContainerView where all the bidders information will
	 * be appended. 
	 */
	public void showAuctions(final Collection<Auction> theAuctions) {
		this.clear();
		for (Auction auction: theAuctions) {
			AnchorPane tile = TileFactory.createAuctionTile(auction);
			
			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					if (auction.getAllItems().size() > 0) {
						showItems(auction.getAllItems());
					}
			});
			
			this.addNode(tile);
		}
	}
	
	public void showAuctionBids(final Collection<Auction> theAuctions) {
		this.clear();
		for (Auction auction: theAuctions) {
			AnchorPane tile = TileFactory.createAuctionTile(auction);
			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				showBids(auction.getAllBidsWithBidder((Bidder) SessionController.getUser()));
			});
			
			this.addNode(tile);
		}
	}
	
	public void showBids(final Collection<Bid> theBids) {
		this.clear();
		for (Bid bid: theBids) {
			AnchorPane tile = TileFactory.createBidTile(bid);
			this.addNode(tile);
		}
	}

	public void showAdminAuctions(Collection<Auction> theAuctions, AuctionManager theManager) {
		this.clear();
		for (Auction auction : theAuctions) {
			AnchorPane tile = TileFactory.createAdminAuctionTile(auction);
			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				if (!auction.isContaingBids()) {
					theManager.removeAuction(auction);	
					this.removeNode(tile);
				}
			});
			this.addNode(tile);
		}
		
	}


}
