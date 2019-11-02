package frontend;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import transactions.CreateAcct;
import transactions.DeleteAcct;
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
		while(true) {
			inputRaw = in.nextLine().toLowerCase();
			input = inputRaw.split(" ");
			
			if (input[0] == "login" && (input[1] =="atm" || input[1]=="agent")) {	//only login, if not already logged in
				t = validateAcctsFile(t, acctsFile);
				cache = new Login(input[1]);
				t = t.setMode(input[1]);
				t = t.addTransaction(cache);
				break;
			}else{
				System.err.println("Selected transaction is unavailable, please login before continuing.");
				System.err.println("Sample login: login atm or login agent");
			}
		}
		
		// Loop until logout
		while(true) {
			inputRaw = in.nextLine().toLowerCase();
			input = inputRaw.split(" ");
			
			//if branches for all trans possibilities
				// validate inputs
				// create trans object
				// example: cache = withdraw(name,acctnum,amount
				// add to file
				// t = t.addTransaction(cache);
			if (input[0] == "logout") {
				//logout function
				cache = new Logout();
				t = t.addTransaction(cache);
				break;
			}else if (input[0] == "createacct") {
				//createacct function
				if (t.getMode() == "agent") {
					boolean exist = DoesAcctNumExist(t.getAccts(),input[1]);
					String acctNum=validateAcctNumandReturn(t.getAccts(),input[1]);
					String acctName=validateNameandReturn(input[2]);
					
					if(!exist && acctNum !="NotValid" && acctName != "NotValid") {
						cache = new CreateAcct(acctName,acctNum);
						t=t.addTransaction(cache);
					}
				}else {
					System.err.println("Need privileged mode to Create Account");
				}
			}else if (input[0] == "deleteacct") {
				//deleteacct function
				if (t.getMode() == "agent") {
					boolean exist = DoesAcctNumExist(t.getAccts(),input[1]);
					String acctNum=validateAcctNumandReturn(t.getAccts(),input[1]);
					String acctName=validateNameandReturn(input[2]);
					
					if(exist && acctNum !="NotValid" && acctName != "NotValid") {
						cache = new DeleteAcct(acctName,acctNum);
						t=t.addTransaction(cache);
					}
				}else {
					System.err.println("Need privileged mode to Delete Account");
				}
			}else if (input[0] == "withdraw") {
				//withdraw function
				int amount;
				String acctNum;
			}else if (input[0] == "deposit") {
				//deposit function
				
			}else if (input[0] == "transfer") {
				//transfer function
				
			}else {
				//error with input
				System.err.println("Selected transaction is unavailable, please enter a valid transaction code");
			}
		}
		
		
		
		//create Transaction Summary File
		createTSF(t, tsfFile);
	}
	
	public static Terminal validateAcctsFile(Terminal t, File acctsFile) {
		//add validate here
		try {
			//create ArrayList<String> accts;
			//validate file and add to accts;
			//t = t.setAccts(accts); 

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: File not found");
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

	public static int validateAmountandReturn(String in) {
		int num = 0;
		return num;
	}
	
	public static String validateNameandReturn(String in) {
		String name = "";
		
		if (in.length()>=3 && in.length()<=30) {
			name=in;
		}else {
			name="NotValid";
		}
		
		return name;
	}
	
	public static String validateAcctNumandReturn(ArrayList<String> accts, String in) {
		String acct = "";
		in = in.trim();
		if (in.charAt(0)!='0' && in.length()==7) {
			try {
				Integer.parseInt(in);
			}catch(NumberFormatException e){
				e.printStackTrace();
			}finally {
				acct=in;
			}
		}else {
			acct="NotValid";
		}
		
		return acct;
	}
	public static boolean DoesAcctNumExist(ArrayList<String> accts, String in) {
		in = in.trim();
		
		if (accts.contains(in)) {
			return true;
		}
		return false;
	}
	
	public static boolean validateFile(String acctNum) {
		try {
			int check = Integer.parseInt(acctNum);	//ensure they are all digits
		}catch (NumberFormatException e) {
			System.err.println("Error with valid_accts.txt");
			return false;
		}
		
		if (acctNum.length() != 7 || acctNum.charAt(0) == '0') { 	//error if it starts with 0, or its length is longer than 7 characters
			System.err.println("Error with account number in valid_accts.txt");
			return false;
		}
		return true;
	}
	
}
