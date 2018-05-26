package gui;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ViewMenuButtonController implements Observer {
	
	@FXML
	private Label myTitle;
	
	@FXML
	private AnchorPane myPane;

	private boolean activeWithAuction;
	
	@FXML
	private void initialize() {
		myTitle.setText("");
		myPane.managedProperty().bind(myPane.visibleProperty());
		activeWithAuction = false;
	}

	public void setActiveWithAuction(boolean theFlag) {
		activeWithAuction = theFlag;
	}
	
	public void setText(String theText) {
		myTitle.setText(theText);
		myTitle.setAlignment(Pos.CENTER);
	}
	
	public void setVisible(boolean theFlag) {
		myPane.setVisible(theFlag);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if ((arg1 instanceof String) && ((String) arg1).equals("hasAuction")) {
			setVisible(activeWithAuction);
		}
		
	}
	
}
