package transactions;

public class Withdraw extends Transaction {
	
	double amount;

	public Withdraw() {
		super("WDR");
		amount = -1;
	}

	public Withdraw(String type, String acctName, String acctNum) {
		super(type, acctName, acctNum);
		amount = -1;
	}
	
	public Withdraw(String type, String acctName, String acctNum, double amount) {
		super(type, acctName, acctNum);
		this.amount = amount;
	}
	public Withdraw(String acctName, String acctNum, double amount) {
		super("WDR", acctName, acctNum);
		this.amount = amount;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
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
