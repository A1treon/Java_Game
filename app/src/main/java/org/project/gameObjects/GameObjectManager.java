package org.project.gameObjects;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.project.Inputs;
import org.project.gameObjects.entity.Entity;
import org.project.gameObjects.entity.EntityConstants;
import org.project.gameObjects.entity.GravityEntity;
import org.project.gameObjects.surfaces.Surface;
import org.project.settings.KeyAction;

public class GameObjectManager {
    private static List<GameObject> gameObjects;
    private static List<Surface> surfaces;
    private static List<Entity> entities;
    private static Inputs inputs;
    private static GravityEntity player;

    public static void initialize(Inputs inputs) {
        GameObjectManager.inputs = inputs;
        gameObjects = new LinkedList<>();
        surfaces = new LinkedList<>();
        entities = new LinkedList<>();
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static void initializeGameObjects() {
        createFloor();
        createPlayer();
        createLeftWall();
        createRightWall();
        createThreeEntities();
    }

    public static void createPlayer() {
        player = new GravityEntity();
        player.setXPos(0.50);
        player.setYPos(0.50);
        player.setWidth(0.02 / (16.0 / 9.0));
        player.setHeight(0.02);
        player.setColor(Color.GREEN);
        gameObjects.add(player);
    }
    
    public static void createFloor() {
        Surface floor = new Surface();
        floor.setXPos(0.0);
        floor.setYPos(0.0);
        floor.setWidth(1.0);
        floor.setHeight(0.05);
        floor.setColor(Color.GRAY);
        gameObjects.add(floor);
        surfaces.add(floor);
    }

    public static void createLeftWall() {
        Surface leftWall = new Surface();
        leftWall.setXPos(0.0);
        leftWall.setYPos(0.0);
        leftWall.setWidth(0.05);
        leftWall.setHeight(1.0);
        leftWall.setColor(Color.GRAY);
        gameObjects.add(leftWall);
        surfaces.add(leftWall);
    }

    public static void createRightWall() {
        Surface rightWall = new Surface();
        rightWall.setXPos(0.95);
        rightWall.setYPos(0.0);
        rightWall.setWidth(0.05);
        rightWall.setHeight(1.0);
        rightWall.setColor(Color.GRAY);
        gameObjects.add(rightWall);
        surfaces.add(rightWall);
    }

    public static void createThreeEntities() {
        for (int i = 0; i < 3; i++) {
            Entity entity = new Entity();
            entity.setXPos(0.2 + i * 0.2);
            entity.setYPos(0.5);
            entity.setWidth(0.02 / (16.0 / 9.0));
            entity.setHeight(0.02);
            entity.setColor(Color.RED);
            gameObjects.add(entity);
            entities.add(entity);
        }
    }

    public static void updateGameObjects() {
        updatePlayer();
        for (Entity entity : entities) {
            updateEntityMovement(entity);
        }
    }

    public static void updatePlayer() {
        updatePlayerMovementGravity();
        updatePlayerAttack();
    }

    public static void updatePlayerMovementGravity() {
        double xSpeed = 0.0;
        double ySpeed = 0.0;
        if (inputs.isActionActive(KeyAction.LEFT)) {
            xSpeed -= EntityConstants.PLAYER_X_VELOCITY;
        }
        if (inputs.isActionActive(KeyAction.RIGHT)) {
            xSpeed += EntityConstants.PLAYER_X_VELOCITY;
        }
        if (inputs.isActionActive(KeyAction.JUMP)) {
            ySpeed += EntityConstants.PLAYER_JUMP_VELOCITY;
        }
        
        player.moveHorizontal(xSpeed);
        player.moveVertical(ySpeed);
        
        boolean isGrounded = false;
        for (Surface surface : surfaces) {
            Collider.CollisionDirection collision = player.checkCollisionWith(surface);
            if (collision != Collider.CollisionDirection.NONE) {
                resolveCollision(player, surface, collision);
                
                if (collision == Collider.CollisionDirection.BOTTOM && player.getPhysics().getYVelocity() < 0) {
                    isGrounded = true;
                }
            }
        }
        
        for (Entity entity : entities) {
            Collider.CollisionDirection collision = player.checkCollisionWith(entity);
            if (collision != Collider.CollisionDirection.NONE) {
                resolveEntityCollision(player, entity, collision);

                if (collision == Collider.CollisionDirection.BOTTOM && player.getPhysics().getYVelocity() < 0) {
                    isGrounded = true;
                }
            }
        }
        
        player.setIsGrounded(isGrounded);
    }


    public static void updatePlayerAttack() {
        if (inputs.isMouseDown()) { 
            
        }
    }



    public static void updateEntityMovement(Entity entity) {
        double xSpeed = 0.0;
        double ySpeed = 0.0;
        if (player.getXPos() < entity.getXPos()) {
            xSpeed -= EntityConstants.ENTITY_X_VELOCITY;
        }
        if (player.getXPos() > entity.getXPos()) {
            xSpeed += EntityConstants.ENTITY_X_VELOCITY;
        }
        if (player.getYPos() > entity.getYPos()) {
            ySpeed += EntityConstants.ENTITY_Y_VELOCITY;
        }
        if (player.getYPos() < entity.getYPos()) {
            ySpeed -= EntityConstants.ENTITY_Y_VELOCITY;
        }
        
        entity.moveHorizontal(xSpeed);
        entity.moveVertical(ySpeed);
        
        for (Surface surface : surfaces) {
            Collider.CollisionDirection collision = entity.checkCollisionWith(surface);
            if (collision != Collider.CollisionDirection.NONE) {
                resolveCollision(entity, surface, collision);
            }
        }
        
        for (Entity otherEntity : entities) {
            if (entity != otherEntity) {
                Collider.CollisionDirection collision = entity.checkCollisionWith(otherEntity);
                if (collision != Collider.CollisionDirection.NONE) {
                    resolveEntityCollision(entity, otherEntity, collision);
                }
            }
        }
        
        Collider.CollisionDirection playerCollision = entity.checkCollisionWith(player);
        if (playerCollision != Collider.CollisionDirection.NONE) {
            resolveEntityCollision(entity, player, playerCollision);
        }
    }

    private static void resolveCollision(Entity entity, Surface surface, Collider.CollisionDirection direction) {        
        double surfaceLeft = surface.getXPos();
        double surfaceRight = surface.getXPos() + surface.getWidth();
        double surfaceTop = surface.getYPos() + surface.getHeight();
        double surfaceBottom = surface.getYPos();
        
        switch(direction) {
            case BOTTOM -> entity.setYPos(surfaceTop);
            case TOP -> entity.setYPos(surfaceBottom - entity.getHeight());
            case LEFT -> entity.setXPos(surfaceLeft - entity.getWidth());
            case RIGHT -> entity.setXPos(surfaceRight);
            default -> {
            }
        }
    }

    private static void resolveEntityCollision(Entity entity1, Entity entity2, Collider.CollisionDirection direction) {
        double entity2Left = entity2.getXPos();
        double entity2Right = entity2.getXPos() + entity2.getWidth();
        double entity2Top = entity2.getYPos() + entity2.getHeight();
        double entity2Bottom = entity2.getYPos();
        
        switch(direction) {
            case BOTTOM -> entity1.setYPos(entity2Top);
            case TOP -> entity1.setYPos(entity2Bottom - entity1.getHeight());
            case LEFT -> entity1.setXPos(entity2Left - entity1.getWidth());
            case RIGHT -> entity1.setXPos(entity2Right);
            default -> {
            }
        }
    }

}

