/**
 * 
 */
package backend;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
	 * Test method for {@link backend.backend#main(java.lang.String[])}.
	 */
	/*
	 * WDR BACKEND WHITE BOX TESTING
	 */
	@Test
    public void testWDRT1() throws Exception {
		//logout before logging in
		
		
		
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{"WDR 0000000 100 1234567 ***",
				"EOS",
				"EOS"/*mergedTSF contents*/};

		String c[] = new String[]{""/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
    }

	/*
	 * NEW BACKEND WHITE BOX TESTING
	 */
	@Test
    public void testNEWPath1() throws Exception {
		//logout before logging in
		
		
		
		
		String a[] = new String[]{"7654321 123 Jane Doe",
				"1234567 11607 John Doe",
				"1000002 74076 Jane Hancock",
				"1000001 74070 John Hancock"
				/*master accts contents*/};
		
		String b[] = new String[]{""/*mergedTSF contents*/};

		String c[] = new String[]{""/*expectedOutput*/};
		
		String d[] = new String[] {""};
		
        runAndTest(Arrays.asList(a), //
                Arrays.asList(b), //
                Arrays.asList(c), //
                Arrays.asList(d), true);
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
