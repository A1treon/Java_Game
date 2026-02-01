package org.project.gameObjects;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.project.Inputs;
import org.project.gameObjects.entity.Entity;
import org.project.gameObjects.entity.EntityConstants;
import org.project.gameObjects.surfaces.Surface;
import org.project.settings.KeyAction;

public class GameObjectManager {
    private static List<GameObject> gameObjects;
    private static List<Surface> surfaces;
    private static Inputs inputs;
    private static Entity player;

    public static void initialize(Inputs inputs) {
        GameObjectManager.inputs = inputs;
        gameObjects = new LinkedList<>();
        surfaces = new LinkedList<>();
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static void createPlayer() {
        player = new Entity();
        player.setXPos(0.00);
        player.setYPos(0.50);
        player.setWidth(0.02 / (16.0 / 9.0));
        player.setHeight(0.02);
        player.setColor(Color.ORANGE);
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

    public static void updatePlayer() {
        updatePlayerMovement();
    }

    public static void updatePlayerMovement() {
        double xSpeed = 0.0;
        double ySpeed = 0.0;
        if (inputs.isActionActive(KeyAction.LEFT)) {
            xSpeed -= EntityConstants.PLAYER_X_VELOCITY;
        }
        if (inputs.isActionActive(KeyAction.RIGHT)) {
            xSpeed += EntityConstants.PLAYER_X_VELOCITY;
        }
        if (inputs.isActionActive(KeyAction.UP)) {
            ySpeed += EntityConstants.PLAYER_Y_VELOCITY;
        }
        if (inputs.isActionActive(KeyAction.DOWN)) {
            ySpeed -= EntityConstants.PLAYER_Y_VELOCITY;
        }
        
        for (Surface surface : surfaces) {
            player.checkCollisionWith(surface);
            if (player.checkCollisionWith(surface) == Collider.CollisionDirection.BOTTOM && ySpeed < 0) {
                ySpeed = 0;
            }
            else if (player.checkCollisionWith(surface) == Collider.CollisionDirection.TOP && ySpeed > 0) {
                ySpeed = 0;
            }
            else if (player.checkCollisionWith(surface) == Collider.CollisionDirection.LEFT && xSpeed < 0) {
                xSpeed = 0;
            }
            else if (player.checkCollisionWith(surface) == Collider.CollisionDirection.RIGHT && xSpeed > 0) {
                xSpeed = 0;
            }
        }
        
        player.moveHorizontal(xSpeed);
        player.moveVertical(ySpeed);
    }


}

