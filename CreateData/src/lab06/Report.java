package lab06;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
/**
 * The Report class shows the current users and asks user to introduce         
 * the name of the user he wants to get a report which includes(Name,
 * house, wage, gross pay and overtime pay). Report will be saved in a
 * text file.
 */ 
public class Report { //Alberto Student, Programmer
	/**
	 * @param emps, User names (Name+Last name format)
	 * @param2 firstVal,secondVal and thirdVal are Strings with the values Name, Hours and Wage from each employee
	 * @param3 twoDecimal, will define the format of the numbers as 2 decimals
	 * @param4 userIn, will be the input introduced by the user as a name and last name
	 * @param5 line will be the string variable which will save each line of values to be tokenized later.
	 */	
	static ArrayList<String> emps=new ArrayList<>();
	String[] firstVal  = new String[100],
		     secondVal = new String[100],
		     thirdVal = new String[100];
		    DecimalFormat twoDecimal = new DecimalFormat("$0.00");
		    String userIn="";
			String line;
			
public Report()
{
	int count=0;
	count++;
	System.out.println("New employee request count #" + count); 
	int index=0;
	double grossPay, overPay;
	try{
    //Open file and get the different kinds of data
	FileReader file = new FileReader("payroll.txt");
	BufferedReader buffer = new BufferedReader(file);
	while((line = buffer.readLine()) != null){
		StringTokenizer tokens = new StringTokenizer(line);
		firstVal[index]=(tokens.nextToken()+" "+tokens.nextToken());
		emps.add(firstVal[index]);
	    secondVal[index]=tokens.nextToken();
	    thirdVal[index]=tokens.nextToken();	
	    index++;
	}
	//JOptionPane.showMessageDialog(null, "Data processed");
	String strList="";
	//we saved the names in ArrayList and now we will sort the names and show it in a pop up
	Collections.sort(emps);
	for(String emp:emps){
		strList+=emp+"\n";
	}
 	buffer.close();
 	do{
	userIn=JOptionPane.showInputDialog(null,strList,"Insert full name of the desired employee report");
	//we will capture the input from the user and compare it with the known user names
	//if the name is found we will proceed with the report of the user. 
	//A new pop up will appear if the introduced name does not exist. User will have to cancel for finishing
    if(emps.contains(userIn)){
    	int i=0;
    	FileWriter ReportFile = new FileWriter(FileName(userIn));  
        BufferedWriter buffer1 = new BufferedWriter(ReportFile);
        while(firstVal[i].equals(userIn)==false){
        	i++;
        }
        //report design
        buffer1.write("***************Payroll Report********************");
        buffer1.newLine();
        buffer1.write(String.format("%20s","Employee name:")+String.format("%20s",firstVal[i]));
        buffer1.newLine();
		buffer1.write(String.format("%20s","Hours:")+String.format("%20s",secondVal[i]));
		buffer1.newLine();
		double wage=Double.parseDouble(thirdVal[i]);
		buffer1.write(String.format("%20s","Wages:")+String.format("%20s",twoDecimal.format(wage)));
		buffer1.newLine();
		if(Double.parseDouble(secondVal[i])<40){
	    	grossPay = Double.parseDouble(secondVal[i])*Double.parseDouble(thirdVal[i]);
	    	overPay=0.0;
	    }
	    else{
	    	double extra = Double.parseDouble(secondVal[i])-40;
	    	overPay=1.5*extra*Double.parseDouble(thirdVal[i]);
	    	grossPay = 40*Double.parseDouble(thirdVal[i])+overPay;
	    }
		buffer1.write(String.format("%20s","Gross pay:")+String.format("%20s",twoDecimal.format(grossPay)));
		buffer1.newLine();
		buffer1.write(String.format("%20s","Overtime Pay:")+String.format("%20s",twoDecimal.format(overPay))+"(Included in gross pay)");
		buffer1.newLine();
	    buffer1.write("*************************************************");
	    buffer1.close();
    }else if (userIn==null){
    	break;
    	}else{
    	JOptionPane.showMessageDialog(null, "Employee’s name is not in the given list and their payroll information cannot be located.");
    }
    
	}while(emps.contains(userIn)==false);}
catch (IOException e ) { System.out.println(e); }
}

     
//Function for creating the folder name with first name initial + last name
String FileName(String name){
	String[] n=new String[2];
	n=name.split(" ");	
	return n[0].charAt(0)+n[1]+".txt";
}


public static void main(String[] args)  
{
	new Report();
}
}

