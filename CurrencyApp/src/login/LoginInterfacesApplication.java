package login;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.LoginManager;

/** Main application class for the login demo application */
public class LoginInterfacesApplication extends Application {
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws IOException {
    
	  Scene scene = new Scene(new StackPane());
	  Scene scene1 = new Scene(new StackPane());
    
    LoginManager loginManager = new LoginManager(scene, scene1);
    loginManager.showLoginScreen();
    
    stage.setScene(scene);
    stage.show();
  }
}