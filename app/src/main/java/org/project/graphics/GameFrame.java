package org.project.graphics;

import javax.swing.JFrame;

import org.project.settings.Settings;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    private Settings settings;

    public GameFrame(Settings settings) {
        this.settings = settings;
        this.setTitle("Game Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gamePanel = new GamePanel(settings);
        gamePanel.setPreferredSize(new java.awt.Dimension(settings.getXResolution(), settings.getYResolution()));
        this.add(gamePanel);
        this.pack();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
