package login;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.beans.Statement;
import currencyapp.DBConnect;

/** Controls the login screen */
public class LoginController {
  @FXML private TextField user;
  @FXML private PasswordField password;
  @FXML private Button loginButton;
  @FXML private Label notallowed;

  
  public void initialize() {}
  
  public void initManager(final LoginManager loginManager) {
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        String sessionID = null; 
        boolean admin=false;
        String username=null;
		try {
			sessionID = authorize();
			admin= isAdmin(user);
			username=user.getText();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (sessionID != null) {
          loginManager.authenticated(sessionID,admin,username);
        }
        else{
        	notallowed.setText("Username do not exist and/or wrong password");
        }
      }
    });
  }

  /**
   * Check authorization credentials.
   * 
   * If accepted, return a sessionID for the authorized session
   * otherwise, return null.
 * @throws Exception 
   */   
  private String authorize() throws Exception {
	HashMapper x=new HashMapper();
    return 
    		x.check_pass(user, password) 
            ? generateSessionID() 
            : null;
  }
  
  boolean isAdmin(TextField user){
  String userr = user.getText();
  if(userr.equals("admin")){
 	 return true;}
  else
 	 return false;
  }  
  
  int sessionID = 0;

  private String generateSessionID() {
    sessionID++;
    return "xyzzy - session " + sessionID;
  }
}
