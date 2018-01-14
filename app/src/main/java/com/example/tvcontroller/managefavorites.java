package com.example.tvcontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class managefavorites extends AppCompatActivity {

    String favlistfile = "favlist.txt";
    String favnumberstring = "0";
    /*
    public String path = (getFilesDir()+favlistfile);
    */
    public Button read, write;
    public TextView textView;
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managefavorites);

        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Get a support Actionbar corresponding to this toolbar
        ActionBar bar = getSupportActionBar();

        //Enable the Up button
        bar.setDisplayHomeAsUpEnabled(true);

        textView = (TextView) findViewById(R.id.textView2);
        write = (Button) findViewById(R.id.writebtn);
        read = (Button) findViewById(R.id.readbtn);
        editText = (EditText) findViewById(R.id.editText);
/*
        File dir = new File(path);
        dir.mkdir();
*/
    }
    public void readFav(View view) {

        //Check ob exists hier

        try {

            FileInputStream fis = openFileInput(favlistfile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;
            //Lines einlesen
            while ((line=br.readLine()) != null) {
                sb.append(line+"\n");
            }
            textView.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeFav(View view) {
        String mymessage = editText.getText().toString();
        //Check ob exists hier
        try{
            //FileOutputStream fos = openFileOutput(favlistfile,MODE_PRIVATE);
            FileOutputStream fos = openFileOutput(favlistfile,MODE_APPEND);
            fos.write("\n".getBytes());
            fos.write(mymessage.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(), "Text saved", Toast.LENGTH_LONG).show();
            editText.setText("");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void makeSampleFavs(View view) {
        String[] sampleFavs = {"1", "4","5"};
        //Check ob exists hier
        try{
            FileOutputStream fos = openFileOutput(favlistfile,MODE_PRIVATE);
            for(int i = 0; i < sampleFavs.length; i++) {
                fos.write(sampleFavs[i].getBytes());
                fos.write("\n".getBytes());
            }
            fos.close();
            Toast.makeText(getApplicationContext(), "SampleFavs written", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFavs(View view) {
        try{
            FileOutputStream fos = openFileOutput(favlistfile,MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(), "Favs deleted", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
