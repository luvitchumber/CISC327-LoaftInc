package transactions;

public class Withdraw extends Transaction {
	
	int amount;

	public Withdraw() {
		super("WDR");
		amount = -1;
	}

	public Withdraw(String acctName, String acctNum) {
		super("WDR", acctName, acctNum);
		amount = -1;
	}
	
	public Withdraw(String acctName, String acctNum, int amount) {
		super("WDR", acctName, acctNum);
		this.amount = amount;
	}
	public Withdraw(String acctNum, int amount) {
		super("WDR", "", acctNum);
		this.amount = amount;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out;
		out = this.type + " " + this.amount + " " +  this.acctNum;
		return out;
	}

}
