package com.example.tvcontroller;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Channellist extends AppCompatActivity {
    ChannelAdapter channelsAdapter;
    ArrayList<Channel> channelList;
    Singleton singleton;
    Toolbar myToolbar;
    ListView listView;
    HttpRequest httpReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channellist);
        listView = (ListView) findViewById(R.id.listView);
        this.httpReq = new HttpRequest("10.0.2.2", 1000, false);
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //Set Toolbar
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);





        singleton = Singleton.getInstance();
        channelList = singleton.getChannelList();

       channelsAdapter = new ChannelAdapter( this, channelList)  ;

        listView.setAdapter(channelsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
               // String item = (String) listView.getItemAtPosition(position);
                Channel channel = channelList.get(position);
                String channelID = channel.getChannelIdentifier();
                Log.d("HEHE", channelID);
                singleton.setAktChannel(channel);
                new HttpRequestAsync(httpReq).execute("channelMain=" + channelID);
            }
        });


    }
}
