package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AuctionManager;
import model.User;

public class SessionController {

	private static AuctionManager myManager = new AuctionManager();
	
	private static Stage myStage;
	
	private static User myUser;
	
	private static final String LOGIN_VIEW = "LoginView.fxml";
	
	private static final String USER_VIEW = "UserView.fxml";
	
	public static void initialize(final Stage theStage) {
		myStage = theStage;
		loadScene(LOGIN_VIEW);
	}
	
	public static User getUser() {
		return myUser;
	}
	
	public static AuctionManager getManager() {
		return myManager;
	}
	
	public static void userLogin(User theUser) {
		myUser = theUser;
		loadScene(USER_VIEW);
	}
	
	public static void userLogout() {
		myUser = null;
		loadScene(LOGIN_VIEW);
	}
	
	private static void loadScene(final String theScene) {
		try {
			FXMLLoader loader = new FXMLLoader(SessionController.class.getResource(theScene));
			myStage.setScene(new Scene((Pane) loader.load()));
		} catch (Exception e) {
			if (myStage == null) {
				System.err.println("ViewController stage is null");
			} 
			System.err.println("Unable to load scene: " + theScene);
			e.printStackTrace();
		}
	}
	
}
