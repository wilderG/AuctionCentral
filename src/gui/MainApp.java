package gui;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionCalendar;
import model.AuctionItem;
import model.AuctionManager;
import model.Bid;
import model.Bidder;
import model.NonProfitContact;
import backend.StorageIO;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * The data as an observable list of bidders.
     */
    private ObservableList<Bidder> bidderData = FXCollections.observableArrayList();
    
    private AuctionManager theManager;
    
    public MainApp() {
    	theManager = new AuctionManager();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Auction Central");

        initRootLayout();

//        showScreenDemo();
        showInfoDemo();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showScreenDemo() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ScreenDemo.fxml"));
            AnchorPane screenDemo = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(screenDemo);
            
            // Give the controller access to the main app.
            LoginController controller = (LoginController) loader.getController();
            controller.setMainApp(this);
            
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showInfoDemo() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("InformationContainer.fxml"));
            FlowPane screenDemo = (FlowPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(screenDemo);
            
            // Give the controller access to the main app.
            InformationContainerController controller = (InformationContainerController) loader.getController();
            controller.setMainApp(this);
            
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObjectProperty<AuctionManager> getManager() {
        return new SimpleObjectProperty<AuctionManager>(theManager);
    }

    public static void main(String[] args) {
        launch(args);
    }
}