package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import model.AuctionManager;

public class AuctionCentralMain extends Application {

	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("AuctionCentral");
		ViewController.setStage(primaryStage);
		ViewController.setAuctionManager(new AuctionManager());
		ViewController.loadLoginScreen();
		primaryStage.show();
	}

}
