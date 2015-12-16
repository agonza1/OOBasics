package currencyapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
//programmer: Alberto Student
/**
* The JDBC class creates a database and insert int values.        
*/ 
public class JDBC {
	  protected Connection connect = null;
	  protected Statement statement = null;
	  private int count=0;
	  public void createCurrencyTable(String currencysource) throws Exception {
	    try {
	    	statement = DBConnect.connect();
	    	String sql="";
	    	if(currencysource.equalsIgnoreCase("USD")){
	    		  sql = " CREATE TABLE IF NOT EXISTS A20358007_currencies"+currencysource+" " +
		                   "(id INTEGER not NULL AUTO_INCREMENT, " +
		                   " Currency VARCHAR(15) NOT NULL, " + 
		                   " valueUSDEUR NUMERIC ( 10,5 ) NOT NULL,"+
		                   " Date VARCHAR(15) NOT NULL,"
		                   + "PRIMARY KEY ( id ))";
	    	}
	    	else{
	      
	       sql = " CREATE TABLE IF NOT EXISTS A20358007_currencies"+currencysource+" " +
	                   "(id INTEGER not NULL AUTO_INCREMENT, " +
	                   " Currency VARCHAR(15) NOT NULL, " + 
	                   " value"+currencysource+"USD NUMERIC ( 10,5 ) NOT NULL,"+
	                   " Date VARCHAR(15) NOT NULL,"
	                   + "PRIMARY KEY ( id ))"; }
	      
	      statement.executeUpdate(sql);
	      System.out.println("Created table in given database...");

		//end create table
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());  
	    }  
	    }
	  
	  public void insertIntoCurrencyTable(String currency,double value, String date) throws Exception {
		    try {
		    	statement = DBConnect.connect();
				      String sql="";
		    	  System.out.println("Inserting records into the table...");
				 String val=String.valueOf(value);
				 count++;
				 if(currency.equalsIgnoreCase("USD")){
					  sql = "INSERT INTO A20358007_currencies"+currency+"(id, Currency, valueUSDEUR, Date)" +
		                     "VALUES (id,'"+currency+"','"+value+"','"+date+"')";
					 }else{
			
		          sql = "INSERT INTO A20358007_currencies"+currency+"(id, Currency, value"+currency+"USD, Date)" +
		                     "VALUES (id,'"+currency+"','"+value+"','"+date+"')";}
		         statement.executeUpdate(sql);
		         /*sql = "INSERT INTO currencies(Currency,valueEURUSD,Date) " +
	             "VALUES ('Euro(1)',1.0976,'3-Nov-15')";
		         statement.executeUpdate(sql);
		         sql = "INSERT INTO currencies(Currency,valueEURUSD,Date) " +
			             "VALUES ('Euro(1)',1.0935,'4-Nov-15')";
			         statement.executeUpdate(sql);*/
		         
		         System.out.println("Inserted records into the table...");
			  } catch (Exception e) {
				    System.out.println(e.getMessage());  
				  }  
			  }
	  public void clearTable(String table) throws Exception {
		    try {
		    	statement = DBConnect.connect();
	    				 //First truncate previous table     
	    		    	  System.out.println("Truncate...");

	    		         String sql = "TRUNCATE TABLE dbfp.a20358007_"+""+table+"";
	    		         statement.executeUpdate(sql);
	    		        System.out.println("Table "+table+" Truncated...");     

			  } catch (Exception e) {
				    System.out.println(e.getMessage());  
				  }  
			  }
	  
	  public String rowsInTable(String table) throws Exception{
		  String num="";
		  statement = DBConnect.connect();
			 ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM dbfp.a20358007_"+table+";");
		     // ResultSet is initially before the first data set	
	        while (resultSet.next()) {
	        	num=resultSet.getString(1);
	        }
		return num;
	  }
	  

/*public static void main(String[] args) throws Exception {
	        JDBC dao = new JDBC();
	        //System.out.println(dao.rowsInTable("users"));
	       // dao.insertIntoDataBase();
	        dao.createCurrencyTable("CAD");
}*/
}



