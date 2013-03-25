package com.example.activities;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

public class MediaManager {
	private static String DEBUG_TAG = MediaManager.class.getName() + ": ";
	private MediaPlayer media;
	
	public MediaManager(Context context){
		this(context, Uri.parse("http://www.perlgurl.org/podcast/archives" + "/podcasts/PerlgurlPromo.mp3"));
	}
	
	//method expects a parsed Uri
	public MediaManager(Context context, Uri uri){
		media = MediaPlayer.create(context, uri);
		Log.i(DEBUG_TAG + "this(context, uri)", uri.toString());
	}
	
	public boolean isPlaying(){
		return media.isPlaying();
	}
	public void start(){
		media.start();
		Log.i(DEBUG_TAG + "start()", media.toString());
	}
	public void pause(){
		media.pause();
	}
	public void play(){
		media.start();
	}
	
	public void release(){
		media.stop();
		media.release();
		media = null;
	}
	
}
