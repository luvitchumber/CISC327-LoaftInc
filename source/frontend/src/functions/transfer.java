package functions;

import java.util.ArrayList;
import java.util.Scanner;

public class transfer {
	
	public static Terminal transferBetweenAccounts(Terminal terminal) {

		ArrayList<String> validAccounts = terminal.getvalidAccts();
		Scanner scanner = terminal.getCLIScanner();
		double dailyLimit = 10000;
		
		System.out.print("Input Account Number to transfer from");
		String accNum = scanner.next();
		
		if (Terminal.accountInputValidation(validAccounts, accNum)) {
			System.out.print("Input Account Number to transfer to");
			String accNumTo = scanner.next();
			if (Terminal.accountInputValidation(validAccounts, accNumTo)) {
				System.out.print("Input Transfer Amount");
				String xfrAmount = scanner.next();
				if (Terminal.amountInputValidation(terminal, "xfr", accNum, xfrAmount, dailyLimit)) {
					String trans = ("XFR" + " " + accNumTo + " " + xfrAmount + " " + accNum);
					terminal.addTransaction(trans);
				}
			}
		}		
		return terminal;
	}

}
