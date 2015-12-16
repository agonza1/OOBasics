package inventory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Arrays;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

//programmer: Alberto Student
/**
* The LIFO class is a last in first out stack that we will use to analyze the inventory COGS, profit or turnover.  
*/ 
public class LIFO <T>
{	  
      private static Connection connect = null;
	  private static Statement statement = null;
      private static ResultSet resultSet = null;
   
      private static int count;
      private T[] data;
 
    public LIFO()
    {
        data = (T[]) new Object[5];
        count = 0;
    }
 
    void expandCapacity()
    {
        data = Arrays.copyOf(data, data.length * 2);
    }
 
    void push(T e)
    {
        if (count == data.length)
            expandCapacity();
        data[count++] = e;
    }
 
    T pop() throws Exception
    {
        if (count <= 0)
        {
            throw new Exception("stack empty");
        }
        count--;
        return data[count];
    }
 
    boolean isEmpty()
    {
        return count == 0;
    }
 
    static int size() 
    {
        return count;
    }
    
    public static void main(String[] args) throws Exception
    {
        LIFO<Integer> s = new LIFO<Integer>();
        int pops=0, pushs=0,popsSell=0,initialTotal=0;
        int times=3;
        try {
	    	 // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = (Connection) DriverManager
		          .getConnection("jdbc:mysql://www.papademas.net/Inventory?"
		              + "user=dbuser&password=db1");
		      // Statements allow to issue SQL queries to the database
		      statement = (Statement) connect.createStatement();
		      // Result set gets the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from agonzinventory");
  		     // ResultSet is initially before the first data set			   
		     while (resultSet.next()) {
		       /* column data may be retrieved via name 
	              e.g. resultSet.getString("name");
	              or via the column number which starts at 1
		       e.g. resultSet.getString(1); */
			 int cost = resultSet.getInt("cost");  //retrieve cost 
			 pushs=pushs+cost;
			 s.push(cost); //push cost value onto stack 
			      
    	      System.out.println("Push item : " + s.count + " " + cost);
			} 
	    	    
		       for(int i=0;i<times;i++){//pop values from stack the number defined in times
		       int pop=s.pop();
		       System.out.println("Pop iem : " + s.count + " " + pop);
		       pops=pops+pop;
		       //All the laptops sold are sold by 800
		       popsSell=popsSell+800;
		       }
		       initialTotal=pushs;
		       System.out.println("Cost of Goods Sold: $"+ pops);
		       System.out.println("Profit:             $"+(popsSell-pops));
		       System.out.println("Ending Inventory:   $"+(initialTotal-pops));
		       float turnover=pops/((2*initialTotal-pops)/2);
		       System.out.println("Inventory Turnover: "+turnover);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());  
	    }  

    }
}

