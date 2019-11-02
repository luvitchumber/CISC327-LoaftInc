package frontend;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import transactions.CreateAcct;
import transactions.DeleteAcct;
import transactions.Deposit;
import transactions.Login;
import transactions.Transaction;
import transactions.Transfer;
import transactions.Withdraw;
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
					String acctNum=validateAcctNumandReturn(input[1]);
					boolean exist = DoesAcctNumExist(t.getAccts(),acctNum);
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
					String acctNum=validateAcctNumandReturn(input[1]);
					boolean exist = DoesAcctNumExist(t.getAccts(),acctNum);
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
				int transLimit;
				int dailyLimit;
				if (t.getMode() == "atm") {
					transLimit = 100000;
					dailyLimit = 500000;
				}else {
					transLimit = 99999999;
					dailyLimit = -1;
				}
				t = moveMoney(t,"WDR", input,transLimit,dailyLimit);
			}else if (input[0] == "deposit") {
				//deposit function
				int transLimit;
				int dailyLimit;
				if (t.getMode() == "atm") {
					transLimit = 200000;
					dailyLimit = 500000;
				}else {
					transLimit = 99999999;
					dailyLimit = -1;
				}
				t = moveMoney(t,"DEP", input,transLimit,dailyLimit);
			}else if (input[0] == "transfer") {
				//transfer function
				int transLimit;
				int dailyLimit;
				if (t.getMode() == "atm") {
					transLimit = 1000000;
					dailyLimit = 1000000;
				}else {
					transLimit = 99999999;
					dailyLimit = -1;
				}
				t = moveMoney(t,"XFR", input,transLimit,dailyLimit);
			}else {
				//error with input
				System.err.println("Selected transaction is unavailable, please enter a valid transaction code");
			}
		}
		
		
		
		//create Transaction Summary File
		createTSF(t, tsfFile);
	}
	
	public static Terminal moveMoney(Terminal t, String type, String[] input, int transLimit, int dailyLimit) {
		String acctNum;
		int amount;
		Transaction cache = null;
		
		String inNum = input[1];
		String inAmount = input[input.length-1]; // needed in case type is transfer then input[2] is acctto
		
		acctNum=validateAcctNumandReturn(inNum);
		boolean exist = DoesAcctNumExist(t.getAccts(),acctNum);
		amount=validateAmountandReturn(inAmount);
		boolean exceeds=t.ExceedTransTotal(type, acctNum, dailyLimit);
		if (exist) {
			if (!exceeds) {
				if (amount != -1) {
					if (amount < transLimit) {
						if (type == "WDR") {
							cache = new Withdraw(acctNum,amount);
						}else if (type == "DEP") {
							cache = new Deposit(acctNum,amount);
						}else if (type == "XFR") {
							inNum = input[2]; // acct number to
							String acctTo=validateAcctNumandReturn(inNum);
							exist = DoesAcctNumExist(t.getAccts(),acctTo);
							if (exist) {
								cache = new Transfer(acctNum,amount,acctTo);
							}else {
								System.err.println("Please enter correct account number to send to");
							}
						}
						t = t.addTransaction(cache);
					}else {
						System.err.println("Selected transaction exceeds terminal limit");
					}
				}else {
					System.err.println("Please enter correct amount and account number");
				}	
			}else {
				System.err.println("Selected transaction exceeds daily transaction limit");
			}
		}else {
			System.err.println("Selected account does not exist");
		}
				
		return t; // might return null object
	}
	
	public static Terminal validateAcctsFile(Terminal t, File acctsFile) {
		//add validate here
		ArrayList<String> accts = new ArrayList<String>();		//create ArrayList<String> accts;
		String acct = "";
		try {
			//validate file and add to accts;
			Scanner sc = new Scanner(acctsFile);
			while (sc.hasNextLine()) {		//while there is another line to parse through
		    	acct = sc.nextLine();
		    	if (acct == "0000000" && !sc.hasNextLine()) { //constraint, end of file = 0000000
		    		break;	//done parsing
		    	}
		    	if (validateAcctNumandReturn(acct) == "NotValid") { 	//perform check on single account
		    		//throw error 
		    		System.err.println("Error with account number in valid_accts.txt");
		    		System.err.println("Cannot validate accounts file.");
		    	}else {
		    		accts.add(acct);
		    	}
	    	}
			t = t.setAccts(accts);
		    sc.close();	//close scanner
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
		int num = -1;
		try {
			num=Integer.parseInt(in);
			if (num > 99999999 || num < 0) {
				num = -1;
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
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
	
	public static String validateAcctNumandReturn(String in) {
		String acct = "";
		in = in.trim();
		if (in.charAt(0)!='0' && in.length()==7) { //error if it starts with 0, or its length is longer than 7 characters
			try {
				Integer.parseInt(in);
				acct=in;
			}catch(NumberFormatException e){
				e.printStackTrace();
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
	
}
