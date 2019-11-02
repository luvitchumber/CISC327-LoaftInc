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
	
	public Terminal setAccts(ArrayList<String> accts) {
		this.accts = accts;
		return this;
	}
	
	public ArrayList<String> getAccts(){
		return this.accts;
	}
	
	public boolean ExceedTransTotal(String type, String acctNum, int limit) {
		int currentAmount = 0;
		if (limit == -1) {
			return false;
		}
		for(int i =0; i < tsf.size(); i++) {
			Transaction item = tsf.get(i);

			if (item.getType() == type && item.getAcctNum() == acctNum) {
				currentAmount += item.getAmount();
				
			}
			if (currentAmount >= limit) {
				return true;
			}
		}
		return false;
	}

	public Terminal setMode(String mode) {
		// TODO Auto-generated method stub
		//add validation
		this.mode = mode;
		return this;
	}

	public String getMode() {
		// TODO Auto-generated method stub
		return this.mode;
	}

}
