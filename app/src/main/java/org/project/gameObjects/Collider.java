package org.project.gameObjects;

public class Collider {
    
    public enum CollisionDirection {
        NONE, LEFT, RIGHT, TOP, BOTTOM
    }
    
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    
    public Collider() {
    }
    
    public Collider(double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
    public double getXPos() { return xPos; }
    public void setXPos(double xPos) { this.xPos = xPos; }
    
    public double getYPos() { return yPos; }
    public void setYPos(double yPos) { this.yPos = yPos; }
    
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    
    public CollisionDirection checkCollisionWith(Collider other) {
        if (xPos < other.xPos + other.width && xPos + width > other.xPos && 
            yPos < other.yPos + other.height && yPos + height > other.yPos) {
            return getCollisionDirection(other);
        }
        return CollisionDirection.NONE;
    }
    
    private CollisionDirection getCollisionDirection(Collider other) {
        double overlapLeft = (xPos + width) - other.xPos;  
        double overlapRight = (other.xPos + other.width) - xPos; 
        double overlapTop = (yPos + height) - other.yPos;       
        double overlapBottom = (other.yPos + other.height) - yPos; 
        double minOverlap = Math.min(
            Math.min(overlapLeft, overlapRight),
            Math.min(overlapTop, overlapBottom)
        );
        
        if (minOverlap == overlapLeft) {
            return CollisionDirection.LEFT;
        } else if (minOverlap == overlapRight) {
            return CollisionDirection.RIGHT;
        } else if (minOverlap == overlapTop) {
            return CollisionDirection.TOP;
        } else {
            return CollisionDirection.BOTTOM;
        }
    }
}