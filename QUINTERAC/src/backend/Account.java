package backend;

public class Account implements Comparable<Object>{
	private String acct;
	private int amount;
	private String name;

	//Sets account number, amount in account and name
	public Account(String acct, int amount, String name) {
		// TODO Auto-generated constructor stub
		this.acct = acct;
		this.amount = amount;
		this.name = name;
	}

	/**
	 * @return the acct
	 */
	
	//Returns account number
	protected String getAcct() {
		return acct;
	}

	/**
	 * @param acct the acct to set
	 */
	//Sets the account number
	protected void setAcct(String acct) {
		this.acct = acct;
	}

	/**
	 * @return the amount
	 */
	//Returns amount in bank account
	protected int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	//Sets the amount in bank account
	protected void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the name
	 */
	//Returns name of account
	protected String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	//Sets name for the account
	protected void setName(String name) {
		this.name = name;
	}

	//override the method used to sort collections
	// now sorts using acct number
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		String compareAcct=((Account)o).getAcct();
		return this.acct.compareTo(compareAcct);
		//need to flip to sort in descending order
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//needs to be overridden when implemented compareTo
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acct == null) ? 0 : acct.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	//override to implement proper method when checking if ArrayList<Account>.contains(acct)
	@Override
	public boolean equals(Object obj) {

		boolean sameSame = false;
		
		if (obj instanceof Account) {
			sameSame = acct.equals(((Account) obj).getAcct());
		}
		
		return sameSame;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	//Returns a string that combines account number, amount and name
	//Used for Master accounts file
	@Override
	public String toString() {
		return acct + " " + amount + " " + name;
	}

}
