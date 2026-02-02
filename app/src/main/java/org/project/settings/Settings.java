package org.project.settings;

import java.awt.event.KeyEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {
    
    private boolean firstRun = true;
    private int frameRate = 60;
    private int xResolution = 1920;
    private int yResolution = 1080; 
    private int[] keyBindings = {KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE};

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
            default -> -1;
        };
    }
}