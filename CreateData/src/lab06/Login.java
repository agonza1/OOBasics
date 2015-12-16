package lab06;

import javax.swing.JOptionPane;
//programmer: Alberto Student
/**
 * The Login class shows a pop up asking for the user name and password.
 * There will be 3 tries until the program is terminated         
 */ 
interface Login {
public static void main(String[] args)  
{ 
  int triesP=0;
  int triesN=0;
  boolean access = false;	
  String message = "welcome" + "\n", response;
  message += "enter your name";
  message += "\n" + " ";  
  while(triesN<3){
  try{
  String name = JOptionPane.showInputDialog(message);
  String password;
  name = name.trim();
  name = name.toUpperCase();

  
  if (name.equals("ALBERTO")) 
  { 
    JOptionPane.showMessageDialog(null,"hello " + name);
    message = "enter your password";
    message += "\n" + " ";
    while(triesP<3){
    password = JOptionPane.showInputDialog(message);
    password = password.trim();
    password = password.toUpperCase();

    
    if (password.equals("AUTUMN")) 
    { 
      access = true;
      break;
    }
    else{
	JOptionPane.showMessageDialog(null, "Incorrect password."+" Tries:"+(triesP+1)+"/3");    
	triesP++;
	//There will be 3 password tries before executing the if and terminating.
	if(triesP==3){
    	JOptionPane.showMessageDialog(null, "Too many tries. Program terminated.");
    	System.exit(1);
    }
  }
  }
  }
  else
  {
    JOptionPane.showMessageDialog(null, "Incorrect login name."+" Tries:"+(triesN+1)+"/3");
    triesN++;
  //There will be 3 name tries before the executing the if and terminating.
    if(triesN==3){
    JOptionPane.showMessageDialog(null, "Too many tries. Program termintated");
    System.exit(1);
    }
  }
  
 if(access == true)
  {
    try {
 	Menu m = new Menu(); 
	System.exit(1);
    }
    catch (Exception e) { System.out.println(e);} 
  }
 //Catching cancel exception and closing program
  }catch(java.lang.NullPointerException e){
	  System.out.println("Bye Bye");
	  System.exit(1);
  }
 }
}//end main
}//end class


