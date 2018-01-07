package com.example.tvcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Creating member variables
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private boolean paused = false, muted = false;
    ImageButton imageButtonPause, imageButtonMute;
    Toolbar myToolbar;
    SeekBar seekBarVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initialisierung
        this.imageButtonPause = (ImageButton) findViewById(R.id.imageButtonPause);
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.imageButtonMute = (ImageButton) findViewById(R.id.imageButtonMute);
        this.seekBarVolume = (SeekBar)findViewById(R.id.seekBar1);

        //Set toolbar as actionbar
        setSupportActionBar(myToolbar);

        //Pause Button
        if(paused == true){
            imageButtonPause.setImageResource(R.drawable.ic_play);
        }else{
            imageButtonPause.setImageResource(R.drawable.ic_pause);
        }
        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(paused == true){
                    //Resume
                    paused=false;
                    imageButtonPause.setImageResource(R.drawable.ic_pause);
                    //Image auf Pause
                }else{
                    //Pause the tv
                    paused=true;
                    //Image auf Play
                    imageButtonPause.setImageResource(R.drawable.ic_play);

                }
            }
        });
        //Mute Button
        if(muted == true){
            imageButtonMute.setImageResource(R.drawable.ic_mute_off);
        }else{
            imageButtonMute.setImageResource(R.drawable.ic_mute_on);
        }
        imageButtonMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(muted == true && seekBarVolume.getProgress() > 0){
                    //Resume
                    muted=false;
                    imageButtonMute.setImageResource(R.drawable.ic_mute_on);
                    //Image auf Pause
                }else{
                    //Pause the tv
                    muted=true;
                    //Image auf Play
                    imageButtonMute.setImageResource(R.drawable.ic_mute_off);

                }
            }
        });
        /*
        //Toggle Button
        final ToggleButton myToggleButton = (ToggleButton)findViewById(R.id.toggleButtonMute);
        myToggleButton.setChecked(true);
        myToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    //Toast.makeText(MainActivity.this, "Unmuted", Toast.LENGTH_SHORT).show();
                } else {
                    // The toggle is disabled
                    Toast.makeText(MainActivity.this, "Muted", Toast.LENGTH_SHORT).show();

                }
            }

        });
        */
        //Setting up Seekbar + Listener


        seekBarVolume.setProgress(50);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int progress_value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progress_value = progress;
                if(progress_value == 0){
                    muted = true;
                    imageButtonMute.setImageResource(R.drawable.ic_mute_off);
                }else{
                    muted = false;
                    imageButtonMute.setImageResource(R.drawable.ic_mute_on);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

                if(progress_value != 0){
                    Toast.makeText(MainActivity.this, "Volume: " + progress_value, Toast.LENGTH_SHORT).show();
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
                Intent intent_favorite = new Intent(this, favorite.class);

                startActivity(intent_favorite);

                return true;

            case R.id.power_off:


                // IMPORTANTE NOCH ZU BEARBEITEN
                // User chose the "power_off" action. send shutdown request to server


                Intent intent_power = new Intent(this, PowerActivity.class);
                startActivity(intent_power);

                return true;

            case R.id.managefavorites:

                Intent intent_managefavorites = new Intent (this, managefavorites.class);
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
    }

    public void onClickNext(View view){
        //Send request channel +1 to server
        //channelMain=PositionInListe
    }


}
