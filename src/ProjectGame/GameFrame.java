package ProjectGame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame {
    public static JPanel panelObject;
    public static PlayerFigure player1;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static GameFrame window;
    public static BufferedImage gameOverImage;
    public static BufferedImage tryAgainImage;


    static {
        try {
            gameOverImage = ImageIO.read(new File("src/ProjectGame/12.png"));
            tryAgainImage = ImageIO.read(new File("src/ProjectGame/13.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
                        GameFrame.bullets.add(new Bullet(0, (int) (Math.random() * GameFrame.panelObject.getHeight()), 1, 0));
                } else {
                    if (Math.random() < z)
                        GameFrame.bullets.add(new Bullet(GameFrame.panelObject.getWidth(), (int) (Math.random() * GameFrame.panelObject.getHeight()), -1, 0));
                }
            } else {
                // y
                if (Math.random() < 0.5) {
                    // x = 0
                    if (Math.random() < z)
                        GameFrame.bullets.add(new Bullet((int) (Math.random() * GameFrame.panelObject.getWidth()), 0, 0, 1));
                } else {
                    if (Math.random() < z)
                        GameFrame.bullets.add(new Bullet((int) (Math.random() * GameFrame.panelObject.getWidth()), GameFrame.panelObject.getHeight(), 0, -1));
                }
            }
            for (Bullet b : GameFrame.bullets) b.update();
            GameFrame.panelObject.repaint();

        }
    }

    public void scoreCounter() {

        if(PlayerFigure.run = true){



        }
        else{



        }
    }

    public void gameOver(Graphics2D drawer) {
        int w = 500;
        int h = 100;
        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h)/2;
        drawer.drawImage(tryAgainImage, x, y, w, h, this);
        drawer.drawImage(gameOverImage, (getWidth() - w) / 2, (getHeight() - h / 2) / 4, w, h, this);
        setVisible(true);
        if (player1.clickedX > x && player1.clickedX < x + w){
            if(player1.clickedY > y && player1.clickedY < y + h)
            PlayerFigure.run = true;
            //System.exit(0);
        }

    }

    public static class Update extends TimerTask {
        @Override
        public void run() {
            GameFrame.player1.update(GameFrame.bullets);

        }
    }
}