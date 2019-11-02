package transactions;

public class DeleteAcct extends Transaction {

	public DeleteAcct(String acctName, String acctNum) {
		super("DEL", acctName, acctNum);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out;
		out = this.type + " " + this.acctNum + " " +  this.acctName;
		return out;
	}

}
