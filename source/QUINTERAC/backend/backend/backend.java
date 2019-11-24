package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class backend {

	public static void main(String[] args) throws IOException {
		// 1. read previous master accounts file
		// 2. read in merged tsf 
		// 3. update master accounts file
		// 4. if invalid field produce a fatal error
		// 5. produce failed constraint log on terminal as processing
		// 6. produce updated master file
		// 7. produce updated valid accts file
		
		File masterAcctsFile = new File(args[0]);
		String[] mergedTsfFileNames = Arrays.copyOfRange(args, 1, args.length); // in case of multiple TSFs
		File validAcctsFile = new File("validaccts.txt");
		
		ArrayList<Account> masterAccts = validateMasterAcctsFile(masterAcctsFile);
		
		masterAccts = updateMasterAccts(masterAccts,mergedTsfFileNames);
		
		Comparator<Account> c = Collections.reverseOrder(); //sort descending order
		Collections.sort(masterAccts,c);
		
		createNewMasterAcctsFile(masterAcctsFile,masterAccts);
		
		//System.out.println(masterAccts.toString());
		
		createNewValidAcctsFile(validAcctsFile,masterAccts);
	}

	//Writes all valid accounts to file (valid_accts.txt)
	private static void createNewValidAcctsFile(File validAcctsFile, ArrayList<Account> masterAccts) throws IOException {

		String output = "";
		for (int i = 0; i < masterAccts.size(); i++) {
			Account cache = masterAccts.get(i);
			if(cache!=null) {
				output += cache.getAcct() + "\n";	// Outputs: acct
			}
		}
		output += "0000000";
		
		Files.write(validAcctsFile.toPath(),output.getBytes()); //throws fatal error if cannot write to file
	}

	//Writes all updated account information to file (masteraccts.txt)
	private static void createNewMasterAcctsFile(File masterAcctsFile, ArrayList<Account> masterAccts) throws IOException {

		String output = "";
		for (int i = 0; i < masterAccts.size(); i++) {
			Account cache = masterAccts.get(i);
			if(cache!=null) {
				output += cache.toString() + "\n"; 	// Outputs: acct amount name
			}
		}
		
		Files.write(masterAcctsFile.toPath(),output.getBytes()); //throws fatal error if cannot write to file
		
	}

	//Performs check on the master accounts file
	//Parses through original file
	//Checks for: line length, remove unnecessary spaces, validate account number
	private static ArrayList<Account> validateMasterAcctsFile(File masterAcctsFile) throws FileNotFoundException {
		//add validate here
		ArrayList<Account> masterAccts = new ArrayList<Account>();		//create ArrayList<String> accts;
		String inRaw = "";
		
		//validate file and add to accts;
		Scanner sc = new Scanner(masterAcctsFile);
		while (sc.hasNextLine()) {		//while there is another line to parse through
	    	inRaw = sc.nextLine();
	    	
	    	// add check if line is correct length else error
	    	
	    	String[] in = inRaw.split(" "); // cannot use this method for names, names can have spaces
	    	String acct = in[0];
	    	String amountStr = in[1];
	    	int startIDX = inRaw.lastIndexOf(amountStr);
	    	String name = inRaw.substring(startIDX+amountStr.length());
	    	name = name.trim(); // removes space from front of string;	    	
	    	
	    	if (validateAcctNumandReturn(acct).equals("NotValid")) { 	//perform check on single account
	    		//throw error 
	    		System.err.println("Error with account number in master accounts files");
	    		System.err.println("Cannot validate master accounts file.");
	    	}else if (validateAmountandReturn(amountStr) == -1){
	    		//throw error 
	    		System.err.println("Error with amount in master accounts files");
	    		System.err.println("Cannot validate master accounts file.");
	    	}else if (validateNameandReturn(name).equals("NotValid")){
	    		//throw error 
	    		System.err.println("Error with name in master accounts files");
	    		System.err.println("Cannot validate master accounts file.");
	    	}else {
	    		
	    		Account cache = new Account(acct,validateAmountandReturn(amountStr),name);
	    		masterAccts.add(cache);
	    	}
    	}
	    sc.close();	//close scanner

		return masterAccts;
	}
	
	//Update the master accounts file (masteraccts.txt)
	//Parse through the original file
	//Validate name, account number to, account number from, amount
	//Ensure type is correct: NEW, DEL, DEP, WDR, XFR, EOS
	private static ArrayList<Account> updateMasterAccts(ArrayList<Account> masterAccts, String[] mergedTsfFileNames) throws FileNotFoundException {

		for(int i = 0; i < mergedTsfFileNames.length; i++) {
			File tsf = new File(mergedTsfFileNames[i]);
			Scanner sc = new Scanner(tsf);
			
			while (sc.hasNextLine()) {
				String inRaw = sc.nextLine();
				if (inRaw.equals("EOS") || !sc.hasNextLine()) {
					System.out.println("WDRStatementReached.TestingLine01");
					continue; // if eos or no more lines, leave current iteration.
				}
				
				String[] in = inRaw.split(" "); // cannot use this method for names, names can have spaces
		    	String type = in[0];
		    	String acctTo = in[1];
		    	String amountStr = in[2];
		    	String acctFrom = in[3];
		    	int startIDX = inRaw.lastIndexOf(acctFrom);
		    	String name = inRaw.substring(startIDX+acctFrom.length());
		    	name = name.trim(); // removes space from front of string;	
		    	
		    	//validate all fields here ELSE FATAL ERROR;
		    	name = validateNameandReturn(name);
		    	acctTo = validateAcctNumandReturn(acctTo);
		    	acctFrom = validateAcctNumandReturn(acctFrom);
		    	int amount = validateAmountandReturn(amountStr);
		    	
		    	try {
			    	switch(type) {
			    		case "NEW":
			    			if (amount != 0) {
			    				throw new IllegalArgumentException("Invalid Amount for Entered for New Account");
			    			}
			    			masterAccts = transaction(masterAccts,type,acctTo,name,amount);
			    			break;
			    		case "DEL":
			    			masterAccts = transaction(masterAccts,type,acctFrom,name,amount);
			    			break;
			    		case "DEP":
			    			masterAccts = transaction(masterAccts,type,acctTo,name,amount);
			    			break;
			    		case "WDR":
			    			System.out.println("WDRStatementReached.TestingLine06");
			    			masterAccts = transaction(masterAccts,type,acctFrom,name,amount);
			    			break;
			    		case "XFR":
			    			try {
			    				masterAccts = transaction(masterAccts,"WDR",acctFrom,name,amount); //withdraw first to make sure there are sufficent resources
			    				masterAccts = transaction(masterAccts,"DEP",acctTo,name,amount);
			    			}catch (IllegalArgumentException e) {
			    				throw new IllegalArgumentException("Cannot Transfer Funds, Invalid Amound");
			    			}
			    			break;
		    			default:
		    				System.out.println("WDRStatementReached.TestingLine07");
		    				throw new IllegalArgumentException("Illegal Transaction Code");
			    	} //end switch   
		    	} catch (IllegalArgumentException e) {
					//e.printStackTrace();
					System.err.println(e.getMessage());
					sc.close();
					throw e;
				}
			} //end while loop
			sc.close();
		} //end for loop
		return masterAccts;
	}
	
	private static ArrayList<Account> transaction(ArrayList<Account> masterAccts,String type,String acct,String name,int amount) throws IllegalArgumentException {
		//		check if account exists else throw fatal error
		if (!type.equals("NEW") && !DoesAcctNumExist(masterAccts,acct)) {
			//throw error
			System.out.println("WDRStatementReached.TestingLine08");
			throw new IllegalArgumentException("Account does not exist");
		}else if(type.equals("NEW") && DoesAcctNumExist(masterAccts,acct)){
			throw new IllegalArgumentException("Cannot Create New Account, Account Number already exists");
		}
		// now no need to check for acct exists again!!
		
		Account temp = new Account(acct,amount,name);
		int idx,currentAmount;
		
		switch(type) {
			case "NEW":
				if(!name.equals("NotValid")){
					temp = new Account(acct, 0, name);
					masterAccts.add(temp);
				}else {
					throw new IllegalArgumentException("Cannot Create New Account, Invalid Account Name");
				}
				
				break;
			case "DEL":
				if (!name.equals("NotValid")){
					idx = masterAccts.indexOf(temp);
					temp = masterAccts.get(idx);
					if(temp.getAmount() == 0 && temp.getName().equals(name)) {
						masterAccts.remove(idx);
					}else {
	//					error
						throw new IllegalArgumentException("Cannont Delete Account, Account Balance Greater than 0");
					}
				}else {
					throw new IllegalArgumentException("Cannont Delete Account, Invalid Account Name");
				}
				break;
			case "DEP":
				if(amount != -1) {
					idx = masterAccts.indexOf(temp);
					temp = masterAccts.get(idx);
					currentAmount = temp.getAmount();
					temp.setAmount(currentAmount+amount);
					masterAccts.set(idx, temp);
				}else {
					throw new IllegalArgumentException("Cannot Deposit to Account, Invalid Amount Value");
				}
				break;
			case "WDR":
				System.out.println("WDRStatementReached.TestingLine11");
				if(amount != -1) {
					System.out.println("WDRStatementReached.TestingLine12");
					idx = masterAccts.indexOf(temp);
					temp = masterAccts.get(idx);
					currentAmount = temp.getAmount();
					if (amount<currentAmount) {
						System.out.println("WDRStatementReached.TestingLine14");
						temp.setAmount(currentAmount-amount);
						masterAccts.set(idx, temp);
					}else {
						System.out.println("WDRStatementReached.TestingLine15");
		//				error
						throw new IllegalArgumentException("Insufficent funds to transfer");
					}
				}else {
					System.out.println("WDRStatementReached.TestingLine13");
					throw new IllegalArgumentException("Cannont Withdraw from Account, Invalid Amount Value");
				}
				break;
		}
		
		return masterAccts;
	}
	
	//Used to validate amounts for transactions and accounts
	//if valid, returns amount
	private static int validateAmountandReturn(String in) {
		int num = -1;
		try {
			double test = Double.parseDouble(in);
			
			if (test % 1 > 0) {
				System.out.println("WDRStatementReached.TestingLine04");
				test *= 100; //left shift amount 2 if amount entered as XX.x
			}
			
			num=(int)test;
			
			if (in.length() < 3 || num > 99999999 || num < 0) {	//cannot exceed 99999999 or be a negative number
				System.out.println("WDRStatementReached.TestingLine05");
				num = -1;
			}
			
		} catch(NumberFormatException e){
			e.printStackTrace();
		}
		return num;
	}
	
	//Used to validate individual names
	//if valid, returns name
	private static String validateNameandReturn(String in) {
		String name = "";
		boolean isAlphanumeric = true;
		
		if (in == null || !in.matches("^[\\sa-zA-Z0-9]*$")) 
			isAlphanumeric = false;
		
		if (in.length()>=3 && in.length()<=30 && isAlphanumeric) 
			name=in;
		else 
			name="NotValid";
		
		return name;
	}
	
	//Used to validate individual account numbers
	//if valid, returns acct
	private static String validateAcctNumandReturn(String in) {
		String acct = "";
		in = in.trim();
		if (in.charAt(0)!='0' && in.length()==7) { //error if it starts with 0, or its length is longer than 7 characters
			System.out.println("WDRStatementReached.TestingLine02");
			try {
				Integer.parseInt(in);
				acct=in;
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}else {
			System.out.println("WDRStatementReached.TestingLine03");
			acct="NotValid";
		}
		
		return acct;
	}
	
	//Used to validate if account number exists
	//returns true if account is valid
	private static boolean DoesAcctNumExist(ArrayList<Account> accts, String in) {
		in = in.trim();
		Account cache = new Account(in,-1,null);
		
		if (accts.contains(cache)) {
			System.out.println("WDRStatementReached.TestingLine09");
			return true;
		}else {
			System.out.println("WDRStatementReached.TestingLine10");
			return false;
		}
	}

}
