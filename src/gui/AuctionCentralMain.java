package gui;


import javafx.application.Application;
import javafx.stage.Stage;

public class AuctionCentralMain extends Application {
	
	public static final String LOGIN_VIEW = "LoginView.FXML";
	
	public static final String USER_VIEW = "UserView.FXML";
	
	private Stage myPrimaryStage;
    

	public static void main(String[] args) {
		Application.launch(AuctionCentralMain.class, (java.lang.String[]) null);		
	}

	@Override
	public void start(final Stage thePrimaryStage) throws Exception {
		SessionController.initialize(thePrimaryStage, this);
        this.myPrimaryStage = thePrimaryStage;
        this.myPrimaryStage.setTitle("Auction Central");
        
        
        myPrimaryStage.show();
        

	}
	
	

}
