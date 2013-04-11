package com.example.asynctasks;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.client.ClientProtocolException;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.activities.DisplayMessageActivity;
import com.example.activities.ExceptionHandling;
import com.example.datatypes.SearchResponse;
import com.google.gson.Gson;

public class QueryTwitterService extends IntentService {
	private final String DEBUG_TAG = QueryTwitterService.class.toString() + ": ";
	private final String TWITTER_QUERY = "http://search.twitter.com/search.json?q=";
	public static final String RESULT_LIST= "RESULT_LIST";
	public static final String TWITTER_QUERY_RESPONSE = "TWITTER_QUERY_RESPONSE";
	public static final String USER_QUERY = "USER_QUERY";
	private static final String TWITTER_QUERY_SERVICE = "TWITTER_QUERY_SERVICE";
	private String query;
	
	public QueryTwitterService() {
		super(TWITTER_QUERY_SERVICE);
		// TODO Auto-generated constructor stub
	} 
	
	@Override
	public void onHandleIntent(Intent intent) {
		query = intent.getStringExtra(USER_QUERY);
		TwitterQueryResponse response = null;
		try {
			if(query == null || query =="")
				ExceptionHandling.handleException(new Exception("EMPTY OR NULL QUERY"), "parseTwitter(String)", DEBUG_TAG);
			
			 response = parseTwitter(query);
		} catch (ClientProtocolException e) {
			ExceptionHandling.handleException(e, "parseTwitter(String)", DEBUG_TAG);
		} catch (IOException e) {
			ExceptionHandling.handleException(e, "parseTwitter(String)", DEBUG_TAG);
		}
		
		Intent responseIntent = new Intent(this, DisplayMessageActivity.class);
		if(response == null)
			Log.e(DEBUG_TAG + "onHandleIntent()", "null response");
		responseIntent.putExtra(TWITTER_QUERY_RESPONSE, response);
		this.startActivity(responseIntent);
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
}
