package ProjectGame;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {
    public static int score;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D drawer = (Graphics2D) g;
        if (PlayerFigure.alive) {
            GameFrame.player1.draw(drawer);
            g.drawString("score:" + score / 2, GameFrame.panelObject.getWidth() - 75, 10);
            for (int i = 0; i < GameFrame.bullets.size(); i++) {
                GameFrame.bullets.get(i).draw(drawer);

            }
        } else {
            GameFrame.window.gameOver(drawer);
            g.drawString("score:" + score / 2, GameFrame.panelObject.getWidth() - 75, 10);


        }

    }
}
