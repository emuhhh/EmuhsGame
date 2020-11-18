package ProjectGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Screen extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D drawer = (Graphics2D) g;
        if ( PlayerFigure.run ) {
            GameFrame.player1.draw(drawer);
            for (int i = 0; i < GameFrame.bullets.size(); i++) {
                GameFrame.bullets.get(i).draw(drawer);
            }
        }
        else {
            GameFrame.window.gameOver(drawer);
            /*try {
                drawer.drawImage(ImageIO.read(new File("src/ProjectGame/12.png")),0,0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }*/


        }

    }
}
