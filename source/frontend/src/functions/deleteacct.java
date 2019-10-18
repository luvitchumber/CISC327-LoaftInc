package functions;

public class deleteacct {
	
	String acctNum;
	String acctName;
	ArrayList<String> validAccounts;
	
	public void deleteAcct() {
		checkMode(Terminal.getMode()); 
		this.validAccounts = Terminal.getvalidAccts();
		
		inputAcctNum();     // user will input an account number to delete and program will validate it
		inputAcctName();    // user will input the corresponding account name
		addToTSF();         // add the deleted account name and number to the Transaction Summary File
		
	} // end deleteAcct method
	
	public void inputAcctNum() {
		System.out.println("Input account number to delete.");
		String acctNum = scanner.next();
		doesExist(acctNum);       // validate account number
		this.acctNum = acctNum;
	} // end inputAcctMum method
	
	public void inputAcctName() {
		System.out.println("Input new account name.");
		String acctName = scanner.next();
		this.acctName = acctName;
	} // end inputAcctName method
	
	public void doesExist(String acctNum) {
		
		//iterate through all existing account numbers to check if the account to delete exists
		for (int i = 0; i < validAccounts.size(); i++) {
			if (acctNum == validAccounts.get(i)) {
				return;     // if we have found the account number, we can continue
			}			
		} // end for-loop
		
		// if we have gotten to this point, the inputted account number does not exist.
		System.out.println("This account number does not exist.")
		inputAcctNum();
	} // end doesExist method
	
	
	// after validating the account name and number, we add it to the transaction summary file
	public void addToTSF() {
		String trans = "DEL " + acctNum + " " + acctName;
		Terminal.addTransaction(trans);
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
