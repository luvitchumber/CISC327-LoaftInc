package functions;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
	//state --> login/etc
	//mode --> agent/machine
	//input buffer
	ArrayList<String> accts;
	ArrayList<String> tsf;
	String state;
	String mode;
	Scanner cli;
	public Terminal() {
		//set default values
		//state = logged out
		//
		state = "out";
		mode = "none";
		cli = new Scanner(System.in);
		
	}
	//get --> 

	public void setTSF(File tsfFile) {
		// TODO Auto-generated method stub
		
	}
	
	public void addTransaction(String trans) {
		// add to tsf list
		tsf.add(trans);
	}
	
	public double transactionTotal(String type, String account) {
		//loop through transactions to find all that match type and account and add up
		// use tokens to split trans
		return 0;
	}

	public void setAccts(ArrayList<String> accts) {
		// TODO Auto-generated method stub
		this.accts = accts;
	}

	public void setMode(String mode) {
		// TODO Auto-generated method stub
		this.mode = mode;
	}
	
	public String getMode() {
		// TODO Auto-generated method stub
		return this.mode;
	}
	
	public void setState(String state) {
		// TODO Auto-generated method stub
		this.state = state;
	}
	
	public String getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	public ArrayList<String> getvalidAccts() {
		// TODO Auto-generated method stub
		return this.accts;
	}
	public Scanner getCLIScanner() {
		// TODO Auto-generated method stub
		return cli;
	}
	
	//check if input is numeric
	static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;		
		} 	catch (NumberFormatException e) {
			return false;
		}
	}
	//check if input starts with 0
	private static boolean inputStart(String s) {
		char c = s.charAt(0);
		if (c==000) {
			return true;		
		} else return false;
	}
	//check if input length is 7 digits
	private static boolean inputLength(String s) {
		if (s.length() > 7) {
			return true;
		} else return false;
	}	
	//check if account number exists on file
	private static boolean isValidAccount(ArrayList<String> list, String s) {
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
		if (terminal.getMode() == "Agent") {
			limit = 99999999;
		} else limit = 2000;
			return limit;
	}
	//check if account exceeds daily limit
	public static boolean dailyTransactionLimit (Terminal terminal, String type, String accNum, double limit) {
		if (terminal.getMode() == "ATM" && terminal.transactionTotal(type,accNum) > limit) { // need to fix functionality of transactiontotal
			return false;
		} 
			else return true; 
	}
	//check if input amount is valid
	public static boolean amountInputValidation(Terminal terminal, String type, String accNum, String amount) {
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
		
		else if(!dailyTransactionLimit(terminal, type, accNum, 5000)) {
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
}
