package org.project.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import org.project.settings.Settings;

public class GameObject {
    private static Settings settings;
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private Color color;

    public static void getSettings(Settings settings) {
        GameObject.settings = settings;
    }

    public double getXPos() {
        return xPos;
    }
    public void setXPos(double xPos) {
        this.xPos = xPos;
    }
    public double getYPos() {
        return yPos;
    }
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Collider getCollider() {
        return new Collider(xPos, yPos, width, height);
    }

    public void draw(Graphics2D g2d) {
        int xResolution = settings.getXResolution();
        int yResolution = settings.getYResolution();
        int renderWidth = (int)(width * xResolution);
        int renderHeight = (int)(height * yResolution);
        int renderX = (int)(xPos * xResolution);
        int renderY = (int)(yResolution - (yPos * yResolution) - renderHeight);
        g2d.setColor(color);
        g2d.fillRect(renderX, renderY, renderWidth, renderHeight);
    }
}

