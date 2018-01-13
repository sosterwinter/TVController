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
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PowerActivity extends AppCompatActivity {
    private HttpRequest httpReq;
    private Handler handler;
    public static final String MESSAGE_KEY = "de.hda.nzse.examples.id.message";
    private static final String TAG = "MainActivity";
    private EditText txtResponse;
    private JSONObject json;
    private Singleton singleton;
    private ArrayList<Channel> channelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        httpReq = new HttpRequest("10.0.2.2", 1000, false);
        this.singleton = Singleton.getInstance();


    }

    public void onClickButton(View view) throws JSONException {

        //Senderliste hier laden, standby=0, scanChannels=
        HttpRequestAsync task = new HttpRequestAsync(this.httpReq);
        //channelList = singleton.getChannelList();
        channelList = new ArrayList<Channel>();
        //task.execute("scanChannels=");
        HttpRequestAsync async = new HttpRequestAsync(httpReq);
        async.execute("scanChannels=");
        try {
            json = async.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(json.has("channels")){
            JSONArray channels = json.getJSONArray("channels");
            for(int i = 0; i < channels.length(); i++) {
                JSONObject channel = channels.getJSONObject(i);
                Channel realChannel = new Channel(channel.getInt("frequency"), channel.getString("channel"), channel.getInt("quality"), channel.getString("program"), channel.getString("provider"));
                realChannel.setNummer(i);
                channelList.add(realChannel);
                //Log.d("HAHA", realChannel.getProvider());
            }
        }
        singleton.setChannelList(channelList);

        Log.d("LUL", singleton.getChannelList().get(1).getProvider());

        Log.d("luls", "i did this");

        //Set aktuelelr Channel zu 1. channel in liste
        singleton.setAktChannel(channelList.get(0));
        new HttpRequestAsync(this.httpReq).execute("standby=0&channelMain=" + singleton.getAktChannel().getChannelIdentifier());//&channelMain=8a


        // json = new HttpRequestAsync(this.httpReq).;

        Intent start_main_activity = new Intent(this, MainActivity.class);
        startActivity(start_main_activity);

    }
}
