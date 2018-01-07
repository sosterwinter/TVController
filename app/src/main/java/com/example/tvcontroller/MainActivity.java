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
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    boolean paused = false;
    ImageButton toggleButtonPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set toolbar as actionbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Pause Button

        this.toggleButtonPause = (ImageButton) findViewById(R.id.imageButtonPause);
        toggleButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(paused == true){
                    //Resume
                    paused=false;
                    toggleButtonPause.setImageResource(R.drawable.ic_pause);
                    //Image auf Pause
                }else{
                    //Pause the tv
                    paused=true;
                    //Image auf Play
                    toggleButtonPause.setImageResource(R.drawable.ic_play);

                }
            }
        });
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

        //Setting up Seekbar + Listener
        SeekBar sb = (SeekBar)findViewById(R.id.seekBar1);

        sb.setProgress(50);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int progress_value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progress_value = progress;
                if(progress_value == 0){
                    myToggleButton.setChecked(false);
                }else{
                    myToggleButton.setChecked(true);
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

    public void onClickPause(View view){
        //Send request pause to server
        //timeShiftPause=

    }
}
