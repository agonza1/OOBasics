package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
//programmer: Alberto Student 
import javax.swing.JOptionPane;

/**
* The Summary class shows a pop up with the overtime paid hours of the employees.
* in a control-break logic.         
*/ 
public class Summary {
	/**
	 * @param emps, User names (Name+Last name format).
	 * @param2 firstVal,secondVal and thirdVal are Strings with the values Name, Hours and Wage from each employee.
	 * @param3 twoDecimal, will define the format of the numbers as 2 decimals.
	 * @param4 line will be the string variable which will save each line of values to be tokenized later.
	 */	
	static ArrayList<String> emps=new ArrayList<>();
	String[] firstVal  = new String[100],
		     secondVal = new String[100],
		     thirdVal = new String[100];
		    DecimalFormat twoDecimal = new DecimalFormat("$0.00");
			String line;
			
public Summary()
{
	int count=0;
	count++;
	System.out.println("Summary #" + count); 
	int index=0;
	double subOverPayAF=0,subOverPayGL=0,subOverPayRest=0,total=0;
	try{
    //Open file and get the different kinds of data
	FileReader file = new FileReader("Overtime.txt");
	BufferedReader buffer = new BufferedReader(file);
	while((line = buffer.readLine()) != null){
		StringTokenizer tokens = new StringTokenizer(line);
		firstVal[index]=(tokens.nextToken()+" "+tokens.nextToken());
		emps.add(firstVal[index]);
	    secondVal[index]=tokens.nextToken();
	    //time
	    thirdVal[index]=tokens.nextToken()+" "+tokens.nextToken();	
	    index++;
	}
	//JOptionPane.showMessageDialog(null, "Data processed");
	String strListAF="A-F LIST\n";
	String strListGL="G-L LIST\n";
	String strListRest="M-Z LIST\n";
	//we saved the names in ArrayList and now we will sort the names and show it in a pop up
	Collections.sort(emps);
	for(String emp:emps){
		char firstLetter=emp.charAt(0);
		//System.out.println(Character.getNumericValue(firstLetter));
		if (9<Character.getNumericValue(firstLetter)&&Character.getNumericValue(firstLetter)<17){
		int x=emps.indexOf(emp);
		strListAF+=emp+" "+"$"+secondVal[x]+"\n";
		subOverPayAF+=Double.parseDouble(secondVal[x]);
		total+=Double.parseDouble(secondVal[x]);
	     }
		else{ if (17<Character.getNumericValue(firstLetter)&& Character.getNumericValue(firstLetter)<21){
			int x=emps.indexOf(emp);
			strListGL+=emp+" "+"$"+secondVal[x]+"\n";
			subOverPayGL+=Double.parseDouble(secondVal[x]);
			total+=Double.parseDouble(secondVal[x]);
		}else{
			int x=emps.indexOf(emp);
			strListRest+=emp+" "+"$"+secondVal[x]+"\n";
			subOverPayRest+=Double.parseDouble(secondVal[x]);
			total+=Double.parseDouble(secondVal[x]);	
		}
		}
	}
 	buffer.close();
 	JOptionPane.showMessageDialog(null,strListAF+"A-F overtime pay..... "+twoDecimal.format(subOverPayAF)+"\n"+"\n"+
 	strListGL+"G-L overtime pay..... "+twoDecimal.format(subOverPayGL)+"\n"+"\n"+strListRest+"M-Z overtime pay..... "+
 			twoDecimal.format(subOverPayRest)+"\n"+"\n"+"Total overtime pay....."+twoDecimal.format(total));
	}catch(Exception e){
		System.out.println(e);}
	}
 	public static void main(String[] args)  
 	{
 		new Summary();
 	}
	
}
