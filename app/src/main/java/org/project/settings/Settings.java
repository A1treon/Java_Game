package org.project.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {
    
    private boolean firstRun = true;
    private int frameRate = 240;
    private int xResolution = 1920;
    private int yResolution = 1080; 
    private int[] keyBindings = {87, 83, 65, 68, 32, 17};


    public boolean getFirstRun() { return firstRun; }
    public void setFirstRun(boolean firstRun) { this.firstRun = firstRun; }

    public int getFrameRate() { return frameRate; }
    public void setFrameRate(int frameRate) { this.frameRate = frameRate; }

    public int getXResolution() { return xResolution; }
    public void setXResolution(int xResolution) { this.xResolution = xResolution; }

    public int getYResolution() { return yResolution; }
    public void setYResolution(int yResolution) { this.yResolution = yResolution; }

    public int[] getKeyBindings() { return keyBindings; }
    public void setKeyBindings(int[] keyBindings) { this.keyBindings = keyBindings; }


    public int getKeyForAction(KeyAction action) {
        return switch (action) {
            case UP -> keyBindings[KeyAction.UP.ordinal()];
            case DOWN -> keyBindings[KeyAction.DOWN.ordinal()];
            case LEFT -> keyBindings[KeyAction.LEFT.ordinal()];
            case RIGHT -> keyBindings[KeyAction.RIGHT.ordinal()];
            case JUMP -> keyBindings[KeyAction.JUMP.ordinal()];
            case ATTACK -> keyBindings[KeyAction.ATTACK.ordinal()];
            default -> -1;
        };
    }
}