package currencyapp;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Class for updating and parsing available online currency data and save it into our database
// the implementation currently works with EUR, USD and CAD currencies. A future feature is to add other currencies
//(Right now other currencies can be added manually in the database and the application will work fine)

public class ParseOnlineTable extends JDBC {
	
	//Update currency table of a determined month, used only the first time, later we will use the next method
	   public void updateCurrencies(int month,String lettermonth,String currencysource) throws Exception {
		   String monthformatted = String.format ("%02d", month);
	      String html = "https://www.imf.org/external/np/fin/data/rms_mth.aspx?SelectDate=2015-"+monthformatted+"&reportType=REP";
	      System.out.println(html);
	      try {
	         Document doc = Jsoup.connect(html).get();
	         Elements tableElements = doc.select("table");

	         Elements tableHeaderEles = tableElements.select("thead tr th");
	         System.out.println("headers");
	         for (int i = 0; i < tableHeaderEles.size(); i++) {
	            System.out.println(tableHeaderEles.get(i).text());
	         }
	         System.out.println();

	         Elements tableRowElements = tableElements.select(":not(thead) tr");
	         int x=0;
	         for (int i = 0; i < tableRowElements.size(); i++) {
	            Element row = tableRowElements.get(i);
	            if (i==6){
	            //System.out.println("row");  
		        JDBC dao = new JDBC();
		        List<String> date = new ArrayList<String>();
		        List<String> value = new ArrayList<String>();
		        String currency = new String();
	            Elements rowItems = row.select("td");
	            for (int j = 0; j < 1; j++) {
	               System.out.println(rowItems.get(j).text().substring(58,72));
	               date.add(rowItems.get(j).text().substring(81,rowItems.get(j).text().indexOf(",")));
	               //Go through all the string and parse the values month and day and the currency value
	               for(int k=0;k<rowItems.get(j).text().length(); k++){
	            	   //parsing dates
	               if (rowItems.get(j).text().substring(rowItems.get(j).text().indexOf(",")+7+18*k,rowItems.get(j).text().indexOf(",")+7+18*k+1).equals(lettermonth)){
	            	   date.add(rowItems.get(j).text().substring(rowItems.get(j).text().indexOf(",")+7+18*k,rowItems.get(j).text().indexOf(",")+7+18*k+11));
	               System.out.println(rowItems.get(j).text().substring(rowItems.get(j).text().indexOf(","),rowItems.get(j).text().indexOf(",")+18));
	               }else{
	            	   //parsing values of currencies based on previous dates
	            	   if(rowItems.get(j).text().substring(rowItems.get(j).text().indexOf(",")+7+18*k,rowItems.get(j).text().indexOf(",")+7+18*k+1).equals("J")==false){
	            	   while (x==0){
	            	   currency=rowItems.get(j).text().substring(rowItems.get(j).text().indexOf(",")+7+18*k,rowItems.get(j).text().indexOf(",")+7+18*k+9);
	            	   x=1;}           
	            	   value.add(rowItems.get(j).text().substring(rowItems.get(j).text().indexOf(",")+7+18*k+9,rowItems.get(j).text().indexOf(",")+7+18*k+16));
	            	   value.add(rowItems.get(j).text().substring(rowItems.get(j).text().indexOf(",")+7+18*k+18,rowItems.get(j).text().indexOf(",")+7+18*k+25));
	               }            
	            	   else{
	            	   //Break when the other currency values. If we wanted to add all the currencies we sill keep going with the same process above.
	            	   //Next currency is Japanes that is the reason why we stop when we see a J
	            	   System.out.print("...Next Currency...");
	            	   break;}
	               }
	            }}
	            System.out.println();
	            try{ 
	            dao.createCurrencyTable(currencysource);
	            //dao.clearTable("currencies"+currencysource);
	    		//add new updated values
	            for (int a=0;a<value.size();a++)
	            	//System.out.println(currency +"..."+ Double.parseDouble(value.get(a)) +"..."+ date.get(a)+",2015");
	            dao.insertIntoCurrencyTable(currencysource, Double.parseDouble(value.get(a)), date.get(a));
	            }catch(java.lang.NumberFormatException e){break;}
	         }}

	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   //Updates the table with a particular currently date currency value, only works with USD ERU and CAD currencies
	   public void updateCurrency(String currencysource,String currencydest) { 
		  JDBC dao=new JDBC();
		  String timeStamp = new SimpleDateFormat("MMMMMMMM dd").format(Calendar.getInstance().getTime());
		  boolean usd=false;
		  double val=0.0;
		  if (currencysource.equals(currencydest))
				return;
		  int vals=0;
		    String html = "https://www.imf.org/external/np/fin/data/rms_mth.aspx?SelectDate=2015-12&reportType=REP";
		    System.out.println(html);
		    try {
		       Document doc = Jsoup.connect(html).get();
		       Elements tableElements = doc.select("table");

		       Elements tableHeaderEles = tableElements.select("thead tr th");
		       System.out.println("headers");
		       for (int i = 0; i < tableHeaderEles.size(); i++) {
		          System.out.println(tableHeaderEles.get(i).text());
		       }
		       System.out.println();

		       Elements tableRowElements = tableElements.select(":not(thead) tr");
		       int x=0;
		      // System.out.println(tableRowElements);
		       for (int i = 0; i < tableRowElements.size(); i++) {
		          Element row = tableRowElements.get(i);
		          if (i==6){
		            System.out.println("row");  
			        List<String> monthday = new ArrayList<String>();
			        List<String> value = new ArrayList<String>();
			        String currency = new String();
		          Elements rowItems = row.select("td");
		          Pattern p2 = Pattern.compile("\\d\\d\\,\\s\\d\\d\\d\\d");
		          Matcher m2 = p2.matcher(rowItems.get(0).text());
		          while(m2.find()){
		        	  monthday.add(rowItems.get(0).text().substring(m2.start(), m2.start()+2));
		        	  vals++;}
		          Pattern peur = Pattern.compile("Euro\\s");
		          Matcher meur = peur.matcher(rowItems.get(0).text());
		          meur.find();
		          Pattern pjapan = Pattern.compile("Japanese\\s");
		          Matcher mjapan = pjapan.matcher(rowItems.get(0).text());
		          mjapan.find();
		          Pattern puk = Pattern.compile("U\\.K\\s");
		          Matcher muk = puk.matcher(rowItems.get(0).text());
		          muk.find();
		          Pattern pcanadian = Pattern.compile("Canadian\\s");
		          Matcher mcanadian = pcanadian.matcher(rowItems.get(0).text());
		          mcanadian.find();
		          Pattern pchilean = Pattern.compile("Chilean\\s");
		          Matcher mchilean = pchilean.matcher(rowItems.get(0).text());
		          mchilean.find();
		         // System.out.println(rowItems)
		          if (currencydest.equals("USD")||currencydest.equals("EUR")){
		          if (currencysource.equals("USD")){
		          System.out.println("1");
		          System.out.println(rowItems.get(0).text());
		             Pattern p = Pattern.compile("\\s\\d\\.\\d\\d\\d\\d");
			          Matcher m = p.matcher(rowItems.get(0).text());
			          for(int j=0;j<vals;j++){
			          m.find();}
			          if(m.end()<mjapan.start())
			        	  val=1/Double.parseDouble(rowItems.get(0).text().substring(m.start(), m.end()));
			          
		          }
		          if (currencysource.equals("EUR")){
		             System.out.println(rowItems.get(0).text());
		             Pattern p = Pattern.compile("\\s\\d\\.\\d\\d\\d\\d");
			          Matcher m = p.matcher(rowItems.get(0).text());
			          for(int j=0;j<vals;j++){
			          m.find();}
			          if(m.end()<mjapan.start())
			        	  val=Double.parseDouble(rowItems.get(0).text().substring(m.start(), m.end()));
			          
		          }
		          
		          if (currencysource.equals("JPY")){
		              System.out.println(rowItems.get(0).text());
		              Pattern p = Pattern.compile("\\d\\d\\d\\.\\d\\d\\d\\d");
		 	          Matcher m = p.matcher(rowItems.get(0).text());
		 	          while(m.find()){ 	          
		 	          if(m.start()>mjapan.start() && m.end()<muk.end())
		 	        	 val=Double.parseDouble(rowItems.get(0).text().substring(m.start(), m.end()));
		 	          }
		          }
		 	         if (currencysource.equals("CAD")){
		 	              System.out.println(rowItems.get(0).text());
		 	              Pattern p = Pattern.compile("\\d\\.\\d\\d\\d\\d");
		 	 	          Matcher m = p.matcher(rowItems.get(0).text());
		 	 	          while(m.find()){
		 	 	          if(m.start()>mcanadian.start() && m.end()<mchilean.end())
		 	 	          val=1/Double.parseDouble(rowItems.get(0).text().substring(m.start(), m.end()));
		 	 	          }
		          
		 	         }
		          }
		        
		          }}
		       if(val!=0){
			        deleteCurrency(currencysource,timeStamp);		        
			        dao.insertIntoCurrencyTable(currencysource, val, timeStamp);}
		  
		    } catch(Exception e1){
			  System.out.println(e1);
		  }	        
	      }
	  
	   
	   private void deleteCurrency(String currencysource, String date) throws Exception {
		   statement = DBConnect.connect();
	        String sql = "DELETE FROM dbfp.A20358007_currencies"+currencysource+" WHERE date = '"+ date+"';";
	        statement.executeUpdate(sql);
	       // System.out.println("deleted user from the table..."); 
		
	}
//get last currency value available in the table
	   public double getCurrencyValToUSD(String srcCurrency)throws SQLException{
			String listString = "";
			double val=1.0;
			//check if both values src dest are the same
			
			if (srcCurrency.equals("USD"))
				return 1;
			try {

				statement = DBConnect.connect();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM dbfp.a20358007_currencies"+srcCurrency
					 +" ORDER BY ID DESC;");
		     // As we do not have a currency value update on weekends we will have to check the
			 //last previous value of the currency. We will use compareTo()
	        resultSet.next();	
			//listCurrencies.add(resultSet.getDouble("value"+srcCurrency+"USD"));
		    val=round(resultSet.getDouble("value"+srcCurrency+"USD"),4);
		    System.out.println(val);
	        //System.out.println(dates.toString());
			  } catch (Exception e) {
				    System.out.println(e.getMessage()); 
				    System.out.println("This currency has not been implemented yet.");
				  }
			return val;  
	   }
	   
	   //Overload get currency value of a determined date
			public double getCurrencyValToUSD(String srcCurrency,String date)throws SQLException{
				String listString = "";
				double val=1.0;
				if (srcCurrency.equals("USD"))
					return 1;
				//if(((srcCurrency.equalsIgnoreCase("EUR") && destCurrency.equalsIgnoreCase("USD"))|| (srcCurrency.equalsIgnoreCase("USD"))&& destCurrency.equalsIgnoreCase("EUR"))){
				try {
					ArrayList<Double> listCurrencies = new ArrayList<Double>();
					ArrayList<Integer> dates = new ArrayList<Integer>();
					statement = DBConnect.connect();
				 ResultSet resultSet = statement.executeQuery("select * from dbfp.a20358007_currencies"+srcCurrency);
			     // As we do not have a currency value update on weekends we will have to check the
				 //last previous value of the currency. We will use compareTo()
		        while (resultSet.next()) {		
			     dates.add(resultSet.getString("Date").compareTo(date));
			     if(srcCurrency.equalsIgnoreCase("USD")==false){
				 listCurrencies.add(resultSet.getDouble("value"+srcCurrency+"USD"));
				//System.out.println(resultSet.getString("Date").compareTo(date));
			     if(resultSet.getString("Date").compareTo(date)==0){
			    	 val=round(resultSet.getDouble("value"+srcCurrency+"USD"),4);
			    	 break;
			     }else if(resultSet.getString("Date").compareTo(date)>0){
			    		 val=round(resultSet.getDouble("value"+srcCurrency+"USD"),4);
			    	 }
			     }else{
			    	 listCurrencies.add(resultSet.getDouble("valueUSDEUR"));
				     if(resultSet.getString("Date").compareTo(date)==0){
				     val=round (resultSet.getDouble("valueUSDEUR"),4);
			    	 break;}
				     else if (resultSet.getString("Date").compareTo(date)>0){
				    	 val=round(resultSet.getDouble("valueUSDEUR"),4);
				     }
			     }
				 }
		        //System.out.println(dates.toString());
				  } catch (Exception e) {
					    System.out.println(e.getMessage()); 
					    System.out.println("This currency combination has not been implemented yet.");
					  }  
			return val;
		}
	   
	   //Get exchange value in a determined date
	   public double getCurrencyVal(String srcCurrency,String destCurrency, String date)throws SQLException{
			double valsrc=1.0;
			double valdest=1.0;
			double val=1.0;
			//check if both values src dest are the same
			if (srcCurrency.equals(destCurrency))
				return 1;
	        valsrc=getCurrencyValToUSD(srcCurrency,date);
	        valdest=getCurrencyValToUSD(destCurrency,date);
		    val=valsrc/valdest;
		     
	        //System.out.println(dates.toString());
		return round(val,4);			
		}
	   
		public double getLastCurrencyVal(String srcCurrency, String destCurrency) throws SQLException {
			double valsrc=1.0;
			double valdest=1.0;
			double val=1.0;
			//check if both values src dest are the same
			if (srcCurrency.equals(destCurrency))
				return 1;
	        valsrc=getCurrencyValToUSD(srcCurrency);
	        valdest=getCurrencyValToUSD(destCurrency);
		    val=valsrc/valdest;
		     
	        //System.out.println(dates.toString());
		return round(val,4);			
		}
		
	
	   //Get string of last currency values available to USD (or to EUR if USD)
	   public String getLastCurrencyVals(String srcCurrency,String destCurrency, String date)throws SQLException{
			String listString = "";
			Double val=1.0;
			if (srcCurrency.equals(destCurrency))
				return "Yes, the exchange rate from "+srcCurrency+" to "+destCurrency+" will be allways 1.";			
			try {
				ArrayList<Double> listCurrencies = new ArrayList<Double>();
				ArrayList<String> days = new ArrayList<String>();
				statement = DBConnect.connect();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM dbfp.a20358007_currencies"+srcCurrency
					 +" ORDER BY ID DESC;");
		     // As we do not have a currency value update on weekends we will have to check the
			 //last previous value of the currency. We will use compareTo()
			 
	        while (resultSet.next()) {		
		     days.add(resultSet.getString("Date").substring(resultSet.getString("Date").length()-2));
		     //if change to USD get from value'X'USD
		     if(((destCurrency.equalsIgnoreCase("USD")))){listCurrencies.add(resultSet.getDouble("value"+srcCurrency+"USD"));}
		   //else if change USD EUR get from valueUSDEUR
		     else if((srcCurrency.equalsIgnoreCase("USD"))&& destCurrency.equalsIgnoreCase("EUR")){listCurrencies.add(resultSet.getDouble("valueUSDEUR")); }
			 //System.out.println(listCurrencies.toString());
		     }
	         int i=0;
	         while (i <= listCurrencies.size()){	         
			 if(srcCurrency.equalsIgnoreCase("EUR")||srcCurrency.equalsIgnoreCase("CAD")||srcCurrency.equalsIgnoreCase("USD")){
			 val=round((listCurrencies.get(i)),4);
		     listString+=days.get(i)+"->"+val.toString()+"\t";}	
			 else {System.out.println("Currency not implemented");}
			 i++;
	        }
			 
	        //System.out.println(dates.toString());
			  } catch (Exception e) {
				    System.out.println(e.getMessage()); 
				    System.out.println("This currency combination has not been implemented yet.");
				  }  
		return listString;
		
		}
	   
	   //Round a double method
	   protected Double round(Double value, int places) {
			if (places < 0) throw new IllegalArgumentException();
			BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}

	   } 

	