package transactionClasses;

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
		//"CCC AAAA MMMM BBBB NNNN"
		//"tranCode toAcct amount fromAcct name"
		out = this.type + " 0000000 000 " + this.acctNum + " " + this.acctName;
		return out;
	}

}
