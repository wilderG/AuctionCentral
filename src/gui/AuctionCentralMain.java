package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class AuctionCentralMain extends Application {

	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}

	@Override
	public void start(final Stage thePrimaryStage) throws Exception {
		ViewController.setStage(thePrimaryStage);
		ViewController.loadLoginScreen();
		thePrimaryStage.setTitle("AuctionCentral");
		thePrimaryStage.show();
	}

}
