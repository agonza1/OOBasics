package currencyapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnect {
	  private static Connection connect = null;
	  private static Statement statement = null;
	  private int count=0;
	  public static Statement connect() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
		          .getConnection("jdbc:mysql://www.papademas.net/dbfp?"
		              + "user=fpuser&password=510");
	      
	      //create table
	    
	      statement = connect.createStatement();
	      System.out.println("Conected...");

		//end create table
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());  
	    }
		return statement;  
	    }
}
