package org.project;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import org.project.settings.Settings;

public class Inputs extends MouseAdapter implements KeyListener {

    private Settings settings;
    private final Set<Integer> pressedKeys = new HashSet<>();
    
    private int mouseX, mouseY;
    private boolean mousePressed;

    public Inputs(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public boolean isActionActive(String actionName) {
        int keyCode = settings.getKeyForAction(actionName);
        return pressedKeys.contains(keyCode);
    }

    // --- Mouse Logic ---
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public int getMouseX() { return mouseX; }
    public int getMouseY() { return mouseY; }
    public boolean isMouseDown() { return mousePressed; }
}