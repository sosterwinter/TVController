package com.example.tvcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
    }

    public void onClickButton(View view){

        //Senderliste hier laden


        Intent start_main_activity = new Intent(this, MainActivity.class);
        startActivity(start_main_activity);

    }
}
