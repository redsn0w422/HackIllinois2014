package com.thing.randomthing;

public class AndroidPhoneDevice {
	private String id;
	private String activity;
	private double latitude;
	private double longitude;
	private int points;
	public AndroidPhoneDevice(String id, String activity, double latitude, double longitude, int points){
		this.id = id;
		this.activity = activity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.points = points;
	}
	
	public String getId(){
		return this.id;
	}
	public String getActivity(){
		return this.activity;
	}
	public double getLat(){
		return latitude;
	}
	public double getLon(){
		return longitude;
	}
	public double getPoints(){
		return points;
	}
}
