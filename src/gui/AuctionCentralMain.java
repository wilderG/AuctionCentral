package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuctionCentralMain extends Application {

	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
            AnchorPane page = (AnchorPane) FXMLLoader.load
            		(AuctionCentralMain.class.getResource("UserLogin.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AuctionCentral");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(AuctionCentralMain.class.getName())
            		.log(Level.SEVERE, null, ex);
        }
	}

}
