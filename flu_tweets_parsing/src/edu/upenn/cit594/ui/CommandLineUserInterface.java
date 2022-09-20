package edu.upenn.cit594.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.util.Tweet;

public class CommandLineUserInterface{
	
	protected Processor processor;
	
	public CommandLineUserInterface(Processor processor) {
		
		if (processor == null) {
			
			return;
		}
				
		this.processor = processor;
	}
	
	public void start() throws IOException {
		
		if (this.processor == null) {
			
			return;
		}
				
		this.processor.setFluTweetsStates();
		
		this.processor.runLogger();
		
		printFluTweetStates(this.processor.getTweets());       //Displays the state names with the corresponding number of flu tweets to the console
	}
	
	public void printFluTweetStates(List<Tweet> tweets) {
		
		Map<String, Integer> fluTweetsPerState = new TreeMap(); //Uses a Treemap to store each state and their corresponding number of flu tweets in alphabetical order
		
		int tempCounter = 0;
		
		for (Tweet tweet : tweets) {
			
			String state = tweet.getState();
			
			if (fluTweetsPerState.containsKey(state)) {
				
				tempCounter = fluTweetsPerState.get(state);   //If the tweet is a flu tweet, increment the flu tweet counter for the corresponding state by 1
				
				tempCounter += 1;
				
				fluTweetsPerState.replace(state, tempCounter);
			
			} else {
				
				fluTweetsPerState.put(state, 1);
			}
		}
		
		String state;
		int tweetCount;
		
		Iterator<Entry<String, Integer>> it = fluTweetsPerState.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Map.Entry <String, Integer> pair = it.next();
			
			state = pair.getKey();
			tweetCount = pair.getValue();
			
			System.out.println(state + ": " + tweetCount);
		}
	}
}
