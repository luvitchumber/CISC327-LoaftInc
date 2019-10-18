package functions;

public class logout {
	
	public static boolean isLoggedOut(Terminal terminal) {
		if (terminal.getState == "out" ) {
			return true;
		} else return false;
	}

	public static Terminal logout(Terminal terminal, File tsfFile) {
		
		if (!isLoggedOut(terminal)) {
			terminal.setState == "out";
		}
		
	}
}