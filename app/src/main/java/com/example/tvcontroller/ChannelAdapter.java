package com.example.tvcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yannik on 12.01.2018.
 */

public class ChannelAdapter extends ArrayAdapter<Channel> {
    public ChannelAdapter(Context context, ArrayList<Channel> channelArrayList){
        super(context, 0, channelArrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Channel channel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.channellist_item, parent, false);
        }
        // Lookup view for data population
        TextView programName = (TextView) convertView.findViewById(R.id.programName);
       // TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        programName.setText(channel.getProgram());
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}
