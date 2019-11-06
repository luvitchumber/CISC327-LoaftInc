package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import frontend.Terminal;

public class backend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// read previous master accounts file
		// read in merged tsf 
		// update master accounts file
		// if invalid field produce a fatal error
		// produce failed constraint log on terminal as processing
		// produce updated master file
		// produce updated valid accts file
		
		File masterAcctsFile = new File(args[0]);
		ArrayList<Account> masterAccts= validateMasterAcctsFile(masterAcctsFile);
	}
	
	private static ArrayList<Account> validateMasterAcctsFile(File masterAcctsFile) {
		//add validate here
		ArrayList<Account> masterAccts = new ArrayList<Account>();		//create ArrayList<String> accts;
		String inRaw = "";
		
		//validate file and add to accts;
		Scanner sc = new Scanner(masterAcctsFile);
		while (sc.hasNextLine()) {		//while there is another line to parse through
	    	inRaw = sc.nextLine();
	    	
	    	String[] in = inRaw.split(" ");
	    	String acct = in[0];
	    	String amountStr = in[1];
	    	String name = in[2];
	    	
	    	if (acct.equals("0000000") && !sc.hasNextLine()) { //constraint, end of file = 0000000
	    		break;	//done parsing
	    	}
	    	if (validateAcctNumandReturn(acct).equals("NotValid")) { 	//perform check on single account
	    		//throw error 
	    		System.err.println("Error with account number in valid_accts.txt");
	    		System.err.println("Cannot validate accounts file.");
	    	}else {
	    		masterAccts.add(acct);
	    	}
    	}
	    sc.close();	//close scanner

		return masterAccts;
	}
	
	//Used to validate amounts for transactions and accounts
	//if valid, returns amount
	private static int validateAmountandReturn(String in) {
		int num = -1;
		try {
			double test = Double.parseDouble(in);
			if (test % 1 > 0) {
				test *= 100; //left shift amount 2 if amount entered as XX.x
			}
			num=(int)test;
			if (in.length() < 3 || num > 99999999 || num < 0) {	//cannot exceed 99999999 or be a negative number
				num = -1;
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return num;
	}
	
	//Used to validate individual names
	//if valid, returns name
	private static String validateNameandReturn(String in) {
		String name = "";
		boolean isAlphanumeric = true;
		
		for (int i = 0; i < in.length(); i++) {
			if (! Character.isDigit(in.charAt(i)) && ! Character.isLetter(in.charAt(i))) {
				isAlphanumeric = false;
				break;
			}
		}
		
		if (in.length()>=3 && in.length()<=30 && isAlphanumeric) {
			name=in;
		}else {
			name="NotValid";
		}
		
		return name;
	}
	
	//Used to validate individual account numbers
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
	
	//Used to validate if account number exists
	//returns true if account is valid
	private static boolean DoesAcctNumExist(ArrayList<Account> accts, String in) {
		in = in.trim();
		
		if (accts.contains(in)) {
			return true;
		}
		return false;
	}

}
