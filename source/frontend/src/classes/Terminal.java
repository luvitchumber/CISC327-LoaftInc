package classes;

import java.io.File;
import java.util.ArrayList;

public class Terminal {
	//state --> login/etc
	//mode --> agent/machine
	//input buffer
	ArrayList<String> accts;
	String state;
	String mode;
	Scanner cli;
	public Terminal() {
		//set default values
		//state = logged out
		//
		state = "out";
		mode = "none";
		in = new Scanner()
		
	}
	//get --> 

	public void setTSF(File tsfFile) {
		// TODO Auto-generated method stub
		
	}

	public void setAccts(ArrayList<String> accts) {
		// TODO Auto-generated method stub
		this.accts = accts;
	}

	public void setMode(String mode) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String> getvalidAccts() {
		// TODO Auto-generated method stub
		return this.accts;
	}
}
