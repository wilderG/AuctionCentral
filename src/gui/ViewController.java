package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AuctionManager;
import model.User;

public class ViewController {

	private static AuctionManager myManager = new AuctionManager();
	
	private static Stage myStage;
	
	private static final String LOGIN_VIEW = "UserLogin.fxml";
	
	private static final String USER_VIEW = "MainView.fxml";
	
	private static final String TILE_VIEW = "InformationContainer.fxml";
	
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
		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("ViewController stage is null");
			} 
			System.err.println("Unable to load scene: " + theScene);
		}
	}
	
}
