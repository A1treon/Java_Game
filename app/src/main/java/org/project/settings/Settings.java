package org.project.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {
    
    private boolean firstRun = true;
    private int frameRate = 60;
    private int xResolution = 1920;
    private int yResolution = 1080; 
    private int[] keyBindings = new int[KeyAction.values().length];


    public boolean isFirstRun() { return firstRun; }
    public void setFirstRun(boolean firstRun) { this.firstRun = firstRun; }

    public int getFrameRate() { return frameRate; }
    public void setFrameRate(int frameRate) { this.frameRate = frameRate; }

    public int getXResolution() { return xResolution; }
    public void setXResolution(int xResolution) { this.xResolution = xResolution; }

    public int getYResolution() { return yResolution; }
    public void setYResolution(int yResolution) { this.yResolution = yResolution; }

    public int getKeyForAction(KeyAction action) {
        switch (action) {
            case UP: return keyBindings[KeyAction.UP.ordinal()];
            case DOWN: return keyBindings[KeyAction.DOWN.ordinal()];
            case LEFT: return keyBindings[KeyAction.LEFT.ordinal()];
            case RIGHT: return keyBindings[KeyAction.RIGHT.ordinal()];
            case JUMP: return keyBindings[KeyAction.JUMP.ordinal()];
            case ATTACK: return keyBindings[KeyAction.ATTACK.ordinal()];
            default: return -1; // Undefined action
        }
    }
}