package login;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/** Controls the main application screen */
public class MainViewController {
  @FXML private Button logoutButton;
  @FXML private Label  sessionLabel;
  @FXML private Label  sessionLabel2;
  @FXML private Button nextButton;

  public void initialize() {}
  
  public void initSessionID(final LoginManager loginManager, String sessionID, boolean isadmin, String username) {
    sessionLabel.setText(sessionID);
    sessionLabel2.setText(username);
   logoutButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        loginManager.logout();
      }
    });
   nextButton.setOnAction(new EventHandler<ActionEvent>() {
    	@Override public void handle(ActionEvent event) {
            loginManager.next(username, isadmin);
          }
        });

    }

  }
