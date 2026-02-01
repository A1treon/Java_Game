package org.project.gameObjects.entity;
import org.project.gameObjects.Collider;
import org.project.gameObjects.GameObject;
import org.project.gameObjects.Physics;

public class Entity extends GameObject {
    private Physics physics;
    private Collider collider;

    public Entity() {
        this.physics = new Physics();
        this.collider = new Collider();
    }
}
