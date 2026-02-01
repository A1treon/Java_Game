package org.project.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JPanel;

import org.project.gameObjects.GameObject;
import org.project.settings.Settings;

public class GamePanel extends JPanel {

    Settings settings;

    List<GameObject> gameObjects;

    public GamePanel(Settings settings, List<GameObject> gameObjects) {
        this.settings = settings;
        this.gameObjects = gameObjects;
        this.setBackground(Color.BLACK); // Set a default background
        this.setDoubleBuffered(true);    // Ensures smooth rendering
        this.setFocusable(true);         // Allows the panel to receive key inputs

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        renderGame(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void renderGame(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, settings.getXResolution(), settings.getYResolution()); 
        for (GameObject obj : gameObjects) {
            obj.draw(g2d);
        }
    }
}