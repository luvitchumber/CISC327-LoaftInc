package frontend;

import java.util.ArrayList;
import java.util.Scanner;

import transactions.Transaction;

public class Terminal {
	
	ArrayList<String> accts;
	ArrayList<Transaction> tsf;
	String state;
	String mode;
	Scanner cli;

	public Terminal() {
		this.state = "out";
		this.mode = "none";
		this.cli = new Scanner(System.in);
	}
	
	public Terminal addTransaction(Transaction trans) {
		// add to tsf list
		tsf.add(trans);
		return this;
	}
	
	public Scanner getCLIScanner() {
		// TODO Auto-generated method stub
		return this.cli;
	}
	
	public String getState() {
		// TODO Auto-generated method stub
		return this.state;
	}
	
	public ArrayList<Transaction> getTSF(){
		return this.tsf;
	}

}
