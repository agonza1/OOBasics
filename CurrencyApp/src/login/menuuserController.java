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
import currencyapp.Exchange_account;
import currencyapp.User;
import currencyapp.UserDAO;
import currencyapp.UserDAOImp;

/** Controls the main menu user screen */
public class menuuserController {
  @FXML private Label  sessionLabel;
//  @FXML private Text  actionusers;
  @FXML private Button logoutButton;
  @FXML private Button stat;
  @FXML private Button newop;
  @FXML private Button myprofile;
  @FXML private Button oldop;
  UserDAO userdao;
  Scene scene_users;
  Scene scene_users2;
  Stage stage;
  public void initialize() {}
  
  public void init1(final LoginManager loginManager, String username) {
	    sessionLabel.setText(username);
	    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent event) {
	        loginManager.logout();
	      }
	    });
	  
	  myprofile.setOnAction(new EventHandler<ActionEvent>() {
	@Override public void handle(ActionEvent event) {	
		userdao=new UserDAOImp(username);
		 stage = new Stage();
			scene_users = new Scene(new Group(new Text(50, 50, userdao.showUser(username))));
			System.out.println("My Profile Clicked");
		     stage.setScene(scene_users);
		     stage.initModality(Modality.APPLICATION_MODAL);
		     stage.sizeToScene(); 
		     stage.setTitle("My Profile");
		     stage.show();	
	}
    });
    newop.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				System.out.println("New Operation clicked");
				     loginManager.newOp(username);
			}
		    });

    
	oldop.setOnAction(new EventHandler<ActionEvent>() {
    	@Override public void handle(ActionEvent event) {
    		String sentence="";
    		System.out.println("old operations clicked");
			 Exchange_account eur=new Exchange_account(username,"EUR");
			 Exchange_account usd=new Exchange_account(username,"USD");
			 Exchange_account cad=new Exchange_account(username,"CAD");
	    	  eur.Create_Account(username,"EUR");
	    	  usd.Create_Account(username,"USD");
	    	  cad.Create_Account(username, "CAD");
			 stage = new Stage();
			 try{
				try{
			 	if (eur.showAllOperations(username).substring(4)!=null)
				sentence = "From an Euro currency account:\n"+eur.showAllOperations(username).substring(4)+"\n";}
				catch(Exception e){sentence = "From an Euro currency account:\n No data...\n";}
				try{
			 	if (usd.showAllOperations(username).substring(4)!=null)
			 		sentence = sentence+ "From a Dollar currency account:\n"+usd.showAllOperations(username).substring(4)+"\n";
				}catch(Exception e){sentence = sentence+ "From a Dollar currency account:\n No data...\n";}
				try{
				 	if (cad.showAllOperations(username).substring(4)!=null)
				 		sentence = sentence+ "From a Canadian Dollar currency account:\n"+cad.showAllOperations(username).substring(4);
					}catch(Exception e){sentence = sentence+ "From a Canadian Dollar currency account:\n No data...\n";}
			 	scene_users = new Scene(new Group(new Text(85, 75, sentence)));
			     stage.setScene(scene_users);
			     stage.initModality(Modality.APPLICATION_MODAL);
			     stage.sizeToScene(); 
			     stage.setTitle("Old Operations");
			     stage.show();
			
			     }catch(java.lang.NullPointerException e){
			     scene_users2 = new Scene(new Group(new Text(75, 70,"No old operations found.")));
			     stage.setScene(scene_users2);
			     stage.initModality(Modality.APPLICATION_MODAL);
			     stage.sizeToScene(); 
			     stage.setTitle("Old Operations");
			     stage.show();}
          }
        });
	
	  stat.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				System.out.println("Statistics clicked");
			     
				loginManager.cstatus(username);
			     
			}
		    });

  }

}

