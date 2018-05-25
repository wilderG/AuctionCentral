package gui;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class ViewMenuButtonController implements Observer {
	
	@FXML
	private Label myTitle;
	

	@FXML
	private void initialize() {
		myTitle.setText("");

	}

	
	public void setText(String theText) {
		myTitle.setText(theText);
		myTitle.setAlignment(Pos.CENTER);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("received");
	}
	
}
