package transactions;

public class CreateAcct extends Transaction {

	public CreateAcct() {
		super("NEW");
	}
	
	public CreateAcct(String acctName, String acctNum) {
		super("NEW", acctName, acctNum);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreateAccts [type=" + type + ", acctName=" + acctName + ", acctNum=" + acctNum + "]";
	}
	

}
