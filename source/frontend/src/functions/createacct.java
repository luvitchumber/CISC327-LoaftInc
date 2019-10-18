package functions;

public class createacct {
	
	String acctNum;
	String acctName;
	ArrayList<String> validAccounts;
	
	
	public void createAcct() {
		checkMode(Terminal.getMode());    // ensure that the user is in agent mode
		this.validAccounts = Terminal.getvalidAccts();     // get valid accounts
		
		inputAcctNum();    // user will input new account number and program will validate it
		inputAcctName();   // user will input new account name and program will validate it
		addToTSF();        // add the new account name and number to the Transaction Summary File
	} // end createAcct
	
	
	// take user input for the account number
	public void inputAcctNum() {
		System.out.println("Input new account number.");
		String acctNum = scanner.next();
		checkAcctNum(acctNum);       // validate account number
		this.acctNum = acctNum;
	} // end inputAcctMum method
	
	
	// take user input for the account name
	public void inputAcctName() {
		System.out.println("Input new account name.");
		String acctName = scanner.next();
		checkAcctName(acctName);     // validate account name
		this.acctName = acctName;
	} // end inputAcctName method
	
	
	// ensures that the new account number follows restrictions. If it does not, it prompts the user to enter a new account number
	public void checkAcctNum(String acctNum) {
		
		// check that the length of the new account number is 7
		if (acctNum.length() != 7) {
			System.out.println("Account number must be 7 digits long");
			inputAcctNum();
		}
		
		if (acctNum.charAt(0) == '0') {
			System.out.println("The first digit in an account number cannot be 0.")
			inputAcctNum();
		}
		
		// check that the account number only contains numerical digits
		for (int i; i < acctNum.length(); i++) {
			if (! Character.isDigit(acctNum.charAt(i))) {
				System.out.println("Account number must only contain integers.");
				inputAcctNum();
			}
		} // end for-loop
		
		// ensure that the new account number isn't already in use
		for (int i = 0; i < validAccounts.size(); i++) {
			if (acctNum == validAccounts.get(i)) {
				System.out.println("Account number already exists, please choose a new account number.");
				inputAcctNum();
			}			
		} // end for-loop
	} // end checkAcctNum method

	
	// ensures that the new account name follows restrictions. If it does not, it prompts the user to enter a new account name
	public void checkAcctName(String acctName) {
		
		// check that the length of the new account name is between 3 and 30 characters
		if (acctName.length() < 3 || acctName.length() > 30) {
			System.out.println("Account name must be between 3 and 30 characters.");
		}
		
		// check that the new account name does not start or end with a space
		if (acctName.charAt(0) == ' ' || acctName.charAt(acctName.length() - 1) == ' ') {
			System.out.println("Account name cannot start or end with a space");
			inputAcctName();
		}
		
		// check that every character is the new account name is alphanumeric
		for (int i; i < acctName.length(); i++) {
			if (! Character.isDigit(acctName.charAt(i)) && ! Character.isLetter(acctName.charAt(i)) && ! acctName.charAt(i) == ' ') {
				System.out.println("The account name can only contain alphanumeric characters.");
				inputAcctName();
			}
		} // end for-loop
	} // end checkAcctName method
	
	// after validating the account name and number, we add it to the transaction summary file
	public void addToTSF() {
		String trans = "NEW " + acctNum + " " + acctName;
		Terminal.addTransaction(trans);
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
