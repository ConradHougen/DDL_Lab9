package com.example.lab9;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MainActivity extends ActionBarActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    
    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        setContentView(R.layout.activity_main);

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
    	
    	 final float aThreshold = 2;
    	 TextView t = (TextView)findViewById(R.id.gesture);
    	

	        if(event.sensor.getType()==Sensor.TYPE_LINEAR_ACCELERATION){

	             // assign directions
	        	 //t.setText("Conrad is lame");
	             float x=Math.abs(event.values[0]);

	             float y=Math.abs(event.values[1]);

	             float z=Math.abs(event.values[2]);

	             if (x < aThreshold && y < aThreshold && z < aThreshold) {

	                     return;

	             }

	             if (x > y && x > z) {
	            	
	            	 if(event.values[0] > 0)
	            	 {
	            		 t.setText("LEFT");
	            	 }
	            	 else
	            	 {
	            		 t.setText("RIGHT");
	            	 }

	             } else if (y > x && y > z)  {
	            	
	            	 if(event.values[1] > 0)
	            	 {
	            		 t.setText("DOWN");
	            	 }
	            	 else
	            	 {
	            		 t.setText("UP");
	            	 }


	             }else  {
	            	 
	            	 t.setText("ABORT!");

	             }

	         }
    }
}
