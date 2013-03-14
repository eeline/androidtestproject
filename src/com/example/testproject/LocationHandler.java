package com.example.testproject;

import android.app.Activity;
import android.util.Log;

//TODO LocationHandler.class make this work
public class LocationHandler extends Activity {
	private static String DEBUG_TAG = LocationHandler.class.getName() + ": ";
	//private LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	
	public void getTheLocation() throws Exception {
		//Location recent = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		//final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		//if(!gpsEnabled)
			//Log.e(DEBUG_TAG + "getLocation()", "GPS PROVIDER NOT ENABLED");
		//Log.i(this.DEBUG_TAG + "getLocation()", "GPS PROVIDER ENABLED");
		//Log.i(DEBUG_TAG + "getLocation()", "loc: " + recent.toString());
		Log.i(DEBUG_TAG + "getTheLocation()", "got here safely");
	}
}
