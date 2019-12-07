package transactionClasses;

public class Transaction {
	
	protected String type;
	protected String acctName;
	protected String acctNum;
	
	public Transaction() {
		this(null,null,null);
	}
	
	public Transaction(String type) {
		this(type,null,null);
	}

	public Transaction(String type, String acctName, String acctNum) {
		this.type = type;
		this.acctName = acctName;
		this.acctNum = acctNum;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the acctName
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * @return the acctNum
	 */
	public String getAcctNum() {
		return acctNum;
	}
	
	public int getAmount() {
		return 0;		
	}

	@Override
	public String toString() {
		System.err.println("Error called parent toString method, call correct subclass method");
		return "Call implemented toString method for subclass instead";
	}
}
