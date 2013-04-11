package com.example.activities;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.asynctasks.QueryTwitterService;
import com.example.fragments.TwitterListViewFragment;
import com.example.testproject.R;

public class DisplayMessageActivity extends Activity {
	@SuppressWarnings("unused")
	private static String DEBUG_TAG = DisplayMessageActivity.class.getName() + ": ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_display_message);
		
		Intent intent = super.getIntent();
		FragmentManager manager = getFragmentManager();
		TwitterListViewFragment twitterListFragment = (TwitterListViewFragment)manager.findFragmentById(R.id.TwitterListEntryFragment);
		
		ArrayAdapter<String> adaptedMessages = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, intent.getStringArrayListExtra(QueryTwitterService.TWITTER_QUERY_RESPONSE));
		twitterListFragment.setListAdapter(adaptedMessages);
		// Show the Up button in the action bsar.
		setupActionBar();
	}
	/*
	private TextView setupTextView(String message) {
		TextView textView = new TextView(this);
		textView.setText(message);
		return textView;
		
	}
	*/
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override 
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
	}

}
