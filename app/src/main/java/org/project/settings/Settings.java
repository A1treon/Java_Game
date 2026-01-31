package org.project.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {
    
    private boolean firstRun = true;
    private int frameRate = 60;
    private int xResolution = 1920;
    private int yResolution = 1080; 


    public boolean isFirstRun() { return firstRun; }
    public void setFirstRun(boolean firstRun) { this.firstRun = firstRun; }

    public int getFrameRate() { return frameRate; }
    public void setFrameRate(int frameRate) { this.frameRate = frameRate; }

    public int getXResolution() { return xResolution; }
    public void setXResolution(int xResolution) { this.xResolution = xResolution; }

    public int getYResolution() { return yResolution; }
    public void setYResolution(int yResolution) { this.yResolution = yResolution; }
}