package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;



public class CsvFileReader implements Reader {

	protected String csvFileName;
	
	protected ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
	
	public CsvFileReader(String name) {
		
		this.csvFileName = name;
	}
	
	public List<Tweet> getAllTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<State> getAllStates() throws IOException {
		// TODO Auto-generated method stub
		
		List<State> states = new ArrayList<State>();
		
		String stateName;
		String stateLatitude;
		String stateLongitude;
		
		double latitudeCoordinate;
		double longitudeCoordinate;
		
		
		FileReader fileReader = null;
		
		File myFile = new File(csvFileName);		

		fileReader = new FileReader(myFile);


		BufferedReader bufferedReader = new BufferedReader(fileReader);	
		
		
		String nextLine = null;

		nextLine = bufferedReader.readLine();
		
		while (nextLine != null) {
			
			String[] splitString;
			
			splitString = nextLine.split(",");   //Parsing out the state coordinates from the .csv file
			
			stateName = splitString[0];
			stateLatitude = splitString[1];
			stateLongitude = splitString[2];
			
			latitudeCoordinate = Double.parseDouble(stateLatitude);        //Converting the string coordinates to double
			longitudeCoordinate = Double.parseDouble(stateLongitude);
			
			states.add(new State(stateName, latitudeCoordinate, longitudeCoordinate));		

			nextLine = bufferedReader.readLine();
		}		
			
		fileReader.close();
		bufferedReader.close();
		
		return states;
	}	
	
}
