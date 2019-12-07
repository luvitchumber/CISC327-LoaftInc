package transactionClasses;

public class Login extends Transaction {
	
	private String mode;

	public Login(String mode) {
		super("login");
		this.setMode(mode);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return null;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
