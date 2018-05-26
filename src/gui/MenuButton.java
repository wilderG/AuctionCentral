package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.User;

/**
 * This class generates new menu buttons for use in the menuBar
 * @author Jared Malone
 *
 */
public class MenuButton {
	
	public static AnchorPane newMenuButton(String theTitle, 
			InformationContainerViewController infoController) {
		FXMLLoader buttonLoader = new FXMLLoader(SessionController.class.getResource("ViewMenuButton.fxml"));
		AnchorPane button = null;
		try {
			button = (AnchorPane) buttonLoader.load();
		} catch (IOException e) {
			System.err.println("Button Error");
			//e.printStackTrace();
		}	
		
		ViewMenuButtonController buttonCtrl = (ViewMenuButtonController) buttonLoader.getController();
		infoController.addObserver(buttonCtrl);
		buttonCtrl.setText(theTitle);
		return button;
	}
	
	public static AnchorPane newMenuButton(String theTitle, 
			User theUser, boolean theActiveWithAuctionFlag) {
		FXMLLoader buttonLoader = new FXMLLoader(SessionController.class.getResource("ViewMenuButton.fxml"));
		AnchorPane button = null;
		try {
			button = (AnchorPane) buttonLoader.load();
		} catch (IOException e) {
			System.err.println("Button Error");
			//e.printStackTrace();
		}	
		
		ViewMenuButtonController buttonCtrl = (ViewMenuButtonController) buttonLoader.getController();
		theUser.addObserver(buttonCtrl);
		buttonCtrl.setText(theTitle);
		buttonCtrl.setActiveWithAuction(theActiveWithAuctionFlag);
		return button;
	}
	
	
	
	public static AnchorPane newMenuButton(String theTitle) {
		FXMLLoader buttonLoader = new FXMLLoader(SessionController.class.getResource("ViewMenuButton.fxml"));
		AnchorPane button = null;
		try {
			button = (AnchorPane) buttonLoader.load();
		} catch (IOException e) {
			System.err.println("Button Error");
			//e.printStackTrace();
		}	
		
		ViewMenuButtonController buttonCtrl = (ViewMenuButtonController) buttonLoader.getController();
		buttonCtrl.setText(theTitle);
		return button;
	}
}
