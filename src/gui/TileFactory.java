package gui;

import java.io.IOException;
import java.time.LocalDate;

import console.ConsoleDriver;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.Auction;
import model.AuctionItem;
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
		controller.setBottomLeft(ConsoleDriver.formatDateMonthYear(date));

		return tile;
	}
	
	public static AnchorPane createItemTile(final AuctionItem theItem) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		controller.setTopRightLabel(theItem.getDescription());
		controller.setTopLeftLabel(theItem.getMinimumAcceptableBidValue().toString());
		controller.setBottomLeft("Minimum\nBid");
		
		return tile;
	}
	
	public static AnchorPane createBidTile(final Bid theBid) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		String minBid = theBid.getAuctionItem().getMinimumAcceptableBidValue().toString();
		
		controller.setTopRightLabel(theBid.getAuctionItem().getDescription());
		controller.setFirstBottomRightLabel(minBid);
		controller.setTopLeftLabel(theBid.getValue().toString());
		controller.setBottomLeft("Bid\nAmount");
		
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
