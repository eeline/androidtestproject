package asynctasks;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;
import android.util.Log;

public class InputStreamHandler {
	private static final String DEBUG_TAG = InputStreamHandler.class.toString() + ": ";

		//expects parsed and properly prepped URI
		public static InputStream retrieveStream(Uri uri) throws IOException, ClientProtocolException {
			DefaultHttpClient client = new DefaultHttpClient();
			
			HttpGet request = new HttpGet(uri.toString());
			
			//TODO find out if Android is JRE7 compliant yet
			//try(HttpResponse getResponse = client.execute(getRequest))
			HttpResponse response;
			try {
				response = client.execute(request);
				final int statusCode = response.getStatusLine().getStatusCode();
				
				if(statusCode != HttpStatus.SC_OK) {
					Log.e(DEBUG_TAG + "retrieveStream(Uri)", "Error " + statusCode + " for URL " + uri);
					return null;
				}
				
				HttpEntity responseEntity = response.getEntity();
				return responseEntity.getContent();
					
			} catch (ClientProtocolException e) {
				Log.e(DEBUG_TAG, e.toString());
				throw new ClientProtocolException(DEBUG_TAG);
			} catch (IOException e) {
				Log.e(DEBUG_TAG, e.toString());
				throw new IOException(DEBUG_TAG);
			}	
		}
}
