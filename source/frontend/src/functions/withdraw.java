package functions;

import java.util.ArrayList;
import java.util.Scanner;

public class withdraw {
	
	public static Terminal withdrawFromAccount(Terminal terminal) {

		ArrayList<String> validAccounts = terminal.getvalidAccts();
		Scanner scanner = terminal.getCLIScanner();
		double dailyLimit = 5000;
		
		System.out.print("Input Account Number");
		String accNum = scanner.next();
		
		if (Terminal.accountInputValidation(validAccounts, accNum)) {
			System.out.print("Input Withdrawal Amount");
			String wdrAmount = scanner.next();
			if (Terminal.amountInputValidation(terminal, "wdr",accNum, wdrAmount, dailyLimit)) {
				String trans = ("WDR" + " " + wdrAmount  + " " + accNum);
				terminal.addTransaction(trans);
			}			
		}		
		return terminal;
	}

}
