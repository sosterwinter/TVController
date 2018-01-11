package com.example.tvcontroller;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PowerActivity extends AppCompatActivity {
    private HttpRequest httpReq;
    private Handler handler;
    public static final String MESSAGE_KEY = "de.hda.nzse.examples.id.message";
    private static final String TAG = "MainActivity";
    private EditText txtResponse;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        httpReq = new HttpRequest("10.0.2.2", 1000, false);



    }

    public void onClickButton(View view) {

        //Senderliste hier laden, standby=0, scanChannels=
        HttpRequestAsync task = new HttpRequestAsync(this.httpReq);

        //task.execute("scanChannels=");


        new HttpRequestAsync(this.httpReq).execute("standby=0&channelMain=8a");

        // json = new HttpRequestAsync(this.httpReq).;

        Intent start_main_activity = new Intent(this, MainActivity.class);
        startActivity(start_main_activity);

    }
}
