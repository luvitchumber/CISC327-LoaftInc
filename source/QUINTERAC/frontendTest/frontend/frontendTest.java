/**
 * 
 */
package frontend;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author luvit
 *
 */
class frontendTest {

	/**
	 * Test method for {@link frontend.frontend#main(java.lang.String[])}.
	 */
	//Login
	@Test
    public void testR1T1() throws Exception {
		//logout before logging in
		String a[] = new String[]{"logout","login atm", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Error: Selected transaction is unavailable, please login before continuing.", "Sample login: 'login atm' or 'login agent'"};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testR1T2() throws Exception {
		//createacct before logging in
		String a[] = new String[]{"createacct 1234567 JohnDoe", "login atm","logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Error: Selected transaction is unavailable, please login before continuing.","Sample login: 'login atm' or 'login agent'"};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
		
	@Test
    public void testR1T3() throws Exception {
		//deleteacct before logging in
		String a[] = new String[]{"deletacct 7654321 JohnDoe", "login atm", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Error: Selected transaction is unavailable, please login before continuing.","Sample login: 'login atm' or 'login agent'"};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testR1T4() throws Exception {
		//deposit before logging in
		String a[] = new String[]{"deposit 7654321 10000", "login atm", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Error: Selected transaction is unavailable, please login before continuing.","Sample login: 'login atm' or 'login agent'"};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testR1T5() throws Exception {
		//withdraw before logging in
		String a[] = new String[]{"withdraw 7654321 10000", "login atm", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Error: Selected transaction is unavailable, please login before continuing.","Sample login: 'login atm' or 'login agent'"};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testR1T6() throws Exception {
		//transfer before logging in
		String a[] = new String[]{"transfer 1234567 10000 7654321","login atm","logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Error: Selected transaction is unavailable, please login before continuing.","Sample login: 'login atm' or 'login agent'"};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testR2T1() throws Exception {
		//login agent
		String a[] = new String[]{"login agent", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Please Login to begin session","Enter next transaction: "};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	
	@Test
    public void testR3T1() throws Exception {
		//login atm
		String a[] = new String[]{"login atm","logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Please Login to begin session","Enter next transaction: "};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	
	@Test
    public void testR4T1() throws Exception {
		//double login
		String a[] = new String[]{"login atm","login atm","logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Selected transaction is unavailable, please enter a valid transaction code"};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	//logout
	@Test
    public void testR6T1() throws Exception {
		//valid TSF upon logout
		String a[] = new String[]{"login atm", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Please Login to begin session","Enter next transaction: "};
		String d[] = new String[] {"EOS"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	
	
	@Test
    public void testR8T1() throws Exception {
		//check that new account num is 7 digits long and doesnt start with a 0
		String a[] = new String[]{"login agent", "createacct 0123456 JohnDoe", "logout"};
		String b[] = new String[]{""};
		String c[] = new String[] {"New account number must be 7 digits long and cannot start with a 0"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR8T1_2() throws Exception {
		// check that new account num is 7 digits long and doesnt start with a 0
		String a[] = new String[]{"login agent", "createacct 123456789123456 JohnDoe", "logout"};
		String b[] = new String[]{""};
		String c[] = new String[] {"New account number must be 7 digits long and cannot start with a 0"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR8T2() throws Exception {
		//check that new account num is different from all other account nums
		String a[] = new String[]{"login agent", "createacct 1234567 JohnDoe", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"This account number exists already"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR8T3() throws Exception {
		// new account name format check
		String a[] = new String[]{"login agent", "createacct 1234567 John***Doe", "logout"};
		String b[] = new String[]{""};
		String c[] = new String[] {"New account name must be 3-30 alphanumeric characters and cannot start or end with a space"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR8T3_2() throws Exception {
		//new account name format check
		String a[] = new String[]{"login agent", "createacct 1234567 JohnDoeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", "logout"};
		String b[] = new String[]{""};
		String c[] = new String[] {"New account name must be 3-30 alphanumeric characters and cannot start or end with a space"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR10T1() throws Exception {
		//ensure the user gets confirmation after creating an account and that TSF output is correct
		String a[] = new String[]{"login agent", "createacct 1234567 JohnDoe", "logout"};
		String b[] = new String[]{""};
		String c[] = new String[] {"Account created", "Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("NEW 1234567 000 0000000 johndoe", "EOS"), false);
    }
	
	@Test
    public void testR11T1() throws Exception {
		//ensure the user gets rejected from creating an account if they are not in agent mode
		String a[] = new String[]{"login atm", "createacct 1234567 JohnDoe", "logout"};
		String b[] = new String[]{""};
		String c[] = new String[] {"Need privileged mode to Create Account"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR12T1() throws Exception {
		//check that delete account num is 7 digits long and doesnt start with a 0
		String a[] = new String[]{"login agent", "deleteacct 7654321 JaneDoe", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"The account number to be deleted does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR13T1() throws Exception {
		//check that we cannot make a transaction on a deleted account
		String a[] = new String[]{"login agent", "deleteacct 7654321 JaneDoe", "deposit 7654321 10000", "logout"};
		String b[] = new String[]{"7654321"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("DEL 0000000 000 7654321 janedoe", "EOS"), true);
    }
	
	@Test
    public void testR13T2() throws Exception {
		// check that we cannot make a transaction on a deleted account
		String a[] = new String[]{"login agent", "deleteacct 7654321 JaneDoe", "withdraw 7654321 10000", "logout"};
		String b[] = new String[]{"7654321"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("DEL 0000000 000 7654321 janedoe", "EOS"), true);
    }
	
	@Test
    public void testR13T3() throws Exception {
		// check that we cannot make a transaction on a deleted account
		String a[] = new String[]{"login agent", "deleteacct 7654321 JaneDoe", "transfer 7654321 10000 1234567", "logout"};
		String b[] = new String[]{"7654321", "1234567"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("DEL 0000000 000 7654321 janedoe", "EOS"), true);
    }
	
	@Test
    public void testR13T3_2() throws Exception {
		// check that we cannot make a transaction on a deleted account
		String a[] = new String[]{"login agent", "deleteacct 7654321 JaneDoe", "transfer 1234567 10000 7654321", "logout"};
		String b[] = new String[]{"7654321", "1234567"};
		String c[] = new String[] {"Please enter correct account number to send to"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("DEL 0000000 000 7654321 janedoe", "EOS"), true);
    }
	
	@Test
    public void testR15T1() throws Exception {
		// ensure the user gets confirmation that the account was deleted
		String a[] = new String[]{"login agent", "deleteacct 7654321 JaneDoe", "logout"};
		String b[] = new String[]{"7654321"};
		String c[] = new String[] {"Account deleted", "Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("DEL 0000000 000 7654321 janedoe", "EOS"), false);
    }
	
	@Test
    public void testR16T1() throws Exception {
		//ensure the user gets rejected from deleting an account if they are not in agent mode
		String a[] = new String[]{"login atm", "deleteacct 7654321 JaneDoe", "logout"};
		String b[] = new String[]{"7654321"};
		String c[] = new String[] {"Need privileged mode to Delete Account"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR17T1() throws Exception {
    	//inputted account number correct format test
		String a[] = new String[]{"login atm", "deposit 1234 10000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR18T1() throws Exception {
    	//inputted account is not valid test
		String a[] = new String[]{"login atm", "deposit 1234568 10000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR19T1() throws Exception {
    	//deposit amount validation test
		//amount < 3 digits
		String a[] = new String[]{"login atm", "deposit 1234567 10", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Please enter correct amount and account number"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
        //amount > 8 digits 
         a = new String[]{"login atm", "deposit 1234567 100000000", "logout"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }

	@Test
    public void testR19T2() throws Exception {
    	// ATM Mode transaction limit test
		String a[] = new String[]{"login atm", "deposit 1234567 200100", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected transaction exceeds terminal limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }


	@Test
    public void testR19T3() throws Exception {
    	// ATM Mode transaction limit test
		String a[] = new String[]{"login atm", "deposit 1234567 500100", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected transaction exceeds terminal limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testmaxDepositDaily() throws Exception {
    	//cannot deposit above 500000 daily in atm mode
		String a[] = new String[]{"login atm", "deposit 1234567 100000", 
				"deposit 1234567 100000", 
				"deposit 1234567 100000",
				"deposit 1234567 100000", 
				"deposit 1234567 100000", 
				"deposit 1234567 100000", 
				"logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected transaction exceeds daily transaction limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("DEP 1234567 100000 0000000 ***", "DEP 1234567 100000 0000000 ***", "DEP 1234567 100000 0000000 ***",
                		      "DEP 1234567 100000 0000000 ***", "DEP 1234567 100000 0000000 ***", "EOS"), true);
    }
	
	@Test
    public void testR19T4() throws Exception {
    	// Agent Mode no transaction limit test
		String a[] = new String[]{"login agent", "deposit 1234567 99999999", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("DEP 1234567 99999999 0000000 ***","EOS"), false);
    }
	
	@Test
    public void testR20T1() throws Exception {
    	//ensure transfer works correctly
		String a[] = new String[]{"login agent", "deposit 1234567 10000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Please Login to begin session","Enter next transaction: "};
	    runAndTest(Arrays.asList(a), //
	               Arrays.asList(b), //
	               Arrays.asList(), //
	               Arrays.asList("DEP 1234567 10000 0000000 ***","EOS"), false);
    }
	
	///////////////////////////////////////////////////////////////////Withdraw
	
	@Test
    public void testR21T2() throws Exception {
    	//make sure withdrawal account exists
		String a[] = new String[]{"login atm", "withdraw 1234568 10000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR21T3() throws Exception {
    	//cannot withdraw above 100000 in atm mode
		String a[] = new String[]{"login atm", "withdraw 1234567 110000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected transaction exceeds terminal limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR22T1_2_3_4_5() throws Exception {
    	//cannot withdraw above 100000 in atm mode
		String a[] = new String[]{"login atm", "withdraw 1234567 100000", 
				"withdraw 1234567 100000", 
				"withdraw 1234567 100000",
				"withdraw 1234567 100000", 
				"withdraw 1234567 100000", 
				"withdraw 1234567 100000", 
				"logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected transaction exceeds daily transaction limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("WDR 0000000 100000 1234567 ***", "WDR 0000000 100000 1234567 ***", "WDR 0000000 100000 1234567 ***",
                		      "WDR 0000000 100000 1234567 ***", "WDR 0000000 100000 1234567 ***", "EOS"), true);
    }
	
	@Test
    public void testR22T6() throws Exception {
    	//cannot withdraw above 100000 in atm mode
		String a[] = new String[]{"login atm", "withdraw 1234567 510000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected transaction exceeds terminal limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR23T1() throws Exception {
    	//successful atm withdraw test
		String a[] = new String[]{"login atm", "withdraw 1234567 10000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("WDR 0000000 10000 1234567 ***", "EOS"), false);
    }
	
	@Test
    public void testR24T1() throws Exception {
    	//no limit in agent mode
		String a[] = new String[]{"login agent", "withdraw 1234567 99999999", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("WDR 0000000 99999999 1234567 ***", "EOS"), false);
    }
	
	@Test
    public void testR24T2() throws Exception {
    	//agent cannot withdraw more than 99999999
		String a[] = new String[]{"login agent", "withdraw 1234567 100000000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Please enter correct amount and account number"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	////////////////////////////transfer
	
	@Test
    public void testR25T2() throws Exception {
    	//ensure transfers only to/from existing accounts
		//incorrect to account 
		String a[] = new String[]{"login atm", "transfer 1234569 1234567 1000", "logout"};
		String b[] = new String[]{"1234567","1234568"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
        a = new String[]{"login atm", "transfer 1234567 1234569 1000", "logout"};
        c = new String[] {"Please enter correct account number to send to"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR25T3() throws Exception {
    	//ensure maximum transfer for atm mode is 10000
		String a[] = new String[]{"login atm", "transfer 1234567 1234568 1000001", "logout"};
		String b[] = new String[]{"1234567","1234568"};
		String c[] = new String[] {"Selected transaction exceeds terminal limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	
	@Test
    public void testmaxTransDaily() throws Exception {
    	//cannot deposit above 500000 daily in atm mode
		String a[] = new String[]{"login atm", "transfer 1234567 1234568 500000", 
				"transfer 1234567 1234568 500000", 
				"transfer 1234567 1234568 100000", 
				"logout"};
		String b[] = new String[]{"1234567", "1234568"};
		String c[] = new String[] {"Selected transaction exceeds daily transaction limit"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("XFR 1234568 500000 1234567 ***",
                		"XFR 1234568 500000 1234567 ***",
                		"EOS"), true);
    }
	
	@Test
    public void testR26T1() throws Exception {
    	//ensure maximum transfer for agent mode is 99999999
		String a[] = new String[]{"login agent", "transfer 1234567 1234568 100000000", "logout"};
		String b[] = new String[]{"1234567","1234568"};
		String c[] = new String[] {"Please enter correct amount and account number"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR27T1() throws Exception {
    	//ensure transfer works correctly
		String a[] = new String[]{"login agent", "transfer 1234567 1234568 1000", "logout"};
		String b[] = new String[]{"1234567","1234568"};
		String c[] = new String[] {"Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("XFR 1234568 1000 1234567 ***","EOS"), false);
    }
	

	/**
     * Helper function to run the main function and verify the output
     * 
     * @param terminal_input                 A list of string as the terminal input
     *                                       to run the program
     * 
     * @param valid_accounts                 A list of valid accounts to be used for
     *                                       the test case
     * 
     * @param expected_terminal_tails        A list of string expected at the tail
     *                                       of terminal output
     * 
     * @param expected_transaction_summaries A list of string expected to be in the
     *                                       output transaction summary file
     *                                       
     * @param err 							 Indicates if the test is expected to 
     * 										 throw an error. 
     * 
     * @throws Exception
     */
    public void runAndTest(List<String> terminal_input, //
            List<String> valid_accounts, //
            List<String> expected_terminal_tails, //
            List<String> expected_transaction_summaries, boolean err) throws Exception {

        // setup parameters for the program to run
        // create temporary files
        File valid_account_list_file = File.createTempFile("valid-accounts", ".tmp");
        Files.write(valid_account_list_file.toPath(), String.join("\n", valid_accounts).getBytes());

        File transaction_summary_file = File.createTempFile("transactions", ".tmp");

        String[] args = { valid_account_list_file.getAbsolutePath(), transaction_summary_file.getAbsolutePath() };

        // setup user input
        String userInput = String.join(System.lineSeparator(), terminal_input);
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        // setup stdin & stdout:
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // run the program
        frontend.main(args);
        String[] printed_lines;
        // capture terminal outputs:
        if (err ==false) {
        printed_lines = outContent.toString().split("[\r\n]+");
        } else {
        printed_lines = errContent.toString().split("[\r\n]+");
        }
        // compare the tail of the terminal outputs:
        int diff = printed_lines.length - expected_terminal_tails.size();
        for (int i = 0; i < expected_terminal_tails.size(); ++i) {
            assertEquals(expected_terminal_tails.get(i), printed_lines[i + diff]);
        }

        // compare output file content to the expected content
        String actual_output = new String(Files.readAllBytes(transaction_summary_file.toPath()), "UTF-8");
        String[] lines = actual_output.split("[\r\n]+");
        for (int i = 0; i < lines.length; ++i)
            assertEquals(expected_transaction_summaries.get(i), lines[i]);

    }

    /**
     * Retrieve the absolute path of the files in the resources folder
     * 
     * @param relativePath The file's relative path in the resources folder
     *                     (/test/resources)
     * @return the absolute path of the file in the resource folder.
     */
    String getFileFromResource(String relativePath) {
        return new File(this.getClass().getResource(relativePath).getFile()).getAbsolutePath();
    }

}
