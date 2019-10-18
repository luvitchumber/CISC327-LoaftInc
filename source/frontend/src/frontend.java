import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import functions.Terminal;
import functions.deposit;
import functions.login;
import functions.transfer;
import functions.withdraw;

public class frontend {

	public static void main(String[] args) {
		File acctsFile = new File(args[0]);
		File tsfFile = new File(args[1]);
		
		//create terminal instance, holds terminal states and shared resources
		Terminal terminal = new Terminal();
		
		Scanner in = terminal.getCLIScanner();
		
		//Accepts inputs from user, runs continuously
		while(true) {
			
			String input = in.next().toLowerCase();
			
			//Check on the user input for next step
			if (input != "login" && terminal.getState() == "out") {	//only login, if not already logged in
				//state must be "in" in order to do anything other than login
				System.err.println("Selected transaction is unavailable, please login before continuing.");
			}else if (input == "login") {
				try {
					terminal = login.loginMode(terminal,acctsFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (input == "logout") {
				//logout function
			}else if (input == "createacct") {
				//createacct function
			}else if (input == "deleteacct") {
				//deleteacct function
			}else if (input == "withdraw") {
				//withdraw function
				terminal = withdraw.withdrawFromAccount(terminal);
			}else if (input == "deposit") {
				//deposit function
				terminal = deposit.depositToAccount(terminal);
			}else if (input == "transfer") {
				//transfer function
				terminal = transfer.transferBetweenAccounts(terminal);
			}else if (input == "logout") {
				//logout function
				//logout
				//break loop
				break;
			}else {
				//error with input
			}
			//error --> command not recognized, enter one of the following
			
		}
	
		//read TSF
		//add helper method here
		terminal = terminal.setTSF(tsfFile);
	}

}
