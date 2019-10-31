package transactions;

public class Deposit extends Transaction {
	
	double amount;

	public Deposit() {
		super("DEP");
		amount = -1;
	}
	
	public Deposit(String type, String acctName, String acctNum) {
		super(type, acctName, acctNum);
		this.amount=-1;
	}

	public Deposit(String type, String acctName, String acctNum, double amount) {
		super(type, acctName, acctNum);
		this.amount=amount;
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
		return "Deposit [amount=" + amount + "]";
	}

}
