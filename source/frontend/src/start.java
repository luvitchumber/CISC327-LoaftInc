import classes.frontend;
import classes.login;

public class start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// use this to call front end from cli with files
		
		//verify
		//call frontend now with parameters to create instance loop
		// call login to start instance
		//read valid_accts.txt
			// send to frontend
		frontend terminal1 = new frontend(valid_accts);
		terminal1.setState(login.loginMode());
	}

}
