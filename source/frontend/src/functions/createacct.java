package functions;
import functions.Terminal;
import java.util.Scanner;
import java.util.ArrayList;

public class createacct {
		
	public void createAcct(Terminal terminal) {
		String acctNum;
		String acctName;
		String mode = terminal.getMode();
		ArrayList<String> accts = terminal.getvalidAccts();
		Scanner in = terminal.getCLIScanner();
		
		checkMode(mode);    // ensure that the user is in agent mode
		
		acctNum = inputAcctNum(accts,in);    // user will input new account number and program will validate it
		acctName = inputAcctName(in);       // user will input new account name and program will validate it
		addToTSF(terminal, acctNum, acctName);      // add the new account name and number to the Transaction Summary File
	} // end createAcct
	
	
	// take user input for the account number
	public String inputAcctNum(ArrayList<String> accts, Scanner in) {
		System.out.println("Input new account number.");
		String acctNum = in.next();
		checkAcctNum(acctNum, accts,in);       // validate account number
		return acctNum;
	} // end inputAcctMum method
	
	
	// take user input for the account name
	public String inputAcctName(Scanner in) {
		System.out.println("Input new account name.");
		String acctName = in.next();
		checkAcctName(acctName, in);     // validate account name
		return acctName;
	} // end inputAcctName method
	
	
	// ensures that the new account number follows restrictions. If it does not, it prompts the user to enter a new account number
	public void checkAcctNum(String acctNum, ArrayList<String> accts, Scanner in) {
		
		// check that the length of the new account number is 7
		if (acctNum.length() != 7) {
			System.out.println("Account number must be 7 digits long");
			inputAcctNum(accts,in);
		}
		
		if (acctNum.charAt(0) == '0') {
			System.out.println("The first digit in an account number cannot be 0.");
			inputAcctNum(accts,in);
		}
		
		// check that the account number only contains numerical digits
		for (int i = 0; i < acctNum.length(); i++) {
			if (! Character.isDigit(acctNum.charAt(i))) {
				System.out.println("Account number must only contain integers.");
				inputAcctNum(accts,in);
			}
		} // end for-loop
		
		// ensure that the new account number isn't already in use
		if (accts.contains(acctNum)) {
			System.out.println("This account number is already in use.");
			inputAcctNum(accts,in);
		}
	} // end checkAcctNum method

	
	// ensures that the new account name follows restrictions. If it does not, it prompts the user to enter a new account name
	public void checkAcctName(String acctName, Scanner in) {
		
		// check that the length of the new account name is between 3 and 30 characters
		if (acctName.length() < 3 || acctName.length() > 30) {
			System.out.println("Account name must be between 3 and 30 characters.");
		}
		
		// check that the new account name does not start or end with a space
		if (acctName.charAt(0) == ' ' || acctName.charAt(acctName.length() - 1) == ' ') {
			System.out.println("Account name cannot start or end with a space");
			inputAcctName(in);
		}
		
		// check that every character in the new account name is alphanumeric
		for (int i = 0; i < acctName.length(); i++) {
			if (! Character.isDigit(acctName.charAt(i)) && ! Character.isLetter(acctName.charAt(i)) && !(acctName.charAt(i) == ' ')) {
				System.out.println("The account name can only contain alphanumeric characters.");
				inputAcctName(in);
			}
		} // end for-loop
	} // end checkAcctName method
	
	// after validating the account name and number, we add it to the transaction summary file
	public Terminal addToTSF(Terminal terminal, String acctNum, String acctName) {
		String trans = "NEW " + acctNum + " " + acctName;
		terminal.addTransaction(trans);
		return terminal;
	} // end addToTSF method
	
	
	// ensures that the user is in agent mode
	public void checkMode(String mode) {	
		if (mode.equals("agent")) {
			System.out.println("In agent mode");
		} else {
			System.out.println("Cannot create account when not in agent mode.");
			return;
		} // end if-else block
	} // end checkMode method
	
} // end createAcct Class
