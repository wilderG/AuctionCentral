package gui;

import java.io.IOException;
import java.util.Collection;
import java.util.Observable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
public class InformationContainerViewController extends Observable {

	/**
	 * The flowpane used by the informationContainer to hold auctionTiles.
	 */
	@FXML
	private FlowPane myFlowPane;
	
	/**
	 * The auction that is currently being accessed by the info display. Used for creating
	 * new forms for user input.
	 */
	private Auction myActiveAuction;
	
	
	private static String NEW_BID_REQUEST = "NewBidForm.fxml";
	
	/**
	 * Constructor for the controller. It is called before the initialize() method.
	 */
	public InformationContainerViewController() {
		myActiveAuction = null;
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
	public void removeNode(Node theNode) {
		myFlowPane.getChildren().remove(theNode);
	}

	private void clear() {
		myFlowPane.getChildren().clear();
	}
	
	
	public void showItems(final Collection<AuctionItem> theItems) {
		this.clear();
		for (AuctionItem item : theItems) {
			
			AnchorPane tile = TileFactory.createItemTile(item);
			
			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				if (SessionController.getUser() instanceof model.Bidder && 
						myActiveAuction.isAllowingNewBid((Bidder) SessionController.getUser())) {
					showNewBidRequest(item);
				}
			});
			
			this.addNode(tile);
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
						myActiveAuction = auction;
						showItems(auction.getAllItems());
					}
			});
			
			this.addNode(tile);
		}
		System.out.println("notify " + countObservers());
		setChanged();
		notifyObservers("showAuctions");
		
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
			
			AnchorPane tile = TileFactory.createAdminAuctionTile(auction, theManager, this);
//			for (Node node: tile.getChildren()) {
//				String id = node.getId();
//				if (id.equals("deleteIcon")) {
//					if (auction.isContaingBids()) {
//						node.setOnMouseClicked(event -> {
//							theManager.removeAuction(auction);	
//							this.removeNode(tile);
//						});
//
//						node.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
//							node.
//						});
//					} else {
//						
//					}
//					node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//							System.out.println("Clicked");
//							if (!auction.isContaingBids()) {
//							theManager.removeAuction(auction);	
//							this.removeNode(tile);
//						}
//					});
//				}
//			}
//			tile.get
//			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//				if (!auction.isContaingBids()) {
//					theManager.removeAuction(auction);	
//					this.removeNode(tile);
//				}
//			});
			this.addNode(tile);
		}
		
	}

	public void showNewBidRequest(AuctionItem theItem) {
		FXMLLoader loader = 
				new FXMLLoader(InformationContainerViewController
						.class.getResource(NEW_BID_REQUEST));
		
		this.clear();
		this.addNode(TileFactory.createItemTile(theItem));
		this.addNode(loadForm(loader));
		
		NewBidFormController controller = (NewBidFormController) loader.getController();
		controller.setAuction(myActiveAuction);
		controller.setItem(theItem);
	}

	public void showNewItemRequest() {
		
	}
	
	
	private Pane loadForm(final FXMLLoader theLoader) {
		Pane form = null;
		try {
			form = (Pane) theLoader.load();
		} catch (IOException e) {
			System.err.println("Error in Method: loadForm, " + 
					"Class: InformationContainerViewController");
			e.printStackTrace();
		}

		return form;
	}
	
}
