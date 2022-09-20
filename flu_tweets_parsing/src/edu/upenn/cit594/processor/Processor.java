package edu.upenn.cit594.processor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.datamanagement.CsvFileReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.logging.Logging;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;

public class Processor {
	
	protected Reader tweetReader;
	protected Reader stateReader;
	protected Logging logFile;
	
	protected List<Tweet> tweets;	
	protected List<State> states;
	
	
	public Processor(Reader tweetReader, Reader stateReader, Logging logFile) throws IOException, ParseException {
		// TODO Auto-generated constructor stub
		
		this.tweetReader = tweetReader;
		this.stateReader = stateReader;
		this.logFile = logFile;
		
		tweets = tweetReader.getAllTweets();	  //Calls the reader to retrieve all tweets from the .json or .txt file
		states = stateReader.getAllStates();     //Calls the reader to retrieve the states and their location coordinates
	}
	
	public static double calculateDistance(double tweetLatitude, double tweetLongitude, double stateLatitude, double stateLongitude) {
		
		double distance = 0;
		
		double longDiff = stateLongitude - tweetLongitude;
		double latDiff = stateLatitude - tweetLatitude;
		
		longDiff = Math.pow(longDiff, 2);
		latDiff = Math.pow(latDiff, 2);
		double toRoot = longDiff + latDiff;
		distance = Math.sqrt(toRoot);      //Calculates the distance between two coordinates as given in the assignment
		
		return distance;
	}	
	
	public static void markFluTweets (Tweet tweet) {
		
		String tweetText = tweet.getText();
		
		String pattern = "\\b[\\W]*?[\\d]*?[Ff][Ll][Uu][\\d]*?[\\W]*?\\b";    //Case insensitive, ignores non-word characters such as hashtags
		
		Pattern p = Pattern.compile(pattern);	

		Matcher m = p.matcher(tweetText);
		
		if (m.find()) {
			
			tweet.setFluStatus(true);
			
		}	else {
			
			tweet.setFluStatus(false);
		}
	}
	
	
	public void calcLocationCoordinates(Tweet tweet) {  //To parse location coordinates from the given array format
		
		double latitudeCoordinate;
		double longitudeCoordinate;
		
		String locationPattern = "\\[([-]?\\d+[.]\\d+)[,\\s]+([-]?\\d+[.]\\d+)\\]";
		
		Pattern p = Pattern.compile(locationPattern);	
		
		String latitude = "";
		String longitude = "";
		Matcher m = p.matcher(tweet.getLocation());
		
		if (m.find()) {
			
			latitude = m.group(1);
			longitude = m.group(2);				

			latitudeCoordinate = Double.parseDouble(latitude);	
			longitudeCoordinate = Double.parseDouble(longitude);
			
			tweet.setLatitude(latitudeCoordinate);
			tweet.setLongitude(longitudeCoordinate);		
		}	
	}
	
	
	public void calculateState (Tweet tweet, List<State> states) {         
		
		String currentState = null;
		Double minimumDistance = 9999999999.999999;
		
		double tweetLatitude = tweet.getLatitude();
		double tweetLongitude = tweet.getLongitude();
		
		double distance = 0;
		
		for (State state : states) {
			
			double stateLatitude = state.getLatitude();	
			double stateLongitude = state.getLongitude();
			
			distance = Processor.calculateDistance(tweetLatitude, tweetLongitude, stateLatitude, stateLongitude);
			
			if (distance < minimumDistance) {
				
				currentState = state.getStateName();     //The state of origin is the closest provided reference point from the location of the tweet
				minimumDistance = distance;
			}
		}
		
		tweet.setState(currentState);	
	}
	
	public void setFluTweetsStates() {
		
		List<Tweet> tweets = this.tweets;
		
		for (Tweet tweet : tweets) {      //Parses the location coordinates
			calcLocationCoordinates (tweet);
		}
		
		for (Tweet tweet : tweets) {      //Calculates the state from where the tweet originated
			calculateState (tweet, this.states);
		}
		
		for (Tweet tweet : tweets) {      //Checks and marks each tweet if the tweet constitutes a flu tweet
			markFluTweets (tweet);
		}
	}
	
	public void runLogger() throws IOException {
		
		this.logFile.setPrintWriter();
		this.logFile.log(this.tweets);
	}
	
	public List<Tweet> getTweets() {
		
		return this.tweets;
	}
	
	public List<State> getStates() {
		
		return this.states;
	}
	
}
