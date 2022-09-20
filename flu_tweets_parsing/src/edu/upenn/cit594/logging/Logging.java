package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import edu.upenn.cit594.util.Tweet;

public class Logging {
	
	private PrintWriter printWriter;
	private String fileName = null;
	private static Logging instance = null;
	
	
	public Logging() {
		
	}	
	
	public static Logging getInstance() {   //Not passing any file name to the getInstance method as instructed on Piazza
		
		if (instance == null) {
			
			instance = new Logging();
		}
		
		return instance;
	}
	
	public void setFileName (String fileName) {  //Non-static method to set the output file as instructed on Piazza
		
		if (this.fileName != null) {
			
			this.closePrintWriter();
		}
		
		this.fileName = fileName;
	}	
	
	public void log(List<Tweet> tweets) {
		
		if (fileName == null) {
			
			return;
		}
		
		if (tweets == null) {
			
			return;
		}
		
		for (Tweet tweet : tweets) {
			
			if (tweet.getFluTweetStatus() == true) {
				
				printWriter.print(tweet.getState());
				printWriter.print("\t");
				printWriter.print(tweet.getText());
				printWriter.print("\n");	
			}
		}
		
		this.closePrintWriter();
	}
	
	public void setPrintWriter() throws IOException {
		
		if (this.fileName != null) {
				
			File file = new File(this.fileName);	
			
			if ( file.exists()  ) {
				
				FileWriter fileWriter = new FileWriter(new File(this.fileName), true);
			    printWriter = new PrintWriter(fileWriter);
			    
			} else {
				
				FileWriter fileWriter = new FileWriter(new File(this.fileName), false);
				printWriter = new PrintWriter(fileWriter);
			}
			
		} else {
			
			System.out.println("Error. You do not have a file name!");
			return;
		}
	}
	
	public void closePrintWriter() {
	
		if (printWriter != null) {
			
			printWriter.flush();
			printWriter.close();			
		}
	}
}
