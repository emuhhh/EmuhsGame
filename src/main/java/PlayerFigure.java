import com.sun.management.GarbageCollectionNotificationInfo;

import java.awt.*;
import java.util.ArrayList;


public class PlayerFigure {
    public double x;
    public double y;
    public Integer clickedX;
    public Integer clickedY;
    private int width = 30;
    private int height = 30;
    public boolean alive = false;

    PlayerFigure(){
        x = GameFrame.panelObject.getWidth() / 2f;
        y = GameFrame.panelObject.getHeight() / 2f;
    }

    public void draw(Graphics2D drawer) {
        drawer.setColor(Color.CYAN);
        drawer.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
        if (clickedX != null && clickedY != null)
            drawer.drawOval(clickedX, clickedY, 1, 1);
    }

    public void update() {
        for (int i = 0; i < GameFrame.bullets.size(); i++) {
            double diffX = Math.abs(x - GameFrame.bullets.get(i).x);
            double diffY = Math.abs(y - GameFrame.bullets.get(i).y);
            double distance = Math.sqrt((diffX * diffX) + (diffY * diffY));

            if (distance < width / 2d + GameFrame.bullets.get(i).width / 2d) {
                alive = false;
                GameFrame.bullets.clear();
                clickedX = null;
                clickedY = null;
            }
        }

        if (clickedX != null && clickedY != null) {
            double offX = clickedX - x;
            double offY = clickedY - y;

            if (Math.abs(offX) > 1 || Math.abs(offY) > 1) {
                float distance = (float) Math.sqrt(offX * offX + offY * offY);
                x += offX / distance;
                y += offY / distance;
            }
        }
    }

    public void saveClickedPoint(int x, int y) {
        clickedX = x;
        clickedY = y;
    }
}





