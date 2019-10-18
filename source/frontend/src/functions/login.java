package functions;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; 

public class login {
	
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
		terminal.setMode(mode);
		
		
		//validate valid_accts.txt file
		ArrayList<String> accts = new ArrayList<String>();
		
	    Scanner sc = new Scanner(acctsfile);
	    String acct = "";
	    while (sc.hasNextLine()) {
	    	acct = sc.nextLine();
	    	if (acct == "0000000" && !sc.hasNextLine()) { //constraint, end of file = 0000000
	    		break;
	    	}
	    	if (!validateFile(acct)) { //perform check on single account
	    		//throw error 
	    		System.err.println("Selected mode is invalid, please select Agent or Machine");
	    	}
	    	accts.add(acct);
    	}
	    sc.close();
	    
	    terminal.setAccts(accts);
	    terminal.setMode(mode);
	    return terminal;
	}
	
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
