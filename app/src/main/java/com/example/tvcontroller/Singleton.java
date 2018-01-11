package com.example.tvcontroller;

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
    private int volume = 50;
    private int seekBarVolumeProgress = 50;
    private boolean muted = false;
    private boolean paused = false;


    public void setVolume(int volume){
        this.volume = volume;
    }
    public int getVolume(){
        return volume;
    }
    public void setSeekBarVolumeProgresse(int seekBarVolumeProgress){
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
}
