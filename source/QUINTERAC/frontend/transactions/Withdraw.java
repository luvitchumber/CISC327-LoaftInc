package transactions;

public class Withdraw extends Transaction {
	
	int amount;

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
