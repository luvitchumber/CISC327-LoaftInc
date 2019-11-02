package transactions;

public class Login extends Transaction {
	
	private String mode;

	public Login(String mode) {
		super("login");
		this.mode = mode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return null;
	}

}
