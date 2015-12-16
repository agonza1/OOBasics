package currencyapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {
	
   //list is working as a database
   List<User> users;
   List<Exchange_account> exaccs;
   Exchange_account exacc;
   User u;
   String username;
   UserDAO uImp;
protected Connection connect = null;
protected Statement statement = null;

   public UserDAOImp(String username){
      users = new ArrayList<User>();
      exaccs = new ArrayList<Exchange_account>();
      this.username=username;
      if (username.equalsIgnoreCase("Admin"))
      u=new User(true,username);
      else
      u=new User(false,username);
   }
   
   @Override
   public void addAccount(String username,String acc_currency) {
   Exchange_account exchangeAccount=new Exchange_account(username,acc_currency);
   exchangeAccount.Create_Account(username, acc_currency);	
	System.out.println("New Account added: "+username+acc_currency+".");
}
   @Override
   public void createAllAccounts(){
	   uImp=new UserDAOImp(username);
	   uImp.addAccount(username, "EUR");
	   uImp.addAccount(username, "USD");
	   uImp.addAccount(username, "CAD");
   }
   
   @Override
   public Exchange_account getAccount(String username, String currencysrc){
		try {
			ArrayList<String> listName = new ArrayList<String>();
			statement = DBConnect.connect();
		 ResultSet resultSet = statement.executeQuery("USE dbfp; SHOW TABLES like 'a20358007_"+username+currencysrc+"';");
	     // ResultSet is initially before the first data set	
       while (resultSet.next()) {
		 exacc=new Exchange_account(username,currencysrc);
		 System.out.println(resultSet.getString(1));
	     }
		} catch (Exception e) {
		    System.out.println(e.getMessage()); 
		    return null;
		  }	   
	   return exacc;
  }  
   
   
   @Override
   public List<Exchange_account> getAllExchangeAccounts(){
	   exaccs.add(uImp.getAccount(username, "EUR"));
	   exaccs.add(uImp.getAccount(username, "USD"));
	   exaccs.add(uImp.getAccount(username, "CAD"));	   
	return exaccs;	   
   }
      
   
   @Override
   public void deleteAccount(String username,String acc_currency){
	   //admin cannot delete an account
		if (!u.isAdmin()){
			try {
				statement = DBConnect.connect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String sql = "DROP TABLE dbfp.A20358007_"+username+acc_currency+";";
	        try {
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Account deleted: "+username+acc_currency+".");
			}
   }
   
   @Override
   public void deleteAllAccounts(){
	   uImp.deleteAccount(username, "EUR");
	   uImp.deleteAccount(username, "USD");
	   uImp.deleteAccount(username, "CAD");
   }
   
   @Override
   public User getUser(String username) {
		String userdata = null;
		User userr=new User(false, userdata);
		try {
			ArrayList<String> listName = new ArrayList<String>();
			statement = DBConnect.connect();
		 ResultSet resultSet = statement.executeQuery("select * from dbfp.A20358007_users");
	     // ResultSet is initially before the first data set	
        while (resultSet.next()) {
		 
		 System.out.println(resultSet.getString("username"));
		 if (resultSet.getString("username").equalsIgnoreCase(username)){
			 userr=new User(resultSet.getInt("u_id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("fname"), resultSet.getString("lname"));
	         break;
		 }else{
	         System.out.println("User do not exist in the database");
	         return null;
		 }
        }
		} catch (Exception e) {
		    System.out.println(e.getMessage());  
		  }
	   
	   return userr;
   }
   
   @Override
   public String showUser(String username){
	   String userdata = "";
		try {
			ArrayList<String> listName = new ArrayList<String>();
			statement = DBConnect.connect();
		 ResultSet resultSet = statement.executeQuery("select * from dbfp.A20358007_users");
	     // ResultSet is initially before the first data set	
         while (resultSet.next()) {
		 System.out.println(resultSet.getString("username"));
		 if (resultSet.getString("username").equalsIgnoreCase(username)){
		  userdata=("ID:   "+resultSet.getString("u_id")+"\n"+"Username: "+resultSet.getString("username")+"\n"+"Full Name: "+resultSet.getString("fname")+" "+resultSet.getString("lname"));
	      break;
		 }else{
	     System.out.println("User do not exist in the database");
	     userdata=null;
		 }
       }
		} catch (Exception e) {
		    System.out.println(e.getMessage());  
		  }
		return userdata;  
   }
   
   
   @Override
   public void deleteUser(String username) {
    //  users.remove(user.getUsername());
	  if(username.equals("admin")==false){
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
      System.out.println("deleted user from the table..."); }
		else{
			System.out.println("User not allowed to do operation");
		}
      System.out.println("User: " + username + ", deleted from database");
   }
   

   @Override
   public List<User> getAllUsers() {
      return users;
   }

	@Override
	public void addUser(User user) {
		//Implemented in Currency Exchanger
	}

   
}

