package org.project.gameObjects.entity;
import org.project.gameObjects.Collider;
import org.project.gameObjects.Collider.CollisionDirection;
import org.project.gameObjects.GameObject;
import org.project.gameObjects.Physics;

public class Entity extends GameObject {
    private Physics physics;
    private Collider collider;

    public Entity() {
        this.physics = new Physics(EntityConstants.ENTITY_GRAVITY);
        this.collider = new Collider();
    }
    
    @Override
    public void setXPos(double xPos) {
        super.setXPos(xPos);
        syncCollider();
    }
    
    @Override
    public void setYPos(double yPos) {
        super.setYPos(yPos);
        syncCollider();
    }
    
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        syncCollider();
    }
    
    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        syncCollider();
    }
    
    private void syncCollider() {
        collider.setXPos(this.getXPos());
        collider.setYPos(this.getYPos());
        collider.setWidth(this.getWidth());
        collider.setHeight(this.getHeight());
    }

    public void moveVertical(double velocity) {
        physics.setYVelocity(velocity);
        setYPos(physics.getNewYPosition(super.getYPos()));
    }
    
    public void moveHorizontal(double velocity) {
        physics.setXVelocity(velocity);
        setXPos(physics.getNewXPosition(super.getXPos()));
    }
    
    public CollisionDirection checkCollisionWith(GameObject other) {
        return collider.checkCollisionWith(other.getCollider());
    }
    
    public Collider getCollider() {
        return collider;
    }

    public Physics getPhysics() {
        return physics;
    }
}
