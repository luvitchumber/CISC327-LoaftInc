package frontend;

import java.util.ArrayList;
import java.util.Scanner;

import transactions.Transaction;

public class Terminal {
	
	protected ArrayList<String> accts;
	protected ArrayList<Transaction> tsf;
	protected String mode;
	protected Scanner cli;

	public Terminal() {
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
	
	public ArrayList<Transaction> getTSF(){
		return this.tsf;
	}
	
	protected Terminal setAccts(ArrayList<String> accts) {
		this.accts = accts;
		return this;
	}
	
	protected ArrayList<String> getAccts(){
		return this.accts;
	}
	
	protected boolean ExceedTransTotal(String type, String acctNum, int limit) {
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

	protected Terminal setMode(String mode) {
		// TODO Auto-generated method stub
		//add validation
		this.mode = mode;
		return this;
	}

	protected String getMode() {
		// TODO Auto-generated method stub
		return this.mode;
	}

}
