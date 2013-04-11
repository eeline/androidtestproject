package com.example.activities;

import android.util.Log;

public class ExceptionHandling {
	private ExceptionHandling(){
		
	}
	public static void handleException(final Exception e, final String tag, final String DEBUG_TAG){
		Log.e(DEBUG_TAG + tag, e.toString());
		StackTraceElement[] elements = e.getStackTrace();
		for(StackTraceElement element : elements)
			Log.e(DEBUG_TAG + "stackTraceElement[]", element.toString());
	}
	
}
