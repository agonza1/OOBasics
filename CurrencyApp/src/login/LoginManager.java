package login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;

import currencyapp.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Manages control flow for logins */
public class LoginManager {
  Scene scene1,scene_users;
  static Scene scene2;
  Stage primaryStage;
  Button btn_showusers;
  Stage stage;

  public LoginManager(Scene scene1,Scene scene2) {
    this.scene1 = scene1;
    this.scene2=scene2;
  }

  /**
   * Callback method invoked to notify that a user has been authenticated.
   * Will show the main application screen.
 * @param username 
   */ 
  public void authenticated(String sessionID, boolean isadmin, String username) {
    showMainView(sessionID,isadmin,username);
  }

  /**
   * Callback method invoked to notify that a user has logged out of the main application.
   * Will show the login application screen.
   */ 
  public void logout() {
    showLoginScreen();
  }
  
  public void showLoginScreen() {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("login.fxml")
      );
      scene1.setRoot((Parent) loader.load());
      LoginController controller = 
        loader.<LoginController>getController();
      controller.initManager(this);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void showMainView(String sessionID, boolean isadmin, String username) {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("mainview.fxml")
      );
      scene1.setRoot((Parent) loader.load());
      MainViewController controller = 
        loader.<MainViewController>getController();
      controller.initSessionID(this, sessionID, isadmin, username);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

 void next(String username, boolean isadmin) {
	if (isadmin){
	try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("menuadmin.fxml")
	      );
	      scene1.setRoot((Parent) loader.load());
	      menuadminController controller = 
	    	        loader.<menuadminController>getController();
	    	      controller.init1(this, username);

	    } catch (IOException ex) {
	      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	else{
		try {
		FXMLLoader loader = new FXMLLoader(
		        getClass().getResource("menuuser.fxml")
		      );
		      scene1.setRoot((Parent) loader.load());
		      menuuserController controller = 
		    	        loader.<menuuserController>getController();
		    	      controller.init1(this, username);

		    } catch (IOException ex) {
		      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		    }
 		}
 }
		
 void adduser() {
	 try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("adduser.fxml")
	      );
	      scene1.setRoot((Parent) loader.load());
	      adduserController controller = 
	    	        loader.<adduserController>getController();
	    	      controller.init1(this, "Admin");

	    } catch (IOException ex) {
	      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
 }
 void newOp(String username) {
	 try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("newoperation.fxml")
	      );
	      scene1.setRoot((Parent) loader.load());
	      menuoperationController controller = 
	    	        loader.<menuoperationController>getController();
	    	      controller.init1(this, username);

	    } catch (IOException ex) {
	      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
 }

public void cstatus(String username) {
	try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("statistics.fxml")
	      );
	      scene1.setRoot((Parent) loader.load());
	      statisticsController controller = 
	    	        loader.<statisticsController>getController();
	    	      controller.init1(this, username);

	    } catch (IOException ex) {
	      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
	
}

public void deleteuser() {
	try {
	      FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("deleteuser.fxml")
	      );
	      scene1.setRoot((Parent) loader.load());
	      deleteuserController controller = 
	    	        loader.<deleteuserController>getController();
	    	      controller.init1(this, "Admin");

	    } catch (IOException ex) {
	      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
	
}
}

