package currencyapp;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Statement;

public class Exchange_Op extends Exchange_account {
	String currencySrc,currencyDest;
	String username;
	double srcval;
	double destval;
	String date;
	Exchange_account exaccount;

	public Exchange_Op(String username, String currencySrc, Double srcval, String currencyDest, Double destval, String date) {
		super(username, currencySrc);
		this.username=username;
		this.currencySrc=currencySrc;
		this.srcval=srcval;
		this.currencyDest=currencyDest;
		this.destval=destval;
		this.date=date;
	}
	
	public Exchange_Op() {
	}
	
	//add new operation into an account
	public void createOp(){
	   Exchange_account account= new Exchange_account();
	   account.Create_Account(username, currencySrc);
	   
	    try {
	    	 // This will load the MySQL driver, each DB has its own driver
	    	statement = DBConnect.connect();
	         String sql = "INSERT INTO "+"a20358007_"+username+""+currencySrc+""+" (a_id, currDest, valSource, valDest, date)" +
	                     "VALUES (a_id,'"+currencyDest+"','"+srcval+"','"+destval+"','"+date+"')";
	         statement.executeUpdate(sql);
	         System.out.println("Inserted operation into the optable...");     
		  } catch (Exception e) {
			    System.out.println(e.getMessage());  
			  }  
		  }
	
	//Show operation from a particular account and user
	public String showOp(String username, String currencySrc) throws SQLException{
		String listString = "";
		try {
			ArrayList<String> listName = new ArrayList<String>();
			statement = DBConnect.connect();
		 ResultSet resultSet = statement.executeQuery("select * from dbfp.a20358007_"+""+username+""+""+currencySrc+"");
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
//Exchange source currency to dest. currency and get the Double value E.g. 100$ -> 120 euro
	public Double exchangeCurrency(String currencysource, String valSource, String currencydest) {
		ParseOnlineTable currency=new ParseOnlineTable();
		double val=0.0;
		try {
		//	String timeStamp = new SimpleDateFormat("MMMMMMMM dd HH:mm:ss").format(Calendar.getInstance().getTime());
			val=currency.getLastCurrencyVal(currencysource, currencydest);
			val=Double.parseDouble(valSource)*val;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}
	
	
	/*public static void main(String[] args) throws Exception {
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Exchange_Op dao = new Exchange_Op("user","eur",1000.0,"USD",1200.2,timeStamp);
        //dao.addUser("admin","admin","admin@gmail.com","Jack","Admin");
        dao.doOp();
        //System.out.println(a);
       // launch(args);
        }*/

}
