package currencyapp;


import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.*;

public class Main extends LoginInterfacesApplication {
    Stage StageLogin;
    
@Override
public void start(Stage primaryStage) {
		 StageLogin = new Stage();
		 LoginInterfacesApplication login=new LoginInterfacesApplication();
		 try {
			login.start(StageLogin);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}

public static void main(String[] args) {
	//ParseOnlineTable update = new ParseOnlineTable();
	/*try {
		update.updateCurrencies();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	launch(args);
}
}
