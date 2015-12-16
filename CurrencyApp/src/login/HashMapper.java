package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import currencyapp.DBConnect;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HashMapper {
	
	  private static ResultSet resultSet = null;
      
      boolean check_pass(TextField user, PasswordField password){
    	int columns=0;
    	String userr = user.getText();
    	String passwordd = password.getText();
    	System.out.println(passwordd);
    	boolean isThere=false;
    	  try{
        java.sql.Statement statement=DBConnect.connect();
		      // Result set gets the result of the SQL query
  		      //user exists?
		      resultSet = statement
		          .executeQuery("SELECT * FROM dbfp.a20358007_users WHERE username = "+"'"+userr+"'"+" AND password = "+"'"+passwordd+"'");
		      ResultSetMetaData md = resultSet.getMetaData();
		      columns = md.getColumnCount(); 
		      while (resultSet.next()) {
		          HashMap<String,Object> row = new HashMap<String, Object>(columns);
		          row.put(md.getColumnName(1),resultSet.getObject(1));
		          if (row.put(md.getColumnName(1),resultSet.getObject(1))==null){
		        	  break;
		        	  }
		          else{
		        	  isThere=true;
		          }
		      }	      
    }catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	return false;		  
      }
    if (isThere){
    	return true;
    	}
    else {
    	System.out.println("Username do not exist and/or wrong password");
    	return false;
        }
      }
      
      
     void show(String table, String column) {      
      try {
    	    java.sql.Statement statement=DBConnect.connect();
		      // Result set gets the result of the SQL query
		      resultSet = statement
		          .executeQuery("select "+""+column+""+" from "+"dbfp.a20358007_"+table+"");

		      ResultSetMetaData md = resultSet.getMetaData();
		      int columns = md.getColumnCount();
		      
		      //create list
		      List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

		      while (resultSet.next()) {
		          HashMap<String,Object> row = new HashMap<String, Object>(columns);
		          for(int i=1; i<=columns; ++i) {
		              row.put(md.getColumnName(i),resultSet.getObject(i));
		          }
		          list.add(row);
		      }
		      //display hashmap result - key:value pair!
		      for (Object results : list) {
		         @SuppressWarnings("rawtypes")
				HashMap map = (HashMap) results;
		           for (Object key : map.keySet()) {
		               System.out.print(key + " = " + map.get(key) + "; ");
		           }
 
	    } 
      }catch (Exception e) {
	    	System.out.println(e.getMessage());  
	    }    
	 

   }
      /*public static void main(String[] args) throws Exception {
	        HashMapper dao = new HashMapper();
	        dao.show("users", "username");
	        //System.out.println(dao.check_pass("user", "user"));
	        //dao.insertIntoDataBase();
	      //  dao.createDataBase();
}*/
}
