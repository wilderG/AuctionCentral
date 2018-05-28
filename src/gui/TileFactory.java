package gui;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

import console.ConsoleDriver;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Auction;
import model.AuctionItem;
import model.AuctionManager;
import model.Bid;

public class TileFactory {

	private static final String TILE_VIEW = "TileView.fxml";
		
	public static AnchorPane createAuctionTile(final Auction theAuction) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		String itemField = "Number of Items: " + theAuction.getAllItems().size();
		LocalDate date = theAuction.getDate();
		
		controller.setTopRightLabel(theAuction.getName());
		controller.setFirstBottomRightLabel(itemField);
		controller.setTopLeftLabel("" + date.getDayOfMonth());
		controller.setBottomLeft(Formatter.formatDateMonthYear(date));

		return tile;
	}
	
	public static AnchorPane createAdminAuctionTile(final Auction theAuction, AuctionManager theManager,
			InformationContainerViewController theInformationContainerViewController) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		String itemField = "Number of Items: " + theAuction.getAllItems().size();
		LocalDate date = theAuction.getDate();
		
		controller.setTopRightLabel(theAuction.getName());
		controller.setFirstBottomRightLabel(itemField);
		controller.setTopLeftLabel("" + date.getDayOfMonth());
		controller.setBottomLeft(ConsoleDriver.formatDateMonthYear(date)); 
		String doesHaveBids = theAuction.isContaingBids() ? "Yes":"No";
		String bidField = "Has bids: " + doesHaveBids;
		
		
		controller.setSecondBottomRightLabel(bidField);
		controller.setDeleteIcon();
		
		ImageView icon = controller.getDeleteIcon();
		if (!theAuction.isContaingBids()) {
			icon.setOnMouseClicked(event -> {
				if (showConfirmationDialog()) {
					theManager.removeAuction(theAuction);	
					theInformationContainerViewController.removeNode(tile);
				}
			});
			icon.setOnMouseEntered(event -> {
				icon.setImage(new Image("/icons/delete-hover.png"));
			});
			icon.setOnMouseExited(event -> {
				icon.setImage(new Image("/icons/delete.png"));
			});
		} else {
			icon.setImage(new Image("/icons/delete-disabled.png"));
		}


		return tile;
	}
	
	private static boolean showConfirmationDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Please click Yes to confirm.", ButtonType.YES, ButtonType.CANCEL);
		alert.setTitle("Confirm Auction Deletion");
		alert.setHeaderText("Are you sure you want to delete this auction?");
		

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.YES;

	}
	
	public static AnchorPane createItemTile(final AuctionItem theItem) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		controller.setTopRightLabel(theItem.getDescription());
		controller.setTopLeftLabel(
				Formatter.getMoneyFormat(theItem.getMinimumAcceptableBidValue()));
		controller.setBottomLeft("Minimum\nBid");
		
		return tile;
	}
	
	public static AnchorPane createBidTile(final Bid theBid) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		BigDecimal minBid = theBid.getAuctionItem().getMinimumAcceptableBidValue();
		
		controller.setTopRightLabel(theBid.getAuctionItem().getDescription());
		controller.setFirstBottomRightLabel(
				"Minimum Bid: " + Formatter.getMoneyFormat(minBid));
		controller.setTopLeftLabel(Formatter.getMoneyFormat(theBid.getValue()));
		controller.setBottomLeft("My Bid\nAmount");
		
		return tile;
	}
	
	
	
	
	
	
	private static AnchorPane getNewTile(final FXMLLoader theLoader) {
		AnchorPane tile = null;
		try {
			tile = (AnchorPane) theLoader.load();
		} catch (IOException e) {
			System.err.println("Error in Method: getNewTile, Class: TileFactory");
			e.printStackTrace();
		}

		return tile;
	}
	
	private static TileViewController getTileController(final FXMLLoader theLoader) {
		return (TileViewController) theLoader.getController();
	}
	
	private static FXMLLoader getLoader() {
		return new FXMLLoader(TileFactory.class.getResource(TILE_VIEW));
	}
	
}
