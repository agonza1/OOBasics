package login;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.Statement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import currencyapp.*;

/** Controls the menu user operation screen */
public class menuoperationController {
  @FXML private Label  sessionLabel;
  @FXML TextField  textval;
  @FXML private Button backButton;
  @FXML private Button saveButton;
  @FXML private ChoiceBox<String> srccurrency;
  @FXML private ChoiceBox<String> destcurrency;
  UserDAO userdao;
  Scene scene_operationdone;
  Stage stage;
 
  
  public void initialize() {}
  
  public void init1(final LoginManager loginManager, String username) {
	    //sessionLabel.setText(username);
	  
	  srccurrency.getItems().addAll("EUR", "USD", "CAD");
	    destcurrency.getItems().addAll("EUR", "USD", "CAD");
	    
	    backButton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent event) {
	        loginManager.next(username,false);
	      }
	    });
	    
	    saveButton.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		    	  System.out.println(textval.getText());
		    	  System.out.println(srccurrency.getValue());
		    	  System.out.println(destcurrency.getValue());
		    	  //Create a new account for the particular currency operation from a user.
		    	  //User user=new User(false, username);
		    	  userdao=new UserDAOImp(username);
		    	  userdao.createAllAccounts();		    	  
		    	  //List<Exchange_account> accounts=userdao.getAllExchangeAccounts();
		    	  Exchange_Op operation=new Exchange_Op();
		    	 // Exchange_account account=userdao.getAccount(username, srccurrency.getValue());
		    	  Double valdest=operation.exchangeCurrency(srccurrency.getValue(),textval.getText(),destcurrency.getValue());
		    	  System.out.println(valdest);
		    	  String timeStamp = new SimpleDateFormat("MMMMMMMM dd HH:mm:ss").format(Calendar.getInstance().getTime());
		    	  Exchange_Op operation2=new Exchange_Op(username, srccurrency.getValue(), Double.parseDouble(textval.getText()), destcurrency.getValue(), round(valdest,4), timeStamp);
		    	 // operation2.Create_Account(username, srccurrency.getValue());
		    	//  operation2.Create_Account(username, destcurrency.getValue());
		    	  operation2.createOp();
		    	  stage = new Stage();
					scene_operationdone = new Scene(new Group(new Text(75, 70, textval.getText()+" "+srccurrency.getValue()+" has been converted to "+destcurrency.getValue()+" succesfully.")));
					System.out.println("Done Clicked");
				     stage.setScene(scene_operationdone);
				     stage.initModality(Modality.APPLICATION_MODAL);
				     stage.sizeToScene(); 
				     stage.setTitle("Operation");
				     stage.show();	
		    	  loginManager.next(username, false);
		      }
		    });

  }

protected Double round(Double value, int places) {
	if (places < 0) throw new IllegalArgumentException();
	BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
}


