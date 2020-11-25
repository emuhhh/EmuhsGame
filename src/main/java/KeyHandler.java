import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Key handler class
 */
public class KeyHandler implements KeyListener {
    /**
     * not required in current project version, preset of KeyListener
     * @param e key typed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * not required in current project version, preset of KeyListener
     * @param e key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (Draw.cooldown >= 100) {
            if (e.getKeyCode() == KeyEvent.VK_F) {
                double flash = 100;
                double offX = GameFrame.player1.clickedX - GameFrame.player1.x;
                double offY = GameFrame.player1.clickedY - GameFrame.player1.y;
                double distance = Math.sqrt(offX * offX + offY * offY);
                double jumpX = offX / distance;
                double jumpY = offY / distance;
                jumpX *= flash;
                jumpY *= flash;
                GameFrame.player1.x += jumpX;
                GameFrame.player1.y += jumpY;
                Draw.cooldown = 0;
            }
        }
    }

    /**
     * not required in current project version, preset of KeyListener
     * @param e key released
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
