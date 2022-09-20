package edu.upenn.cit594;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.datamanagement.CsvFileReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.datamanagement.JsonFileReader;
import edu.upenn.cit594.datamanagement.TxtFileReader;
import edu.upenn.cit594.logging.Logging;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class Main {

	public static void main(String[] args) {    //To create objects and set up relationships by passing one object to the constructor of another
				
		int commandArguments = args.length;
		
		if (commandArguments != 3) {
			
			System.out.println("Error! The number of arguments is incorrect!");
			return;
		}
		
		String tweetsInputFile = args[0];
		String statesInputFile = args[1];
		String logFile = args[2];
		
				
		Reader tweetReader;
		
		boolean jsonExtension = matchFileExtension(tweetsInputFile, ".json");   //To check that the extension for the first file is correct
		boolean txtExtension = matchFileExtension(tweetsInputFile, ".txt");
		
		if (jsonExtension == true) {
			
			tweetReader = new JsonFileReader(tweetsInputFile);
			
		} else if (txtExtension  == true) {
			
			tweetReader = new TxtFileReader(tweetsInputFile);			
			
		} else {			
			
			System.out.println("Error! The first file argument is incorrect! The extension should be .json or .txt");			
			return;
		}
								
		
		Reader stateReader;
		
		boolean cvsExtension = matchFileExtension(statesInputFile, ".csv");
		
		if (cvsExtension == false) {
			
			System.out.println("Error! The second file argument is incorrect! The extension should be .csv");			
			return;
		
		} else {
			
			stateReader = new CsvFileReader(statesInputFile);
		}

		Logging logger = Logging.getInstance();    //Does not pass in a filename because we want to be able to change the filename dynamically 
		logger.setFileName(logFile);
		
		Processor processor = null;
		
		try {
			
			processor = new Processor(tweetReader, stateReader, logger);        //Passes the readers and logger to the processor
			
		} catch (IOException | ParseException e) {         //If there is an IOException or ParseException, the program will display an error and terminate
			// TODO Auto-generated catch block
			e.printStackTrace();			
			return;
		}		
		
		CommandLineUserInterface ui = new CommandLineUserInterface(processor);      //Passes the processor through to the user interface
		
		try {
			
			ui.start();   //Starts the application via the user interface
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return;
		}    
	}
	
	
	public static boolean matchFileExtension(String fileName, String match) {     //Function to check file extensions, placed in Main after discussions with a TA
		
		String extension = "";

		int i = fileName.lastIndexOf('.');
		
		if (i > 0) {
			
		    extension = fileName.substring(i);
		    
		    extension = extension.toLowerCase();
		    
		    if (extension.equals(match)) {
				
				return true;
			
			} else {
				
				return false;
			}
		}
		
		return false;		
	}
}
