package org.project.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import org.project.settings.Settings;

public class GamePanel extends JPanel {

    Settings settings;

    public GamePanel(Settings settings) {
        this.settings = settings;
        this.setBackground(Color.BLACK); // Set a default background
        this.setDoubleBuffered(true);    // Ensures smooth rendering
        this.setFocusable(true);         // Allows the panel to receive key inputs
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double scaleX = (double) getWidth() / settings.getXResolution();
        double scaleY = (double) getHeight() / settings.getYResolution();
        double scale = Math.min(scaleX, scaleY);

        // 2. Center the game "canvas"
        int xOffset = (int) ((getWidth() - (settings.getXResolution() * scale)) / 2);
        int yOffset = (int) ((getHeight() - (settings.getYResolution() * scale)) / 2);

        // 3. Apply transformations
        g2d.translate(xOffset, yOffset);
        g2d.scale(scale, scale);

        // 4. Draw your game logic using TARGET coordinates
        renderGame(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void renderGame(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        // This will always be a square in the center, regardless of window size
        g2d.fillRect(0, 0, settings.getXResolution(), settings.getYResolution()); 
    }
}