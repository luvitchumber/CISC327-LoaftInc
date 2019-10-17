package classes;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; 

public class login {
	
	public static int loginMode(Terminal terminal, File acctsfile) {
		//1=error
		
		//get terminal input
		Scanner scanner = new Scanner(System.in);
		System.out.print("Select One: (Agent/Machine) ");
		String mode = scanner.next();
		
		System.out.println("Chosen mode: "+ mode); 
		String checkType = mode.toLowerCase();
		
		if (checkType.contains("agent")) {
			mode = "Agent";
		}else if (checkType.contains("machine") || checkType.contains("atm")) {
			mode = "Machine";
		}else {
			mode = "Error";
			System.err.println("Selected mode is invalid, please select Agent or Machine");
	        return 1;
		}
		
		ArrayList<String> accts = new ArrayList<String>();
		
	    Scanner sc = new Scanner(acctsfile);
	    String acct = "";
	    while (sc.hasNextLine()) {
	    	acct = sc.nextLine();
	    	if (acct == "0000000" && !sc.hasNextLine()) {
	    		break;
	    	}
	    	if (!validateFile(acct)) {
	    		return 1;
	    	}
	    	accts.add(acct);
	    }
	    terminal.setAccts(accts);
	    terminal.setMode(mode);
		return 0;
	}
	
	static boolean validateFile(String acctNum) {
		int check = 0;
		try {
			check = Integer.parseInt(acctNum);
		}catch (NumberFormatException e) {
			System.err.println("Error with valid_accts.txt");
			return false;
		}
		
		if (acctNum.length() != 7 || acctNum.charAt(0) == '0') {
			return false;
		}
			
		
		return true;
		
		
	}
}
