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
public final class MainMenuButtonFactory {
	/** The location of the menu button fxml file. **/
	private static final String MENU_BUTTON_FXML = "MainMenuButton.fxml";
	
	/** Static class should not be constructed. **/
	private MainMenuButtonFactory() {}
	
	
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
		FXMLLoader loader = getLoader();
		AnchorPane button = getNewMenuButton(loader);
		MainMenuButtonController buttonCtrl = getMenuButtonController(loader);
		
		buttonCtrl.setText(theTitle);
		return button;
	}
	
	
	/**
	 * Generates a new menu button using the given title that observes the current user. If
	 * the ActiveWithAuctionFlag is true then this button will become visible if notified
	 * that an auction has been added, otherwise the button will become invisible. 
	 * 
	 * Post-Condition: A new menu button will be loaded and returned
	 * @param theTitle that will be set for the button.
	 * @param theUser to observe.
	 * @param theActiveWithAuctionFlag Flag used to indicate whether or not the user has an auction
	 * @return A new menu button with the given title.
	 */
	public static AnchorPane newMenuButton(String theTitle, 
			User theUser, boolean theActiveWithAuctionFlag) {
		FXMLLoader loader = getLoader();
		AnchorPane button = getNewMenuButton(loader);
		MainMenuButtonController buttonCtrl = getMenuButtonController(loader);
				
		theUser.addObserver(buttonCtrl);
		buttonCtrl.setText(theTitle);
		buttonCtrl.setActiveWithAuction(theActiveWithAuctionFlag);
		return button;
	}
		
	/** Helper method attempts to load the fxml pane. **/
	private static AnchorPane getNewMenuButton(final FXMLLoader theLoader) {
		AnchorPane button = null;
		try {
			button = (AnchorPane) theLoader.load();
		} catch (IOException e) {
			System.err.println("MenuButton Load Error");
		}
		return button;
	}
	
	private static MainMenuButtonController getMenuButtonController(final FXMLLoader theLoader) {
		return (MainMenuButtonController) theLoader.getController();
	}
	
	private static FXMLLoader getLoader() {
		return new FXMLLoader(MainMenuButtonFactory.class.getResource(MENU_BUTTON_FXML));
	}
}
