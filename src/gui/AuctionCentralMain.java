package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class AuctionCentralMain extends Application {

	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}

	@Override
	public void start(final Stage thePrimaryStage) throws Exception {
		SessionController.initialize(thePrimaryStage);
		thePrimaryStage.setTitle("AuctionCentral");
		thePrimaryStage.show();
	}

}
