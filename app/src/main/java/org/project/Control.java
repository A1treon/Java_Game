package org.project;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingUtilities;

import org.project.graphics.GameFrame;
import org.project.settings.Settings;
import org.project.settings.SettingsManager;


public class Control implements Runnable {

    private GameFrame gameFrame;
    private Settings settings;
    private boolean running = false;
    private Thread gameThread;

    public Control() {
        this.settings = SettingsManager.load();
    }

    private void initialize() {
        if (settings.isFirstRun()) {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int refreshRate = gd.getDisplayMode().getRefreshRate();
            if (refreshRate != DisplayMode.REFRESH_RATE_UNKNOWN) {
                settings.setFrameRate(refreshRate);
            }
            settings.setXResolution(gd.getDisplayMode().getWidth());
            settings.setYResolution(gd.getDisplayMode().getHeight());

            settings.setFirstRun(false);
            SettingsManager.save(settings);
        }

        SwingUtilities.invokeLater(() -> {
            gameFrame = new GameFrame(settings);
        });
        Inputs inputHandler = new Inputs(settings);
        gameFrame.getGamePanel().addKeyListener(inputHandler);
        gameFrame.getGamePanel().addMouseListener(inputHandler);
        gameFrame.getGamePanel().addMouseMotionListener(inputHandler);
    }

    @Override
    public void run() {
        double nsPerFrame = 1000000000.0 / settings.getFrameRate();
        
        long lastTime = System.nanoTime();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;
            while (delta >= 1) {
                controlUpdate(); 
                render();        
                delta--;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void controlUpdate() {
        // Handle your game logic here (math, collision, etc.)
    }

    private void render() {
        gameFrame.updateFrame();
    }

    public void start() {
        initialize();
        running = true;
        gameThread = new Thread(this, "GameLoopThread");
        gameThread.start();
    }
}