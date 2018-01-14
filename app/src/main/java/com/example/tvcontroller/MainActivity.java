package com.example.tvcontroller;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Creating member variables
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    ImageButton imageButtonPause, imageButtonMute, imageButtonPip;
    Toolbar myToolbar;
    SeekBar seekBarVolume;
    Button switchPipChannel;
    HttpRequest httpReq;
    Singleton singleton;
    ArrayList<Channel> favoriteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisierung
        this.switchPipChannel = (Button)findViewById(R.id.buttonSwitchPipChannel);
        this.imageButtonPip = (ImageButton)findViewById(R.id.imageButtonPip);
        this.imageButtonPause = (ImageButton) findViewById(R.id.imageButtonPause);
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.imageButtonMute = (ImageButton) findViewById(R.id.imageButtonMute);
        this.seekBarVolume = (SeekBar)findViewById(R.id.seekBar1);
        this.favoriteList = new ArrayList<Channel>();

        this.httpReq = new HttpRequest("10.0.2.2", 1000, false);
        //this.task = new HttpRequestAsync(this.httpReq);
        this.singleton = Singleton.getInstance();
        String filename;
        //Initialisierung

        //TV-Server initialisieren

        seekBarVolume.setProgress(singleton.getSeekBarVolumeProgress());
        new HttpRequestAsync(httpReq).execute("volume=" + singleton.getVolume());


        //Set toolbar as actionbar
        setSupportActionBar(myToolbar);


        //SwitchPipChannel
        if(singleton.getSwitchPipChannel() == true){
            switchPipChannel.setText("Switch Main Channels");
        }else{
            switchPipChannel.setText("Switch PiP Channels");
        }
        switchPipChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singleton.getSwitchPipChannel() == true){
                   // new HttpRequestAsync(httpReq).execute("showPip=0");
                    singleton.setSwitchPipChannel(false);
                    switchPipChannel.setText("Switch PiP Channels");
                }
                else{
                   // new HttpRequestAsync(httpReq).execute("showPip=1");
                    singleton.setSwitchPipChannel(true);
                    switchPipChannel.setText("Switch Main Channels");
                }
            }
        });


        //Pip Button
        if(singleton.getPip() == true){
            imageButtonPip.setImageResource(R.drawable.ic_close_pip);
        }else{
            imageButtonPip.setImageResource(R.drawable.ic_open_pip);
        }
        imageButtonPip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singleton.getPip() == true){
                    new HttpRequestAsync(httpReq).execute("showPip=0");
                    singleton.setPip(false);
                    imageButtonPip.setImageResource(R.drawable.ic_open_pip);
                }
                else{
                    String chID = singleton.getAktPipChannel().getChannelIdentifier();
                    Log.d("chID", chID);
                    new HttpRequestAsync(httpReq).execute("showPip=1&channelPip=" + chID);
                    singleton.setPip(true);
                    imageButtonPip.setImageResource(R.drawable.ic_close_pip);
                }
            }
        });

        //Pause Button
        if(singleton.getPaused() == true){
            imageButtonPause.setImageResource(R.drawable.ic_play);

        }else{
            imageButtonPause.setImageResource(R.drawable.ic_pause);
        }
        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singleton.getPaused() == true){
                    //task.execute("timeShiftPause=");
                    int offset = (int) (System.currentTimeMillis() - singleton.getTimeStopped())/1000;
                    Log.d("zeit", "offset" + offset);
                    new HttpRequestAsync(httpReq).execute("timeShiftPlay=" + offset);
                    //Resume
                    singleton.setPaused(false);
                    imageButtonPause.setImageResource(R.drawable.ic_pause);
                    //Image auf Pause
                }else{
                    //task.execute("timeShiftPlay=0");
                    singleton.setTimeStopped(System.currentTimeMillis());
                    new HttpRequestAsync(httpReq).execute("timeShiftPause=");
                    //Pause the tv
                    singleton.setPaused(true);
                    //Image auf Play
                    imageButtonPause.setImageResource(R.drawable.ic_play);

                }
            }
        });
        //Mute Button
        if(singleton.getMuted() == true){
            imageButtonMute.setImageResource(R.drawable.ic_mute_off);
        }else{
            imageButtonMute.setImageResource(R.drawable.ic_mute_on);
        }
        imageButtonMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singleton.getMuted() == true && seekBarVolume.getProgress() > 0){
                    //HTTP-Request
                    singleton.setVolume(seekBarVolume.getProgress());
                    new HttpRequestAsync(httpReq).execute("volume=" + singleton.getVolume());
                    singleton.setMuted(false);
                    imageButtonMute.setImageResource(R.drawable.ic_mute_on);

                }else{
                    //HTTP-Request
                    new HttpRequestAsync(httpReq).execute("volume=0");
                    singleton.setVolume(0);
                    //seekBarVolume.setProgress(0);
                    singleton.setMuted(true);

                    imageButtonMute.setImageResource(R.drawable.ic_mute_off);

                }
            }
        });

        //Setting up Seekbar Listener




        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            //int progress_value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                singleton.setVolume(progress);
                singleton.setSeekBarVolumeProgress(progress);
                if(singleton.getVolume() == 0){

                    singleton.setMuted(true);
                    imageButtonMute.setImageResource(R.drawable.ic_mute_off);
                }else{
                    singleton.setMuted(false);
                    imageButtonMute.setImageResource(R.drawable.ic_mute_on);
                }
                //HTTP-Request
                new HttpRequestAsync(httpReq).execute("volume="+singleton.getVolume());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

                if(singleton.getVolume() != 0){
                    Toast.makeText(MainActivity.this, "Volume: " + singleton.getVolume(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.action_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent_settings = new Intent(this, settings.class);

                startActivity(intent_settings);

                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                if(singleton.getFavoriteList() == null){
                    favoriteList.add(singleton.getAktChannel());
                    singleton.setFavoriteList(favoriteList);
                }else{
                    singleton.getFavoriteList().add(singleton.getAktChannel());
                }
                //singleton.getFavoriteList().add(singleton.getAktChannel());


                return true;

            case R.id.action_channellist:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent_channellist = new Intent(this, Channellist.class);

                startActivity(intent_channellist);

                return true;

            case R.id.power_off:

                //HTTP-Request
                new HttpRequestAsync(httpReq).execute("standby=1");

                // IMPORTANTE NOCH ZU BEARBEITEN
                // User chose the "power_off" action. send shutdown request to server


                Intent intent_power = new Intent(this, PowerActivity.class);
                startActivity(intent_power);

                return true;

            case R.id.managefavorites:

                Intent intent_managefavorites = new Intent (this, favorite.class);
                startActivity(intent_managefavorites);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void onClickPrev(View view){
        //Send request channel -1 to server
        //channelMain=PositionInListe
        //new HttpRequestAsync(httpReq).execute("channelMain=" + aktChannel.getNummer()-1);+
        if(singleton.getSwitchPipChannel() == true){
            int prev = singleton.getAktPipChannel().getNummer() - 1;
            if(prev < 0){
                prev = singleton.getChannelList().size() - 1;
                new HttpRequestAsync(httpReq).execute("channelPip=" + singleton.getChannelList().get(prev).getChannelIdentifier());
                singleton.setAktPipChannel(singleton.getChannelList().get(prev));
            }else{
                new HttpRequestAsync(httpReq).execute("channelPip=" + singleton.getChannelList().get(prev).getChannelIdentifier());
                singleton.setAktPipChannel(singleton.getChannelList().get(prev));
            }

        }else {
            int prev = singleton.getAktChannel().getNummer() - 1;

            if (prev < 0) {
                prev = singleton.getChannelList().size() - 1;
                new HttpRequestAsync(httpReq).execute("channelMain=" + singleton.getChannelList().get(prev).getChannelIdentifier());
                singleton.setAktChannel(singleton.getChannelList().get(prev));
            } else {
                new HttpRequestAsync(httpReq).execute("channelMain=" + singleton.getChannelList().get(prev).getChannelIdentifier());
                singleton.setAktChannel(singleton.getChannelList().get(prev));
            }
        }

    }

    public void onClickNext(View view){
        //Send request channel +1 to server
        //channelMain=PositionInListe
        if(singleton.getSwitchPipChannel() == true){
            int next = singleton.getAktPipChannel().getNummer() +1;
            if(next >= singleton.getChannelList().size()){
                next = 0;
                new HttpRequestAsync(httpReq).execute("channelPip=" + singleton.getChannelList().get(next).getChannelIdentifier());
                singleton.setAktPipChannel(singleton.getChannelList().get(next));
            }else{
                new HttpRequestAsync(httpReq).execute("channelPip=" + singleton.getChannelList().get(next).getChannelIdentifier());
                singleton.setAktPipChannel(singleton.getChannelList().get(next));
            }

        }else {
            int next = singleton.getAktChannel().getNummer() + 1;
            if (next >= singleton.getChannelList().size()) {
                next = 0;
                new HttpRequestAsync(httpReq).execute("channelMain=" + singleton.getChannelList().get(next).getChannelIdentifier());
                singleton.setAktChannel(singleton.getChannelList().get(next));
            } else {
                new HttpRequestAsync(httpReq).execute("channelMain=" + singleton.getChannelList().get(next).getChannelIdentifier());
                singleton.setAktChannel(singleton.getChannelList().get(next));
            }
        }

    }


}
