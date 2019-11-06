package backend;

public class Account implements Comparable{
	private String acct;
	private int amount;
	private String name;

	public Account(String acct, int amount, String name) {
		// TODO Auto-generated constructor stub
		this.acct = acct;
		this.amount = amount;
		this.name = name;
	}

	/**
	 * @return the acct
	 */
	protected String getAcct() {
		return acct;
	}

	/**
	 * @param acct the acct to set
	 */
	protected void setAcct(String acct) {
		this.acct = acct;
	}

	/**
	 * @return the amount
	 */
	protected int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	protected void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		String compareAcct=((Account)o).getAcct();
		return this.acct.compareTo(compareAcct);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "";
	}

}
