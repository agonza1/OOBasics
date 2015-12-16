package login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.beans.Statement;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import currencyapp.*;

/** Controls the statistics screen. Some data button */
public class statisticsController {
  @FXML private Label  labelvalues;
  @FXML TextField  textval;
  @FXML private Button okButton;
  @FXML private ChoiceBox<String> srccurrency;
  @FXML private ChoiceBox<String> destcurrency;
  @FXML private LineChart<Double, String> lineChart;
  @FXML private CategoryAxis xAxis;
  private ObservableList<String> monthNames = FXCollections.observableArrayList();
  Scene scene_operationdone;
  Stage stage;
  int both;
  ObservableList<String> days;
  ParseOnlineTable val=new ParseOnlineTable();
  String timeStamp = new SimpleDateFormat("MMMMMMMM dd").format(Calendar.getInstance().getTime());
  
  public void initialize(String username) {}
  
  public void init1(final LoginManager loginManager, String username) {
	    //sessionLabel.setText(username);
	  
	  srccurrency.getItems().addAll("EUR", "USD", "CAD");
	    destcurrency.getItems().addAll("EUR", "USD", "CAD");
	    both=0;
	  
	    
	    srccurrency.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        String src=srccurrency.getValue();
		        both++;
		        if (both>=2){
		        	System.out.println("Execute chart view");
		        	//Execute the chart view
		        	try {
						labelvalues.setText(val.getLastCurrencyVals(srccurrency.getValue(), destcurrency.getValue(), timeStamp));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	try {
						linechart();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}            
		        
		        }
		      }
		    });
	    
	    destcurrency.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        String dest=destcurrency.getValue();
		        both++;
		        if (both>=2){
		        	System.out.println("Execute chart view");
		        	//Execute the chart view
		        	try {
						labelvalues.setText(val.getLastCurrencyVals(srccurrency.getValue(), destcurrency.getValue(), timeStamp));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	try {
						linechart();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		      }
		    });
	    
	    okButton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent event) {
	        loginManager.next(username,false);
	      }
	    });
	    

  }
  
  void linechart() throws SQLException{
  
      try {
			val.updateCurrency(srccurrency.getValue(),destcurrency.getValue());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
      finally{
       

      XYChart.Series<Double, String> series = new XYChart.Series<>();
      

      // Create a XYChart.Data object for each month. Add it to the series.
      for (int i = 1; i < Integer.parseInt((timeStamp.substring(timeStamp.length()-2, timeStamp.length())))+1; i++) {
    	  //System.out.println(Double.toString(val.getCurrencyVal(srccurrency.getValue(), destcurrency.getValue(), timeStamp.substring(0,timeStamp.length()-2)+String.format("%02d",Integer.toString(i)))));
    	  String time=timeStamp.substring(0,timeStamp.length()-2)+String.format("%02d",i);
    	  System.out.println(time);
    	  series.getData().add(new XYChart.Data(timeStamp.substring(0, timeStamp.length()-2)+" "+Integer.toString(i), val.getCurrencyVal(srccurrency.getValue(), destcurrency.getValue(), time)));
    	  System.out.println(val.getCurrencyVal(srccurrency.getValue(), destcurrency.getValue(), time));
      }
 //     xAxis.setLabel("Day of Month");
      lineChart.getData().add(series);
  }
  }
  
  }