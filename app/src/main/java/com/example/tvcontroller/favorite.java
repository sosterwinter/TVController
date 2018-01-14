package com.example.tvcontroller;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class favorite extends AppCompatActivity {

    ChannelAdapter channelsAdapter;
    ArrayList<Channel> favoriteList;
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
        favoriteList = singleton.getFavoriteList();

        channelsAdapter = new ChannelAdapter( this, favoriteList)  ;

        if(favoriteList!=null && favoriteList.size() > 0 )
        {
            listView.setAdapter(channelsAdapter);

        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // String item = (String) listView.getItemAtPosition(position);
                Channel channel = favoriteList.get(position);
                String channelID = channel.getChannelIdentifier();
                Log.d("HEHE", channelID);
                singleton.setAktChannel(channel);
                new HttpRequestAsync(httpReq).execute("channelMain=" + channelID);
            }
        });


    }

}
