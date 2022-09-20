package edu.upenn.cit594.util;

public class Tweet {
	
	private String location;
	private String text;
	private String state;
	private double latitudeCoordinate;
	private double longitudeCoordinate;
	private boolean fluTweetStatus = false;
	
	public Tweet(String location, String text){
		
		this.location = location;
		this.text = text;
	}
	
	public String getLocation() {
		
		return this.location;
	}
	
	public String getText() {
		
		return this.text;
	}
	
	public void setState(String state) {
		
		this.state = state;
	}
	
	public String getState() {
		
		return this.state;
	}
	
	public void setFluStatus(boolean bool) {
		
		this.fluTweetStatus = bool;
	}
	
	public boolean getFluTweetStatus() {
		
		return this.fluTweetStatus;
	}
	
	
	public void setLatitude(double latitude) {
		
		this.latitudeCoordinate = latitude;
	}
	
	public void setLongitude(double longitude) {
		
		this.longitudeCoordinate = longitude;
	}
	
	public double getLatitude() {
		
		return this.latitudeCoordinate;
	}
	
	public double getLongitude() {
		
		return this.longitudeCoordinate;
	}

}
