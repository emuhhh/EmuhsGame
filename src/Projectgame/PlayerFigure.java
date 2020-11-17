package Projectgame;

import java.awt.*;
import java.util.ArrayList;


public class PlayerFigure {
    private float x = 100;
    private float y = 100;
    private Integer clickedX;
    private Integer clickedY;
    private int width = 30;
    private int height = 30;
    public static boolean run = true;


    public void draw(Graphics2D drawer) {
        drawer.setColor(Color.CYAN);
        drawer.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
        if (clickedX != null && clickedY != null)
            drawer.drawOval(clickedX, clickedY, 1, 1);
    }



    public void update(ArrayList<Bullet> bullets) {

        for (int i = 0; i < bullets.size(); i++) {
            double diffX = Math.abs(x - GameFrame.bullets.get(i).x);
            double diffY = Math.abs(y - GameFrame.bullets.get(i).y);
            double distance = Math.sqrt((diffX * diffX) + (diffY * diffY));

            if (distance < width / 2d + GameFrame.bullets.get(i).width / 2d) {
                run = false;

            }

        }
        if (!(clickedX == null && clickedY == null)) {
            float offX = clickedX - x;
            float offY = clickedY - y;


            if (Math.abs(offX) > 1 || Math.abs(offY) > 1) {
                float distance = (float) Math.sqrt(offX * offX + offY * offY);
                x += offX / distance;
                y += offY / distance;

            }


        }


    }



    public void saveCLickedPoint(int x, int y) {
        clickedX = x;
        clickedY = y;
    }
}



