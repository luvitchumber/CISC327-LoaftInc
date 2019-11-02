package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import transactions.Transaction;
import transactions.Login;
import transactions.Logout;
import transactions.CreateAcct;
import transactions.DeleteAcct;
import transactions.Deposit;
import transactions.Transfer;
import transactions.Withdraw;


public class frontend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File acctsFile = new File(args[0]);
		File tsfFile = new File(args[1]);

		Terminal t = new Terminal();
		Scanner in = t.getCLIScanner();
		Transaction cache;

		String inputRaw;
		String[] input;
		
		
		// Loop until logged in 
		while(true) {
			System.out.println("Please Login to begin session");
			inputRaw = in.nextLine().toLowerCase();
			input = inputRaw.split(" ");
			
			//add a check for input len
			if (input[0].equals("login") && (input[1].equals("atm") || input[1].equals("agent"))) {	//only login, if not already logged in
				t = validateAcctsFile(t, acctsFile);
				cache = new Login(input[1]);
				t = t.setMode(input[1]);
				//t = t.addTransaction(cache);
				break;
			}else{
				System.err.println("Error: Selected transaction is unavailable, please login before continuing.");
				System.err.println("Sample login: 'login atm' or 'login agent'");
			}
		}
		
		// Loop until logout
		while(true) {
			System.out.println("Enter next transaction: ");
			inputRaw = in.nextLine().toLowerCase();
			input = inputRaw.split(" ");
			
			//if branches for all trans possibilities
				// validate inputs
				// create trans object
				// example: cache = withdraw(name,acctnum,amount
				// add to file
				// t = t.addTransaction(cache);
			if (input[0].equals("logout")) {
				//logout function
				cache = new Logout();
				t = t.addTransaction(cache);
				break;
			}else if (input[0].equals("createacct")) {
				//createacct function
				if (t.getMode().equals("agent")) {
					String acctNum=validateAcctNumandReturn(input[1]);
					boolean exist = DoesAcctNumExist(t.getAccts(),acctNum);
					String acctName=validateNameandReturn(input[2]);
					
					if(!exist && !acctNum.equals("NotValid") && !acctName.equals("NotValid")) {
						cache = new CreateAcct(acctName,acctNum);
						t=t.addTransaction(cache);
						System.out.println("Account created.");
					}
				}else {
					System.err.println("Need privileged mode to Create Account");
				}
			}else if (input[0].equals("deleteacct")) {
				//deleteacct function
				if (t.getMode().equals("agent")) {
					String acctNum=validateAcctNumandReturn(input[1]);
					boolean exist = DoesAcctNumExist(t.getAccts(),acctNum);
					String acctName=validateNameandReturn(input[2]);
					
					if(exist && !acctNum.equals("NotValid") && !acctName.equals("NotValid")) {
						cache = new DeleteAcct(acctName,acctNum);
						t=t.addTransaction(cache);
					}
				}else {
					System.err.println("Need privileged mode to Delete Account");
				}
			}else if (input[0].equals("withdraw")) {
				//withdraw function
				int transLimit;
				int dailyLimit;
				if (t.getMode().equals("atm")) {
					transLimit = 100000;
					dailyLimit = 500000;
				}else {
					transLimit = 99999999;
					dailyLimit = -1;
				}
				t = moveMoney(t,"WDR", input,transLimit,dailyLimit);
			}else if (input[0].equals("deposit")) {
				//deposit function
				int transLimit;
				int dailyLimit;
				if (t.getMode().equals("atm")) {
					transLimit = 200000;
					dailyLimit = 500000;
				}else {
					transLimit = 99999999;
					dailyLimit = -1;
				}
				t = moveMoney(t,"DEP", input,transLimit,dailyLimit);
			}else if (input[0].equals("transfer")) {
				//transfer function
				int transLimit;
				int dailyLimit;
				if (t.getMode().equals("atm")) {
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
		t = createTSF(t, tsfFile);
	}
	
	//Used to withdraw, deposit, transfer money
	private static Terminal moveMoney(Terminal t, String type, String[] input, int transLimit, int dailyLimit) {
		String acctNum;
		int amount;
		Transaction cache = null;
		
		String inNum = input[1];
		String inAmount = input[input.length-1]; // needed in case type is transfer then input[2] is acctto
		
		acctNum=validateAcctNumandReturn(inNum);
		boolean exist = DoesAcctNumExist(t.getAccts(),acctNum);
		amount=validateAmountandReturn(inAmount);
		boolean exceeds=t.ExceedTransTotal(type, acctNum, dailyLimit);
		if (exist) {							//account number exists?
			if (!exceeds) {						//amount does not exceed transfer total
				if (amount != -1) {				//amount is valid
					if (amount < transLimit) {	//amount is less than the max limit
						if (type.equals("WDR")) {		//Withdraw command
							cache = new Withdraw(acctNum,amount);
						}else if (type.equals("DEP")) {	//Deposit command
							cache = new Deposit(acctNum,amount);
						}else if (type.equals("XFR")) {	//Transfer command
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
	
	//Used to validate the entire file of valid_accts.txt
	//returns terminal once completed parsing through the file
	private static Terminal validateAcctsFile(Terminal t, File acctsFile) {
		//add validate here
		ArrayList<String> accts = new ArrayList<String>();		//create ArrayList<String> accts;
		String acct = "";
		try {
			//validate file and add to accts;
			Scanner sc = new Scanner(acctsFile);
			while (sc.hasNextLine()) {		//while there is another line to parse through
		    	acct = sc.nextLine();
		    	if (acct.equals("0000000") && !sc.hasNextLine()) { //constraint, end of file = 0000000
		    		break;	//done parsing
		    	}
		    	if (validateAcctNumandReturn(acct).equals("NotValid")) { 	//perform check on single account
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
	
	//creating TSF file
	//returns terminal
	private static Terminal createTSF(Terminal t, File tsfFile) {
		ArrayList<Transaction> tsf = t.getTSF();
		String output = "";
		for (int i = 0; i < tsf.size(); i++) {
			output += tsf.get(i).toString() + "\n";
		}
		try {
			Files.write(tsfFile.toPath(),output.getBytes());
			
		}catch (IOException e) {
			System.err.println("Error unable to write TSF");
			e.printStackTrace();
		}
		
		//add to tsf file
		return t;
	}

	//Used to validate amounts for withdrawing/depositing/transferring
	//if valid, returns amount
	private static int validateAmountandReturn(String in) {
		int num = -1;
		try {
			num=Integer.parseInt(in);
			if (in.length() < 3 || num > 99999999 || num < 0) {	//cannot exceed 99999999 or be a negative number
				num = -1;
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return num;
	}
	
	//Used to validate individual names when creating an account
	//if valid, returns name
	private static String validateNameandReturn(String in) {
		String name = "";
		
		if (in.length()>=3 && in.length()<=30) {
			name=in;
		}else {
			name="NotValid";
		}
		
		return name;
	}
	
	//Used to validate individual accounts in valid_accts.txt
	//if valid, returns acct
	private static String validateAcctNumandReturn(String in) {
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
	
	//Used to validate if account number exists from valid_accts.txt
	//returns true if account is valid
	private static boolean DoesAcctNumExist(ArrayList<String> accts, String in) {
		in = in.trim();
		
		if (accts.contains(in)) {
			return true;
		}
		return false;
	}
	
}
