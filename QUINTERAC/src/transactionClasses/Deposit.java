package transactionClasses;

public class Deposit extends Transaction {
	
	private int amount;

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
		//"CCC AAAA MMMM BBBB NNNN"
		//"tranCode toAcct amount fromAcct name"
		out = this.type + " " + this.acctNum + " " +  this.amount + " 0000000 ***";
		return out;
	}

}
