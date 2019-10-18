import java.io.File;
import java.util.Scanner;

import functions.Terminal;
import functions.login;

public class frontend {

	public static void main(String[] args) {
		File acctsFile = new File(args[0]);
		File tsfFile = new File(args[1]);
		
		//create terminal instance, holds terminal states and shared resources
		Terminal terminal = new Terminal();
		
		Scanner in = terminal.getCLIScanner();
		
		//Accepts inputs from user, runs continuously
		while(true) {
			
			String input = in.next().toLowerCase();	//input will not be case sensitive
			
			//Check on the user input for next step
			if (input != "login" && terminal.getState() == "out") {	//only login, if not already logged in
				//state must be "in" in order to do anything other than login
				System.err.println("Selected transaction is unavailable, please login before continuing.");
				
			}else if (input == "login") {
				terminal = login.loginMode(terminal,acctsFile);
				
			}else if (input == "logout") {
				//logout function
				//terminal = ;
				
			}else if (input == "createacct") {
				//createacct function
				//terminal = ;
				
			}else if (input == "deleteacct") {
				//deleteacct function
				//terminal = ;
				
			}else if (input == "withdraw") {
				//withdraw function
				//terminal = ;
				
			}else if (input == "deposit") {
				//deposit function
				//terminal = ;
				
			}else if (input == "logout") {
				//logout function
				//terminal = ;
				
			}else {
				//error with input
				
			}
			//error --> command not recognized, enter one of the following
			
		}
	
		//read TSF
		//add helper method here
		terminal.setTSF(tsfFile);
	}

}
