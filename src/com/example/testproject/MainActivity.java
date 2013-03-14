package com.example.testproject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import asynctasks.InputStreamHandler;
import asynctasks.TwitterQueryResponse;

import com.example.datatypes.SearchResponse;
import com.google.gson.Gson;

public class MainActivity extends Activity {
	private MediaManager media;
	private static String DEBUG_TAG = MainActivity.class.getName() + ": ";
	private static String QUERY_GOOGLE = "https://www.google.com/?q=";
	private Toast toast;

	
	public static String EXTRA_MESSAGE = "com.example.testproject.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		try {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		media = new MediaManager(getApplicationContext());
		
		//if(media == null)
			//Log.e(DEBUG_TAG + "onCreate()", "media is null");
		
		} catch(Exception e){
			ExceptionHandling.handleException(e, "onCreate()", DEBUG_TAG);
		}
	}
	
	private void doToast(String message){
		toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
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
		
		switch(view.getId()){
			case R.id.inputButton:
				handleSendIntent(new Intent(this, DisplayMessageActivity.class), message);
				break;
			case R.id.twitterButton:
				handleTwitterQuery(message);
				break;
			case R.id.internetButton:
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(QUERY_GOOGLE + message));
				super.startActivity(intent);
				break;
			case R.id.mediaToggle:
				handleMediaToggle();
			default:
				Log.i(DEBUG_TAG + "onClick(View)", "no input");
		}
	}

	//START handlers	
	private void handleTwitterQuery(String message) {
		TwitterParserAsyncTask task = new TwitterParserAsyncTask();
		task.execute(message);
	}

	private void handleSendIntent(Intent intent, String message) {
		intent.putExtra(EXTRA_MESSAGE, message);
		super.startActivity(intent);
	}
	
	private void handleMediaToggle(){
		if (media.isPlaying())
			media.pause();
		else 
			media.play();
	}
	//END Handlers
	
	//START State Change Management
	@Override
	protected void onStop(){
		super.onStop();
		
		if(media != null)
			media.release();
		Log.i(DEBUG_TAG + "onStop()", "stopping");
	}
	
	protected void onResume(){
		super.onResume();
		Log.i(DEBUG_TAG + "onResume()", "resuming");
	}
	
	protected void onDestroy(){
		super.onDestroy();
		if(media != null)
			media.release();
		Log.i(DEBUG_TAG + "onDestroy()", "destroying");
	}
	
	protected void onPause(){
		super.onPause();
		if(media != null && media.isPlaying())
			media.pause();
		Log.i(DEBUG_TAG + "onPause()", "pausing");
	}
	//END State Change Management
	
	private class TwitterParserAsyncTask extends AsyncTask<String, Integer, TwitterQueryResponse> {
		private final String DEBUG_TAG = TwitterParserAsyncTask.class.toString() + ": ";
		private final String TWITTER_QUERY = "http://search.twitter.com/search.json?q=";
		
		@Override
		//TODO reimplement for multiple searches
		protected TwitterQueryResponse doInBackground(String... params) {
			try {
				return parseTwitter((String) params[0]);
			} catch (ClientProtocolException e) {
				ExceptionHandling.handleException(e, "parseTwitter(String)", DEBUG_TAG);
			} catch (IOException e) {
				ExceptionHandling.handleException(e, "parseTwitter(String)", DEBUG_TAG);
			}	
			
			return null;
		}
		
		private TwitterQueryResponse parseTwitter(String message) throws IOException, ClientProtocolException {
			Gson gson = new Gson();
			InputStream source = null;
			try {
				source = InputStreamHandler.retrieveStream(Uri.parse(TWITTER_QUERY + message));
				
				Reader reader = new InputStreamReader(source);
				SearchResponse response =gson.fromJson(reader, SearchResponse.class);
				
				return new TwitterQueryResponse(response.query, response.results);
				
			} finally{
				source.close();
			}
		}
		
		@Override 
		protected void onPostExecute(TwitterQueryResponse response){
			doToast(response.getQuery());
		}

	}
}
