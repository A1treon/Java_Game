package org.project.gameObjects;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.project.Inputs;
import org.project.gameObjects.entity.Entity;
import org.project.gameObjects.entity.EntityConstants;
import org.project.settings.KeyAction;

public class GameObjectManager {
    private static List<GameObject> gameObjects;
    private static Inputs inputs;
    private static Entity player;

    public static void initialize(Inputs inputs) {
        GameObjectManager.inputs = inputs;
        gameObjects = new LinkedList<>();
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static void createPlayer() {
        player = new Entity();
        player.setXPos(0.00);
        player.setYPos(0.00);
        player.setWidth(0.02 / (16.0 / 9.0));
        player.setHeight(0.02);
        player.setColor(Color.ORANGE);
        gameObjects.add(player);
    }

    public static void updatePlayer() {
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
        player.moveHorizontal(xSpeed);
        player.moveVertical(ySpeed);
    }
}
