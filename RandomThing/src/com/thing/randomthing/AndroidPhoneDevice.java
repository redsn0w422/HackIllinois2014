package com.thing.randomthing;

public class AndroidPhoneDevice {
	private double latitude;
	private double longitude;
	private String activity;
	public AndroidPhoneDevice(String activity, double latitude, double longitude){
		this.activity = activity;
		this.latitude = latitude;
		this.longitude = longitude;
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
