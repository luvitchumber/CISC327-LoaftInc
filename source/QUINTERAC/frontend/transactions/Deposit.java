package transactions;

public class Deposit extends Transaction {
	
	int amount;

	public Deposit() {
		super("DEP");
		amount = -1;
	}
	
	public Deposit(String type, String acctName, String acctNum) {
		super(type, acctName, acctNum);
		this.amount=-1;
	}

	public Deposit(String type, String acctName, String acctNum, int amount) {
		super(type, acctName, acctNum);
		this.amount=amount;
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
		return "Deposit [amount=" + amount + "]";
	}

}
