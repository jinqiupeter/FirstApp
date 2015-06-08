package com.example.peterjin.firstapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

// Uses AsyncTask to create a task away from the main UI thread. This task takes a
// URL string and uses it to create an HttpUrlConnection. Once the connection
// has been established, the AsyncTask downloads the contents of the webpage as
// an InputStream. Finally, the InputStream is converted into a string, which is
// displayed in the UI by the AsyncTask's onPostExecute method.
public class JSonArrayGetter extends AsyncTask<String, Void, Void> {
    final String TAG = "JSonArrayGetter.java";

    private InputStream is = null;
    private JSONArray jArr = null;
    private String jsonString = "";
    private String url;
    private MyListActivity activity;
    private ProgressDialog dialog;
    private boolean showProgress = false;

    public JSonArrayGetter(MyListActivity activity, boolean showProgress) {
        this.activity = activity;
        this.showProgress = showProgress;
        this.dialog = new ProgressDialog(activity);
    }

    public JSonArrayGetter(MyListActivity activity) {
        this.activity = activity;
        this.dialog = new ProgressDialog(activity);
    }

    @Override
    protected Void doInBackground(String... urls) {
        this.url = urls[0];

        Void v = null;
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return downloadUrl(url);
            } else {
                Log.e(TAG, "No network connection available.");
                return v;
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.toString());
            return v;
        }
    }

    @Override
    protected void onPreExecute() {
        if (!showProgress)
            return;
        this.dialog.setMessage(activity.getString(R.string.loading));
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(Void v) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        Log.d(TAG, "The jsonContent from " + url + " is: " + jsonString);
        // try parse the string to a JSON object
        try {
            jArr = new JSONArray(jsonString);
            if (jArr == null) {
                Toast.makeText(activity, R.string.no_result, Toast.LENGTH_SHORT);
                return;
            }
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jObject = jArr.getJSONObject(i);
                activity.addChild(new Child(jObject), url);
            }
            activity.dataFetched(url);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing json string: " + e.toString());
        }

    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    private Void downloadUrl(String u) throws IOException{
        Void v=null;
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Accept", "application/json");
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            Log.d(TAG, "The response code for " + u + " is: " + response);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            jsonString = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            Log.e(TAG, "Error converting result: " + e.toString());
        }

        if (is != null) {
            is.close();
        }
        return v;
    }
}
