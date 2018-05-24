package gui;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ViewMenuButtonController implements Observer {
	
	@FXML
	private Label myTitle;
	
	@FXML
	private Pane myPane;
	
	@FXML
	private void initialize() {
		myTitle.setText("");
		myPane.getStyleClass().add("menuButton");
		myTitle.getStyleClass().add("menuButton");
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

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("received");
		myPane.setStyle("-fx-background: #FFFFFF;");
		myTitle.setStyle("-fx-background: #FFFFFF;");
	}
	
}
