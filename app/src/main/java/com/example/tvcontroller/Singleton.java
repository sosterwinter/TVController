package com.example.tvcontroller;

import java.util.ArrayList;

/**
 * Created by Yannik on 11.01.2018.
 */

public class Singleton {
    private static Singleton instance = null;
    public static Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
    private Singleton(){

    }



    private ArrayList<Channel> channelList;
    private boolean switchPipChannel;
    private Channel aktChannel;
    private int volume = 50;
    private int seekBarVolumeProgress = 50;
    private boolean muted = false;
    private boolean paused = false;
    private boolean pip = false;
    private long timeStopped;
    private Channel aktPipChannel;
    public Channel getAktChannel() {
        return aktChannel;
    }

    public void setAktChannel(Channel aktChannel) {
        this.aktChannel = aktChannel;
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(ArrayList<Channel> channelList) {
        this.channelList = channelList;
    }

    public void setVolume(int volume){
        this.volume = volume;
    }
    public int getVolume(){
        return volume;
    }
    public void setSeekBarVolumeProgress(int seekBarVolumeProgress){
        this.seekBarVolumeProgress = seekBarVolumeProgress;
    }
    public int getSeekBarVolumeProgress(){
        return seekBarVolumeProgress;
    }
    public void setMuted(boolean muted){
        this.muted = muted;
    }
    public boolean getMuted(){
        return this.muted;
    }
    public void setPaused(boolean paused){
        this.paused = paused;
    }
    public boolean getPaused(){
        return this.paused;
    }

    public boolean getPip() {
        return pip;
    }

    public void setPip(boolean pip) {
        this.pip = pip;
    }

    public long getTimeStopped() {
        return timeStopped;
    }

    public void setTimeStopped(long timeStopped) {
        this.timeStopped = timeStopped;
    }

    public boolean getSwitchPipChannel() {
        return switchPipChannel;
    }

    public void setSwitchPipChannel(boolean switchPipChannel) {
        this.switchPipChannel = switchPipChannel;
    }

    public Channel getAktPipChannel() {
        return aktPipChannel;
    }

    public void setAktPipChannel(Channel aktPipChannel) {
        this.aktPipChannel = aktPipChannel;
    }
}
