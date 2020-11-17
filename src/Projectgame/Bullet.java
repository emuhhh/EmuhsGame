package Projectgame;

import java.awt.*;

public class Bullet {
    double x;
    double y;
    int height = 10;
    int width = 10;
    double velX, velY;

    public Bullet(double x, double y, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
    }

    public void draw(Graphics2D zeichner) {
        zeichner.setColor(Color.red);
        zeichner.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
    }

    public void update() {
        x += velX;
        y += velY;
    }
}