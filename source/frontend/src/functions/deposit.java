package functions;

import java.util.ArrayList;
import java.util.Scanner;

public class deposit {
	
	public static Terminal depositToAccount(Terminal terminal) 
	{
		//read in valid accounts
		ArrayList<String> validAccounts = terminal.getvalidAccts();
		Scanner scanner = terminal.getCLIScanner();
		
		System.out.print("Input Account Number");
		String accNum = scanner.next();
		
		if (Terminal.accountInputValidation(validAccounts, accNum)) {
			System.out.print("Input Deposit Amount");
			String depAmount = scanner.next();
			if (Terminal.amountInputValidation(terminal, "dep", accNum, depAmount)) {
				String trans = ("DEP" + " " + accNum + " " + depAmount);
				terminal.addTransaction(trans);
			}			
		}		
		return terminal;
	}

}
