package gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ViewMenuButtonController {
	
	@FXML
	private Label myTitle;
	
	@FXML
	private Pane myPane;
	
	@FXML
	private void initialize() {
		myTitle.setText("");
		myPane.getStyleClass().add("myPane");
	}
		
//	
//	@FXML
//	private void onMouseEntered() {
//		myPane.setBackground(
//				new Background(new BackgroundFill(Color.web(AuctionTileViewController.ON_HOVER_DEFAULT_INFO_PANE_COLOR),
//						CornerRadii.EMPTY, Insets.EMPTY)));
//	}
//	
//	@FXML
//	private void onMouseExited() {
//		myPane.setBackground(
//				new Background(new BackgroundFill(Color.web(AuctionTileViewController.DEFAULT_LEFT_INFO_PANE_COLOR),
//						CornerRadii.EMPTY, Insets.EMPTY)));
//	}
	
	public void setText(String theText) {
		myTitle.setText(theText);
		myTitle.setAlignment(Pos.CENTER);
	}
	
}
