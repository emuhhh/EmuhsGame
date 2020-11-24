import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    public static BufferedImage tryAgainIcon;
    public static BufferedImage exitIcon;
    public static BufferedImage menuIcon;
    public static BufferedImage menuBackground;
    public static BufferedImage background;

    static {
        try {
            gameOverImage = ImageIO.read(GameFrame.class.getResource("game_over.png"));
            tryAgainIcon = ImageIO.read(GameFrame.class.getResource("restart_button.png"));
            menuIcon = ImageIO.read(GameFrame.class.getResource("menu_button.png"));
            background = ImageIO.read(GameFrame.class.getResource("game.background.png"));
            exitIcon = ImageIO.read(GameFrame.class.getResource("exit_button.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Music sound = new Music();
        window = new GameFrame();
        window.setName("Dodge Game");
        window.getFullscreen();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelObject = new Draw();
        panelObject.setBackground(Color.DARK_GRAY);
        panelObject.addKeyListener(new KeyHandler());
        panelObject.addMouseListener(new MouseHandler());
        window.add(panelObject);

        player1 = new PlayerFigure();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Repaint(), 0, 1000 / 100);
        timer.scheduleAtFixedRate(new Update(), 0, 1000 / 200);
        timer.scheduleAtFixedRate(new ScoreUpdate(), 0, 100);
        sound.start();
    }

    public static class Repaint extends TimerTask {
        @Override
        public void run() {
            if (GameFrame.player1.alive) {
                double x;
                double y;
                if (Math.random() < 0.5) {
                    y = (int) (Math.random() * GameFrame.panelObject.getHeight());
                    if (Math.random() < 0.5) x = 0;
                    else x = GameFrame.panelObject.getWidth();
                } else {
                    x = (int) (Math.random() * GameFrame.panelObject.getWidth());
                    if (Math.random() < 0.5) y = 0;
                    else y = GameFrame.panelObject.getHeight();
                }

                double offX = player1.x - x;
                double offY = player1.y - y;
                double dist = Math.sqrt(offX * offX + offY * offY);

                int noise = 100;
                offX += Math.random() * noise - noise / 2d;
                offY += Math.random() * noise - noise / 2d;

                offX /= dist;
                offY /= dist;
                offX *= 2;
                offY *= 2;
                if (Math.random() < 0.05 + Draw.score / 5000d)
                    GameFrame.bullets.add(new Bullet(x, y, offX, offY));
            }
            //}
			/*if (Math.random() < 0.5) {
				// x
				if (Math.random() < 0.5) {
					// x = 0
					if (Math.random() < z)
						GameFrame.bullets.add(new Bullet(0, (int) (Math.random() * GameFrame.player1.y), ((Math.random() + 0.5) * 1), Math.random() * 1));
				} else {
					if (Math.random() < z)
						GameFrame.bullets.add(new Bullet(GameFrame.panelObject.getWidth(), (int) (Math.random() * GameFrame.player1.y), ((Math.random() + 0.5) * -1), Math.random() * 1));
				}
			} else {
				// y
				if (Math.random() < 0.5) {
					// x = 0
					if (Math.random() < z)
						GameFrame.bullets.add(new Bullet((int) (Math.random() * GameFrame.player1.x), 0, Math.random() * 1, ((Math.random() + 0.5) * 1)));
				} else {
					if (Math.random() < z)
						GameFrame.bullets.add(new Bullet((int) (Math.random() * GameFrame.player1.x), GameFrame.panelObject.getHeight(), Math.random() * 1, ((Math.random() + 0.5) * -1)));
				}
			}*/
            for (Bullet b : GameFrame.bullets) b.update();
            GameFrame.panelObject.repaint();
        }
    }


    public void getFullscreen() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = env.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(window);
        }
    }


    public void gameMenuScreen(Graphics2D g) {
        int w = panelObject.getWidth() / 2;
        int h = panelObject.getHeight() / 6;
        int x = (getWidth() / 2) - w / 2;
        int y = panelObject.getHeight() / 2;
        g.drawImage(menuBackground, x, y, w, h / 2, this);
        setVisible(true);
        if (player1.clickedX != null && player1.clickedY != null && player1.clickedX > x && player1.clickedX < x + w) {
            if (player1.clickedY > y && player1.clickedY < y + h / 2) {
                player1.alive = true;
                player1.clickedX = null;
                player1.clickedY = null;
                Draw.score = 0;
                player1.x = GameFrame.panelObject.getWidth() / 2f;
                player1.y = GameFrame.panelObject.getHeight() / 2f;
            }
        }
    }


    public void gameOverScreen(Graphics2D g) {
        int wGameOver = window.getWidth() / 2;
        int hGameOver = window.getHeight() / 6;
        int xGameOver = (window.getWidth() / 2) - wGameOver / 2;
        int yGameOver = window.getHeight() / 6;

        int wRestart = window.getWidth() / 16;
        int hRestart = 1;
        int xRestart = 1 ;
        int yRestart = 1;

        int xMenu = xGameOver;
        int yMenu = 1;
        int wMenu = window.getWidth() / 16;
        int hMenu = window.getHeight() / 9;

        int wExit = window.getWidth() / 16;
        int hExit = window.getWidth() / 9;
        int xExit = 1;
        int yExit = 1;

        setVisible(true);
        g.drawImage(gameOverImage, xGameOver, yGameOver, wGameOver, hGameOver, this);
        g.drawImage(menuIcon, xMenu, yGameOver + hMenu * 2, window.getWidth() / 16, hMenu, this);
        g.drawImage(tryAgainIcon, xMenu + wGameOver / 3, yGameOver + hMenu * 2, wRestart, hMenu, this);
        g.drawImage(exitIcon, xMenu + wGameOver * 2 / 3, yGameOver + hMenu * 2, wExit, hMenu, this);

        if (player1.clickedX != null && player1.clickedY != null && player1.clickedX > xMenu + wGameOver * 2 / 3 && player1.clickedX < xMenu + wGameOver * 2 / 3 + wExit) {
            if (player1.clickedY > yGameOver + hMenu * 2 && player1.clickedY < yGameOver + hMenu * 2 + hMenu) {
                System.exit(0);
            }
        }

        if (player1.clickedX != null && player1.clickedY != null && player1.clickedX > xMenu + wGameOver / 3 && player1.clickedX < xMenu + wGameOver / 3 + wExit) {
            if (player1.clickedY > yGameOver + hMenu * 2 && player1.clickedY < yGameOver + hMenu * 2 + hMenu) {
                player1.alive = true;
                player1.clickedX = null;
                player1.clickedY = null;
                Draw.score = 0;
                player1.x = GameFrame.panelObject.getWidth() / 2f;
                player1.y = GameFrame.panelObject.getHeight() / 2f;
            }
        }
      /*  if (player1.clickedX != null && player1.clickedY != null && player1.clickedX > x && player1.clickedX < x + w) {
            if (player1.clickedY > y && player1.clickedY < y + h / 2) {
                player1.alive = true;
                player1.clickedX = null;
                player1.clickedY = null;
                Draw.score = 0;
                player1.x = GameFrame.panelObject.getWidth() / 2f;
                player1.y = GameFrame.panelObject.getHeight() / 2f;
            }

        }*/
    }

    public static class Update extends TimerTask {
        @Override
        public void run() {
            GameFrame.player1.update();
        }
    }

    public static class ScoreUpdate extends TimerTask {
        @Override
        public void run() {
            if (player1.alive) {
                Draw.score++;
            }
        }
    }
}