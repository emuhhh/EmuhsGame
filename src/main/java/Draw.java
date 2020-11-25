import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {
    public static int score;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D drawer = (Graphics2D) g;
        g.drawImage(GameFrame.background, 0, 0, GameFrame.panelObject.getWidth(), GameFrame.panelObject.getHeight(), this);

      /*  if (GameFrame.player1.alive) {
            GameFrame.window.gameMenuScreen(drawer);
        }*/
        if (GameFrame.player1.alive == null) {
            GameFrame.window.gameMenuScreen(drawer);
        }

         else if (GameFrame.player1.alive) {
            GameFrame.player1.draw(drawer);

            g.setFont(g.getFont().deriveFont(GameFrame.panelObject.getWidth() / 200f));
            g.setColor(new Color(0, 255, 0));
            g.drawString("Score: " + score, GameFrame.panelObject.getWidth() - 100, 20);
            for (int i = 0; i < GameFrame.bullets.size(); i++) {
                GameFrame.bullets.get(i).draw(drawer);
            }
        } else {
            GameFrame.window.gameOverScreen(drawer);
            g.setFont(g.getFont().deriveFont(40f));
            g.setColor(Color.white);
            String scoreString = "Score:" + score;
            g.drawString("Score:" + score, GameFrame.panelObject.getWidth() / 2 - getFontMetrics(g.getFont()).stringWidth(scoreString) / 2, GameFrame.panelObject.getHeight() / 2 + GameFrame.panelObject.getHeight() / 4);
        }

    }
}
