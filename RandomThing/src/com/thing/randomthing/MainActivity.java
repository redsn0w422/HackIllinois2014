package com.thing.randomthing;

import java.util.Map;
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
      Button noButton;
      Button yesButton;
	  
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 

        
        fb = new Firebase("https://chickenapp.firebaseIO.com");
        noButton = (Button)findViewById(R.id.noButton);
        yesButton = (Button)findViewById(R.id.yesButton);
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
				
				Map<String, Object> map = (Map<String, Object>) snap.getValue();
				String inputString = (String) map.get("activity");
					
					
				if(inputString.equals("fail")){
					//TODO: DISPLAY SOMEONE SAID NO!
					((TextView)findViewById(R.id.daTextView)).setText("Some jerk said no!");
					noButton.setEnabled(false);
					yesButton.setEnabled(false);
				}
				else if(!inputString.equals("false")){
					//TODO: TEXT VIEW
					
					((TextView)findViewById(R.id.daTextView)).setText(inputString);
					noButton.setEnabled(true);
					yesButton.setEnabled(true);
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
          fb.getRoot().child(phoneNumber).setValue(new AndroidPhoneDevice(phoneNumber, "false",tempArray[rnd.nextInt(tempArray.length)],
        		  tempArray[rnd.nextInt(tempArray.length)]));

      }
            
      public void pressedNo(View v){
    	  noButton.setEnabled(false);
    	  yesButton.setEnabled(false);
    	  ((TextView)findViewById(R.id.daOtherTextView)).setText("Wow, you are a jerk");
      }
      
      public void pressedYes(View v){
    	  noButton.setEnabled(false);
    	  yesButton.setEnabled(false);
    	  ((TextView)findViewById(R.id.daOtherTextView)).setText("Wow, much friendship, such teamwork!");
      }
   
}
