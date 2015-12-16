package currencyapp;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class Exchange_account extends User {
	List<Exchange_Op> exchangeOps = new ArrayList<Exchange_Op>();
	static String username;
	String currency;
	String date;
	User user;
	
	public Exchange_account(String username, String currency) {
		// TODO Auto-generated constructor stub
		super(false, username);
	
		this.currency=currency;
	}
	
	public Exchange_account() {
		super(false, username);
	}

	//Create account for the desired currency, each user will have x accounts with a particular currency in each one
	public void Create_Account(String username,String currencySource){
		
	    try {
	    	statement = DBConnect.connect();
	      
	      String sql = " CREATE TABLE IF NOT EXISTS a20358007_"+username+""+currencySource+" "+
	                   "(a_id INTEGER not NULL AUTO_INCREMENT, " +
	                   " currDest VARCHAR(16) NOT NULL, " + 
	                   " valSource VARCHAR(20) NOT NULL, " +
	                   " valDest VARCHAR(20) NOT NULL, " +
	                   " date VARCHAR(32) NOT NULL, "
	                   + "PRIMARY KEY ( a_id ))"; 
		      
	      statement.executeUpdate(sql);
	      System.out.println("Created table a20358007_"+username+""+currencySource+" ");

		//end create table
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());  
	    }  
	    }
	
/*	public static void main(String[] args) throws Exception {
        Exchange_account dao = new Exchange_account();
        //dao.addUser("admin","admin","admin@gmail.com","Jack","Admin");
        dao.Create_Account("user", "EUR");
       // launch(args);
        }*/
	
	//Show all operations done in one particular account, operations cannot be deleted

	public String showAllOperations(String username) {
		String listString=null;
		try {
			ArrayList<String> listName = new ArrayList<String>();
			statement = DBConnect.connect();
		 ResultSet resultSet = statement.executeQuery("select * from dbfp.a20358007_"+""+username+""+""+currency+"");
	     // ResultSet is initially before the first data set	
       while (resultSet.next()) {
		 listName.add(resultSet.getString("a_id")+"\t"+resultSet.getString("currDest")+"   "+resultSet.getString("valSource")+"   "+resultSet.getString("valDest")+"   "+resultSet.getString("date"));
		 }
       for (String s : listName)
       {
           listString += s + "\n";
       }
     	} catch (Exception e) {
		    System.out.println(e.getMessage());  
	    }
		return listString;  
	}
	
}
