import java.awt.*;

public class Bullet {
    double x;
    double y;
    int height = 10;
    int width = 10;
    double velX, velY;

    /**
     *
     * @param x
     * @param y
     * @param velX
     * @param velY
     */
    public Bullet(double x, double y, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
    }

    /**
     *
     * @param g
     */
    public void draw(Graphics2D g) {
        g.setColor(new Color(0,255,0));
        g.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
    }

    public void update() {
        x += velX;
        y += velY;
    }
}