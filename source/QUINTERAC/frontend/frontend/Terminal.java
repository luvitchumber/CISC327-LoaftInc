package frontend;

import java.util.ArrayList;
import java.util.Scanner;

import transactions.Transaction;

public class Terminal {
	
	protected ArrayList<String> accts;
	protected ArrayList<Transaction> tsf;
	protected String mode;
	protected Scanner cli;

	//open new terminal window to read from CLI
	public Terminal() {
		this.mode = "none";
		this.cli = new Scanner(System.in);
	}
	
	//add transaction to the TSF list
	public Terminal addTransaction(Transaction trans) {
		// add to tsf list
		tsf.add(trans);
		return this;
	}
	
	//Get input from CLI
	public Scanner getCLIScanner() {
		return this.cli;
	}
	
	//Get TSF file information
	public ArrayList<Transaction> getTSF(){
		return this.tsf;
	}
	
	//Set valid accounts list
	protected Terminal setAccts(ArrayList<String> accts) {
		this.accts = accts;
		return this;
	}
	
	//Get valid accounts
	protected ArrayList<String> getAccts(){
		return this.accts;
	}
	
	//Check if amount exceeds the max limit
	protected boolean ExceedTransTotal(String type, String acctNum, int limit) {
		int currentAmount = 0;
		if (limit == -1) {
			return false;
		}
		if (tsf != null) {
			for(int i =0; i < tsf.size(); i++) {
				Transaction item = tsf.get(i);
	
				if (item.getType() == type && item.getAcctNum() == acctNum) {
					currentAmount += item.getAmount();
					
				}
				if (currentAmount >= limit) {
					return true;
				}
			}
		}
		return false;
	}

	//Set mode to ATM or Agent
	protected Terminal setMode(String mode) {
		//add validation
		this.mode = mode;
		return this;
	}

	//Check if mode is ATM or Agent
	protected String getMode() {
		return this.mode;
	}

}
