package org.project.gameObjects.entity;
import org.project.gameObjects.Collider;
import org.project.gameObjects.GameObject;
import org.project.gameObjects.Physics;

public class Entity extends GameObject {
    private Physics physics;
    private Collider collider;

    public Entity() {
        this.physics = new Physics(EntityConstants.ENTITY_GRAVITY);
        this.collider = new Collider();
    }

    public void moveVertical(double speed) {
        physics.setYVelocity(speed);
        super.setYPos(physics.getNewYPosition(super.getYPos()));
    }
    public void moveHorizontal(double speed) {
        physics.setXVelocity(speed);
        super.setXPos(physics.getNewXPosition(super.getXPos()));
    }

}
