package com.example.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TwitterQueryReceiver extends BroadcastReceiver {
	public static final String QUERY_ACTION = "Query_Action";
	
	public TwitterQueryReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
