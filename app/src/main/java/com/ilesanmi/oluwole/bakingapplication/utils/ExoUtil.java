package com.ilesanmi.oluwole.bakingapplication.utils;

public class ExoUtil {
    private String videoUrl;
    private boolean playWhenReady;
    private long playbackPosition;
    private int currentWindow;
    public ExoUtil(){}

    public ExoUtil(String videoUrl, boolean playWhenReady, long playbackPosition, int currentWindow) {
        this.videoUrl = videoUrl;
        this.playWhenReady = playWhenReady;
        this.playbackPosition = playbackPosition;
        this.currentWindow = currentWindow;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        this.playWhenReady = playWhenReady;
    }

    public void setPlaybackPosition(long playbackPosition) {
        this.playbackPosition = playbackPosition;
    }


    public void setCurrentWindow(int currentWindow) {
        this.currentWindow = currentWindow;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public long getPlaybackPosition() {
        return playbackPosition;
    }

    public int getCurrentWindow() {
        return currentWindow;
    }

    public boolean getPlayWhenReady() {
        return playWhenReady;
    }



}
