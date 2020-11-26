
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Emus game
 * @author Emmanuel
 * @version X
 * Game frame class which extends from the class JFrame
 */
public class GameFrame extends JFrame {
    /**
     * Variable named panelObject of Class JPanel
     */
    public static JPanel panelObject;
    /**
     * Variable named player1 of Class PlayerFigure
     */
    public static PlayerFigure player1;
    /**
     * Variable named bullets of ArrayList with bullets out of Bullet class
     */
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    /**
     * Variable named window of class GameFrame
     */
    public static GameFrame window;
    /**
     * Image used when Game over
     */
    public static BufferedImage gameOverImage;
    /**
     * Image used as Try-again button
     */
    public static BufferedImage tryAgainIcon;
    /**
     * Image used as Exit button
     */
    public static BufferedImage exitIcon;
    /**
     * Image used as Menu button
     */
    public static BufferedImage menuIcon;
    /**
     * Image used as Menu background
     */
    public static BufferedImage menuBackground;
    /**
     * Image used as Menu background (needed if game ever changes design)
     */
    public static BufferedImage background;
    /**
     * Image used as play icon
     */
    public static BufferedImage playIcon;
    /**
     * Image used as flash icon
     */
    public static BufferedImage flashIcon;

    static {
        try {
            gameOverImage = ImageIO.read(GameFrame.class.getResource("game_over.png"));
            tryAgainIcon = ImageIO.read(GameFrame.class.getResource("restart_button.png"));
            menuIcon = ImageIO.read(GameFrame.class.getResource("menu_button.png"));
            background = ImageIO.read(GameFrame.class.getResource("game.background.png"));
            exitIcon = ImageIO.read(GameFrame.class.getResource("exit_button.png"));
            menuBackground = ImageIO.read(GameFrame.class.getResource("game.background.png"));
            playIcon = ImageIO.read(GameFrame.class.getResource("play_button.png"));
            flashIcon = ImageIO.read(GameFrame.class.getResource("flash_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        window = new GameFrame();
        window.setTitle("Dodge Game");
        window.setSize(1600, 900);
        window.getFullscreen();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        panelObject = new Draw();
        panelObject.setBackground(Color.DARK_GRAY);
        panelObject.addMouseListener(new MouseHandler());
        window.addKeyListener(new KeyHandler());
        window.add(panelObject);

        player1 = new PlayerFigure();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Repaint(), 0, 1000 / 60);
        timer.scheduleAtFixedRate(new Update(), 0, 1000 / 60);
        timer.scheduleAtFixedRate(new ScoreUpdate(), 0, 100);
        JOptionPane.showMessageDialog(window,"Game instructions: Right / Left click to move, don't get hit and press \"f\" wisely" + ".If you obtain a white screen, please press \"alt + tab\"!");
    }

    /**
     * Repaint class extends from Timer task class
     */
    public static class Repaint extends TimerTask {
        @Override
        public void run() {
            if (GameFrame.player1.alive != null && GameFrame.player1.alive) {
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
            for (Bullet b : GameFrame.bullets) b.update();
            GameFrame.panelObject.repaint();
        }
    }

    /**
     * Fullscreen method, Resizes window
     */
    public void getFullscreen() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = env.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(window);
        }
    }

    /**
     * Method to check if icon is clicked
     * @param x xPosition of certain icon
     * @param y yPosition of certain icon
     * @param w width of certain icon
     * @param h height of certain icon
     * @param mouseX xPos mouse
     * @param mouseY yPos mouse
     * @return Hit-box of an icon with parameters of x,y,w,h
     */
    public boolean in(int x, int y, int w, int h, Integer mouseX, Integer mouseY) {
        if (mouseX == null || mouseY == null) return false;
        return mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h;
    }

    /**
     * template for Menu screen
     * @param g Instance of class Graphics2D
     */
    public void gameMenuScreen(Graphics2D g) {
        int w = panelObject.getWidth();
        int h = panelObject.getHeight();
        g.drawImage(playIcon, w / 2 - w / 32, h / 2 - h / 18, w / 18, h / 9, this);
        setVisible(true);
        String scoreString = "DODGIT";
        g.setFont(g.getFont().deriveFont(300f));
        g.drawString(scoreString, w / 2 - getFontMetrics(g.getFont()).stringWidth(scoreString) / 2, h / 3);
        if (in(w / 2 - w / 32, h / 2 - h / 18, w / 18, h / 9, player1.clickedX, player1.clickedY)) {
            player1.alive = true;
            player1.clickedX = null;
            player1.clickedY = null;
            Draw.score = 0;
            Draw.cooldown = 0;
            player1.x = GameFrame.panelObject.getWidth() / 2f;
            player1.y = GameFrame.panelObject.getHeight() / 2f;
        }
    }

    /**
     * template for Game over screen
     * @param g Instance of class Graphics2D
     */
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
            GameFrame.player1.alive = null;
        }

        if (in(xRestart, yIcon, wIcon, hIcon, player1.clickedX, player1.clickedY)) {
            player1.alive = true;
            player1.clickedX = null;
            player1.clickedY = null;
            Draw.score = 0;
            Draw.cooldown = 0;
            player1.x = GameFrame.panelObject.getWidth() / 2f;
            player1.y = GameFrame.panelObject.getHeight() / 2f;
        }
    }
    /**
     * Calls {@link PlayerFigure#update()}
     */
    public static class Update extends TimerTask {
        @Override
        public void run() {
            GameFrame.player1.update();
        }
    }
    /**
     * increases {@link Draw#score} and {@link Draw#cooldown}
     */
    public static class ScoreUpdate extends TimerTask {
        @Override
        public void run() {
            if (player1.alive != null && player1.alive) {
                Draw.score++;
                Draw.cooldown++;
            }
        }
    }
}