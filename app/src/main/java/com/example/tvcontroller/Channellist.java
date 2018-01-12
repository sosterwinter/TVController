package com.example.tvcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Channellist extends AppCompatActivity {
    ChannelAdapter channelsAdapter;
    ArrayList<Channel> channelList;
    Singleton singleton;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channellist);
       listView = (ListView) findViewById(R.id.listView);









        singleton = Singleton.getInstance();
        channelList = singleton.getChannelList();

       channelsAdapter = new ChannelAdapter( this, channelList)  ;

        listView.setAdapter(channelsAdapter);


    }
}
