package org.project.gameObjects;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.project.gameObjects.entity.Entity;

public class GameObjectManager {
    private static List<GameObject> gameObjects;

    public static void initialize() {
        gameObjects = new LinkedList<>();
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static void createPlayer() {
        Entity player = new Entity();
        player.setXPos(0.1);
        player.setYPos(0.1);
        player.setWidth(0.1);
        player.setHeight(0.1);
        player.setColor(Color.ORANGE);
        gameObjects.add(player);
    }
}
