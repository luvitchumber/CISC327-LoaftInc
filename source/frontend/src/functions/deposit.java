package functions;

import java.util.ArrayList;
import java.util.Scanner;

public class deposit {
	
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;		
		} 	catch (NumberFormatException e) {
			return false;
		}
	}
	public static boolean inputStart(String s) {
		char c = s.charAt(0);
		if (c==000) {
			return true;		
		} else return false;
	}
	
	public static boolean inputLength(String s) {
		if (s.length() > 7) {
			return true;
		} else return false;
	}	
	public static boolean isValidAccount(ArrayList<String> list, String s) {
		if (list.contains(s)) {
			return true;
		} else return false;
	}
	public static boolean accountInputValidation(ArrayList<String> list, String s) {
			boolean result = true;
		if (inputStart(s)) {
			s = "Invalid";
			System.err.println("Invalid account number: 1st digit cannot be 0");
			result = false;
		}
		//check if input is numeric
		else if (!isNumeric(s)) {
			s = "Invalid";
			System.err.println("Invalid account number: Please enter numbers only");
			result = false;
		}
		//check if input length is 7 digits
		else if (inputLength(s)) {
			s = "Invalid";
			System.err.println("Invalid account number: Please enter 7 digits only");
			result = false;
		}
		//check if account number exists on file
		else if (!isValidAccount(list, s)) {
			s = "Invalid";
			System.err.println("Invalid account number: Account number does not exist");
			result = false;
		} return result;
	}
	
	public static boolean depositInputValidation()
	///////////////////////////////////////////////////////////
	
	public Terminal depositToAccount(Terminal terminal, String account, double amount) 
	{
		//read in valid accounts
		ArrayList<String> validAccounts = terminal.getvalidAccts();
		Scanner scanner = terminal.getCLIScanner();
		
		System.out.print("Input Account Number");
		String accNum = scanner.next();
		
		if (accountInputValidation(validAccounts, accNum)) {
			System.out.print("Input Deposit Amount");
			String depAmount = scanner.next();
			
		}
		
		
		
			//prompt user to input acct number
				//read inputted account
					//check if valid
						//if not, re enter
			//if valid, prompt to enter amount
				//read inputted amount
					//check if valid
						//if not, reenter
			//addtransaction to terminal
			
		
	
		return terminal;
	}

}
