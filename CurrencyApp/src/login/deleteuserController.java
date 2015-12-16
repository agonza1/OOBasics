package login;

import java.sql.SQLException;

import currencyapp.Currency_Exchanger;
import currencyapp.User;
import currencyapp.UserDAO;
import currencyapp.UserDAOImp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Controls the delete user screen */

public class deleteuserController {
	 @FXML private TextField  user;
	  @FXML private TextField  firstlastname;
	  @FXML private Button cancelButton;
	  @FXML private Button saveButton;
	  @FXML private ListView<String> listusers;
	  Scene scene_deleteduser;
	  Stage stage;
	  UserDAO userdao;
	  //Initialize for the delete user view
	  
	  public void initialize() {}
	  
	  public void init1(final LoginManager loginManager, String username) {
		    
		    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        loginManager.next(username, true);
		      }
		    });
		    
		    saveButton.setOnAction(new EventHandler<ActionEvent>() {
			      @Override public void handle(ActionEvent event) {
			    	  Currency_Exchanger a=new Currency_Exchanger();
			    	  userdao=new UserDAOImp(username);
					    	  
			    	  try {
			    		  //user is the user introduced, username is the user admin
			    		  String before=a.rowsInTable("users");
			    		  System.out.println(user.getText());
				    	  userdao.deleteUser(user.getText());
				    	  System.out.println(user.getText());	
						if(a.rowsInTable("users").equals(before)){
							stage = new Stage();
							scene_deleteduser = new Scene(new Group(new Text(25, 25, "The introduced user doesn't exist.")));	
						}else{
			    	  stage = new Stage();
						scene_deleteduser = new Scene(new Group(new Text(25, 25, user.getText()+" deleted succesfully.")));}
						System.out.println("Delete Clicked");
					     stage.setScene(scene_deleteduser);
					     stage.initModality(Modality.APPLICATION_MODAL);
					     stage.sizeToScene(); 
					     stage.setTitle("User deleted");
					     stage.show();	
			    	     loginManager.next(username, true);
			    		} catch (Exception e) {
							e.printStackTrace();
						}
			      }
			    });
		    
		    Currency_Exchanger a=new Currency_Exchanger();		    
		    try {
				listusers.getItems().addAll(a.showUsers());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	  }
	  }


