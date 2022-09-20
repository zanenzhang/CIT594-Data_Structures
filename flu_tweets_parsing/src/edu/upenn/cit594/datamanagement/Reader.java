package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;
	
	public interface Reader{
		
		public List<Tweet> getAllTweets() throws IOException, ParseException;
		
		public List<State> getAllStates() throws IOException;
		
	}
	
	


