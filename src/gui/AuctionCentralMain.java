package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuctionCentralMain extends Application {

	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UserLogin.fxml"));
			Parent root = (Parent) loader.load();
			
			Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AuctionCentral");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(AuctionCentralMain.class.getName())
            		.log(Level.SEVERE, null, ex);
        }
	}

}
