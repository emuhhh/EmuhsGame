import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {
    public static int score;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D drawer = (Graphics2D) g;
        g.drawImage(GameFrame.background, 0, 0, GameFrame.panelObject.getWidth(), GameFrame.panelObject.getHeight(), this);

        if (GameFrame.player1.alive) {
            GameFrame.player1.draw(drawer);

            g.setFont(g.getFont().deriveFont(GameFrame.panelObject.getWidth() / 1000));
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, GameFrame.panelObject.getWidth() - 100, 20);
            for (int i = 0; i < GameFrame.bullets.size(); i++) {
                GameFrame.bullets.get(i).draw(drawer);
            }
        } else {
            GameFrame.window.gameOverScreen(drawer);
            g.setFont(g.getFont().deriveFont(40f));
            g.setColor(Color.MAGENTA);
            String scoreString = "Score:" + score;
            g.drawString("Score:" + score, GameFrame.panelObject.getWidth() / 2 - getFontMetrics(g.getFont()).stringWidth(scoreString) / 2, GameFrame.panelObject.getHeight() / 2 + GameFrame.panelObject.getHeight() / 4);
        }
    }
}
