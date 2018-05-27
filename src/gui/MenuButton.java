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
	
	/**
	 * Generates a new menu button using the given title with the informationViewController added as an observer. The
	 * generated button will reflect the button found on ViewMenuButton.fxml.
	 * 
	 * Pre-Condition: infoController != null
	 * Post-Condition: A new menu button will be loaded and returned
	 * @param theTitle that will be set for the button.
	 * @param infoController The informationContainterViewController currently used by the system.
	 * @return A new menu button with the given title.
	 */
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
	
	/**
	 * Generates a new menu button using the given title with the informationViewController added as an observer. Button
	 * will be generated using the given active flag. The generated button will reflect the button found on ViewMenuButton.fxml.
	 * 
	 * Pre-Condition: infoController != null
	 * Post-Condition: A new menu button will be loaded and returned
	 * @param theTitle that will be set for the button.
	 * @param infoController The informationContainterViewController currently used by the system.
	 * @param theActiveWithAuctionFlag Flag used to indicate whether or not the user has an auction
	 * @return A new menu button with the given title.
	 */
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
	
	
	/**
	 * Generates a new menu button using the given title. The generated button will reflect the button found on 
	 * ViewMenuButton.fxml.
	 * 
	 * Pre-Condition: infoController != null
	 * Post-Condition: A new menu button will be loaded and returned
	 * @param theTitle that will be set for the button.
	 * @return A new menu button with the given title.
	 */
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
