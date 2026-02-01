package org.project.graphics;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.List;

import javax.swing.JFrame;

import org.project.gameObjects.GameObject;
import org.project.settings.Settings;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    private Settings settings;
    GraphicsEnvironment env;
    GraphicsDevice device;

    public GameFrame(Settings settings, List<GameObject> gameObjects) {
        this.settings = settings;
        env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = env.getDefaultScreenDevice();
        this.setTitle("Game Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gamePanel = new GamePanel(settings, gameObjects);
        gamePanel.setPreferredSize(new java.awt.Dimension(settings.getXResolution(), settings.getYResolution()));
        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(this);
        }
    }

    public void updateFrameResolution() {

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public void updateFrame() {
        gamePanel.repaint();
    }
	
}
