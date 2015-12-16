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
import java.sql.SQLException;

import currencyapp.Currency_Exchanger;
import currencyapp.DBConnect;

/** Controls the menu admin screen */
public class menuadminController {
  @FXML private Label  sessionLabel;
  @FXML private Button logoutButton;
  @FXML private Button showusersButton;
  @FXML private Button adduserButton;
  @FXML private Button nextButton;
  @FXML private Button deleteuserButton;
  Scene scene_users;
  Stage stage;
  public void initialize() {}
  
  public void init1(final LoginManager loginManager, String username) {
	    sessionLabel.setText(username);
	    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent event) {
	        loginManager.logout();
	      }
	    });
	  
	  showusersButton.setOnAction(new EventHandler<ActionEvent>() {
	@Override public void handle(ActionEvent event) {
		 Currency_Exchanger a=new Currency_Exchanger();
		 stage = new Stage();
			try {
			scene_users = new Scene(new Group(new Text(75, 70, a.showUsers())));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Show users clicked");
		     stage.setScene(scene_users);
		     stage.initModality(Modality.APPLICATION_MODAL);
		     stage.sizeToScene(); 
		     stage.setTitle("Users");
		     stage.show();	
	}
    });
	  adduserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				     loginManager.adduser();
			}
		    });

    
	deleteuserButton.setOnAction(new EventHandler<ActionEvent>() {
    	@Override public void handle(ActionEvent event) {
    		System.out.println("delete user clicked");
    		loginManager.deleteuser();
		  }
        });

  }

}

