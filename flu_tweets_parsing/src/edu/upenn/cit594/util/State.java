package edu.upenn.cit594.util;

public class State {
	
	private String stateName;
	private double latitudeCoordinate;
	private double longitudeCoordinate;
	
	public State(String name, double latitudeCoordinate, double longitudeCoordinate){
		
		this.stateName = name;
		this.latitudeCoordinate = latitudeCoordinate;
		this.longitudeCoordinate = longitudeCoordinate;
	}

	public String getStateName() {
	
		return this.stateName;
	}
	
	public double getLatitude() {
		
		return this.latitudeCoordinate;
	}
	
	public double getLongitude() {
		
		return this.longitudeCoordinate;
	}

}
