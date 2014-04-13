package com.thing.randomthing;

public class AndroidPhoneDevice {
	private String id;
	private String activity;
	private double latitude;
	private double longitude;
	public AndroidPhoneDevice(String id, String activity, double latitude, double longitude){
		this.id = id;
		this.activity = activity;
		this.latitude = latitude;
		this.longitude = longitude;
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
}
