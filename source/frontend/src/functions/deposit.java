package functions;

import java.util.ArrayList;
import java.util.Scanner;

public class deposit {
	
	//check if user inputed deposit length is valid
	public static boolean depositLen(String amount) {
		if (amount.length() >= 3 && amount.length() <= 8) {
			return true;
		} else return false;
	}
	//set limit based on user account type
	public static double accountLimit (Terminal terminal) {
		double limit;
		if (terminal.getMode() == "Agent") {
			limit = 99999999;
		} else limit = 2000;
			return limit;
	}
	//check if account exceeds daily limit
	public static boolean dailyTransactionLimit (Terminal terminal) {
		//if (terminal.getMode() == "ATM" && terminal.transactionTotal() > 5000) { // need to fix functionality of transactiontotal
		if (terminal.getMode() == "ATM") {
			return false;
		} 
			else return true; 
	}
	//check if deposit input is valid
	public static boolean depositInputValidation(Terminal terminal, String amount) {
		boolean result = true;
		double limit = accountLimit(terminal);
		if (!Terminal.isNumeric(amount)) {			
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
		
		if (Terminal.accountInputValidation(validAccounts, accNum)) {
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
