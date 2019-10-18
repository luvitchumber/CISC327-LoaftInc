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
	
//	public double transactionTotal(void) {
//		tsf.print(trans);
//	}

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
}
