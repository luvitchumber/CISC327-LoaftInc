package frontend;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import transactions.Login;
import transactions.Transaction;
import transactions.Logout;

public class frontend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File acctsFile = new File(args[0]);
		File tsfFile = new File(args[1]);

		Terminal t = new Terminal();
		Scanner in = t.getCLIScanner();
		Transaction cache;

		String inputRaw = in.nextLine().toLowerCase();
		String[] input = inputRaw.split(" ");
		
		
		// Loop until logged in 
		while(input[0] != "login") {
			inputRaw = in.nextLine().toLowerCase();
			input = inputRaw.split(" ");
			
			if (input[0] == "login" && (input[1] =="atm" || input[1]=="agent")) {	//only login, if not already logged in
				t = validateAcctsFile(t, acctsFile);
				cache = new Login(input[1]);
				t = t.addTransaction(cache);
			}else{
				System.err.println("Selected transaction is unavailable, please login before continuing.");
				System.err.println("Sample login: login atm or login agent");
			}
		}
		
		// Loop until logout
		while(input[0] != "logout") {
			inputRaw = in.nextLine().toLowerCase();
			input = inputRaw.split(" ");
			
			//if branches for all trans possibilities
				// validate inputs
				// create trans object
				// example: cache = withdraw(name,acctnum,amount
				// add to file
				// t = t.addTransaction(cache);
		}
		
		cache = new Logout();
		t = t.addTransaction(cache);
		
		//create Transaction Summary File
		createTSF(t, tsfFile);
	}
	
	public static Terminal validateAcctsFile(Terminal t, File accts) {
		//add validate here
		try {
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		return t;
	}
	
	public static Terminal createTSF(Terminal t, File tsf) {
		ArrayList<Transaction> trans = t.getTSF();
		String output = tsf.toString();
		//add to tsf file
		return t;
	}

	public static int validateNumberandReturn(String in) {
		int num = 0;
		return num;
	}
	
	public static String validateNameandReturn(String in) {
		String name = "";
		return name;
	}
	
	public static String validateAcctandReturn(String in) {
		String acct = "";
		return acct;
	}
	
}
