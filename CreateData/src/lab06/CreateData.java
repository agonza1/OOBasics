package lab06;

import javax.swing.JOptionPane;
import java.io.*;
import java.io.FileWriter;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;    
//Alberto Gonzalez Trastoy Student programmer
public class CreateData {
	static Formatter format=new Formatter();
public static void main(String[] args)
{
  new CreateData();	
}
public CreateData()
/**
 * @param repeat, it is a parameter for saving the user input after asking the user if he wants to write payroll data. 
 * If it is 1 the program will start again asking the user for more new data.
 * @param1 answer, current string value introduced by the user to be converted to int.
 */
{
  int repeat = 1;
  String answer;	
  do 
  {
    Write();
    answer = JOptionPane.showInputDialog ("write payroll " + 
          "data?\n" + "enter 1 to continue or 0 to exit");

    try{
    	repeat = Integer.parseInt(answer);
    }catch(java.lang.NumberFormatException e){
    	//Here the user can also close the program by pressing cancel
    	System.out.println("Program closed");
    	System.exit(1);}
    //Repeat the write method if user writes 1.
  }while(repeat == 1);
  
  System.exit(1);
  System.out.println("Program closed");
}
//Write method asks user to write specific payroll data
//and saves it sequentially in a payroll.txt document
static void Write()
{
  try { 
    String firstLine = null, secondLine=null, thirdLine=null; 
    File check = new File("payroll.txt");  
    FileWriter file;
    if(check.exists()) 
      file = new FileWriter("payroll.txt", true);
    else
      file = new FileWriter("payroll.txt"); 
    BufferedWriter buffer = new BufferedWriter(file);
    int size=0, count = 1; 
    while(size==0 || size == -1){
    	  size=HowMany();  //method HowMany: catch if there is not a number and force user to write again
    	  }

    //while(number == null || number.equals("")) 
    //number = JOptionPane.showInputDialog("how many records?");

    //size = Integer.parseInt(number);
  
     do {
    	 	while(firstLine == null || firstLine.equals("")){ 
    		firstLine = WhatName();    		
    		}
    	    while(secondLine == null || secondLine.equals("")){ 
    	    //secondLine = JOptionPane.showInputDialog("Enter hours");	
    		secondLine = HowManyHours();
    		}
    		while(thirdLine == null || thirdLine.equals("")){ 
    		//thirdLine = JOptionPane.showInputDialog("Enter wage");
    		thirdLine = HowMuch();
    		}
    		buffer.write(String.format("%20s",firstLine));
    		buffer.write(String.format("%20s",secondLine));
    		buffer.write(String.format("%20s",thirdLine));
    		buffer.newLine();
    		count++;
    		firstLine=secondLine=thirdLine="";
    }while(count <= size);

  buffer.close();

     JOptionPane.showMessageDialog(null, "data processed",
     "Result", JOptionPane.PLAIN_MESSAGE );
   }
   catch (IOException e) { System.out.println(e);
   }  
}


/* Method that asks for the number of records.
 * @return the number of records or -1 if the introduced value is not a number.
 */
public static int HowMany(){
	
	String answer = null;
	try{
	answer = JOptionPane.showInputDialog("how many records?");
	//If the return value is null will mean that the user has pressed cancel and the program will be closed.
	if (answer==null){
		System.out.println("Program closed");
		System.exit(1);
	}
	int size = Integer.parseInt(answer);
	return size;
	}catch(java.lang.NumberFormatException | InputMismatchException e2){
		if (answer==null){
			
		}
		System.out.println("The number of records must be an integer number. Please try again.");
		JOptionPane.showMessageDialog(null, "The number of records must be an integer number. Please try again.");
		return -1;
	}	
}
/* Method that asks for the number of hours.
 * @return the number of hours or "" if the introduced value is not a number.
 */
public static String HowManyHours(){	
	String answer;
	try{
	answer = JOptionPane.showInputDialog("Enter hours");
	float size = Float.parseFloat(answer);
	}catch(java.lang.NumberFormatException | InputMismatchException e2){
		System.out.println("The introduced hour value should be a number. Please try again.");
		JOptionPane.showMessageDialog(null, "The introduced value should be a number. Please try again.");
		answer="";
	}
	return answer;
	}
/* Method that asks for the worker wage.
 * @return the worker wage number or "" if the introduced value is not a number.
 */
public static String HowMuch(){	
	String answer;
	try{
	answer = JOptionPane.showInputDialog("Enter wage");
	float size = Float.parseFloat(answer);
	}catch(java.lang.NumberFormatException | InputMismatchException e2){
		System.out.println("The introduced worker wage value should be a number. Please try again.");
		JOptionPane.showMessageDialog(null, "The introduced value should be a number. Please try again.");
		answer="";
	}
	return answer;
	}

public static String WhatName(){	
	String answer;
	try{
	answer = JOptionPane.showInputDialog("Enter name and last name");
	StringTokenizer tokens = new StringTokenizer(answer);
	String a= tokens.nextToken();
	a= tokens.nextToken();
	}catch(java.util.NoSuchElementException e3){
		System.out.println("The introduced value should have 2 words. Please try again.");
		JOptionPane.showMessageDialog(null, "The introduced value should be a number. Please try again.");
		answer="";
	}
	return answer;
	}
}