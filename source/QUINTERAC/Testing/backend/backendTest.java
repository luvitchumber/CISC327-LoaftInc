/**
 * 
 */
package backend;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author luvit
 *
 */
class backendTest {

	/**
	 * Test method for {@link backend.backend#main(java.lang.String[])}.
	 */
	/*
	 * WDR BACKEND WHITE BOX TESTING
	 */
	@Test
    public void testWDRT1() throws Exception {
		//Successful run for withdraw
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{"WDR 0000000 100 1234567 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine03",
				/*test specific output*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine06",
				"WDRStatementReached.TestingLine09",
				"WDRStatementReached.TestingLine11",
				"WDRStatementReached.TestingLine12",
				"WDRStatementReached.TestingLine14",
				/*clean up output*/
				"WDRStatementReached.TestingLine01",
				"WDRStatementReached.TestingLine01"
				};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	
	@Test
    public void testWDRT2() throws Exception {
		//EOS
		//"WDRStatementReached.TestingLine01"
		
		
		String a[] = new String[]{
				"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				/*test specific output*/
				/*clean up output*/
				"WDRStatementReached.TestingLine01",
				"WDRStatementReached.TestingLine01"
				};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }

	@Test
    public void testWDRT3() throws Exception {
		//error if it starts with 0, or its length is longer than 7 characters
		//WDRStatementReached.TestingLine03
		
		String a[] = new String[]{
				"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{
				"WDR 0000000 100 111111 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				/*test specific output*/
				"WDRStatementReached.TestingLine03",
				"WDRStatementReached.TestingLine03",
				"WDRStatementReached.TestingLine06", 
				"WDRStatementReached.TestingLine10",
				"WDRStatementReached.TestingLine08"
				//Stops after error
				};
		
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	
	@Test
    public void testWDRT4() throws Exception {
		//If there are cents in amount
		//"WDRStatementReached.TestingLine04"
		
		
		
		String a[] = new String[]{
				"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{
				"WDR 0000000 1.00 1234567 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				/*test specific output*/
				"WDRStatementReached.TestingLine03",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine04",
				"WDRStatementReached.TestingLine06",
				"WDRStatementReached.TestingLine09",
				"WDRStatementReached.TestingLine11",
				"WDRStatementReached.TestingLine12",
				"WDRStatementReached.TestingLine14",
				/*clean up output*/
				"WDRStatementReached.TestingLine01",
				"WDRStatementReached.TestingLine01"
				};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	
	@Test
    public void testWDRT5() throws Exception {
		//Amount is negative
		//"WDRStatementReached.TestingLine05",
		
		
		
		
		String a[] = new String[]{
				"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{
				"WDR 0000000 -100 1234567 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				/*test specific output*/
				"WDRStatementReached.TestingLine03",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine05",
				"WDRStatementReached.TestingLine06",
				"WDRStatementReached.TestingLine09",
				"WDRStatementReached.TestingLine11",
				"WDRStatementReached.TestingLine13"
				
				};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	
	@Test
    public void testWDRT6() throws Exception {
		//If not WDR (ABC), dont enter first switch case
		//"WDRStatementReached.TestingLine07",
		
		
		
		String a[] = new String[]{
				"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{
				"ABC 0000000 100 1234567 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				/*test specific output*/
				"WDRStatementReached.TestingLine03",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine07",
				//error after
				};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	@Test
    public void testWDRT7() throws Exception {
		//Account number does not exist
		//"WDRStatementReached.TestingLine03",
		
		
		
		String a[] = new String[]{
				"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{
				"WDR 0000000 100 2345678 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				/*test specific output*/
				"WDRStatementReached.TestingLine03",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine06",
				"WDRStatementReached.TestingLine10",
				"WDRStatementReached.TestingLine08",
				//errors after
				};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }
	@Test
    public void testWDRT8() throws Exception {
		//WDR more than account balance
		
		
		
		
		String a[] = new String[]{
				"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{
				"WDR 0000000 99999999 1234567 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};
		
		String c[] = new String[]{/*expectedOutput*/
				/*always output from reading master accts*/
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine02",
				/*test specific output*/
				"WDRStatementReached.TestingLine03",
				"WDRStatementReached.TestingLine02",
				"WDRStatementReached.TestingLine06",
				"WDRStatementReached.TestingLine09",
				"WDRStatementReached.TestingLine11",
				"WDRStatementReached.TestingLine12",
				"WDRStatementReached.TestingLine15"
				//errors after
				};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), false);
    }

	/*
	 * NEW BACKEND WHITE BOX TESTING
	 */
	
	@Test
    public void testNEWPath1() throws Exception {
		//Create new account path 1: correct inputs, successful account creation
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1000003 74076 Jane Hancock",
				"1000002 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{"NEW 1000001 000 0000000 JohnDoe",
				"EOS",
				"EOS" /*mergedTSF contents*/};

		String c[] = new String[]{""/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testNEWPath2() throws Exception {
		//Create new account path 2: new account amount not 0
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				/*master accts contents*/};
		
		String b[] = new String[]{"NEW 1234567 111 0000000 John Doe",
				"EOS",
				"EOS" /*mergedTSF contents*/};

		String c[] = new String[]{"Invalid Amount Entered for New Account"/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testNEWPath3() throws Exception {
		//Create new account path 3: account already exists
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				/*master accts contents*/};
		
		String b[] = new String[]{"NEW 1234567 000 0000000 John Doe",
				"EOS",
				"EOS" /*mergedTSF contents*/};

		String c[] = new String[]{"Cannot Create New Account, Account Number already exists"/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testNEWPath4() throws Exception {
		//Create new account path 4: invalid account name
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				/*master accts contents*/};
		
		String b[] = new String[]{"NEW 1000001 000 0000000 Nol??dsfiur",
				"EOS",
				"EOS" /*mergedTSF contents*/};

		String c[] = new String[]{"Cannot Create New Account, Invalid Account Name"/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }
	
	@Test
    public void testNEWPath5() throws Exception {
		//Create new account path 5: invalid amount 
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1000003 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{"NEW 1000002 00 0000000 John Doe",
				"EOS",
				"EOS" /*mergedTSF contents*/};

		String c[] = new String[]{"Invalid Amount Entered for New Account"/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }

	@Test
    public void testNEWPath6() throws Exception {
		//Create new account path 6: invalid account number
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1000003 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{"NEW 10000001 000 0000000 JohnDoe",
				"EOS",
				"EOS" /*mergedTSF contents*/};

		String c[] = new String[]{"Invalid Account Number"/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }

	/**
     * Helper function to run the main function and verify the output
     * 
     * @param masterAccts                 The contents of the masterAccts file
     * 
     * @param transactions                 The contents of the merged TSF
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
    public void runAndTest(List<String> masterAccts, //
            List<String> transactions, //
            List<String> expected_terminal_tails, //
            List<String> expected_transaction_summaries, boolean err) throws Exception {

        // setup parameters for the program to run
        // create temporary files
        File	transactions_file = File.createTempFile("transactions_file", ".tmp");
        Files.write(transactions_file.toPath(), String.join("\n", transactions).getBytes());

        File masterAccts_file = File.createTempFile("masterAccts", ".tmp");
        Files.write(masterAccts_file.toPath(), String.join("\n", masterAccts).getBytes());

        String[] args = { masterAccts_file.getAbsolutePath(), transactions_file.getAbsolutePath() };


        // setup stdin & stdout:
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // run the program
        try {
        	backend.main(args);
        }catch(IOException a) {
        		
        }catch(IllegalArgumentException b) {
        	
        }
        
        String[] printed_lines;
        // capture terminal outputs:
        if (err == false) {
        	printed_lines = outContent.toString().split("[\r\n]+");
        } else {
        	printed_lines = errContent.toString().split("[\r\n]+");
        }
        // compare the tail of the terminal outputs:
        int diff = printed_lines.length - expected_terminal_tails.size();
        for (int i = 0; i < expected_terminal_tails.size(); ++i) {
        	
            assertEquals(expected_terminal_tails.get(i), printed_lines[i + diff]);
        }
        
//        while expected
//        	for printed.size
//        		if match
//        			assertEq()expected,printed
//        		if printed.has no more line
//        			fail
//        end while
        		

        // compare output file content to the expected content
//        String actual_output = new String(Files.readAllBytes(transaction_summary_file.toPath()), "UTF-8");
//        String[] lines = actual_output.split("[\r\n]+");
//        for (int i = 0; i < lines.length; ++i)
//            assertEquals(expected_transaction_summaries.get(i), lines[i]);

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
