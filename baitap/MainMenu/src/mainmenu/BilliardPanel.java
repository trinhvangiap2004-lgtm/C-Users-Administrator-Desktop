/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainmenu;

/**
 *
 * @author Admin
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BilliardPanel extends JPanel implements Runnable {
    private java.util.List<Ball> balls = new ArrayList<>();
    private Thread thread;
    private boolean running = false;
    private int holeX, holeY;
    private String firstBallDropped = "None";

    public BilliardPanel(int map) {
        setBackground(Color.BLACK);
        setFocusable(true);
        setDoubleBuffered(true);

        // Set hole position based on map
        switch (map) {
            case 1 -> { holeX = 400; holeY = 300; }
            case 2 -> { holeX = 600; holeY = 150; }
            case 3 -> { holeX = 200; holeY = 450; }
        }

        // Start with 30 balls
        for (int i = 0; i < 30; i++) addBall();
    }

    public void addBall() {
        balls.add(new Ball(getWidth(), getHeight()));
        repaint();
    }

    public void removeBall() {
        if (!balls.isEmpty()) balls.remove(balls.size() - 1);
        repaint();
    }

    public void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            for (Ball b : balls) {
                b.move(getWidth(), getHeight());
                if (!b.isDropped() && b.checkHole(holeX, holeY, 20)) {
                    b.setDropped(true);
                    if (firstBallDropped.equals("None"))
                        firstBallDropped = "Ball " + b.getId();
                }
            }
            repaint();
            try { Thread.sleep(15); } catch (InterruptedException ignored) {}
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw hole
        g.setColor(Color.WHITE);
        g.fillOval(holeX - 10, holeY - 10, 20, 20);

        // Draw balls
        for (Ball b : balls) {
            if (!b.isDropped()) b.draw(g);
        }

        // Info
        g.setColor(Color.GREEN);
        g.setFont(new Font("Consolas", Font.PLAIN, 14));
        g.drawString("Balls: " + balls.size(), 20, 20);
        g.drawString("First Ball Dropped: " + firstBallDropped, 20, 40);
    }

    // Inner Ball class
    private static class Ball {
        private static int counter = 1;
        private final int id;
        private double x, y, dx, dy;
        private final int r = 6;
        private final Color color;
        private boolean dropped = false;
        private final Random rand = new Random();

        public Ball(int w, int h) {
            this.id = counter++;
            x = rand.nextInt(Math.max(w, 1));
            y = rand.nextInt(Math.max(h, 1));
            dx = rand.nextDouble() * 3 - 1.5;
            dy = rand.nextDouble() * 3 - 1.5;
            color = Color.getHSBColor(rand.nextFloat(), 1f, 1f);
        }

        public void move(int w, int h) {
            x += dx;
            y += dy;
            if (x < 0 || x > w - r) dx = -dx;
            if (y < 0 || y > h - r) dy = -dy;
        }

        public boolean checkHole(int hx, int hy, int hr) {
            double dist = Math.hypot(x - hx, y - hy);
            return dist < hr;
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval((int) x, (int) y, r * 2, r * 2);
        }

        public int getId() { return id; }
        public boolean isDropped() { return dropped; }
        public void setDropped(boolean val) { dropped = val; }
    }
}
