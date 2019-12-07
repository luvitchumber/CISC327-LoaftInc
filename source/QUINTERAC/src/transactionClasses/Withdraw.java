package transactionClasses;

public class Withdraw extends Transaction {
	
	private int amount;

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
		//"CCC AAAA MMMM BBBB NNNN"
		//"tranCode toAcct amount fromAcct name"
		out = this.type + " 0000000 " +  this.amount + " " + this.acctNum + " ***";
		return out;
	}

}
