package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AuctionManager;
import model.User;

public class ViewController {

	private static AuctionManager myManager;
	
	private static Stage myStage;
	
	private static final String LOGIN_VIEW = "UserLogin.fxml";
	
	private static final String USER_VIEW = "MainView.fxml";
	
	public static void setAuctionManager(final AuctionManager theManager) {
		myManager = theManager;
	}
	
	public static void setStage(final Stage theStage) {
		myStage = theStage;
	}
	
	public static void loadLoginScreen() {
		UserLoginController userLoginController = new UserLoginController(myManager);
		loadScene(userLoginController, LOGIN_VIEW);
	}
	
	public static void loadUserScreen(User theUser) {
		MainViewController mainViewController = new MainViewController(theUser, myManager);
		loadScene(mainViewController, USER_VIEW);
	}
	
	private static void loadScene(Object theController, final String theScene) {
		try {
			FXMLLoader loader = new FXMLLoader(ViewController.class.getResource(theScene));
			loader.setController(theController);
			myStage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			System.err.println("Unable to load scene: " + theScene);
			e.printStackTrace();
		}
	}
	
}
