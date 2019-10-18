package functions;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
	//state --> login/etc
	//mode --> agent/machine
	//input buffer
	ArrayList<String> accts;
	ArrayList<String> tsf;
	String state;
	String mode;
	Scanner cli;
	public Terminal() {
		//set default values
		//state = logged out
		//
		state = "out";
		mode = "none";
		cli = new Scanner(System.in);
		
	}
	//get --> 

	public void setTSF(File tsfFile) {
		// TODO Auto-generated method stub
		
	}
	
	public void addTransaction(String trans) {
		// add to tsf list
		tsf.add(trans);
	}
	
//	public double transactionTotal(void) {
//		tsf.print(trans);
//	}

	public void setAccts(ArrayList<String> accts) {
		// TODO Auto-generated method stub
		this.accts = accts;
	}

	public void setMode(String mode) {
		// TODO Auto-generated method stub
		this.mode = mode;
	}
	
	public String getMode() {
		// TODO Auto-generated method stub
		return this.mode;
	}
	
	public void setState(String state) {
		// TODO Auto-generated method stub
		this.state = state;
	}
	
	public String getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	public ArrayList<String> getvalidAccts() {
		// TODO Auto-generated method stub
		return this.accts;
	}
	public Scanner getCLIScanner() {
		// TODO Auto-generated method stub
		return cli;
	}
}
