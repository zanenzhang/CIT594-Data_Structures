package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;



public class JsonFileReader implements Reader {

	private static final String jsonArray = null;
	protected String jsonFileName;
	
	
	public JsonFileReader(String name) {
		
		this.jsonFileName = name;
	}
	
	public List<Tweet> getAllTweets() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		FileReader fileReader = null;
		
		File myFile = new File(jsonFileName);		

		fileReader = new FileReader(myFile);
		
		
		Object arr = null;		

		arr = new JSONParser().parse(fileReader);
		
		JSONArray jsonArr = (JSONArray) arr; 		
		
		JSONObject tweet = new JSONObject();
		
		
		for (int i = 0; i < jsonArr.size(); i++) {
			
			tweet = (JSONObject) jsonArr.get(i);
			
			String text = tweet.get("text").toString();		
			String location = tweet.get("location").toString();	
			
			tweets.add(new Tweet(location, text));
		}
		
		return tweets;
	}

	@Override
	public List<State> getAllStates() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
