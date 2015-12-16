package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//programmer: Alberto Student
/**
* The JDBC class creates a database and insert int values.        
*/ 
public class JDBC {
	  private Connection connect = null;
	  private Statement statement = null;

	  public void createDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://www.papademas.net/Inventory?"
	              + "user=dbuser&password=db1");
 	      
	      //create table
	    
	      statement = connect.createStatement();
	      
	      String sql = "CREATE TABLE agonzinventory " +
	                   "(id INTEGER not NULL AUTO_INCREMENT, " +
	                   " cost INTEGER, " + 
	                   " PRIMARY KEY ( id ))"; 

	      statement.executeUpdate(sql);
	      System.out.println("Created table in given database...");

		//end create table
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());  
	    }  
	    }
	  
	  public void insertIntoDataBase() throws Exception {
		    try {
		    	 // This will load the MySQL driver, each DB has its own driver
		       Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			 connect = DriverManager
			          .getConnection("jdbc:mysql://www.papademas.net/Inventory?"
				              + "user=dbuser&password=db1");
				      
		    	  System.out.println("Inserting records into the table...");
		         statement = connect.createStatement();
				      
		         String sql = "INSERT INTO agonzinventory(cost) " +
		                      "VALUES (400)";
		         statement.executeUpdate(sql);
		         sql = "INSERT INTO agonzinventory(cost) " +
		               "VALUES (400)";
		         statement.executeUpdate(sql);
		         sql = "INSERT INTO agonzinventory(cost) " +
			               "VALUES (500)";
			         statement.executeUpdate(sql);
			     sql = "INSERT INTO agonzinventory(cost) " +
				               "VALUES (500)";
				         statement.executeUpdate(sql);
				 sql = "INSERT INTO agonzinventory(cost) " +
					               "VALUES (600)";
					         statement.executeUpdate(sql);
		    
		         System.out.println("Inserted records into the table...");
			  } catch (Exception e) {
				    System.out.println(e.getMessage());  
				  }  
			  }

	  
public static void main(String[] args) throws Exception {
	        JDBC dao = new JDBC();
	        //dao.insertIntoDataBase();
	        dao.createDataBase();
	      }
}

