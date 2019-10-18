package functions;

public class deleteacct {
	
	public void deleteAcct(ArrayList<String> accts) {	
		String acctNum;
		String acctName;
		
		checkMode(Terminal.getMode());    // ensure that the user is in agent mode
		
		acctNum = inputAcctNum(accts);    // user will input an account number to delete and program will validate it
		acctName = inputAcctName();       // user will input the corresponding account name
		addToTSF(acctNum, acctName);      // add the deleted account name and number to the Transaction Summary File
		
	} // end deleteAcct method
	
	public String inputAcctNum(ArrayList<String> accts) {
		System.out.println("Input account number to delete.");
		String acctNum = scanner.next();
		doesExist(acctNum, accts);       // validate account number
		return acctNum;
	} // end inputAcctMum method
	
	public String inputAcctName() {
		System.out.println("Input new account name.");
		String acctName = scanner.next();
		return acctName;
	} // end inputAcctName method
	
	public void doesExist(String acctNum, ArrayList<String> accts) {
		
		//iterate through all existing account numbers to check if the account to delete exists
		for (int i = 0; i < accts.size(); i++) {
			if (acctNum == accts.get(i)) {
				return;     // if we have found the account number, we can continue
			}			
		} // end for-loop
		
		// if we have gotten to this point, the inputted account number does not exist.
		System.out.println("This account number does not exist.")
		inputAcctNum();
	} // end doesExist method
	
	
	// after validating the account name and number, we add it to the transaction summary file
	public void addToTSF(String acctNum, String acctName) {
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
