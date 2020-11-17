package Projectgame;

import javax.swing.*;
import java.awt.*;


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
            System.out.println("LOST");



        }

    }
}
