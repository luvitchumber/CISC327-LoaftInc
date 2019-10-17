import java.io.File;

import classes.Terminal;
import classes.login;

public class frontend {

	public static void main(String[] args) {
		File acctsFile = new File(args[0]);
		File tsfFile = new File(args[1]);
		
		//create terminal instance, holds terminal states and shared resources
		Terminal terminal = new Terminal();
		
		//login (updates terminal state) [returns 0 for success and 1 for error]
		login.loginMode(terminal,acctsFile);
		
		//read TSF
		//add helper method here
		terminal.setTSF(tsfFile);
	}

}
