package transactions;

public class Logout extends Transaction {

	public Logout() {
		// TODO Auto-generated constructor stub
		super("EOS");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out;
		out = this.type;
		return out;
	}

}
