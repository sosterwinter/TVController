package com.example.tvcontroller;

/**
 * Created by Yannik on 12.01.2018.
 */

public class Channel {
    int frequency;
    String channelIdentifier;
    int quality;



    String program;
    String provider;
    public Channel(int frequency, String channel, int quality, String program, String provider){
        this.frequency = frequency;
        this.channelIdentifier = channel;
        this.quality = quality;
        this.program = program;
        this.provider = provider;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getChannelIdentifier() {
        return channelIdentifier;
    }

    public void setChannelIdentifier(String channelIdentifier) {
        this.channelIdentifier = channelIdentifier;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
