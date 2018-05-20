package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import console.ConsoleDriver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Auction;
import model.AuctionManager;
import model.User;

public class InformationContainerController {

  @FXML
  private FlowPane myFlowPane;
  
  /**
   * The constructor.
   * The constructor is called before the initialize() method.
   */
  public InformationContainerController() {
	// TODO Auto-generated constructor stub
  }

  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   */
  @FXML
  private void initialize() {

  }
  
  public void addNode(Node theNode) {
	  myFlowPane.getChildren().add(theNode);
  }
  
  
//  public void loadAuctionInformation(Collection<Auction> theAuctions, final String theScene) {
//	  for (Auction auction: theAuctions) {
//		  FXMLLoader loader = new FXMLLoader(SessionController.class.getResource(theScene));
//	      loader.setLocation(InformationContainerController.class.getResource("auctionTile.fxml"));
//		  try {
//			  auctionTile = (SplitPane) loader.load();
//			  titleField.setText(auction.getName());
//			  LocalDate date = auction.getDate();
//			  dateInfoDay.setText(date.getDayOfMonth() + "");
//			  dateInfoMonthYear.setText(ConsoleDriver.formatDateMonthYear(date));
//			  myFlowPane.getChildren().add(auctionTile);
//		  } catch (IOException e) {
//			  e.printStackTrace();
//		  }
//	  }
//  }

  
}
