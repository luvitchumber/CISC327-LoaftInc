package transactionClasses;

public class CreateAcct extends Transaction {

	public CreateAcct(String acctName, String acctNum) {
		super("NEW", acctName, acctNum);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out;
		//"CCC AAAA MMMM BBBB NNNN"
		//"tranCode toAcct amount fromAcct name"
		out = this.type + " " + this.acctNum + " 000 0000000 " +  this.acctName;
		return out;
	}
	

}
