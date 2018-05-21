package gui;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.Auction;
import model.AuctionItem;
import model.Bid;

public class TileFactory {

	private static final String TILE_VIEW = "TileView.fxml";
		
	public static AnchorPane auctionTile(final Auction theAuction) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		String itemField = "Number of Items: " + theAuction.getAllItems().size();
		LocalDate date = theAuction.getDate();
		
		controller.setTopRight(theAuction.getName());
		controller.setBottomRight(itemField);
		controller.setTopLeft(Integer.toString(date.getDayOfMonth()));
		controller.setBottomLeft(date.getMonth() + " " + Integer.toString(date.getYear()));

		return tile;
	}
	
	public static AnchorPane itemTile(final AuctionItem theItem) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		controller.setTopRight(theItem.getDescription());
		controller.setTopLeft(theItem.getMinimumAcceptableBidValue().toString());
		controller.setBottomLeft("Minimum\nBid");
		
		return tile;
	}
	
	public static AnchorPane bidTile(final Bid theBid) {
		FXMLLoader loader = getLoader();
		AnchorPane tile = getNewTile(loader);
		TileViewController controller = getTileController(loader);
		
		String minBid = theBid.getAuctionItem().getMinimumAcceptableBidValue().toString();
		
		controller.setTopRight(theBid.getAuctionItem().getDescription());
		controller.setBottomRight(minBid);
		controller.setTopLeft(theBid.getValue().toString());
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
