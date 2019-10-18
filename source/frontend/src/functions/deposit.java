package functions;

import java.util.ArrayList;
import java.util.Scanner;

public class deposit {
	//check if input is numeric
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;		
		} 	catch (NumberFormatException e) {
			return false;
		}
	}
	//check if input starts with 0
	public static boolean inputStart(String s) {
		char c = s.charAt(0);
		if (c==000) {
			return true;		
		} else return false;
	}
	//check if input length is 7 digits
	public static boolean inputLength(String s) {
		if (s.length() > 7) {
			return true;
		} else return false;
	}	
	//check if account number exists on file
	public static boolean isValidAccount(ArrayList<String> list, String s) {
		if (list.contains(s)) {
			return true;
		} else return false;
	}
	//check if the inputed account number passes all requirements
	public static boolean accountInputValidation(ArrayList<String> list, String s) {
			boolean result = true;
		if (inputStart(s)) {
			s = "Invalid";
			System.err.println("Invalid account number: 1st digit cannot be 0");
			result = false;
		}
		
		else if (!isNumeric(s)) {
			s = "Invalid";
			System.err.println("Invalid account number: Please enter numbers only");
			result = false;
		}
		
		else if (inputLength(s)) {
			s = "Invalid";
			System.err.println("Invalid account number: Please enter 7 digits only");
			result = false;
		}
		
		else if (!isValidAccount(list, s)) {
			s = "Invalid";
			System.err.println("Invalid account number: Account number does not exist");
			result = false;
		} return result;
	}
	
	//check if user inputed deposit length is valid
	public static boolean depositLen(String amount) {
		if (amount.length() >= 3 && amount.length() <= 8) {
			return true;
		} else return false;
	}
	//set limit based on user account type
	public static double accountLimit (Terminal terminal) {
		double limit;
		if (terminal.getMode == "Agent") {
			limit = 99999999;
		} else limit = 2000;
			return limit;
	}
	//check if account exceeds daily limit
	public static boolean dailyTransactionLimit (Terminal terminal) {
		if (terminal.getMode == "ATM" && terminal.transactionTotal > 5000) {
			return false;
		} 
			else return true; 
	}
	//check if deposit input is valid
	public static boolean depositInputValidation(Terminal terminal, String amount) {
		boolean result = true;
		double limit = accountLimit(terminal);
		if (!isNumeric(amount)) {			
			amount = "Invalid";
			System.err.println("Invalid amount: Please input a numerical amount");
			result = false;
		}
		else if (!depositLen(amount)) {
			amount = "Invalid";
			System.err.println("Invalid amount: Please input amount between 3-8 digits");
			result = false;
		}
		
		else if(!dailyTransactionLimit(terminal)) {
			amount = "Invalid";
			System.err.println("Invalid amount: Account Exceeds daily limit");
			result = false;		
		}
		else if (Double.parseDouble(amount) > limit) {
			amount = "Invalid";
			System.err.println("Invalid amount: Amount Exceeds transaction limit");
			result =  false;		
	
		}
		return result;
	}
	///////////////////////////////////////////////////////////////////////
	
	public static Terminal depositToAccount(Terminal terminal) 
	{
		//read in valid accounts
		ArrayList<String> validAccounts = terminal.getvalidAccts();
		Scanner scanner = terminal.getCLIScanner();
		
		System.out.print("Input Account Number");
		String accNum = scanner.next();
		
		if (accountInputValidation(validAccounts, accNum)) {
			System.out.print("Input Deposit Amount");
			String depAmount = scanner.next();
			if (depositInputValidation(terminal, depAmount)) {
				String trans = ("DEP" + " " + accNum + " " + depAmount);
				terminal.addTransaction(trans);
			}			
		}		
		return terminal;
	}

}
