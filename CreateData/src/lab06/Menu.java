package lab06;

import javax.swing.JOptionPane;
//programmer: Alberto Student 
/**
 * The Menu class shows a pop up with different options 
 * which will execute one of the implemented functions.         
 */ 
public class Menu {
public Menu()
{
int choice=0;
String message = "welcome" + "\n", response = null;

  message += "\n" + "enter...";
  message += "\n" + "  1 to enter payroll data";
  message += "\n" + "  2 to view payroll data";
  message += "\n" + "  3 to generate report by employee";
  message += "\n" + "  4 to view the summarized overtime paid hours of all the employees";
  message += "\n" + "  5 to exit" + "\n" + " ";

  char answer = 'Y';

  do {
	
   try {  
    //Catch the input of the user and depending on it input
	//one of the implemented functions will be executed.
    response  = JOptionPane.showInputDialog(message);

    choice = Integer.parseInt(response);
	
    switch (choice) {
	case 1:  CreateData cd = new CreateData();
	         answer = 'N'; System.exit(1);
	         break;
	case 2:  ReadData rd = new ReadData();
 	         answer = 'N';  System.exit(1);
 	         break;
	case 3:  Report  rpt = new Report();
	         answer = 'N';  System.exit(1);
	         break;
	case 4:  Summary  smr = new Summary();
    		answer = 'N';  System.exit(1);
    		break;	      
	case 5:  answer = 'N';  System.exit(1);
	         break;
	default: { answer = 'Y'; choice = 0;
	JOptionPane.showMessageDialog(null,"enter a number: 1 - 4");
	} 
    }//end switch
   }//end try and catching exceptions
   catch (java.lang.NumberFormatException e ) {
   JOptionPane.showMessageDialog(null,"Please, enter a number: 1 - 4"); 
   }  
   catch (Exception e ) { System.out.println(e); }  
  }while(answer == 'Y' || answer == 'y');  

}
public static void main(String[] args)  
{ 
 new Menu();
}//end main
}//end class 
