package functions;

public class logout {
	
	public static boolean isLoggedOut(Terminal terminal) {
		if (terminal.getState().equals("out")) {
			return true;
		} else return false;
	}

	public Terminal logout(Terminal terminal, File tsfFile) {
		
		if (!isLoggedOut(terminal)) {
			terminal.setState("out");
		}
		
		return terminal;
		
	}
}