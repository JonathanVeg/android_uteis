package wexpense.wallet.abastract;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class InternetRequests {
	// Is recommended all functions here be used in an AsyncTask
	// This is only for help you
	// If your application use a lot of requests, this class can be useful for
	// you

	// Execute a simple post, returning your result in String
	// For your use, you can read this and put in Json reader or use how you
	// want
	public String executePost(String url, List<NameValuePair> params)
			throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;

		HttpPost post = new HttpPost(url);

		post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		response = httpclient.execute(post);

		StatusLine statusLine = response.getStatusLine();

		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.getEntity().writeTo(out);
			out.close();
			responseString = out.toString();
		} else {
			// Closes the connection.
			response.getEntity().getContent().close();
		}

		return responseString;
	}

	// Execute a simple get, returning your result in String
	// For your use, you can read this and put in Json reader or use how you
	// want
	public String executeGet(String url, List<String> paramsKeys,
			List<String> paramsValues) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;

		String paramsEncoded = "";

		if (paramsKeys.size() > 0)
			paramsEncoded += "?";

		for (int i = 0; i < paramsKeys.size(); i++) {

			paramsEncoded += paramsKeys.get(i)
					+ "="
					+ URLEncoder.encode(paramsValues.get(i), HTTP.UTF_8)
							.toString();

			if (i < paramsKeys.size() - 2) {
				paramsEncoded += "&";
			}
		}

		url += paramsEncoded;

		response = httpclient.execute(new HttpGet(url));
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.getEntity().writeTo(out);
			out.close();
			responseString = out.toString();
		} else {
			// Closes the connection.
			response.getEntity().getContent().close();

		}

		return responseString;
	}

	// This function get (by stream) an image and returns it for you using
	// bitmap
	public Bitmap getImage(String url) throws Exception {
		String urldisplay = url;
		Bitmap bitmap = null;

		InputStream in = new java.net.URL(urldisplay).openStream();
		bitmap = BitmapFactory.decodeStream(in);

		return bitmap;
	}

}
