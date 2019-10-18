package functions;
import functions.Terminal;
import java.util.Scanner;
import java.util.ArrayList;

public class deleteacct {
	
	public void deleteAcct(Terminal terminal) {	
		String acctNum;
		String acctName;
		String mode = terminal.getMode();
		ArrayList<String> accts = terminal.getvalidAccts();
		Scanner in = terminal.getCLIScanner();
		
		checkMode(mode);    // ensure that the user is in agent mode
		if (mode.equals("atm")) {
			return;
		}
		
		acctNum = inputAcctNum(accts, in);    // user will input an account number to delete and program will validate it
		acctName = inputAcctName(in);       // user will input the corresponding account name
		addToTSF(terminal,acctNum, acctName);      // add the deleted account name and number to the Transaction Summary File
		
	} // end deleteAcct method
	
	public String inputAcctNum(ArrayList<String> accts, Scanner in) {
		System.out.println("Input account number to delete.");
		String acctNum = in.next();
		doesExist(acctNum, accts,in);       // validate account number
		return acctNum;
	} // end inputAcctMum method
	
	public String inputAcctName(Scanner in) {
		System.out.println("Input new account name.");
		String acctName = in.next();
		return acctName;
	} // end inputAcctName method
	
	
	// check if the account number entered is a valid account
	public void doesExist(String acctNum, ArrayList<String> accts, Scanner in) {
		if (! accts.contains(acctNum)) {
			System.out.println("This account number does not exist.");
			inputAcctNum(accts, in);
		}
	} // end doesExist method
	
	
	// after validating the account name and number, we add it to the transaction summary file
	public void addToTSF(Terminal terminal, String acctNum, String acctName) {
		String trans = "DEL " + acctNum + " " + acctName;
		terminal.addTransaction(trans);
	} // end addToTSF method
	
	// ensure that the user is in agent mode to delete accounts
	public void checkMode(String mode) {
		if (mode.equals("agent")) {
			System.out.println("in agent mode");
		} else {
			System.out.println("Cannot create account when not in agent mode.");
			return;
		} // end if-else block
	} // end checkMode method
		
} // end deleteacct Class
