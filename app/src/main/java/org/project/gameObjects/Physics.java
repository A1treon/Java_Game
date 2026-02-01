package org.project.gameObjects;


public class Physics {
    private double gravity;
    private double xVelocity; // %screen per second
    private double yVelocity; // %screen per second
    private boolean gravityOn;
    private static double deltaTime;
    
    public static void setDeltaTime(double deltaTime) {
        Physics.deltaTime = deltaTime;
    }

    public void setGravityOn(boolean gravityOn) {
        this.gravityOn = gravityOn;
    }

    public boolean isGravityOn() {
        return gravityOn;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public double getNewXPosition(int currentX) {
        return currentX + xVelocity * deltaTime;
    }

    public double getNewYPosition(int currentY) {
        if (gravityOn) {
            yVelocity += gravity * deltaTime; 
        }
        return currentY + yVelocity * deltaTime;
    }
    
}