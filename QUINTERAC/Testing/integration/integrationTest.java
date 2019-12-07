/*
 * Quinterac Integration Testing
 * Loaft Inc.
 * Author: Luvit Chumber
 * Date: December 6, 2019
 * */
package integration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;
import frontend.frontend;
import backend.backend;

public class integrationTest {

    private static String runFrontEndDaily(String[][] args,String [][] transactions) throws IOException {
    	String tsf_merged = "";
    	
    	for (int i = 0; i < args.length; i++) {
    		String userInput = String.join(System.lineSeparator(), transactions[i]);
            ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(in);
            
    		frontend.main(args[i]);
    		Scanner sc = new Scanner(new File(args[i][1]));
    		while(sc.hasNext()) {
    			tsf_merged += sc.nextLine() + System.lineSeparator();
    		}
    		sc.close();
    	}
    	tsf_merged += "EOS" + System.lineSeparator(); // as per requirements merged tsf contains empty tsf file at end
    	return tsf_merged;
    }
    
	public static void main(String[] args) throws IOException {
        
		// setup stdin & stdout:
	    PrintStream console = System.out; 
	    PrintStream outContent = new PrintStream(new ByteArrayOutputStream());
	    PrintStream errContent = new PrintStream(new ByteArrayOutputStream());
	    System.setOut(outContent);
	    System.setErr(errContent);
	    
	    // reset files to clean slate
	    File mAccts = new File(System.getProperty("user.dir")+"\\files\\masteraccts.txt");
	    File vAccts = new File(System.getProperty("user.dir")+"\\files\\validaccts.txt");
	    Files.write(mAccts.toPath(),"".getBytes());
	    Files.write(vAccts.toPath(),"0000000".getBytes()); 
	    
	    String t_in_day1[][] = { 
	    	{ "login agent",          //session 1
			  "createacct 1000000 TayyabA",
			  "createacct 1000001 LuvitC",
			  "createacct 1000002 SamirM",
			  "createacct 1000003 JessicaW",
			  "logout"}, 
	    	{ "login agent",                  //session 2
			  "createacct 9999999 JohnDoe420",
			  "createacct 5000001 JaneDee69",
			  "logout"}, 
	    	{ "login agent",                  //session 3
			  "createacct 7777777 BethanyWhite212",
			  "createacct 3030303 Walter101Grey",
			  "createacct 8300781 747JonathanBlue",
			  "logout"} };                   //END DAY 1
			 
	    String t_in_day2[][] = { 
	    	{ "login agent",	        //session 1
		      "deposit 1000000 50000",
			  "deposit 1000001 100",
			  "deposit 9999999 200000",
			  "logout"}, 
	    	{ "login agent",                  //session 2
			  "deposit 9999999 9500000",
			  "deposit 5000001 320",
			  "deposit 8300781 100000",
			  "deposit 3030303 671000",
			  "deposit 1000002 500",
			  "logout"}, 
	    	{ "login agent",                  //session 3
			  "deposit 7777777 8000000",
			  "deposit 3030303 500000",
			  "deposit 1000003 1500",
			  "deposit 5000001 400000",
			  "logout"} };					 //END DAY 2
			 
	    String t_in_day3[][] = { 
	    	{ "login atm",          //session 1
			  "transfer 1000000 1000001 1000",
			  "transfer 1000000 1000002 1000",
			  "logout"}, 
	    	{ "login agent",                  //session 2
			  "transfer 7777777 1000000 50000",
			  "transfer 3030303 8300781 7000",
			  "transfer 3030303 9999999 100000",
			  "transfer 5000001 1000003 320",
			  "logout"}, 
	    	{ "login agent",                  //session 3
			  "transfer 7777777 1000003 50000",
			  "transfer 9999999 1000000 150000",
			  "transfer 9999999 1000001 100000",
			  "transfer 9999999 1000002 10000",
			  "transfer 9999999 1000003 10000",
			  "logout"} };				     //END DAY 3
			  
	    String t_in_day4[][] = { 
	    	{ "login agent",          //session 1
			  "withdraw 9999999 8180000", 
			  "logout"}, 
	    	{ "login agent",                  //session 2
			  "withdraw 1000001 101100",
			  "withdraw 5000001 400000",
			  "withdraw 1000003 61820",
			  "withdraw 1000002 11500",
			  "withdraw 1000000 98000",
			  "withdraw 7777777 7900000",
			  "logout"}, 
	    	{ "login agent",                  //session 3
			  "withdraw 3030303 1064000",
			  "withdraw 8300781 107000",
			  "withdraw 1000000 150000",
			  "logout"} };				     //END DAY 4
			  
	    String t_in_day5[][] = { 
	    	{ "login agent",		    //session 1
			  "deleteacct 5000001 JaneDee69",
			  "transfer 9999999 1000000 100",
			  "transfer 9999999 1000001 100",
			  "withdraw 9999999 1349600",
			  "logout"}, 
	    	{ "login agent",                      //session 2
			  "deleteacct 7777777 BethanyWhite212",
			  "transfer 9999999 1000003 100",
			  "deleteacct 3030303 Walter101Grey",
			  "transfer 9999999 1000002 100",
			  "logout"}, 
	    	{ "login agent",                  //session 3
			  "deleteacct 8300781 747JonathanBlue",
			  "deleteacct 9999999 JohnDoe420",
			  "logout"} };					 //END DAY 5
	   
	    String t_in[][][] = {t_in_day1,t_in_day2,t_in_day3,t_in_day4,t_in_day5};
	    
	 // declare all filenames
	    String mTsfFN[] = 		{	System.getProperty("user.dir")+"\\files\\mergedtsfday1.txt",
						    		System.getProperty("user.dir")+"\\files\\mergedtsfday2.txt",
						    		System.getProperty("user.dir")+"\\files\\mergedtsfday3.txt",
						    		System.getProperty("user.dir")+"\\files\\mergedtsfday4.txt",
						    		System.getProperty("user.dir")+"\\files\\mergedtsfday5.txt"};
	    
	    String tsfFN[][] = 	  {	{ System.getProperty("user.dir")+"\\files\\tsfday1_instance1.txt",
	    							System.getProperty("user.dir")+"\\files\\tsfday1_instance2.txt", 
	    							System.getProperty("user.dir")+"\\files\\tsfday1_instance3.txt"},
	    						{ System.getProperty("user.dir")+"\\files\\tsfday2_instance1.txt",
		    						System.getProperty("user.dir")+"\\files\\tsfday2_instance2.txt", 
		    						System.getProperty("user.dir")+"\\files\\tsfday2_instance3.txt"},
    							{ System.getProperty("user.dir")+"\\files\\tsfday3_instance1.txt",
		    						System.getProperty("user.dir")+"\\files\\tsfday3_instance2.txt", 
		    						System.getProperty("user.dir")+"\\files\\tsfday3_instance3.txt"},
    							{ System.getProperty("user.dir")+"\\files\\tsfday4_instance1.txt",
		    						System.getProperty("user.dir")+"\\files\\tsfday4_instance2.txt", 
		    						System.getProperty("user.dir")+"\\files\\tsfday4_instance3.txt"},
    							{ System.getProperty("user.dir")+"\\files\\tsfday5_instance1.txt",
		    						System.getProperty("user.dir")+"\\files\\tsfday5_instance2.txt", 
		    						System.getProperty("user.dir")+"\\files\\tsfday5_instance3.txt"}};
	   
	    String vAcctsFN = System.getProperty("user.dir")+"\\files\\validaccts.txt";
	    String mAcctsFN = System.getProperty("user.dir")+"\\files\\masteraccts.txt";
	    
	    for (int i = 0; i < 5 ; i++) {	  
	    	// create temporary files for tsf transaction
		    File transaction_summary_file1 = new File(tsfFN[i][0]);
		    File transaction_summary_file2 = new File(tsfFN[i][1]);
		    File transaction_summary_file3 = new File(tsfFN[i][2]);
		    
		    // setup parameters for the program to run
		    String[][] args_to_frontend = { { vAcctsFN, transaction_summary_file1.getAbsolutePath() },
		    								{ vAcctsFN, transaction_summary_file2.getAbsolutePath() },
		    								{ vAcctsFN, transaction_summary_file3.getAbsolutePath() }};
		    
		    
		    // run frontend (3 instances)
		    String tsf_merged = runFrontEndDaily(args_to_frontend, t_in[i]);
		    
		    //prepare merged tsf
		    File mergedtsf = new File(mTsfFN[i]);
		    Files.write(mergedtsf.toPath(), String.join("\n", tsf_merged).getBytes());
		    
		    // run backend
		    String [] args_to_backend = { mAcctsFN, mergedtsf.getAbsolutePath()};
		    
		    backend.main(args_to_backend);
		    
		    System.setOut(console);
		    
		    // Print Debug Report Each Day
		    mAccts = new File(mAcctsFN);
		    
		    System.out.println("Day " + (i+1));
		    System.out.println("> Tranactions being issued");
		    
		    for(int j = 0; j<t_in[i].length;j++) {
		    	System.out.println(">> FrontEnd #" + (j+1));
		    	for(int k = 0; k<t_in[i][j].length; k++) {
		    		System.out.println(t_in[i][j][k]);
		    	}
		    }
		    
		    System.out.println("\n> Merged TSF:");
		    System.out.println(tsf_merged);
		    System.out.println("\n> Updated Master Accounts:");
		    
		    Scanner sc = new Scanner(mAccts);
		    while (sc.hasNext()) {
		    	System.out.println(sc.nextLine());
		    }
		    sc.close();
		    
		    System.out.println("\n");
		    System.setOut(outContent);
	  }
	}
}
