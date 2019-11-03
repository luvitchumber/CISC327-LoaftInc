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
	@Test
    public void testAppLogout() throws Exception {
		//logout before logging in
		String a[] = new String[]{"logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[]{"Error: Selected transaction is unavailable, please login before continuing."};
		String d[] = new String[] {""};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testR18T1() throws Exception {
    	//inputted account number correct format test
		//String input = new String(Files.readAllBytes(Paths.get("frontendTest/TestFiles/Deposit/input/R18T1.txt")));
		//String output = new String(Files.readAllBytes(Paths.get("frontendTest/TestFiles/Deposit/expectedOutput/R18T1.txt")));
		String a[] = new String[]{"login atm", "deposit 1234 10000", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Selected account does not exist"};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), true);
    }
	
	@Test
    public void testR18T2() throws Exception {
    	//inputted account is not valid test
		//String input = new String(Files.readAllBytes(Paths.get("frontendTest/TestFiles/Deposit/input/R18T1.txt")));
		//String output = new String(Files.readAllBytes(Paths.get("frontendTest/TestFiles/Deposit/expectedOutput/R18T1.txt")));
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
    	// deposit amount validation test
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
        //amount between 3-8 digits
        a = new String[]{"login atm", "deposit 1234567 10000", "logout"};
        c = new String[] {"Please Login to begin session","Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(), //
                Arrays.asList("DEP 1234567 10000 0000000 ***","EOS"), false);
    }

	@Test
    public void testAppR1() throws Exception {
		//successful logout 
		String a[] = new String[]{"login atm", "logout"};
		String b[] = new String[]{"1234567"};
		String c[] = new String[] {"Please Login to begin session","Enter next transaction: "};
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList("EOS"), false);
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
