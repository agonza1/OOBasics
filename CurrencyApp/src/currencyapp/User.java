package currencyapp;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;


public class User extends Currency_Exchanger {

	List<Exchange_account> exchangeAccounts = new ArrayList<Exchange_account>();
	private String password;
	private String username;
	private String email;
	private String fname;
	private String lname;
	private boolean isAdmin;

	User(int id, String username, String password, String email, String fname, String lname) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.username=username;
		this.password=password;				
		this.email=email;
		this.fname=fname;
		this.lname=lname;
	}
	public User(boolean isAdmin,String username) {
		this.isAdmin=isAdmin;
		this.username=username;
	}
	
    //Add/create account to user
	void createAccount(String acc_currency){
		if (!isAdmin){
		Exchange_account exchangeAccount=new Exchange_account(username,acc_currency);
		exchangeAccounts.add(exchangeAccount);
		}
	}
	
	//Only Admin type user can delete account or operation
	void createAllAccounts(){
		//Now only implemented for EUR USD and CAD
		User u=new User(false,username);
		u.createAccount("EUR");
		u.createAccount("USD");
		u.createAccount("CAD");
		
	}
	
	void deleteAccount(String acc_currency){
		if (!isAdmin){
		Exchange_account exchangeAccount=new Exchange_account(username,acc_currency);
		exchangeAccounts.remove(exchangeAccount);
		System.out.println("Account deleted: "+username+acc_currency+".");
		}
	}
	
	void deleteAllAccounts(){
		User u=new User(false,username);
		u.deleteAccount("EUR");
		u.deleteAccount("USD");
		u.deleteAccount("CAD");
	}
	
	//Show user profile
		public String userprofile(){
	    return "ID:   "+id+"\n"+"Username: "+username+"\n"+"Full Name: "+fname+" "+lname;
		}
		
		// true if it is admin
		public boolean isAdmin(){
			if (isAdmin){
				return true;
			}
			else
				return false;
		}
		
		//Delete user		
		public void deleteUser(String username){
			if(!username.equals("admin")){
			User u=new User(false, username);
			if(isAdmin){
			try {
				statement = DBConnect.connect();
	        String sql = "DELETE FROM dbfp.A20358007_users WHERE username = '"+ username+"';";
	        statement.executeUpdate(sql);
	        System.out.println("deleted user from the table..."); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			u.deleteAllAccounts();}
			else{
				System.out.println("User not allowed to do operation");
				}
			}
			
			else{System.out.println("User not allowed to do operation");}
		
		}		
		
		public List<Exchange_account> getAllExchangeAccounts() {
			       exchangeAccounts=new ArrayList<Exchange_account>();
			       Exchange_account a=new Exchange_account(username, "EUR");
				   exchangeAccounts.add(a);
				   Exchange_account b=new Exchange_account(username, "USD");
				   exchangeAccounts.add(b);
				   Exchange_account c=new Exchange_account(username, "CAD");
				   exchangeAccounts.add(c);
				return exchangeAccounts;
		}
			   
		public Object getUsername() {
			// TODO Auto-generated method stub
			return username;
		}
	
	}

