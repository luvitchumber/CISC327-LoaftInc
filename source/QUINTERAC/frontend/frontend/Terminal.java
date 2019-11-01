package frontend;

import java.util.ArrayList;
import java.util.Scanner;

import transactions.Deposit;
import transactions.Transaction;
import transactions.Transfer;
import transactions.Withdraw;

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
	
	public boolean DoesNotExceedTransTotal(String type, String acctNum, int limit) {
		for(int i =0; i < tsf.size()) {
			Transaction item;
			if (tsf.get(i).getType() == "WDR"){
				item = (Withdraw) tsf.get(i);
			}else if (tsf.get(i).getType() == "XFR") {
				item = (Transfer) tsf.get(i);
			}else if (tsf.get(i).getType() == "DEP") {
				item = (Deposit) tsf.get(i);
			}
			
			if (item != null && item.getType() == type) {
				item.getAmount();
				
			}
		}
	}

}
