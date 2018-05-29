package gui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main driver for the AuctionCentral application.
 * @version 2.0 5/29/2018
 */
public final class AuctionCentralMain extends Application {
	
	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}
	
	@Override
	public void start(final Stage thePrimaryStage) throws Exception {
        SessionController.initialize(thePrimaryStage);
        thePrimaryStage.setTitle("Auction Central");
        thePrimaryStage.show();
	}

}
