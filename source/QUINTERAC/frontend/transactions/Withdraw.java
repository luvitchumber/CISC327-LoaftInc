package transactions;

public class Withdraw extends Transaction {
	
	int amount;

	public Withdraw() {
		super("WDR");
		amount = -1;
	}

	public Withdraw(String type, String acctName, String acctNum) {
		super(type, acctName, acctNum);
		amount = -1;
	}
	
	public Withdraw(String type, String acctName, String acctNum, int amount) {
		super(type, acctName, acctNum);
		this.amount = amount;
	}
	public Withdraw(String acctName, String acctNum, int amount) {
		super("WDR", acctName, acctNum);
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
		return "Withdraw [amount=" + amount + "]";
	}

}
