package transactions;

public class DeleteAcct extends Transaction {

	public DeleteAcct() {
		super("DEL");
	}

	public DeleteAcct(String type, String acctName, String acctNum) {
		super(type, acctName, acctNum);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeleteAcct [type=" + type + ", acctName=" + acctName + ", acctNum=" + acctNum + "]";
	}

}
