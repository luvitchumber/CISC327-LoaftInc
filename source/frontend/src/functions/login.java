package functions;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; 

public class login {
	
	//main login function
	public static Terminal loginMode(Terminal terminal, File acctsfile) throws FileNotFoundException {
		
		//get terminal input
		Scanner scanner = terminal.getCLIScanner();
		System.out.print("Select One: (Agent/Machine) ");
		String mode = scanner.next();
		
		System.out.println("Chosen mode: "+ mode); 
		String checkType = mode.toLowerCase();
		
		if (checkType.contains("agent") || checkType.contains("teller")) {
			mode = "agent";
		}else if (checkType.contains("machine") || checkType.contains("atm")) {
			mode = "machine";
		}else {
			mode = "none";
			System.err.println("Selected mode is invalid, please select Agent or Machine");
	        //throw error
		}
		terminal.setMode(mode);			//set mode: agent/machine for the instance
		
		
		
		//validate valid_accts.txt file
		ArrayList<String> accts = new ArrayList<String>();
		
		//scan valid_accts.txt file
	    Scanner sc = new Scanner(acctsfile);
	    String acct = "";
	    while (sc.hasNextLine()) {		//while there is another line to parse through
	    	acct = sc.nextLine();
	    	if (acct == "0000000" && !sc.hasNextLine()) { //constraint, end of file = 0000000
	    		break;	//done parsing
	    	}
	    	if (!validateFile(acct)) { 	//perform check on single account
	    		//throw error 
	    		System.err.println("Selected mode is invalid, please select Agent or Machine");
	    	}
	    	accts.add(acct);
    	}
	    sc.close();	//close scanner
	    
	    terminal.setAccts(accts); 		//after confirming all of the entries, send to array list
	    
	    return terminal;
	}
	
	
	//validate single account number
	static boolean validateFile(String acctNum) {
		int check = 0;
		
		try {
			check = Integer.parseInt(acctNum);	//ensure they are all digits
		}catch (NumberFormatException e) {
			System.err.println("Error with valid_accts.txt");
			return false;
		}
		
		if (acctNum.length() != 7 || acctNum.charAt(0) == '0') { 	//error if it starts with 0, or its length is longer than 7 characters
			return false;
		}
		return true;
		
		
	}
}
