import java.awt.*;

/**
 * Constructor bullet class
 */
public class Bullet {
    double x;
    double y;
    int height = 10;
    int width = 10;
    double velX, velY;

    /**
     * properties of Bullet
     * @param x xPosition of Bullet
     * @param y yPosition of Bullet
     * @param velX xVelocity of Bullet
     * @param velY yVelocity of Bullet
     */
    public Bullet(double x, double y, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
    }

    /**
     * sets color and shape of bullet
     * @param g Instance of class Graphics2D
     */
    public void draw(Graphics2D g) {
        g.setColor(new Color(156, 255, 0));
        g.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
    }

    /**
     * defines Velocity
     */
    public void update() {
        x += velX;
        y += velY;
    }
}