package org.project.gameObjects;


public class Physics {
    private double gravity;
    private double xVelocity; // %screen per second
    private double yVelocity; // %screen per second
    private boolean gravityOn;
    private static double deltaTime;

    public Physics(double gravity) {
        this.gravity = gravity;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.gravityOn = false;
    }
    
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

    public double getNewXPosition(double currentX) {
        return currentX + xVelocity * deltaTime;
    }

    public double getNewYPosition(double currentY) {
        if (gravityOn) {
            yVelocity += gravity * deltaTime; 
        }
        return currentY + yVelocity * deltaTime;
    }
    
}