package transactions;

public class Transaction {
	String type;
	String acctName;
	String acctNum;
	
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

	@Override
	public String toString() {
		return "Transaction [type=" + type + ", acctName=" + acctName + ", acctNum=" + acctNum + "]";
	}
}
