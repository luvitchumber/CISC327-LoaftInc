package classes;
import java.io.*; 
import java.util.Scanner; 

public class login {
	static boolean signIn (String args[]) {
		boolean state = false;
		//confirm that the account number is valid
		try {
	        int accNum = Integer.parseInt(args[0]);
	    } catch (NumberFormatException e) {
	        System.err.println("Inputted account number is invalid.");
	        return state;
	    }
		
		//select agent or atm
		//if start instance is started, stay logged in
		return state;
	}
	
	static String loginMode(String args[]) {
		System.out.println("Chosen mode: "+args[0]); 
		String mode = "";
		String checkType = args[0].toLowerCase();
		
		if (checkType.contains("agent")) {
			mode = "Agent";
		}else if (checkType.contains("machine") || checkType.contains("atm")) {
			mode = "Machine";
		}else {
			mode = "Error";
			System.err.println("Selected mode is invalid, please select Agent or Machine");
	        return mode;
		}
		
		File file = new File("C:\\valid_accts.txt"); 
	    Scanner sc = new Scanner(file);
	    String acct = "";
	    while (sc.hasNextLine()) {
	    	acct = sc.nextLine();
	    	if (!validateFile(acct)) {
	    		return "Accounts list is invalid";
	    	}
	    }
	    
		return mode;
	}
	
	static boolean validateFile(String acctNum) {
		int check = 0;
		try {
			check = Integer.parseInt(acctNum);
		}catch (NumberFormatException e) {
			System.err.println("Error with valid_accts.txt");
			return false;
		}
		
		if (acctNum.length() == 7) {
			
		}
			
		
		return true;
		
		
	}
}
