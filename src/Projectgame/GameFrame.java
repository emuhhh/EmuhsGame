package Projectgame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame {
    public static JPanel panelObject;
    public static PlayerFigure player1;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static GameFrame window;



    public static void main(String[] args) {
        Music sound = new Music();
        window = new GameFrame();
        window.setName("Dodge Game");
        window.setSize(1000, 1000);
        window.setVisible(true);
        panelObject = new Screen();
        panelObject.setBackground(Color.DARK_GRAY);
        window.add(panelObject);
        player1 = new PlayerFigure();
        panelObject.addKeyListener(new KeyHandler());
        panelObject.addMouseListener(new MouseHandler());
        Timer counter = new Timer();
        counter.scheduleAtFixedRate(new Repaint(), 0, 1000 / 100);
        new Timer().scheduleAtFixedRate(new Update(), 0, 1000 / 200);
        sound.start();

    }

    public static class Repaint extends TimerTask {

        @Override
        public void run() {
            double z = 0.10;
            if (Math.random() < 0.5) {
                // x
                if (Math.random() < 0.5) {
                    // x = 0
                    if (Math.random() < z)
                        GameFrame.bullets.add(new Bullet(0, (int) (Math.random() * GameFrame.panelObject.getHeight()), 0.5, 0));
                } else {
                    if (Math.random() < z)
                        GameFrame.bullets.add(new Bullet(GameFrame.panelObject.getWidth(), (int) (Math.random() * GameFrame.panelObject.getHeight()), -0.5, 0));
                }
            } else {
                // y
                if (Math.random() < 0.5) {
                    // x = 0
                    if (Math.random() < z)
                        GameFrame.bullets.add(new Bullet((int) (Math.random() * GameFrame.panelObject.getWidth()), 0, 0, 0.5));
                } else {
                    if (Math.random() < z)
                        GameFrame.bullets.add(new Bullet((int) (Math.random() * GameFrame.panelObject.getWidth()), GameFrame.panelObject.getHeight(), 0, -0.5));
                }
            }
            for (Bullet b : GameFrame.bullets) b.update();
            GameFrame.panelObject.repaint();

        }
    }

    public void gameOver(Graphics2D drawer) {
        drawer.setColor(Color.black);
        drawer.fillRect(0,0,GameFrame.panelObject.getWidth(),GameFrame.panelObject.getHeight());
    }

    public static class Update extends TimerTask {
        @Override
        public void run() {
            GameFrame.player1.update(bullets);

        }
    }
}