package gui;

import java.io.IOException;
import java.util.Collection;
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
import model.NonProfitContact;

/**
 * A controller for the informationContainer which is used to hold various auctionTiles.
 * @author Jim Rosales
 * @version May 19, 2018
 */
public final class InformationContainerViewController {

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
	
	/**
	 * File path for a new bid request form.
	 */
	private static String NEW_BID_REQUEST = "NewBidForm.fxml";
	
	/**
	 * File path for a new auction request form.
	 */
	private static String NEW_AUCTION_REQUEST = "NewAuctionForm.fxml";
	
	/**
	 * File path for a new item request form.
	 */
	private static String NEW_ITEM_REQUEST = "NewItemForm.fxml";
	
	/**
	 * File path for a modify system view.
	 */
	private static String MODIFY_SYSTEM_VIEW = "ModifySystemView.fxml";
	
	
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
	 * 
	 * Pre-Condition: theNode != null
	 * Post-Condition: The given node will be added to the nodes flow pane.
	 * @param theNode that will be added to the InformationContainerControllers flow pane.
	 */
	private void addNode(Node theNode) {
		myFlowPane.getChildren().add(theNode);
	}
	
	/**
	 * Removes the given node to the containers flow pane.
	 * 
	 * Pre-Condition: theNode != null
	 * Post-Condition: The given node will be removed from the flow pane.
	 * @param theNode that will be removed from the InformationContainerControllers flow pane.
	 */
	public void removeNode(Node theNode) {
		myFlowPane.getChildren().remove(theNode);
	}

	/**
	 * Clears all nodes in my FlowPane.
	 * Pre-Condition: myFlowPane != null
	 * Post-Condition: All noded in myFlowPane will be removed
	 */
	private void clear() {
		myFlowPane.getChildren().clear();
	}
	
	/**
	 * Using theItems given creates ItemTiles attaching an appropriate event handler for the tiles and adds them to
	 * myFlowPane
	 * Post-Condition: An ItemTile will be created for each AuctionItem in theItems and will be added to myFlowPane
	 * @param theItems who for a new item tile will be created and added to the informationViewContainer.
	 */
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
	 * 
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
					String auctionName = auction.getName() + ": " 
							+ Formatter.formatDate(auction.getDate());
					SubMenuFactory.addMessage(auctionName);
					myActiveAuction = auction;
					showItems(auction.getAllItems());
				} else {
					String message = "The auction for " +  
							auction.getName() + " on " +
							Formatter.formatDate(auction.getDate()) +
							" has no items.";
					SubMenuFactory.addMessage(message);
				}
			});
			
			this.addNode(tile);
		}

		
	}
	
	/**
	 * Loads all the auctions for the given collection into the informationContainerView by creating a new AuctionTile
	 * for each auction.
	 * 
	 * Pre-Condition: theController != null
	 * Post-Condition: An autionTile will be created for each auction and added to
	 * the flowPane of the InformationContainerView
	 * @param theAuctions containing all the Auction objects for an auctionTile will be created. 
	 */
	public void showAuctionBids(final Collection<Auction> theAuctions) {
		this.clear();
		for (Auction auction: theAuctions) {
			AnchorPane tile = TileFactory.createAuctionTile(auction);
			tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				String auctionName = auction.getName() + ": " 
						+ Formatter.formatDate(auction.getDate());
				SubMenuFactory.addMessage(auctionName);
				showBids(auction.getAllBidsWithBidder((Bidder) SessionController.getUser()));
			});
			
			this.addNode(tile);
		}
	}
	
	/**
	 * Loads all the bids in the given collection onto the systems InformationContainerView.
	 * 
	 * Pre-Condition: theController != null
	 * Post-Condition: A bid tile will be created for each bid in the given collection and added to
	 * the flowPane of the InformationContainerView
	 * @param theBids containing all the bids for which a bid tile will be created and added to the 
	 * informationContainerView.
	 */
	public void showBids(final Collection<Bid> theBids) {
		this.clear();
		for (Bid bid: theBids) {
			AnchorPane tile = TileFactory.createBidTile(bid);
			// Override the default onMouseEntered event (currently we don't want one)
			tile.setOnMouseEntered(event -> {
				
			});
			this.addNode(tile);
		}
	}

	/**
	 * Loads all the auctions in the system into the informationContainerView. 
	 * 
	 * Pre-Condition: theController != null
	 * Post-Condition: An autionTile will be created for each auction in the system and added to
	 * the flowPane of the InformationContainerView.
	 * @param theAuctions containing all auction object for which an admin Auction tile will be created.
	 * @param theManager currently being used by the system.
	 */
	public void showAdminAuctions(Collection<Auction> theAuctions, AuctionManager theManager) {
		this.clear();
		for (Auction auction : theAuctions) {
			
			AnchorPane tile = TileFactory.createAdminAuctionTile(auction, theManager, this);
			this.addNode(tile);
		}
		
	}

	/**
	 * Loads the new bid request form into the InformationContainerView.
	 *
	 * pre-condition: a user is permitted to make a bid for the item.
	 * @param theItem is not null
	 */
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

	/**
	 * Loads the new Item request form into the InformationContainerView. 
	 * 
	 * pre-condition: a user is permitted to request a new item. 
	 * @param theAuction is not null
	 */
	public void showNewItemRequest(Auction theAuction) {
		FXMLLoader loader = 
				new FXMLLoader(InformationContainerViewController
						.class.getResource(NEW_ITEM_REQUEST));
		
		this.clear();
		this.addNode(TileFactory.createAuctionTile(theAuction));
		this.addNode(loadForm(loader));
		
		NewItemFormController controller = (NewItemFormController) loader.getController();
		controller.setAuction(((NonProfitContact) 
				SessionController.getUser()).getFutureAuction());
	}
	
	/**
	 * Loads the new Auction request form into the InformationContainerView.
	 * 
	 * pre-condition: a user is permitted to request a new auction.
	 */
	public void showNewAuctionRequest() {
		FXMLLoader loader = 
				new FXMLLoader(InformationContainerViewController
						.class.getResource(NEW_AUCTION_REQUEST));
		
		this.clear();
		this.addNode(loadForm(loader));
	}

	/**
	 * Loads the system modification form into the InformationContainerView.
	 * 
	 * pre-condition: a user is permitted to change the system configuration.
	 */
	public void showModifySystemView() {
		FXMLLoader loader = 
				new FXMLLoader(InformationContainerViewController
						.class.getResource(MODIFY_SYSTEM_VIEW));
		
		this.clear();
		this.addNode(loadForm(loader));
	}
	
	/**
	 * Helper method loads the specified FXML file. If any error occurs while
	 * loading then an error is displayed to the console and null is returned.
	 *
	 * @param theFxmlFile a valid reference to an existing .fxml file
	 * @return a JavaFX Pane presenting the specified file.
	 */
	private Pane loadForm(final FXMLLoader theFxmlFile) {
		Pane form = null;
		try {
			form = (Pane) theFxmlFile.load();
		} catch (IOException e) {
			System.err.println("Error in Method: loadForm, " + 
					"Class: InformationContainerViewController");
		}
		return form;
	}
	
}
