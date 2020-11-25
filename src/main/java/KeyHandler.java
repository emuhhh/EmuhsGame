import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F){
            GameFrame.player1.x = GameFrame.player1.clickedX;
            GameFrame.player1.y = GameFrame.player1.clickedY;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
