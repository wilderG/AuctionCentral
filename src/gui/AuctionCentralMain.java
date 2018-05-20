package gui;


import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuctionCentralMain extends Application {
	
	public static final String LOGIN_VIEW = "LoginView.FXML";
	
	public static final String USER_VIEW = "UserView.FXML";
	
	private Stage myPrimaryStage;
	
	/**
	 * The main root screen that will be used by the GUI
	 */
    private AnchorPane myRootLayout;
    

	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}

	@Override
	public void start(final Stage thePrimaryStage) throws Exception {
		SessionController.initialize(thePrimaryStage, this);
        this.myPrimaryStage = thePrimaryStage;
        this.myPrimaryStage.setTitle("Auction Central");
        
        
        myPrimaryStage.show();
        
        //initRootLayout();
//        showLoginScreen();
		
//		thePrimaryStage.setTitle("AuctionCentral");
//		thePrimaryStage.show();
		//setMainApp();
	}
	
	/**
     * Initializes the root layout.
     */
//    public void initRootLayout() {
//        try {
//            // Load root layout from fxml file.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(AuctionCentralMain.class.getResource("RootLayout.fxml"));
//            myRootLayout = (AnchorPane) loader.load();
//
//            // Show the scene containing the root layout.
//            Scene scene = new Scene(myRootLayout);
//            myPrimaryStage.setScene(scene);
//            myPrimaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
	
	

}
