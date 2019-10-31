package transactions;

public class Login extends Transaction {
	String mode;

	public Login(String mode) {
		super("login");
		this.mode = mode;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return null;
	}

}
