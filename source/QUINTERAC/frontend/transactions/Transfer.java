package transactions;

public class Transfer extends Transaction {
	
	private String acctTo;
	private int amount;

	public Transfer(String acctNum, int amount, String acctTo) {
		super("XFR", "", acctNum);
		this.amount = amount;
		this.acctTo = acctTo;
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
	public int getAmount() {
		return amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out;
		out = this.type + " " + this.acctTo + " " +  this.amount + " " + this.acctNum;
		return out;
	}

}
