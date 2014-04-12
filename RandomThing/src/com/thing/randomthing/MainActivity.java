package com.thing.randomthing;

import java.util.Random;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;



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
	  
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 

        
        fb = new Firebase("https://chickenapp.firebaseIO.com");
        
        
        
        
        
        
        
        childListener();
        
        
        
    }
    
    private void childListener(){
    	fb.addChildEventListener(new ChildEventListener(){

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildChanged(DataSnapshot snap, String txt) {
				
				
				//List<Map<String,Object>> s =  (List<Map<String,Object>>)
				
				//Map<String,Object> map = s.get(0);
				
				//String value = (String)map.get("activity");
				
				Object input = snap.getValue();
				if(input instanceof String){
					String inputString = (String)input;
					
					
					if(inputString.equals("fail")){
						//TODO: DISPLAY SOMEONE SAID NO!
					}
					else if(!inputString.equals("false")){
						//TODO: TEXT VIEW
						
						((TextView)findViewById(R.id.daTextView)).setText(inputString);
					}
		
					
				}
				
				
				
			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
    }
        
      public void clickedDaButton(View v){
    	  Button b = (Button)v;
    	  
    	//  b.setEnabled(false);
    	  
    	  TelephonyManager rMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
          Random rnd = new Random();
          
          String phoneNumber = rMgr.getLine1Number();
          fb.getRoot().child(phoneNumber).setValue(new AndroidPhoneDevice("false",tempArray[rnd.nextInt(tempArray.length)],
        		  tempArray[rnd.nextInt(tempArray.length)]));
          
          
          
    	  
      }
       
   
}
