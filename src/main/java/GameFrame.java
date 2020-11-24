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
    public static BufferedImage playIcon;

    static {
        try {
            gameOverImage = ImageIO.read(GameFrame.class.getResource("game_over.png"));
            tryAgainIcon = ImageIO.read(GameFrame.class.getResource("restart_button.png"));
            menuIcon = ImageIO.read(GameFrame.class.getResource("menu_button.png"));
            background = ImageIO.read(GameFrame.class.getResource("game.background.png"));
            exitIcon = ImageIO.read(GameFrame.class.getResource("exit_button.png"));
            menuBackground = ImageIO.read(GameFrame.class.getResource("game.background.png"));
            playIcon = ImageIO.read(GameFrame.class.getResource("play_button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        window = new GameFrame();
        window.setTitle("Dodge Game");
        window.setSize(1600, 900);
        //window.getFullscreen();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        panelObject = new Draw();
        panelObject.setBackground(Color.DARK_GRAY);
        panelObject.addMouseListener(new MouseHandler());
        window.add(panelObject);

        player1 = new PlayerFigure();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Repaint(), 0, 1000 / 60);
        timer.scheduleAtFixedRate(new Update(), 0, 1000 / 60);
        timer.scheduleAtFixedRate(new ScoreUpdate(), 0, 100);
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

                int noise = 100;
                offX += Math.random() * noise - noise / 2d;
                offY += Math.random() * noise - noise / 2d;

                double dist = Math.sqrt(offX * offX + offY * offY);
                double velX = offX / dist;
                double velY = offY / dist;
                velX *= 3;
                velY *= 3;

                x += velX;
                y += velY;

                if (Math.random() < 0.05 + Draw.score / 5000d)
                    GameFrame.bullets.add(new Bullet(x, y, velX, velY));

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


	/*public void getFullscreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();

		if (gd.isFullScreenSupported()) {
			gd.setFullScreenWindow(window);
		}
	}*/


    public void gameMenuScreen(Graphics2D g) {
        int w = panelObject.getWidth();
        int h = panelObject.getHeight();
        int x = 0;
        int y = 0;
        g.drawImage(menuBackground, x, y, w, h, this);
        g.drawImage(playIcon, w / 2 - w / 32, h / 2 - h / 18, h / 18, h / 9, this);
        setVisible(true);
        String scoreString = "DODGIT";
        g.setFont(g.getFont().deriveFont(100f));
        g.drawString(scoreString, w / 2 - getFontMetrics(g.getFont()).stringWidth(scoreString) / 2, h / 3);
        if (in(w / 2 - w / 32,h / 2 - h / 18,h / 2 - h / 18,h / 9, player1.clickedX, player1.clickedY)){
        }

    }

    public boolean in(int x, int y, int w, int h, Integer mouseX, Integer mouseY) {
        if (mouseX == null || mouseY == null) return false;
        return mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h;
    }



    public void gameOverScreen(Graphics2D g) {

        int wGameOver = window.getWidth() / 2;
        int hGameOver = window.getHeight() / 6;
        int xGameOver = (window.getWidth() / 2) - wGameOver / 2;
        int yGameOver = window.getHeight() / 6;

        int hIcon = window.getHeight() / 9;
        int wIcon = window.getWidth() / 16;
        int yIcon = window.getHeight() / 2 - window.getHeight() / 9;

        int xRestart = window.getWidth() / 2 - wIcon / 2;
        int xMenu = window.getWidth() / 4 - wIcon / 2;
        int xExit = window.getWidth() - window.getWidth() / 4 - wIcon / 2;

        setVisible(true);
        g.drawImage(gameOverImage, xGameOver, yGameOver, wGameOver, hGameOver, this);
        g.drawImage(menuIcon, xMenu, yIcon, wIcon, hIcon, this);
        g.drawImage(tryAgainIcon, xRestart, yIcon, wIcon, hIcon, this);
        g.drawImage(exitIcon, xExit, yIcon, wIcon, hIcon, this);

        if (in(xExit, yIcon, wIcon, hIcon, player1.clickedX, player1.clickedY)) {
            System.exit(0);
        }

        if (in(xMenu, yIcon, wIcon, hIcon, player1.clickedX, player1.clickedY)) {

        }

        if (in(xRestart, yIcon, wIcon, hIcon, player1.clickedX, player1.clickedY)) {
            player1.alive = true;
            player1.clickedX = null;
            player1.clickedY = null;
            Draw.score = 0;
            player1.x = GameFrame.panelObject.getWidth() / 2f;
            player1.y = GameFrame.panelObject.getHeight() / 2f;
        }
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