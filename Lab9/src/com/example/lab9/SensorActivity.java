package com.example.lab9;
import android.hardware.Sensor;

import android.hardware.SensorEvent;

import android.hardware.SensorEventListener;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends MainActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(R.layout.activity_main);
        TextView t = (TextView)findViewById(R.id.gesture);
   	 	t.setText("Conrad is lame");
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
    	 t.setText("Conrad is lame");

	        if(event.sensor.getType()==Sensor.TYPE_LINEAR_ACCELERATION){

	             // assign directions

	             float x=Math.abs(event.values[0]);

	             float y=Math.abs(event.values[1]);

	             float z=Math.abs(event.values[2]);

	             if (x < aThreshold && y < aThreshold && z < aThreshold) {

	                     return;

	             }

	             if (x > y && x > z) {
	            	
	            	 if(event.values[0] > 0)
	            	 {
	            		 t.setText("Right");
	            	 }
	            	 else
	            	 {
	            		 t.setText("Left");
	            	 }

	             } else if (y > x && y > z)  {
	            	
	            	 if(event.values[1] > 0)
	            	 {
	            		 t.setText("Up");
	            	 }
	            	 else
	            	 {
	            		 t.setText("Down");
	            	 }


	             }else  {
	            	 
	            	 t.setText("ABORT!");

	             }

	         }
    }
}
