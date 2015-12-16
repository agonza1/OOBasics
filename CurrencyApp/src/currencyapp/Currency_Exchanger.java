package currencyapp;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Currency_Exchanger extends JDBC {
	protected int id;
	List<User> users = new ArrayList<User>();

	//Create users table in this currency exchanger app

	void createUsersTable() throws Exception {
			    try {
 	      
			      //create table
			    
			      statement = DBConnect.connect();
			      
			      String sql = " CREATE TABLE IF NOT EXISTS A20358007_users " +
			                   "(u_id INTEGER not NULL AUTO_INCREMENT, " +
			                   " username VARCHAR(16) NOT NULL, " +
			                   " password VARCHAR(32) NOT NULL, " + 
			                   " email VARCHAR(32) NOT NULL, " +
			                   " fname VARCHAR(16) NOT NULL, " +
			                   " lname VARCHAR(16) NOT NULL, "
			                   + "PRIMARY KEY ( u_id ))"; 
				      
			      statement.executeUpdate(sql);
			      System.out.println("Created table users...");

				//end create table
			    } catch (Exception e) {
			    	System.out.println(e.getMessage());  
			    }  
			    }	
    	
	//Add users in this currency exchanger app table
	
	public void addUser(String username, String password, String email, String fname, String lname){
    	User u=new User(id, username, password, email, fname, lname);
    	users.add(u);
		try {
			  statement = DBConnect.connect();
	         String sql = "INSERT INTO A20358007_users(u_id, username, password, email, fname, lname)" +
	                     "VALUES (u_id,'"+username+"','"+password+"','"+email+"','"+fname+"','"+lname+"')";
	         statement.executeUpdate(sql);
	         System.out.println("Inserted user into the table...");     
		  } catch (Exception e) {
			    System.out.println(e.getMessage());  
			  }  
		  }

	
	//Show the users from the table
	public String showUsers() throws SQLException{
		String listString = "";
		try {
			ArrayList<String> listName = new ArrayList<String>();
			statement = DBConnect.connect();
		 ResultSet resultSet = statement.executeQuery("select * from dbfp.a20358007_users");
	     // ResultSet is initially before the first data set	
        while (resultSet.next()) {
		 id++;
		 User userr=new User(resultSet.getInt("u_id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("fname"), resultSet.getString("lname"));
		 users.add(userr);
		 listName.add(String.format("%-10s%6s%32s%16s%16s",resultSet.getString("u_id"),resultSet.getString("username"),resultSet.getString("email"),resultSet.getString("fname"),resultSet.getString("lname")));
		 }
        listString=String.format("%-10s%6s%22s%30s%16s\n","u_id","username","email","first name","last name");
        for (String s : listName)
        {
            listString += s + "\n";
        }
		  } catch (Exception e) {
			    System.out.println(e.getMessage());  
			  }  
	return listString;
	
	}
	
	public void deleteUser(String username) {

		  try {
			statement = DBConnect.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	      String sql = "DELETE FROM dbfp.A20358007_users WHERE username = '"+ username+"';";
	      try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	      System.out.println("User: " + username + ", deleted from database");
	}
	   

	
	
	//public static void main(String[] args) throws Exception {
       // Currency_Exchanger dao = new Currency_Exchanger();
       // dao.createUsersTable();
       // dao.addUser("admin","admin","admin@gmail.com","Root","Admin");
        //dao.deleteUser(" ");
        //System.out.println(dao.showUsers());
       // launch(args);
      //  }

}
