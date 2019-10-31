package transactions;

public class Transfer extends Transaction {
	String acctTo;
	double amount;

	public Transfer() {
		super("XFR");
	}
	
	public Transfer(String type, String acctName, String acctNum) {
		super(type, acctName, acctNum);
		this.acctTo = null;
		this.amount = -1;
	}

	public Transfer(String type, String acctName, String acctNum, String acctTo, double amount) {
		super(type, acctName, acctNum);
		this.acctTo = acctTo;
		this.amount = amount;
	}

	/**
	 * @return the acctTo
	 */
	public String getAcctTo() {
		return acctTo;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transfer [acctTo=" + acctTo + ", amount=" + amount + "]";
	}

}
