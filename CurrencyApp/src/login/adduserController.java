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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import currencyapp.*;

/** Controls the add user screen */
public class adduserController {
  @FXML private TextField  user;
  @FXML private TextField  email;
  @FXML private TextField  password;
  @FXML private TextField  firstlastname;
  @FXML private Button cancelButton;
  @FXML private Button saveButton;
  Scene scene_addeduser;
  Stage stage;
  public void initialize() {}
  //Controller for the add user view
  public void init1(final LoginManager loginManager, String username) {
	    
	    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent event) {
	        loginManager.next(username, true);
	      }
	    });
	    
	    saveButton.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		    	  Currency_Exchanger a=new Currency_Exchanger();
		    	  System.out.println(user.getText()+password.getText()+email.getText()+firstlastname.getText().split(" ")[0]);
		    	  try{
		    	  a.addUser(user.getText(), password.getText(), email.getText(), firstlastname.getText().split(" ")[0], firstlastname.getText().split(" ")[1]);
		    	  stage = new Stage();
					scene_addeduser = new Scene(new Group(new Text(30, 30, user.getText()+" added succesfully.")));
					System.out.println("Done Clicked");
				     stage.setScene(scene_addeduser);
				     stage.initModality(Modality.APPLICATION_MODAL);
				     stage.sizeToScene(); 
				     stage.setTitle("User added");
				     stage.show();	
		    	  loginManager.next(username, true);
		      }catch (Exception e){System.out.println("Please, fill all the white fields.");}
		    	  }
		    });

  }
  }

