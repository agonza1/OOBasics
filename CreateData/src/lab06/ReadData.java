package lab06;


import javax.swing.JOptionPane;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;   
 
public class ReadData { //Alberto Student, Programmer
public ReadData ()
{
try {  
  String[] firstVal  = new String[100],
           secondVal = new String[100],
           thirdVal = new String[100];
  		     
  double hours[] = new double[100], wages[] = new double[100],grossPay[] = new double[100],overPay[] = new double[100];
  DecimalFormat twoDecimal = new DecimalFormat("$0.00");
  int index;
  for (index = 0; index < 100; index++) {
  firstVal[index] = "";
  secondVal[index] = "";
  thirdVal[index ] = "";
  hours[index] = 0.0;
  wages[index]= 0.0;
  grossPay[index]= 0.0;
  overPay[index]= 0.0;
}
  FileReader file = new FileReader("payroll.txt");
  BufferedReader buffer = new BufferedReader(file);
  index = 0;
  String line;
   
  while((line = buffer.readLine()) != null)
  {
	//line.split(" ");
	StringTokenizer tokens = new StringTokenizer(line);
	firstVal[index] = tokens.nextToken()+" "+tokens.nextToken();
    secondVal[index] = tokens.nextToken();
    thirdVal[index] = tokens.nextToken();
      
    hours[index] = Double.parseDouble(secondVal[index]); 
    wages[index] = Double.parseDouble(thirdVal[index]);
    if(hours[index]<40){
    	grossPay[index] = hours[index]*wages[index];
    	overPay[index]=0;
    }
    else{
    	double extra = hours[index]-40;
    	overPay[index]=1.5*extra*wages[index];
    	grossPay[index] = 40*wages[index]+overPay[index];
    }
    JOptionPane.showMessageDialog(null, "Name: "+firstVal[index] + "\n"
     + "Hours: "+hours[index] + "\n" + "Wage: "+twoDecimal.format(wages[index])+ "\n" + "Gross Pay: "+twoDecimal.format(grossPay[index]), "Result",
     JOptionPane.PLAIN_MESSAGE );
    WriteOver(firstVal[index],overPay[index]);
 
    index++;
  }

  buffer.close();
  System.exit(0);
}
catch (IOException e ) { System.out.println(e); }        
 }

static void WriteOver(String name, double overtime)
{
  DecimalFormat twoDecimal = new DecimalFormat("0.00");
  try { 
	String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    File check = new File("Overtime.txt");  
    FileWriter file;
    if(check.exists()){ 
      file = new FileWriter("Overtime.txt", true);}
    else{
      file = new FileWriter("Overtime.txt"); }
    BufferedWriter buffer = new BufferedWriter(file);
    buffer.write(String.format("%20s ",name));
    String formatedOvertime=twoDecimal.format(overtime);
    buffer.write(String.format("%20s ",formatedOvertime));
    buffer.write(String.format(" %30s",timeStamp));
    buffer.newLine();
    buffer.close();
  }catch(IOException e){
	  System.out.println(e); 
  }
}
		public static void main(String[] args)  
		{
			new ReadData();
		}
		}

