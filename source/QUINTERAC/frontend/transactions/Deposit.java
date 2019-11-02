package transactions;

public class Deposit extends Transaction {
	
	int amount;

	public Deposit(String acctNum, int amount) {
		super("DEP", "", acctNum);
		this.amount=amount;
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
		out = this.type + " " + this.acctNum + " " +  this.amount;
		return out;
	}

}
