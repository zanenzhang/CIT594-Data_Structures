package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;



public class TxtFileReader implements Reader {

	protected String txtFileName;
	
	public TxtFileReader(String filename) {
		
		this.txtFileName = filename;
	}	

	public List<Tweet> getAllTweets() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		List<Tweet> tweets = new ArrayList<Tweet>();
			
		FileReader fileReader = null;
		
		File myFile = new File(txtFileName);		

		fileReader = new FileReader(myFile);

		BufferedReader bufferedReader = new BufferedReader(fileReader);			
		
		String nextLine = null;

		nextLine = bufferedReader.readLine();
		
		while (nextLine != null) {
			
			String[] splitString;
			
			splitString = nextLine.split("[\t]");
			
			
			String tweetLocation = splitString[0];
			String tweetText = splitString[3];
			
			tweets.add(new Tweet(tweetLocation, tweetText));

			nextLine = bufferedReader.readLine();
		}
		
		fileReader.close();
		bufferedReader.close();
				
		return tweets;
	}

	@Override
	public List<State> getAllStates() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
