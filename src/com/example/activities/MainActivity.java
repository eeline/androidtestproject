package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asynctasks.QueryTwitterService;
import com.example.testproject.R;

public class MainActivity extends Activity {
	private static String DEBUG_TAG = MainActivity.class.getName() + ": ";
	private static String QUERY_GOOGLE = "https://www.google.com/?q=";
	private Toast toast;
	//private PackageManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//media = new MediaManager(getApplicationContext());
		
		//if(media == null)
			//Log.e(DEBUG_TAG + "onCreate()", "media is null");
		
		} catch(Exception e){
			ExceptionHandling.handleException(e, "onCreate()", DEBUG_TAG);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//TODO onCreateOptionsMenu(Menu) do something with this
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View view){
		EditText text = (EditText)findViewById(R.id.tutorialText);
		String message = text.getText().toString();
		//TODO switch these to using intents that are recognized by broadcastreceivers
		switch(view.getId()){
			case R.id.twitterButton:
				Intent twitterIntent = new Intent(this, QueryTwitterService.class);
				twitterIntent.putExtra(QueryTwitterService.USER_QUERY, message);
				super.startService(twitterIntent);
				break;
			default:
				//default should not be reachable
				Log.i(DEBUG_TAG + "onClick(View)", "no input");
		}
	}

	//START handlers	
	
	//END Handlers
	//START utility functions	
	@SuppressWarnings("unused")
	private void doToast(String message){
		toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	/* no use for this yet
	private void verifyAndStartActivity(Intent intent){
		manager = getPackageManager();
		ComponentName component = intent.resolveActivity(manager);
		
		if(component == null){
			Uri marketUri = Uri.parse("market://searchq=pname:com.example.activities");
			Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(marketUri);
		
		
			if(marketIntent.resolveActivity(manager) != null)
				startActivity(marketIntent);
			else
				Log.d(DEBUG_TAG + "verifyReceiverExists(Intent)", "Market client not available");
		}
		else
			startActivity(intent);
	}*/
	//END utility functions
	
	//START State Change Management
	@Override
	protected void onStop(){
		super.onStop();
		Log.i(DEBUG_TAG + "onStop()", "stopping");
	}
	
	protected void onResume(){
		super.onResume();
		Log.i(DEBUG_TAG + "onResume()", "resuming");
	}
	
	protected void onDestroy(){
		super.onDestroy();
		Log.i(DEBUG_TAG + "onDestroy()", "destroying");
	}
	
	protected void onPause(){
		super.onPause();
		Log.i(DEBUG_TAG + "onPause()", "pausing");
	}
	//END State Change Management
}
