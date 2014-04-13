package com.thing.randomthing;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;



public class MainActivity extends ActionBarActivity {
	private LocationManager mLocationManager;
  	private String provider;
  	private String UNIQUE_ID;
  	public static final String PREFS_NAME = "thePrefs";
  	public int[] tempArray = {41,24,25,15,6,8,74,51};
  	TextView lat;
  	TextView lon;
  	Firebase fb;
    int x = 0;
    int y = 0;
    Button noButton;
    Button yesButton;
    String phoneNumber;
	  
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 

  	    TelephonyManager rMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        phoneNumber = rMgr.getLine1Number();

        
        fb = (new Firebase("https://chickenapp.firebaseIO.com")).child(phoneNumber);
        noButton = (Button)findViewById(R.id.noButton);
        yesButton = (Button)findViewById(R.id.yesButton);
        childListener();

  	  	mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        
        Timer time = new Timer();
        time.schedule( new TimerTask() {
			@Override
			public void run() {
				Location loc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				Log.d("locthingy", "LocTimer:" + loc);
	    	    fb.child("lat").setValue(loc.getLatitude());
	    	    fb.child("lon").setValue(loc.getLongitude());
			}
    	}, 0, 1000);
        
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
			}
			@Override
			public void onProviderDisabled(String provider) {
			}
			@Override
			public void onProviderEnabled(String provider) {
			}
			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {}
        });
    }
    
    private void childListener(){
    	fb.child("activity").addValueEventListener(new ValueEventListener(){

		

			@Override
			public void onDataChange(DataSnapshot snap) {
				
				
				//List<Map<String,Object>> s =  (List<Map<String,Object>>)
				
				//Map<String,Object> map = s.get(0);
				
				//String value = (String)map.get("activity");
				String inputString = "";
				inputString = (String) snap.getValue();
					
				if (inputString == null){
					return;
				}
				else if("otherfail".equals(inputString)){
					//TODO: DISPLAY SOMEONE SAID NO!
					((TextView)findViewById(R.id.daTextView)).setText("Some jerk said no!");
					noButton.setEnabled(false);
					yesButton.setEnabled(false);
				}
				else if(!("false".equals(inputString) || "success".equals(inputString) || "fail".equals(inputString))){
					//TODO: TEXT VIEW
					
					((TextView)findViewById(R.id.daTextView)).setText(inputString);
					((TextView)findViewById(R.id.daOtherTextView)).setText("Did you all participate?");
					noButton.setEnabled(true);
					yesButton.setEnabled(true);
				}
		
					
				
				
				
			}

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}

	
        	
        });
    }
        
      public void clickedDaButton(View v){
    	  Location loc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	  Log.d("locthingy", "LocTimer:" + loc);
    	  fb.child("lat").setValue(loc.getLatitude());
    	  fb.child("lon").setValue(loc.getLongitude());
    	//  b.setEnabled(false);
    	  /*
          Random rnd = new Random();
          fb.setValue(new AndroidPhoneDevice(phoneNumber, "false",tempArray[rnd.nextInt(tempArray.length)],
        		  tempArray[rnd.nextInt(tempArray.length)]));
		  */

      }
            
      public void pressedNo(View v){
    	  noButton.setEnabled(false);
    	  yesButton.setEnabled(false);
    	  fb.child("activity").setValue("fail");
    	  ((TextView)findViewById(R.id.daOtherTextView)).setText("Wow, you are a jerk!");
      }
      
      public void pressedYes(View v){
    	  noButton.setEnabled(false);
    	  yesButton.setEnabled(false);
    	  fb.child("activity").setValue("success");
    	  ((TextView)findViewById(R.id.daOtherTextView)).setText("Wow, much friendship, such teamwork!");
      }
}
