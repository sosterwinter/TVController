package com.example.tvcontroller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Yannik on 08.01.2018.
 */

public class HttpRequestAsync extends AsyncTask<String, Integer, JSONObject> {



    public static final String TAG = "ScanChannelsTask";
    private HttpRequest request;
    private Context context;
    private Handler handler;
    public HttpRequestAsync(HttpRequest request) {
        this.request = request;


    }
    

    @Override
    protected JSONObject doInBackground(String[] params) {
        //httpReq = new HttpRequest("10.0.2.2", 1000);
        JSONObject obj = null;

        if (params[0] != null) {
            try {
//                Log.e(TAG, params[0] + " - " + this.request.getIpAddress());

                obj = this.request.sendHttp(params[0]);
               
                String string = obj.toString(2);
             // Log.d(TAG, "Channel Scan almost finished...");
             //   Log.d(TAG, string);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return obj;

    }

    @Override
    protected void onPostExecute(JSONObject obj){

    }
}
